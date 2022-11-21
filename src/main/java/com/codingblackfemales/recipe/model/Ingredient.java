package com.codingblackfemales.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="INGREDIENTS")
public class Ingredient {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="recipe_id")
    private Integer recipeId;
    @Column(name = "ingredient_name")
    private String name;
    @Column(name = "ingredient_quantity")
    private double quantity;

    public Ingredient(String name, double quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
