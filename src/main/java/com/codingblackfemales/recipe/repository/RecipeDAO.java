package com.codingblackfemales.recipe.repository;

import com.codingblackfemales.recipe.model.Recipe;

import java.util.List;

public interface RecipeDAO {
    void postRecipe(Recipe recipe);

    void deleteRecipe(Integer id);

    Recipe getRecipeById(Integer id);

    List<Recipe> getAllRecipes();

    void updateRecipe(Integer id, Recipe update);

}
