
package com.filloasoft.android.androeat.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "recipeID",
        "recipeName",
        "recipeIngredients",
        "recipeImage",
        "cookingTimeMinutes",
        "recipeDiets",
        "recipeInstructions",
        "unusedIngredients"
})
public class Recipe implements Serializable, Parcelable {

    @JsonProperty("recipeID")
    private String recipeID;
    @JsonProperty("recipeName")
    private String recipeName;
    @JsonProperty("recipeIngredients")
    private List<RecipeIngredient> recipeIngredients = null;
    @JsonProperty("recipeImage")
    private String recipeImage;
    @JsonProperty("cookingTimeMinutes")
    private String cookingTimeMinutes;
    @JsonProperty("recipeDiets")
    private List<String> recipeDiets = null;
    @JsonProperty("recipeInstructions")
    private List<String> recipeInstructions = null;
    @JsonProperty("unusedIngredients")
    private Object unusedIngredients;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    private Bitmap recipeBitmapImage;

    @JsonProperty("recipeID")
    public String getRecipeID() {
        return recipeID;
    }

    @JsonProperty("recipeID")
    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    @JsonProperty("recipeName")
    public String getRecipeName() {
        return recipeName;
    }

    @JsonProperty("recipeName")
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    @JsonProperty("recipeIngredients")
    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    @JsonProperty("recipeIngredients")
    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    @JsonProperty("recipeImage")
    public String getRecipeImage() {
        return recipeImage;
    }

    @JsonProperty("recipeImage")
    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    @JsonProperty("cookingTimeMinutes")
    public String getCookingTimeMinutes() {
        return cookingTimeMinutes;
    }

    @JsonProperty("cookingTimeMinutes")
    public void setCookingTimeMinutes(String cookingTimeMinutes) {
        this.cookingTimeMinutes = cookingTimeMinutes;
    }

    @JsonProperty("recipeDiets")
    public List<String> getRecipeDiets() {
        return recipeDiets;
    }

    @JsonProperty("recipeDiets")
    public void setRecipeDiets(List<String> recipeDiets) {
        this.recipeDiets = recipeDiets;
    }

    @JsonProperty("recipeInstructions")
    public List<String> getRecipeInstructions() {
        return recipeInstructions;
    }

    @JsonProperty("recipeInstructions")
    public void setRecipeInstructions(List<String> recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    @JsonProperty("unusedIngredients")
    public Object getUnusedIngredients() {
        return unusedIngredients;
    }

    @JsonProperty("unusedIngredients")
    public void setUnusedIngredients(Object unusedIngredients) {
        this.unusedIngredients = unusedIngredients;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Bitmap getRecipeBitmapImage() {
        return recipeBitmapImage;
    }

    public void setRecipeBitmapImage(Bitmap bitmap) {
        this.recipeBitmapImage = bitmap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}