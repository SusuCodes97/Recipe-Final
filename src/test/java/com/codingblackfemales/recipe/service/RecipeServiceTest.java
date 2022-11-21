package com.codingblackfemales.recipe.service;

import com.codingblackfemales.recipe.model.Ingredient;
import com.codingblackfemales.recipe.model.Recipe;
import com.codingblackfemales.recipe.repository.DatabaseRepository;
//import org.junit.Test;
//import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    public void canAddRecipeEntry(){
        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
        Ingredient ingredient2 = new Ingredient("lamb", 1.0);
        List<Ingredient> ingredients = List.of(ingredient1, ingredient2);

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
    public void canThrowWhenNameFieldIsMissing(){
        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
        Ingredient ingredient2 = new Ingredient("lamb", 1.0);
        List<Ingredient> ingredients = List.of(ingredient1, ingredient2);

        Recipe recipe1 = new Recipe(1, null, ingredients, "heat up", "chicken.come");

        assertThatThrownBy(() -> underTest.postRecipe(recipe1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Fields cannot be empty");

    }

    @Test
    public void canThrowWhenIngredientFieldIsMissing(){
        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
        Ingredient ingredient2 = new Ingredient("lamb", 1.0);
        List<Ingredient> ingredients = List.of(ingredient1, ingredient2);

        Recipe recipe1 = new Recipe(1, "chicken", null, "heat up", "chicken.come");

        assertThatThrownBy(() -> underTest.postRecipe(recipe1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Fields cannot be empty");
    }

    @Test
    public void canThrowWhenIdAlreadyExists(){
        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
        Ingredient ingredient2 = new Ingredient("lamb", 1.0);
        List<Ingredient> ingredients = List.of(ingredient1, ingredient2);
        Recipe recipe1 = new Recipe(1, "chicken", ingredients, "heat up", "chicken.come");

        given(databaseRepository.existsById(1)).willReturn(true);

        assertThatThrownBy(() -> underTest.postRecipe(recipe1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Recipe with id 1 already exists. Cannot register with the same id");
    }
    @Test
    public void canThrowWhenInstructionFieldIsMissing(){
        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
        Ingredient ingredient2 = new Ingredient("lamb", 1.0);
        List<Ingredient> ingredients = List.of(ingredient1, ingredient2);

        Recipe recipe1 = new Recipe(1, "chicken", ingredients, null, "chicken.come");

        assertThatThrownBy(() -> underTest.postRecipe(recipe1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Fields cannot be empty");
    }

    @Test
    public void canGetAllRecipes() {
        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
        Ingredient ingredient2 = new Ingredient("lamb", 1.0);
        List<Ingredient> ingredients = List.of(ingredient1, ingredient2);

        Recipe recipe1 = new Recipe(1, "chicken", ingredients, "heat up", "chicken.come");
        Recipe recipe2 = new Recipe(2, "lamb", ingredients, "heat up", "chicken.come");

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
        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
        Ingredient ingredient2 = new Ingredient("lamb", 1.0);
        List<Ingredient> ingredients = List.of(ingredient1, ingredient2);

        Recipe recipe1 = new Recipe(1, "chicken", ingredients, "heat up", "chicken.come");
        Recipe recipe2 = new Recipe(2, "chicken", ingredients, "heat up", "chicken.come");

//        given(this.databaseRepository.findById(recipe1.getId())).willReturn(Optional.of(recipe1));

        when(databaseRepository.findById(1)).thenReturn(Optional.of(recipe1));

        Recipe actual = underTest.getRecipeById(recipe1.getId()).get();

        assertThat(actual).isEqualTo(recipe1);

//        Optional<Recipe> expected  = this.databaseRepository.findById(1);
//        Optional<Recipe> actual  = underTest.getRecipeById(1);
//        System.out.println(this.databaseRepository.findById(1).get());
//        assertThat(actual).isEqualTo(expected);
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
    public void canThrowWhenIdIsNull() {
//        given(databaseRepository.findById(null)).willReturn(null);

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
        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
        Ingredient ingredient2 = new Ingredient("lamb", 1.0);
        List<Ingredient> ingredients = List.of(ingredient1, ingredient2);

        Recipe recipe1 = new Recipe("chicken shish", ingredients, "heat up", "chicken.come");
        Recipe recipe2 = new Recipe("lamb", ingredients, "heat up", "chicken.come");

        List<Recipe> expectedRecipeList = new ArrayList<>();
        expectedRecipeList.add(recipe1);
        expectedRecipeList.add(recipe2);

        given(databaseRepository.findRecipeByName("chicken")).willReturn(expectedRecipeList);
        List<Recipe> actualRecipeList = underTest.getRecipeByName("chicken");

        assertEquals(expectedRecipeList, actualRecipeList);
    }

    //
    @Test
    public void canUpdateRecipe() {
        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
        Ingredient ingredient2 = new Ingredient("lamb", 1.0);
        List<Ingredient> ingredients = List.of(ingredient1, ingredient2);
        // do given. and put update one in
        Recipe recipe1 = new Recipe("chicken shish", ingredients, "heat up", "chicken.come");
        Recipe recipe2 = new Recipe("lamb", ingredients, "heat up", "chicken.come");

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
    public void cantThrowWhenIdIsNullForUpdate() {


        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
        Ingredient ingredient2 = new Ingredient("lamb", 1.0);
        List<Ingredient> ingredients = List.of(ingredient1, ingredient2);
        // do given. and put update one in
        Recipe recipe1 = new Recipe(1, "chicken shish", ingredients, "heat up", "chicken.come");
        Recipe recipe2 = new Recipe("lamb", ingredients, "heat up", "chicken.come");

//        given(databaseRepository.findById(1)).willReturn(Optional.of(recipe1));

        assertThatThrownBy(() -> underTest.updateRecipe(null, recipe2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Id cannot be null");
    }

    @Test
    public void cantThrowWhenRecipeIsNullForUpdate() {

        assertThatThrownBy(() -> underTest.updateRecipe(1, null))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Update recipe cannot be null");
    }

    @Test
    public void cantThrowWhenNameIsNullForUpdate() {
        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
        Ingredient ingredient2 = new Ingredient("lamb", 1.0);
        List<Ingredient> ingredients = List.of(ingredient1, ingredient2);
        // do given. and put update one in
        Recipe recipe1 = new Recipe(1, null, ingredients, "heat up", "chicken.come");

        assertThatThrownBy(() -> underTest.updateRecipe(1, recipe1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Recipe name cannot be null");
    }

    @Test
    public void cantThrowWhenIngredientIsNullForUpdate() {
        // do given. and put update one in
        Recipe recipe1 = new Recipe(1, "chicken", null, "heat up", "chicken.come");

        assertThatThrownBy(() -> underTest.updateRecipe(1, recipe1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Ingredients cannot be null");
    }

    @Test
    public void cantThrowWhenInstructionIsNullForUpdate() {
        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
        Ingredient ingredient2 = new Ingredient("lamb", 1.0);
        List<Ingredient> ingredients = List.of(ingredient1, ingredient2);
        // do given. and put update one in
        Recipe recipe1 = new Recipe(1, "chicken", ingredients, null, "chicken.come");

        assertThatThrownBy(() -> underTest.updateRecipe(1, recipe1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Instructions cannot be null");
    }

    @Test
    public void cantThrowWhenIdDoesntExistForUpdate() {
        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
        Ingredient ingredient2 = new Ingredient("lamb", 1.0);
        List<Ingredient> ingredients = List.of(ingredient1, ingredient2);
        // do given. and put update one in
        Recipe recipe1 = new Recipe(1, "chicken", ingredients, "heat up", "chicken.come");

        given(databaseRepository.findById(1)).willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.updateRecipe(1, recipe1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Recipe with id 1 does not exist");
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

//        given(databaseRepository.save())
        given(databaseRepository.findById(1)).willReturn(Optional.of(recipe1));
//        given(databaseRepository.deleteById(1)).willReturn()



        underTest.deleteById(1);

        verify(databaseRepository, times(1)).deleteById(1);



//        given(databaseRepository.)
    }


    @Test
    public void canThrowWhenRecipeWithIdDoesntExistForDelete() {
        given(databaseRepository.findById(1)).willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.deleteById(1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Recipe with id 1 does not exist. Cannot delete a recipe which does not exist");
    }


    @Test
    public void canGetRecipeByIngredientName() {
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient2 = new Ingredient(1, 1, "chicken", 1.0);
        Ingredient ingredient1 = new Ingredient(2, 2, "Lamb", 1.0);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        Recipe recipe1 = new Recipe(1, "chicken shish", List.of(ingredient2, ingredient1), "heat up", "chicken.com");
        Recipe recipe2 = new Recipe(2, "lamb", List.of(ingredient1), "heat up", "chicken.com");

        List<Recipe> expectedRecipeList = new ArrayList<>();
        expectedRecipeList.add(recipe1);

        given(databaseRepository.findRecipeByIngredientName("chicken")).willReturn(List.of(ingredient2));
//        List<Optional<Recipe>> actualRecipeList = underTest.getRecipeByIngredientName("chicken");

//        verify()
//        assertEquals(expectedRecipeList, actualRecipeList);


        underTest.getRecipeByIngredientName("chicken");

        //this is being invoked 2 times
        verify(databaseRepository, times(1)).findRecipeByIngredientName("chicken");


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