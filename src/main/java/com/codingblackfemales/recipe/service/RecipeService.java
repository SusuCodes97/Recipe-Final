package com.codingblackfemales.recipe.service;

import com.codingblackfemales.recipe.model.Recipe;
import com.codingblackfemales.recipe.repository.RecipeDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RecipeService {

    private RecipeDAO recipeDAO;

    public RecipeService(@Qualifier("local") RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    public Recipe getRecipeById(Integer id) {
        if (id == null) {
            throw new IllegalStateException("ID Cannot be null");
        }
        Recipe chosenRecipe = recipeDAO.getRecipeById(id);
        if(chosenRecipe == null ) {
            throw new IllegalStateException("Recipe with id " + id + " not found");
        }else {
            return chosenRecipe;
        }
    }

    public List<Recipe> getAllRecipes() {
        return recipeDAO.getAllRecipes();
    }

    public void postRecipe(Recipe recipe) {
        if(recipe.getInstruction() == null || recipe.getIngredient() == null || recipe.getName() == null || recipe.getId() == null) {
            throw  new IllegalStateException("Fields cannot be empty");
        }
        Recipe checkIfRecipeExists = recipeDAO.getRecipeById(recipe.getId());

        if (checkIfRecipeExists != null ){
            throw  new IllegalStateException("Recipe with id " + recipe.getId() + " already exists. Cannot register with the same id");
        }
        recipeDAO.postRecipe(recipe);
    }

    public void deleteById(Integer id) {
        Recipe recipeToRemove = recipeDAO.getRecipeById(id);
        System.out.println("deleted from service");
        if (recipeToRemove == null){
            throw  new IllegalStateException("Recipe with id " + id + " does not exist. Cannot remove a recipe which does not exist");
        }
        recipeDAO.deleteRecipe(id);
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

        Recipe recipeToUpdate = recipeDAO.getRecipeById(id);

        if (recipeToUpdate == null) {
            throw new IllegalStateException("Id is not found");
        }

        recipeDAO.updateRecipe(id, update);
    }

    public List<Recipe> getRecipeByName(String name) {
        List<Recipe> chosenRecipe = recipeDAO.getRecipeByName(name);
        if(chosenRecipe == null ) {
            throw new IllegalStateException("Recipe with name " + name + " not found");
        }else {
            return chosenRecipe;
        }
    }

    public Set<Recipe> getRecipeByIngredientName(String ingredientName) {
        Set<Recipe> chosenRecipe = recipeDAO.getRecipeByIngredientName(ingredientName);
        if(chosenRecipe == null ) {
            throw new IllegalStateException("Recipe with name " + ingredientName + " not found");
        }else {
            return chosenRecipe;
        }
    }
}

//is there a way to make the id automated
//problem with instruction when using jpa
//how to implement JSON objects into this
//does this use encapsulation/SOLID?
//WHICH DESIGN PATTERN SHOULD I IMPLEMENT??!?!?!?


//implememted oop, method overriding (interface), access modifiers, encapsulation!... can potentially implement inheritance?
// can polymorphism work with interfaces?
//implemented abstraction through the use of interface
//maven github