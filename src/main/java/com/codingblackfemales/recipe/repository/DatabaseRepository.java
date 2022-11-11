package com.codingblackfemales.recipe.repository;

import com.codingblackfemales.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("database")
public interface DatabaseRepository extends JpaRepository<Recipe, Integer> {

//    @Query("SELECT i FROM Ingredient i where i.name = :name")
//    List<Recipe> findbyIngredientName(String name);

    @Query("SELECT r FROM Recipe r where r.name = :name")
    List<Recipe> getByName(String name);
    //allow different variations - ie chicken can be chicken roast, chicken dish, chicken burger


    //custom method to find by name/ingredient
}
