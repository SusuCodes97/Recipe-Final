package com.codingblackfemales.recipe.controller;

import com.codingblackfemales.recipe.model.Ingredient;
import com.codingblackfemales.recipe.model.Recipe;
import com.codingblackfemales.recipe.service.RecipeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DisplayName("The greeting controller should")
public class GreetingControllerTests {

    private static final String GREETING_ENDPOINT = "/greeting";
    private static final String GET_ALL = "/recipes/all";

    private static final String GET_BY_ID = "/recipes/1";

    private static final String POST = "/recipes/post";

    private final List<Ingredient> ingredients = List.of(new Ingredient(1, 1, "spice", 1.0), new Ingredient(2, 1, "vegetable", 1.0));
    private final Recipe recipe1 = new Recipe(1, "chicken", ingredients, "heat up", "chicken.come");



    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;


//    @Test
//    @DisplayName("return Hello World!")
//    void receivesGreetingRequests() {
//        final ResponseEntity<String> response = restTemplate.getForEntity(GREETING_ENDPOINT, String.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isEqualTo("Hello World!");
//    }

    @Test
    @DisplayName("gets all recipes")
    void getsAllRecipes() {
        final ResponseEntity<String> response = restTemplate.getForEntity(GET_ALL, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isEqualTo("Hello World!");
    }

    @Test
    @DisplayName("gets recipe by ID")
    void getsRecipeById() {
        final ResponseEntity<String> response = restTemplate.getForEntity(GET_ALL, String.class);

        given(recipeService.getRecipeById(1)).willReturn(Optional.of(recipe1));

        System.out.println(recipeController.getRecipeById(1));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isEqualTo(recipe1);


//        when(databaseRepository.findById(1)).thenReturn(Optional.of(recipe1));
//
//        Recipe actual = underTest.getRecipeById(recipe1.getId()).get();
//
//        assertThat(actual).isEqualTo(recipe1);
    }

    @Test
    @DisplayName("posts a recipe")
    void postRecipe() {
        final ResponseEntity<String> response = restTemplate.getForEntity(POST, String.class);

        given(recipeService.postRecipe(recipe1)).willReturn(1);

        recipeController.postRecipe(recipe1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(response.getBody()).isEqualTo("Hello World!");
    }
}
