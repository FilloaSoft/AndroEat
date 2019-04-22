package com.filloasoft.android.androeat.model;

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
        "id",
        "product_name",
        "generic_name",
        "brands",
        "traces",
        "serving_size",
        "ingredients_from_palm_oil",
        "image_url",
        "energy",
        "proteins",
        "carbohydrates",
        "sugars",
        "fat",
        "quantity",
        "manufacturing_places",
        "stores",
        "nutritionScore",
        "labels_tags",
        "categories_hierarchy",
        "ingredients_text",
        "allergens",
        "label_tags"
})
public class RecipeIngredient {

    @JsonProperty("id")
    private Object id;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("generic_name")
    private Object genericName;
    @JsonProperty("brands")
    private Object brands;
    @JsonProperty("traces")
    private Object traces;
    @JsonProperty("serving_size")
    private Object servingSize;
    @JsonProperty("ingredients_from_palm_oil")
    private Object ingredientsFromPalmOil;
    @JsonProperty("image_url")
    private Object imageUrl;
    @JsonProperty("energy")
    private Object energy;
    @JsonProperty("proteins")
    private Object proteins;
    @JsonProperty("carbohydrates")
    private Object carbohydrates;
    @JsonProperty("sugars")
    private Object sugars;
    @JsonProperty("fat")
    private Object fat;
    @JsonProperty("quantity")
    private Object quantity;
    @JsonProperty("manufacturing_places")
    private Object manufacturingPlaces;
    @JsonProperty("stores")
    private Object stores;
    @JsonProperty("nutritionScore")
    private Object nutritionScore;
    @JsonProperty("labels_tags")
    private Object labelsTags;
    @JsonProperty("categories_hierarchy")
    private List<String> categoriesHierarchy = null;
    @JsonProperty("ingredients_text")
    private Object ingredientsText;
    @JsonProperty("allergens")
    private Object allergens;
    @JsonProperty("label_tags")
    private Object labelTags;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Object getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Object id) {
        this.id = id;
    }

    @JsonProperty("product_name")
    public String getProductName() {
        return productName;
    }

    @JsonProperty("product_name")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("generic_name")
    public Object getGenericName() {
        return genericName;
    }

    @JsonProperty("generic_name")
    public void setGenericName(Object genericName) {
        this.genericName = genericName;
    }

    @JsonProperty("brands")
    public Object getBrands() {
        return brands;
    }

    @JsonProperty("brands")
    public void setBrands(Object brands) {
        this.brands = brands;
    }

    @JsonProperty("traces")
    public Object getTraces() {
        return traces;
    }

    @JsonProperty("traces")
    public void setTraces(Object traces) {
        this.traces = traces;
    }

    @JsonProperty("serving_size")
    public Object getServingSize() {
        return servingSize;
    }

    @JsonProperty("serving_size")
    public void setServingSize(Object servingSize) {
        this.servingSize = servingSize;
    }

    @JsonProperty("ingredients_from_palm_oil")
    public Object getIngredientsFromPalmOil() {
        return ingredientsFromPalmOil;
    }

    @JsonProperty("ingredients_from_palm_oil")
    public void setIngredientsFromPalmOil(Object ingredientsFromPalmOil) {
        this.ingredientsFromPalmOil = ingredientsFromPalmOil;
    }

    @JsonProperty("image_url")
    public Object getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("energy")
    public Object getEnergy() {
        return energy;
    }

    @JsonProperty("energy")
    public void setEnergy(Object energy) {
        this.energy = energy;
    }

    @JsonProperty("proteins")
    public Object getProteins() {
        return proteins;
    }

    @JsonProperty("proteins")
    public void setProteins(Object proteins) {
        this.proteins = proteins;
    }

    @JsonProperty("carbohydrates")
    public Object getCarbohydrates() {
        return carbohydrates;
    }

    @JsonProperty("carbohydrates")
    public void setCarbohydrates(Object carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @JsonProperty("sugars")
    public Object getSugars() {
        return sugars;
    }

    @JsonProperty("sugars")
    public void setSugars(Object sugars) {
        this.sugars = sugars;
    }

    @JsonProperty("fat")
    public Object getFat() {
        return fat;
    }

    @JsonProperty("fat")
    public void setFat(Object fat) {
        this.fat = fat;
    }

    @JsonProperty("quantity")
    public Object getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(Object quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("manufacturing_places")
    public Object getManufacturingPlaces() {
        return manufacturingPlaces;
    }

    @JsonProperty("manufacturing_places")
    public void setManufacturingPlaces(Object manufacturingPlaces) {
        this.manufacturingPlaces = manufacturingPlaces;
    }

    @JsonProperty("stores")
    public Object getStores() {
        return stores;
    }

    @JsonProperty("stores")
    public void setStores(Object stores) {
        this.stores = stores;
    }

    @JsonProperty("nutritionScore")
    public Object getNutritionScore() {
        return nutritionScore;
    }

    @JsonProperty("nutritionScore")
    public void setNutritionScore(Object nutritionScore) {
        this.nutritionScore = nutritionScore;
    }

    @JsonProperty("labels_tags")
    public Object getLabelsTags() {
        return labelsTags;
    }

    @JsonProperty("labels_tags")
    public void setLabelsTags(Object labelsTags) {
        this.labelsTags = labelsTags;
    }

    @JsonProperty("categories_hierarchy")
    public List<String> getCategoriesHierarchy() {
        return categoriesHierarchy;
    }

    @JsonProperty("categories_hierarchy")
    public void setCategoriesHierarchy(List<String> categoriesHierarchy) {
        this.categoriesHierarchy = categoriesHierarchy;
    }

    @JsonProperty("ingredients_text")
    public Object getIngredientsText() {
        return ingredientsText;
    }

    @JsonProperty("ingredients_text")
    public void setIngredientsText(Object ingredientsText) {
        this.ingredientsText = ingredientsText;
    }

    @JsonProperty("allergens")
    public Object getAllergens() {
        return allergens;
    }

    @JsonProperty("allergens")
    public void setAllergens(Object allergens) {
        this.allergens = allergens;
    }

    @JsonProperty("label_tags")
    public Object getLabelTags() {
        return labelTags;
    }

    @JsonProperty("label_tags")
    public void setLabelTags(Object labelTags) {
        this.labelTags = labelTags;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}