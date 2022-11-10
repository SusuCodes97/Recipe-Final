package com.codingblackfemales.recipe.repository;

import com.codingblackfemales.recipe.model.Recipe;

import java.util.List;
import java.util.Set;

public interface RecipeDAO {
    void postRecipe(Recipe recipe);

    void deleteRecipe(Integer id);

    Recipe getRecipeById(Integer id);

    List<Recipe> getAllRecipes();

    void updateRecipe(Integer id, Recipe update);

    List<Recipe> getRecipeByName(String name);

    Set<Recipe> getRecipeByIngredientName(String name);

}
