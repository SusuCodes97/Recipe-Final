package com.codingblackfemales.recipe.controller;

import com.codingblackfemales.recipe.model.Recipe;
import com.codingblackfemales.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("post")
    @ResponseStatus(HttpStatus.CREATED)
    public void postRecipe(@RequestBody Recipe recipe) {
        recipeService.postRecipe(recipe);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Integer id) {
        recipeService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRecipe(@PathVariable("id") Integer id, @RequestBody Recipe update) {
        recipeService.updateRecipe(id, update);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> getAllRecipes(){
        return recipeService.getAllRecipes();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Recipe getRecipeById(@PathVariable("id") Integer id) {
        return recipeService.getRecipeById(id);
    }

    @GetMapping("name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> getRecipeByName(@PathVariable("name") String name) {
        return recipeService.getRecipeByName(name);
    }

    @GetMapping("ingredient/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Recipe> getRecipeByIngredientName(@PathVariable("name") String ingredientName) {
        return recipeService.getRecipeByIngredientName(ingredientName);
    }
}
