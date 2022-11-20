package com.codingblackfemales.recipe.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DisplayName("The greeting controller should")
public class GreetingControllerTests {

    private static final String GREETING_ENDPOINT = "/greeting";
    private static final String GET_ALL = "/recipes";

    private static final String GET_BY_ID = "/recipes/1";

    private static final String POST = "/recipes";



    @Autowired
    private TestRestTemplate restTemplate;

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

//    @Test
//    @DisplayName("gets all recipes")
//    void getsRecipeById() {
//        final ResponseEntity<String> response = restTemplate.getForEntity(GET_ALL, String.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
////        assertThat(response.getBody()).isEqualTo("Hello World!");
//    }

    @Test
    @DisplayName("gets all recipes")
    void postRecipe() {
        final ResponseEntity<String> response = restTemplate.getForEntity(POST, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(response.getBody()).isEqualTo("Hello World!");
    }
}
