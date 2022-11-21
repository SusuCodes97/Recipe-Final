package com.codingblackfemales.recipe.service;

import com.codingblackfemales.recipe.model.Ingredient;
import com.codingblackfemales.recipe.model.Recipe;
import com.codingblackfemales.recipe.repository.DatabaseRepository;
//import org.junit.Test;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
//import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @Mock
    private DatabaseRepository databaseRepository;
    @InjectMocks
    private RecipeService underTest;

    //this. doesnt make a difference, can remove?
//    @BeforeEach
//    void setUp(){
//        this.databaseRepository = Mockito.mock(DatabaseRepository.class);
////        ingredient = Mockito.mock(Ingredient.class);
////        ingredient = new Ingredient("chicken", 1.0);
////        MockitoAnnotations.initMocks(this);
//        this.underTest = new RecipeService(databaseRepository);
//    }

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

        Recipe recipe1 = new Recipe(1, "chicken", ingredients, "heat up", "chicken.come");

//        when(databaseRepository.save(Mockito.any(Recipe.class))).thenAnswer(i -> i.getArguments()[0]);
        when(databaseRepository.save(recipe1)).thenReturn(recipe1);
//        when(databaseRepository.save(Mockito.any(Recipe.class))).thenReturn(recipe1);


        underTest.postRecipe(recipe1);

        verify(databaseRepository, times(1)).save(recipe1);


//        Mockito.when(databaseRepository.findbyIngredientName("chicken")).thenReturn(ingredients); // <-- Add this line

//        verify(databaseRepository, times(1)).save(any());
//

//         underTest.postRecipe(recipe1);
//
//        assertThat();


        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(databaseRepository).save(recipeArgumentCaptor.capture());
        assertTrue(recipeArgumentCaptor.getValue().getName().equals("chicken"));

    }

    @Test
    public void canGetAllRecipes() {
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient2 = new Ingredient("chicken", 1.0);
        Ingredient ingredient1 = new Ingredient("spices", 1.0);
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
    public void canThrowWhenDatabaseIsEmpty() {
        given(databaseRepository.findAll()).willReturn(null);

        assertThatThrownBy(() -> underTest.getAllRecipes())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("No recipes found");
    }


    @Test
    public void canGetRecipeById() {
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient2 = new Ingredient("chicken", 1.0);
        Ingredient ingredient1 = new Ingredient("spices", 1.0);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        Recipe recipe1 = new Recipe(1, "chicken", ingredients, "heat up", "chicken.come");
        Recipe recipe2 = new Recipe(2, "chicken", ingredients, "heat up", "chicken.come");

        given(this.databaseRepository.findById(anyInt())).willReturn(Optional.of(recipe1));

        Optional<Recipe> expected  = this.databaseRepository.findById(1);
        Optional<Recipe> actual  = underTest.getRecipeById(1);
        System.out.println(this.databaseRepository.findById(1).get());
        assertThat(actual).isEqualTo(expected);
//        Recipe recipe3 = new Recipe();
//        recipe3.setId(3);
//        recipe3.setIngredient(ingredients);
//        recipe3.setInstruction("heat up");
//        recipe3.setUrl("blah");
//
//////        databaseRepository.save(recipe3);
//////        when(databaseRepository.findById(3)).thenReturn(Optional.of(recipe3));
//
//
//  given(databaseRepository.findById(3)).willReturn(Optional.of(recipe3));
//
//        Recipe actualRecipe = underTest.getRecipeById(3).get();
//
//        assertThat(actualRecipe).isEqualTo(recipe3);

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


    @Test
    public void canDeleteRecipe() {

        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient2 = new Ingredient("chicken", 1.0);
        Ingredient ingredient1 = new Ingredient("spices", 1.0);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        Recipe recipe1 = new Recipe(1, "chicken", ingredients, "heat up", "chicken.come");
        Recipe recipe2 = new Recipe(2, "lamb", ingredients, "heat up", "chicken.come");

        List<Recipe> originalRecipeList = new ArrayList<>();
        originalRecipeList.add(recipe1);
        originalRecipeList.add(recipe2);

        List<Recipe> newRecipeList = new ArrayList<>();
        newRecipeList.add(recipe1);

        underTest.deleteById(anyInt());

        verify(databaseRepository).deleteById(anyInt());



//        given(databaseRepository.)
    }


}