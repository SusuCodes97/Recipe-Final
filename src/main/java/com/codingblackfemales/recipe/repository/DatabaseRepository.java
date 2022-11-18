package com.codingblackfemales.recipe.repository;

import com.codingblackfemales.recipe.model.Ingredient;
import com.codingblackfemales.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("database")
public interface DatabaseRepository extends JpaRepository<Recipe, Integer> {

    @Query("SELECT i FROM Ingredient i where i.name LIKE %:name%")
    List<Ingredient> findbyIngredientName(String name);



    @Query("SELECT r FROM Recipe r where r.name LIKE %:name%")
    List<Recipe> getByName(String name);



}


//SELECT m FROM Material m WHERE m.productName LIKE %:searchText%