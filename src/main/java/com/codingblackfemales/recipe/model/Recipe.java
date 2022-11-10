package com.codingblackfemales.recipe.model;

import java.util.List;
import java.util.Objects;

public class Recipe {
    private Integer id;
    private String name;
    private List<Ingredient> ingredient;
    private String instruction;
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

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getUrl() {
        return url;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private List<Ingredient> ingredient;
        private String instruction;
        private String url;

        public Builder(){}

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setIngredient(List<Ingredient> ingredient) {
            this.ingredient = ingredient;
            return this;
        }

        public Builder setInstruction(String instruction) {
            this.instruction = instruction;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Recipe build(){
            return new Recipe(id, name, ingredient, instruction, url);
        }
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
