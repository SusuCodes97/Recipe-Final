package com.codingblackfemales.recipe.controller;

import com.codingblackfemales.recipe.model.Recipe;
import com.codingblackfemales.recipe.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
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



//#TODO:
/*
- fix namings - DONE
- refactor get by name and get by ingredient and write properly - DONE
- make id not needed (atm it says empty field)
- get rid of id and recipe_id in ingredients when getting on postman
- TESTS
- more streams? - DONE
- factory?

*/

