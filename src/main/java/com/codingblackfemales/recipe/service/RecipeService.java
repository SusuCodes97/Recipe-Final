package com.codingblackfemales.recipe.service;

import com.codingblackfemales.recipe.model.Ingredient;
import com.codingblackfemales.recipe.model.Recipe;
import com.codingblackfemales.recipe.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class RecipeService {

    private DatabaseRepository databaseRepository;

    public RecipeService(@Qualifier("database") DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    public Optional<Recipe> getRecipeById(Integer id) {
        if (id == null) {
            throw new IllegalStateException("ID Cannot be null");
        }
        Optional<Recipe> chosenRecipe = databaseRepository.findById(id);
        if(chosenRecipe.isEmpty()) {
            throw new IllegalStateException("Recipe with id " + id + " not found");
        }else {
            return databaseRepository.findById(id);
        }
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipeList = databaseRepository.findAll();
        if (recipeList == null || recipeList.isEmpty()) {
            throw new IllegalStateException("No recipes found");
        }
        return recipeList;
    }

    public void postRecipe(Recipe recipe) {

        if(recipe.getName() != null ) {
            recipe.setName(recipe.getName().toLowerCase());
        }

        List<Ingredient> ingredientList = recipe.getIngredient();

        if(recipe.getIngredient() != null ) {
            ingredientList.stream().forEach(ingredient -> ingredient.setName(ingredient.getName().toLowerCase()));
        }

        if(recipe.getInstruction() == null || recipe.getIngredient() == null || recipe.getName() == null) {
            throw  new IllegalStateException("Fields cannot be empty");
        }
        boolean recipeExists = databaseRepository.existsById(recipe.getId());

        if (recipeExists){
            throw  new IllegalStateException("Recipe with id " + recipe.getId() + " already exists. Cannot register with the same id");
        }
        databaseRepository.save(recipe);
    }

    public void deleteById(Integer id) {

        Optional<Recipe> chosenRecipe = databaseRepository.findById(id);
        if(chosenRecipe.isEmpty()) {
            throw new IllegalStateException("Recipe with id " + id + " does not exist. Cannot delete a recipe which does not exist");
        }

        databaseRepository.deleteById(id);
    }

    public void updateRecipe(Integer id, Recipe update) {
       //double check if we want some fields to be null or not?
        if (id == null) {
            throw new IllegalStateException("Id cannot be null");
        }else if (update == null ) {
            throw new IllegalStateException("Update recipe cannot be null");
        }else if (update.getName() == null ) {
            throw new IllegalStateException("Recipe name cannot be null");
        } else if (update.getIngredient() == null) {
            throw new IllegalStateException("Ingredients cannot be null");
        } else if (update.getInstruction() == null) {
            throw new IllegalStateException("Instructions cannot be null");
        }

        Optional<Recipe> recipeToUpdate = databaseRepository.findById(id);

        if (recipeToUpdate.isEmpty()) {
            throw new IllegalStateException("Recipe with id " + id + " does not exist");
        }

        Recipe recipe = recipeToUpdate.get();
        recipe.setId(id);
        recipe.setName(update.getName());
        recipe.setIngredient(update.getIngredient());
        recipe.setInstruction(update.getInstruction());
        recipe.setUrl(update.getUrl());

        databaseRepository.save(recipe);
    }

    public List<Recipe> getRecipeByName(String name){
        return databaseRepository.findRecipeByName(name);
    }

    public List<Optional<Recipe>> getRecipeByIngredientName(String ingredientName){
        List<Ingredient> listOfIngredients= databaseRepository.findRecipeByIngredientName(ingredientName);
        List<Optional<Recipe>> recipes = new ArrayList<>();

        listOfIngredients.stream().forEach(ingredient -> recipes.add(databaseRepository.findById(ingredient.getRecipeId())));
        return recipes;
    }
}