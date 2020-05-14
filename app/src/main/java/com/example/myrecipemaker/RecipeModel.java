package com.example.myrecipemaker;

import java.io.Serializable;
import java.util.ArrayList;

public class RecipeModel implements Serializable {
    String title;
    String descrption;
    String instruction;
    String type;
    String prepare_time;
    String ingrediens;

    public RecipeModel() {
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    String recipeId;


    public String getIngrediens() {
        return ingrediens;
    }

    public void setIngrediens(String ingrediens) {
        this.ingrediens = ingrediens;
    }



    public RecipeModel(String title, String descrption, String instruction, String type,  String prepare_time,String ingrediens) {
        this.title = title;
        this.descrption = descrption;
        this.instruction = instruction;
        this.type = type;
        this.ingrediens = ingrediens;
        this.prepare_time = prepare_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }







    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getPrepare_time() {
        return prepare_time;
    }

    public void setPrepare_time(String prepare_time) {
        this.prepare_time = prepare_time;
    }

}
