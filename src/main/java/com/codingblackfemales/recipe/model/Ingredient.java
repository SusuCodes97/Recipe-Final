package com.codingblackfemales.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serial;
import java.util.Objects;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="INGREDIENTS")
//@Embeddable
public class Ingredient {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private long id; // do i have to change to long/integer. TRY SERIAL

//        @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(name="recipe_id")
    private Long recipeId;
    @Column(name = "ingredient_name")
    private String name;
    @Column(name = "ingredient_quantity")
    private double quantity;

//    @ManyToOne
//    @JoinColumn(name="recipe_id", nullable=false)
//    private Recipe recipe;

    public Ingredient(String name, double quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Double.compare(that.quantity, quantity) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
