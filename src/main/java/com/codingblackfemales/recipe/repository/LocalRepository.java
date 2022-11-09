package com.codingblackfemales.recipe.repository;

import com.codingblackfemales.recipe.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("local")
public class LocalRepository implements RecipeDAO{


    private List<Recipe> local;

    public LocalRepository(List<Recipe> local) {
        this.local = new ArrayList<>();
    }

    @Override
    public Recipe getRecipeById(Integer id) {
        for (Recipe recipe: local) {
            if (recipe.getId() == id) {
                return recipe;
            }
        }
        return null;
    }

    @Override
    public void postRecipe(Recipe recipe) {
        local.add(recipe);
    }

    @Override
    public void deleteRecipe(Integer id) {
        Recipe recipeToRemove = getRecipeById(id);
        local.remove(recipeToRemove);
    }


    @Override
    public List<Recipe> getAllRecipes() {
        return local;
    }

    @Override
    public void updateRecipe(Integer id, Recipe update) {
        Recipe recipeToUpdate = getRecipeById(id);
        recipeToUpdate.setName(update.getName());
        recipeToUpdate.setIngredient(update.getIngredient());
        recipeToUpdate.setInstruction(update.getInstruction());
        recipeToUpdate.setUrl(update.getUrl());
    }
}
