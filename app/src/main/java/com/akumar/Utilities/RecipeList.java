package com.akumar.Utilities;

import org.json.JSONArray;

public class RecipeList {

    private int id;
    private String name;
    JSONArray ingredients;


    public RecipeList(int id, String name, JSONArray ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;

    }

    public JSONArray getIngredients() {
        return ingredients;
    }

    public void setIngredients(JSONArray ingredients) {
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
