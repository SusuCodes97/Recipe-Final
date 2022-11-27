package com.codingblackfemales.recipe.controller;

import com.codingblackfemales.recipe.model.Recipe;
import com.codingblackfemales.recipe.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public void postRecipe(@RequestBody Recipe recipe) {

        recipeService.postRecipe(recipe);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Integer id) {
        recipeService.deleteById(id);
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRecipe(@PathVariable("id") Integer id, @RequestBody Recipe update) {
        recipeService.updateRecipe(id, update);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> getAllRecipes(){
        return recipeService.getAllRecipes();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Recipe> getRecipeById(@PathVariable("id") Integer id) {
        return recipeService.getRecipeById(id);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> getByName(@PathVariable("name") String name) {
        String nameLowerCase = name.toLowerCase();
        return recipeService.getRecipeByName(nameLowerCase);
    }


    @GetMapping("/ingredient/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Optional<Recipe>> getRecipeByIngredientName(@PathVariable("name") String ingredientName) {
        String ingredientNameLowerCase = ingredientName.toLowerCase();
        return recipeService.getRecipeByIngredientName(ingredientNameLowerCase);
    }
}
