package com.codingblackfemales.recipe.repository;

import com.codingblackfemales.recipe.model.Ingredient;
import com.codingblackfemales.recipe.model.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("database")
public interface DatabaseRepository extends JpaRepository<Recipe, Integer> {


    //try this
    //@Query(nativeQuery=true, value="select user_name as name, email, COUNT(*) as numNotes from user_accounts JOIN notes ON user_id = note_user WHERE user_id = ?1 GROUP BY user_name, email")
    //   FriendList findFriend(long id);
    //https://coderanch.com/t/740840/frameworks/Spring-Data-JPA-Invoke-Data
    @Query("SELECT i FROM Ingredient i where LOWER(i.name) LIKE %:name%")
    List<Ingredient> findbyIngredientName(String name);


    @Query("SELECT r FROM Recipe r where r.name LIKE %:name%")
    List<Recipe> getByName(String name);



}


//SELECT m FROM Material m WHERE m.productName LIKE %:searchText%