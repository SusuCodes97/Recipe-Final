package com.codingblackfemales.recipe.service;

import com.codingblackfemales.recipe.model.Ingredient;
import com.codingblackfemales.recipe.model.Recipe;
import com.codingblackfemales.recipe.repository.DatabaseRepository;
//import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
//import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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

    /*
       CustomerData customerDataTest = customerData;
   //when
   Mockito.when(customerDataRepository.findCustomerByDialogId(Mockito.any())).thenReturn(new CustomerData()); // <-- Add this line
   customerDataService.giveConsent(false,22L);
   //then
   verify(customerDataRepository,times(1)).save(customerDataTest);
    * */
    @Test
    public void canAddRecipeEntry(){
//        Ingredient ingredient = new Ingredient("chicken", 1.0);
//        Recipe recipe1 = new Recipe(1, "chicken", (List<Ingredient>) ingredient, "heat up", "chicken.come");
//        given(recipeDAO.postRecipe(recipe1)).willReturn(1);

        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient2 = new Ingredient("chicken", 1.0);
        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        List<Recipe> recipeList = new ArrayList<>();
        Recipe recipe1 = new Recipe(1, "chicken", ingredients, "heat up", "chicken.come");
        recipeList.add(recipe1);
        given(databaseRepository.save(recipe1)).willReturn(recipe1);

//        Mockito.when(databaseRepository.findbyIngredientName("chicken")).thenReturn(ingredients); // <-- Add this line

//        verify(databaseRepository).save(recipe1);
//

//         underTest.postRecipe(recipe1);
//
//        assertThat();


        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(databaseRepository).save(recipeArgumentCaptor.capture());
        Recipe actualRecipeSaved = recipeArgumentCaptor.getValue();
        assertEquals(recipe1, actualRecipeSaved);
    }

    @Test
    public void canGetAllRecipes() {
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient2 = new Ingredient("chicken", 1.0);
        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        Recipe recipe1 = new Recipe(1, "chicken", ingredients, "heat up", "chicken.come");
        Recipe recipe2 = new Recipe(2, "chicken", ingredients, "heat up", "chicken.come");

        List<Recipe> expectedRecipeList = new ArrayList<>();
        expectedRecipeList.add(recipe1);
        expectedRecipeList.add(recipe2);

        given(databaseRepository.findAll()).willReturn(expectedRecipeList);

        List<Recipe> actualRecipeList = underTest.getAllRecipes();

        assertEquals(expectedRecipeList, actualRecipeList);

    }


    @Test
    public void canGetRecipeById() {
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient2 = new Ingredient("chicken", 1.0);
        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        Recipe recipe1 = new Recipe("chicken", ingredients, "heat up", "chicken.come");
        Recipe recipe2 = new Recipe("chicken", ingredients, "heat up", "chicken.come");

        Recipe recipe3 = new Recipe();
        recipe3.setId(3);
        recipe3.setIngredient(ingredients);
        recipe3.setInstruction("heat up");
        recipe3.setUrl("blah");

//        databaseRepository.save(recipe3);
//        when(databaseRepository.findById(3)).thenReturn(Optional.of(recipe3));


  given(databaseRepository.findById(3)).willReturn(Optional.of(recipe3));

        Recipe actualRecipe = underTest.getRecipeById(3).get();

        assertThat(actualRecipe).isEqualTo(recipe3);

//        assertEquals(recipe1, actualRecipe);

    }


    @Test
    public void canGetRecipeByName() {
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient2 = new Ingredient("chicken", 1.0);
        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        Recipe recipe1 = new Recipe("chicken shish", ingredients, "heat up", "chicken.come");
        Recipe recipe2 = new Recipe("lamb", ingredients, "heat up", "chicken.come");

        List<Recipe> expectedRecipeList = new ArrayList<>();
        expectedRecipeList.add(recipe1);
        expectedRecipeList.add(recipe2);

        given(databaseRepository.findRecipeByName("chicken")).willReturn(expectedRecipeList);
        List<Recipe> actualRecipeList = underTest.getRecipeByName("chicken");

        assertEquals(expectedRecipeList, actualRecipeList);
    }

    @Test
    public void canUpdateRecipe() {
//        List<Ingredient> ingredients = new ArrayList<>();
//        Ingredient ingredient2 = new Ingredient("chicken", 1.0);
//        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
//        ingredients.add(ingredient1);
//        ingredients.add(ingredient2);
//
//        Recipe recipe1 = new Recipe(1, "chicken shish", ingredients, "heat up", "chicken.come");
//
//        given(databaseRepository.findById(1)).willReturn(Optional.of(recipe1));
//
//        Recipe recipe2 = new Recipe(2, "lamb", ingredients, "heat up", "chicken.come");
//
//        given(databaseRepository.save(recipe2)).willReturn(recipe2);
//
//        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);
//        verify(databaseRepository).save(recipeArgumentCaptor.capture());
//        Recipe actualRecipeSaved = recipeArgumentCaptor.getValue();
//        assertEquals(recipe1, actualRecipeSaved);
//        List<Recipe> expectedRecipeList = new ArrayList<>();
//        expectedRecipeList.add(recipe1);
//        expectedRecipeList.add(recipe2);
//
//        given(databaseRepository.findRecipeByName("chicken")).willReturn(expectedRecipeList);
//        List<Recipe> actualRecipeList = underTest.getRecipeByName("chicken");
//
//        assertEquals(expectedRecipeList, actualRecipeList);
    }


}