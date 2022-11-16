package com.codingblackfemales.recipe.service;

import com.codingblackfemales.recipe.model.Recipe;
import com.codingblackfemales.recipe.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
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

    public List<Recipe> getAllRecipes() {
        return databaseRepository.findAll();
    }

    public void postRecipe(Recipe recipe) {
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

    public List<Recipe> getByName(String name){
        return databaseRepository.getByName(name);
    }

//    public Set<Recipe> getRecipeByIngredientName(String ingredientName){
//        return databaseRepository.findbyIngredientName(ingredientName);
//    }

}