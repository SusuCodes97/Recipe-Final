package com.codingblackfemales.recipe.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @SequenceGenerator(
            name = "recipe_sequence", sequenceName = "recipe_sequence", allocationSize = 1 //increment
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, generator = "recipe_sequence"
    )
    @Column(
            name = "recipe_id", updatable = false

    )
    private Integer id;

    @Column(nullable = false)
    private String name;

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    private List<Ingredient> ingredient;

    @Column(nullable = false)
    private String instruction;

    @Column
    private String url;

    public Recipe(String name, List<Ingredient> ingredient, String instruction, String url) {
        this.name = name;
        this.ingredient = ingredient;
        this.instruction = instruction;
        this.url = url;
    }
}
