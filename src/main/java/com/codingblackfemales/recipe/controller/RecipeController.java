package com.codingblackfemales.recipe.controller;

import com.codingblackfemales.recipe.model.Recipe;
import com.codingblackfemales.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

//    @Autowired
    private RecipeService recipeService;

    //get rid of
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
        //THIS WORKS BUT GET RID OF CAPITALS//cases
        String nameLowerCase = name.toLowerCase();
        return recipeService.getByName(nameLowerCase);
    }


    @GetMapping("/ingredient/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Optional<Recipe>> getRecipeByIngredientName(@PathVariable("name") String ingredientName) {
        //THIS WORKS BUT GET RID OF CAPITALS//cases
//        String ingredientNameLowerCase = ingredientName.toLowerCase();
//        System.out.println(recipeService.getByIngredient(ingredientName));
        return recipeService.getByIngredient(ingredientName);
    }

//    @GetMapping("/greeting")
//    public String greeting() {
//        return "Hello World!";
//    }
}

//


//#TODO:
/*
- fix namings
- refactor get by name and get by ingredient and write properly
- make id not needed (atm it says empty field)
- get rid of id and recipe_id in ingredients when getting on postman
- TESTS
- more streams?
- factory?

*/

