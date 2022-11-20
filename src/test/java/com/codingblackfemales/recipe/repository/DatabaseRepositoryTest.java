//package com.codingblackfemales.recipe.repository;
//
//import com.codingblackfemales.recipe.model.Ingredient;
//import com.codingblackfemales.recipe.model.Recipe;
////import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//public class DatabaseRepositoryTest {
//
//    @Autowired
//    private DatabaseRepository databaseRepository;
//
//    @Test
//    public void saveRecipe(){
//        List<Ingredient> ingredients = new ArrayList<>();
//        Ingredient ingredient2 = new Ingredient("chicken", 1.0);
//        Ingredient ingredient1 = new Ingredient("chicken", 1.0);
//        ingredients.add(ingredient1);
//        ingredients.add(ingredient2);
//
//        Recipe recipe = Recipe.builder()
//                .name("chicken")
//                .ingredient(ingredients)
//                .instruction("heat up")
//                .url("chicken.com")
//                .build();
//
//        databaseRepository.save(recipe);
//
//        assertThat(recipe.getId()).isGreaterThan(0); //validates
////        Assertions.assertThat(recipe.getId()).is
//    }
//
//}