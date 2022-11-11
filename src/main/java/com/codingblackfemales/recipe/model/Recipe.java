package com.codingblackfemales.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@ToString
@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @SequenceGenerator(
            name = "recipe_sequence",
            sequenceName = "recipe_sequence",
            allocationSize = 1 //increment
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "recipe_sequence"
    )
    @Column(
            name = "id",
            updatable = false //no one can update it

    )
    private Integer id;
    @Column(nullable = false)
    private String name;

    //    @Transient //change later!
    @ElementCollection
    private List<Ingredient> ingredient;
    @Column(nullable = false)
    private String instruction;

    @Column
    private String url;

    public Recipe(Integer id, String name, List<Ingredient> ingredient, String instruction, String url) {
        this.id = id;
        this.name = name;
        this.ingredient = ingredient;
        this.instruction = instruction;
        this.url = url;
    }

    public Recipe(String name, List<Ingredient> ingredient, String instruction, String url) {
        this.name = name;
        this.ingredient = ingredient;
        this.instruction = instruction;
        this.url = url;
    }

    public Recipe() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id) && Objects.equals(name, recipe.name) && Objects.equals(ingredient, recipe.ingredient) && Objects.equals(instruction, recipe.instruction) && Objects.equals(url, recipe.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ingredient, instruction, url);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredient=" + ingredient +
                ", instruction='" + instruction + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
