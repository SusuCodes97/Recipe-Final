package com.codingblackfemales.recipe.service;

import com.codingblackfemales.recipe.model.Ingredient;
import com.codingblackfemales.recipe.model.Recipe;
import com.codingblackfemales.recipe.repository.DatabaseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

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


    private final List<Ingredient> ingredients = List.of(new Ingredient(1, 1, "spice", 1.0), new Ingredient(2, 1, "vegetable", 1.0));
    private final Recipe recipe1 = new Recipe(1, "chicken", ingredients, "heat up", "chicken.come");
    private final Recipe recipe2 = new Recipe(2, "lamb", ingredients, "heat up", "chicken.come");
    //Add displaynames for each, fix namings


    @DisplayName("This test checks if I can add a recipe")
    @Test
    public void canAddRecipeEntry(){
        Recipe recipe1 = new Recipe(1, "chicken", ingredients, "heat up", "chicken.come");

        when(databaseRepository.save(recipe1)).thenReturn(recipe1);
//        when(databaseRepository.save(Mockito.any(Recipe.class))).thenReturn(recipe1);
        underTest.postRecipe(recipe1);
        verify(databaseRepository, times(1)).save(recipe1);

        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(databaseRepository).save(recipeArgumentCaptor.capture());
        assertTrue(recipeArgumentCaptor.getValue().getName().equals("chicken"));
    }

    @Test
    public void canThrowWhenNameFieldIsMissing(){
        Recipe recipe1 = new Recipe(1, null, ingredients, "heat up", "chicken.come");

        assertThatThrownBy(() -> underTest.postRecipe(recipe1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Fields cannot be empty");
    }

    @Test
    public void canThrowWhenIngredientFieldIsMissing(){
        Recipe recipe1 = new Recipe(1, "chicken", null, "heat up", "chicken.come");

        assertThatThrownBy(() -> underTest.postRecipe(recipe1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Fields cannot be empty");
    }

    @Test
    public void canThrowWhenIdAlreadyExists(){
        Recipe recipe1 = new Recipe(1, "chicken", ingredients, "heat up", "chicken.come");

        given(databaseRepository.existsById(1)).willReturn(true);

        assertThatThrownBy(() -> underTest.postRecipe(recipe1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Recipe with id 1 already exists. Cannot register with the same id");
    }
    @Test
    public void canThrowWhenInstructionFieldIsMissing(){
        Recipe recipe1 = new Recipe(1, "chicken", ingredients, null, "chicken.come");

        assertThatThrownBy(() -> underTest.postRecipe(recipe1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Fields cannot be empty");
    }

    @Test
    public void canGetAllRecipes() {
        List<Recipe> expectedRecipeList = List.of(recipe1, recipe2);

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
        when(databaseRepository.findById(1)).thenReturn(Optional.of(recipe1));

        Recipe actual = underTest.getRecipeById(recipe1.getId()).get();

        assertThat(actual).isEqualTo(recipe1);
    }

    @Test
    public void canThrowWhenIdIsNull() {
        assertThatThrownBy(() -> underTest.getRecipeById(null))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("ID Cannot be null");
    }

    @Test
    public void canThrowWhenRecipeWithIdDoesntExistForFindById() {
        given(databaseRepository.findById(1)).willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getRecipeById(1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Recipe with id 1 not found");
    }

//can get recipebyname when capitals
    //cangetrecipebyIgredientnamewhen capitals
    @Test
    public void canGetRecipeByName() {
        List<Recipe> expectedRecipeList = List.of(recipe1, recipe2);

        given(databaseRepository.findRecipeByName("chicken")).willReturn(expectedRecipeList);
        List<Recipe> actualRecipeList = underTest.getRecipeByName("chicken");

        assertEquals(expectedRecipeList, actualRecipeList);
    }

    @Test
    public void canGetRecipeByNameWhenCapitals() {
        List<Recipe> expectedRecipeList = List.of(recipe1);

        given(databaseRepository.findRecipeByName("CHICKEN")).willReturn(expectedRecipeList);
        List<Recipe> actualRecipeList = underTest.getRecipeByName("CHICKEN");


        assertEquals(expectedRecipeList, actualRecipeList);
    }


    //try argument captor?
    @Test
    public void canUpdateRecipe() {
        given(databaseRepository.findById(1)).willReturn(Optional.of(recipe1));
//        ArgumentCaptor<Recipe> arg = ArgumentCaptor.forClass(Recipe.class);
        underTest.updateRecipe(1, recipe2);
                verify(databaseRepository).save(any());

//        verify(databaseRepository).save(arg.capture());

//        Recipe savedRecipe = arg.getValue();






//        verify(databaseRepository)
//
//        assertThat(underTest.updateRecipe(1, recipe2)).
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
    public void canThrowWhenIdIsNullForUpdate() {
        assertThatThrownBy(() -> underTest.updateRecipe(null, recipe2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Id cannot be null");
    }

    @Test
    public void canThrowWhenRecipeIsNullForUpdate() {
        assertThatThrownBy(() -> underTest.updateRecipe(1, null))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Update recipe cannot be null");
    }

    @Test
    public void canThrowWhenNameIsNullForUpdate() {
        Recipe recipe3 = new Recipe(3, null, ingredients, "heat up", "chicken.come");

        assertThatThrownBy(() -> underTest.updateRecipe(1, recipe3))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Recipe name cannot be null");
    }

    @Test
    public void canThrowWhenIngredientIsNullForUpdate() {
        Recipe recipe3 = new Recipe(3, "chicken", null, "heat up", "chicken.com");

        assertThatThrownBy(() -> underTest.updateRecipe(1, recipe3))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Ingredients cannot be null");
    }

    @Test
    public void canThrowWhenInstructionIsNullForUpdate() {
        Recipe recipe3 = new Recipe(1, "chicken", ingredients, null, "chicken.com");

        assertThatThrownBy(() -> underTest.updateRecipe(1, recipe3))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Instructions cannot be null");
    }

    @Test
    public void cantThrowWhenIdDoesntExistForUpdate() {
        given(databaseRepository.findById(3)).willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.updateRecipe(3, recipe1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Recipe with id 3 does not exist");
    }

    //try argument captor?
    @Test
    public void canDeleteRecipe() {
        //may get rid off
        List<Recipe> expectedRecipeList = List.of(recipe1, recipe2);
        given(databaseRepository.findById(1)).willReturn(Optional.of(recipe1));

        underTest.deleteById(1);

        verify(databaseRepository, times(1)).deleteById(1);
    }


    @Test
    public void canThrowWhenRecipeWithIdDoesntExistForDelete() {
        given(databaseRepository.findById(1)).willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.deleteById(1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Recipe with id 1 does not exist. Cannot delete a recipe which does not exist");
    }


    //try argument captor
    @Test
    public void canGetRecipeByIngredientName() {
        underTest.getRecipeByIngredientName("chicken");

        verify(databaseRepository, times(1)).findRecipeByIngredientName("chicken");

//
//        List<Recipe> expectedRecipeList = List.of(recipe1, recipe2);
//
//        given(databaseRepository.findAll()).willReturn(expectedRecipeList);
//        given(databaseRepository.findRecipeByIngredientName("spice")).willReturn(ingredients);
//        List<Optional<Recipe>> actualRecipeList = underTest.getRecipeByIngredientName("spice");
//        assertEquals(expectedRecipeList, actualRecipeList);





//

//        Mockito.when(databaseRepository.findbyIngredientName("chicken")).thenReturn(ingredients); // <-- Add this line

//        verify(databaseRepository, times(1)).save(any());
//

//         underTest.postRecipe(recipe1);
//
//        assertThat();

//
//        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);
////        verify(databaseRepository).findRecipeByIngredientName("chicken");
//        assertTrue(recipeArgumentCaptor.capture().getName().equals("chicken"));
    }

}