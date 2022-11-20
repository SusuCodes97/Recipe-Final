package com.codingblackfemales.recipe.service;

import com.codingblackfemales.recipe.model.Ingredient;
import com.codingblackfemales.recipe.model.Recipe;
import com.codingblackfemales.recipe.repository.DatabaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
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
        boolean chosenRecipe = databaseRepository.existsById(id);
        if(!chosenRecipe) {
            throw new IllegalStateException("Recipe with id " + id + " not found");
        }else {

            return databaseRepository.findById(id);
        }
    }

    //refactor this! MAke sure it doesnt include the id and recipe id when showing ingredients
    public List<Recipe> getAllRecipes() {
        //tried getting rid of id and recipe id from postman but didnt work
//        List<Recipe> recipeListNotRefactored = databaseRepository.findAll();

//        for (Recipe recipe: recipeListNotRefactored) {
//
//            List<Ingredient> ingredients = recipe.getIngredient();
//            List<Ingredient> ingredientsEditedList = new ArrayList<>();
//            for (Ingredient ingredient: ingredients) {
//               Ingredient ingredientEdited = new Ingredient(ingredient.getName(), ingredient.getQuantity());
//               ingredientsEditedList.add(ingredientEdited);
//            }
//            recipe.setIngredient(ingredientsEditedList);
//            System.out.println(recipe);
//        }

        return databaseRepository.findAll();
    }

    public void postRecipe(Recipe recipe) {
        // maybe try making the information received lowercase in this section?
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
        boolean recipeExists = databaseRepository.existsById(id);
        System.out.println("deleted from service");
        if (!recipeExists){
            throw  new IllegalStateException("Recipe with id " + id + " does not exist. Cannot delete a recipe which does not exist");
        }
        databaseRepository.deleteById(id);
    }

    public void updateRecipe(Integer id, Recipe update) {
        if (id == null) {
            throw new IllegalStateException("Id cannot be null");
        }
        else if (update == null ) {
            throw new IllegalStateException("recipe cannot be null");
        } else if (update.getIngredient() == null) {
            throw new IllegalStateException("Ingredients cannot be null");
        } else if (update.getInstruction() == null) {
            throw new IllegalStateException("Instructions cannot be null");
        }

        Optional<Recipe> recipeToUpdate = databaseRepository.findById(id);

        if (recipeToUpdate == null) {
            throw new IllegalStateException("Id is not found");
        }

        Recipe recipe = recipeToUpdate.get();
        recipe.setId(id);
        recipe.setName(update.getName());
        recipe.setIngredient(update.getIngredient());
        recipe.setInstruction(update.getInstruction());
        recipe.setUrl(update.getUrl());

        databaseRepository.save(recipe);

//        databaseRepository.deleteById(id);
//        update.setId(id);
//        Recipe update2 = new Recipe(id, update.getName(), update.getIngredient(), update.getInstruction(), update.getUrl());
//        databaseRepository.save(update2);
    }

    //refactor this! MAke sure it doesnt include the id and recipe id when showing ingredients
    public List<Recipe> getByName(String name){
        return databaseRepository.getByName(name);
    }

//    public List<Ingredient> getRecipeByIngredientName(String ingredientName){
//        System.out.println(databaseRepository.findbyIngredientName(ingredientName));
//        return databaseRepository.findbyIngredientName(ingredientName);
//    }

    //refactor this! MAke sure it doesnt include the id and recipe id when showing ingredients
    public List<Optional<Recipe>> getByIngredient(String ingredientName){
        List<Ingredient> listOfIngredients= databaseRepository.findbyIngredientName(ingredientName);
        List<Optional<Recipe>> recipes = new ArrayList<>();
        for (Ingredient ingredient: listOfIngredients
             ) {
            System.out.println(ingredient.getRecipeId());
            Integer id = Math.toIntExact(ingredient.getRecipeId());
            recipes.add(databaseRepository.findById(id));
        }
//        List<Recipe> recipe = recipes.get();
        return recipes;
    }



}