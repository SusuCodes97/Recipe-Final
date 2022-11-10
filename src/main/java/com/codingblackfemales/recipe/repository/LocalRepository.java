package com.codingblackfemales.recipe.repository;

import com.codingblackfemales.recipe.model.Ingredient;
import com.codingblackfemales.recipe.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        Recipe recipeUpdated = new Recipe.Builder()
                .setId(update.getId())
                .setName(update.getName())
                .setIngredient(update.getIngredient())
                .setInstruction(update.getInstruction())
                .setUrl(update.getUrl())
                .build();

        local.remove(recipeToUpdate);
        local.add(recipeUpdated);
//
//        recipeToUpdate.setName(update.getName());
//        recipeToUpdate.setIngredient(update.getIngredient());
//        recipeToUpdate.setInstruction(update.getInstruction());
//        recipeToUpdate.setUrl(update.getUrl());
    }

    @Override
    public List<Recipe> getRecipeByName(String name) {
        List<Recipe> recipeByName = new ArrayList<>();
        System.out.println(name.getClass().getName());
        System.out.println("Local storage = " + local);


        List<Recipe> recipeByName2 = local
                .stream()
                .filter(c -> c.getName().indexOf(name) != -1)
                .collect(Collectors.toList());

//        for (Recipe recipe: local) {
//            System.out.println("recipe id = " + recipe.getId() + " and the recipe anem is " + recipe.getName().toLowerCase());
//           //check if it contains the name
//            if (recipe.getName().indexOf(name) != -1) { //kept on doing == instead of .equals, forgetting the string pool and reference point etc
////recipe.getName().toLowerCase().equals(name)
////                System.out.println(recipe.getName().indexOf(name));
////                System.out.println(recipe.getName().indexOf("flip"));
//                //problem with this is it only matches 100%
//                recipeByName.add(recipe);
//            }
////            System.out.println(recipeByName);
//        }
//        return recipeByName

        return recipeByName2;
    }

    //doesnt really need to be a set as someone wouldnt put the same ingredient twice but precaution
    @Override
    public Set<Recipe> getRecipeByIngredientName(String ingredientName) {
        Set<Recipe> recipeByIngredientName = new HashSet<>();
//        List<Recipe> recipeByIngredientName = new ArrayList<>();
//        System.out.println(name.getClass().getName());
//        System.out.println("Local storage = " + local);
//       local
//                .stream()
//                .forEach((c) -> c.getIngredient().stream().filter(x -> x.getName().equals(ingredientName)).collect(Collectors.toList()));

//        List<Recipe> recipeByName2 = local
//                .stream()
//                .filter(c -> c.getIngredient().stream().toList())
//                .collect(Collectors.toList());





//        List<Recipe> RecipeByIngredient = local.stream().forEach((c) -> c.getIngredient().stream().filter(x -> x.getName().equals(ingredientName))(Collectors.toList()));

        //can probably use stream/filter?
        for (Recipe recipe: local) {
            List<Ingredient> ingredients = recipe.getIngredient();
            for (Ingredient ingredient: ingredients) {
                if(ingredient.getName().equals(ingredientName)) {
                    recipeByIngredientName.add(recipe);
                }

            }
        }
        return recipeByIngredientName;
    }
}
