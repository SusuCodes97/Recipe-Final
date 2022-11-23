package com.codingblackfemales.recipe.repository;

import com.codingblackfemales.recipe.model.Ingredient;
import com.codingblackfemales.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("database")
public interface DatabaseRepository extends JpaRepository<Recipe, Integer> {
    @Query("SELECT i FROM Ingredient i where LOWER(i.name) LIKE %:name%")
    List<Ingredient> findRecipeByIngredientName(String name);

    @Query("SELECT r FROM Recipe r where r.name LIKE %:name%")
    List<Recipe> findRecipeByName(String name);



}
