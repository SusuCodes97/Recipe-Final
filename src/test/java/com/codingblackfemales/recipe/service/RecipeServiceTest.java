package com.codingblackfemales.recipe.service;

import com.codingblackfemales.recipe.model.Ingredient;
import com.codingblackfemales.recipe.model.Recipe;
import com.codingblackfemales.recipe.repository.DatabaseRepository;
import com.codingblackfemales.recipe.repository.RecipeDAO;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class RecipeServiceTest {

    @Mock
    private DatabaseRepository databaseRepository;

//    @Mock
//    private Ingredient ingredient;
    private RecipeService underTest;

    @BeforeEach
    void setUp(){
        databaseRepository = Mockito.mock(DatabaseRepository.class);
//        ingredient = Mockito.mock(Ingredient.class);
//        ingredient = new Ingredient("chicken", 1.0);
//        MockitoAnnotations.initMocks(this);
        underTest = new RecipeService(databaseRepository);
    }

//    @Test
//    void canAddRecipeEntry(){
//        Ingredient ingredient = new Ingredient("chicken", 1.0);
//        Recipe recipe1 = new Recipe(1, "chicken", (List<Ingredient>) ingredient, "heat up", "chicken.come");
////        given(recipeDAO.postRecipe(recipe1)).willReturn(1);
//
//
//        underTest.postRecipe(recipe1);
//
//        assertThat();
//    }

    @Test
    public void canGetAllRecipes() {
        Ingredient ingredient = new Ingredient("chicken", 1.0);
        Recipe recipe1 = new Recipe(1, "chicken", (List<Ingredient>) ingredient, "heat up", "chicken.come");
        Recipe recipe2 = new Recipe(2, "chicken", (List<Ingredient>) ingredient, "heat up", "chicken.come");

        List<Recipe> expectedRecipeList = new ArrayList<>();
        expectedRecipeList.add(recipe1);
        expectedRecipeList.add(recipe2);

        given(databaseRepository.findAll()).willReturn(expectedRecipeList);

        List<Recipe> actualRecipeList = underTest.getAllRecipes();

        assertEquals(expectedRecipeList, actualRecipeList);

    }


}