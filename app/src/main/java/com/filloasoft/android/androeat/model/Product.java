package com.filloasoft.android.androeat.model;

import android.graphics.Bitmap;

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
public class Product implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("generic_name")
    private String genericName;
    @JsonProperty("brands")
    private String brands;
    @JsonProperty("traces")
    private String traces;
    @JsonProperty("serving_size")
    private String servingSize;
    @JsonProperty("ingredients_from_palm_oil")
    private String ingredientsFromPalmOil;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("energy")
    private String energy;
    @JsonProperty("proteins")
    private String proteins;
    @JsonProperty("carbohydrates")
    private String carbohydrates;
    @JsonProperty("sugars")
    private String sugars;
    @JsonProperty("fat")
    private String fat;
    @JsonProperty("quantity")
    private String quantity;
    @JsonProperty("manufacturing_places")
    private String manufacturingPlaces;
    @JsonProperty("stores")
    private String stores;
    @JsonProperty("nutritionScore")
    private String nutritionScore;
    @JsonProperty("labels_tags")
    private List<String> labelsTags = null;
    @JsonProperty("categories_hierarchy")
    private List<String> categoriesHierarchy = null;
    @JsonProperty("ingredients_text")
    private List<String> ingredientsText = null;
    @JsonProperty("allergens")
    private List<String> allergens = null;
    @JsonProperty("label_tags")
    private List<String> labelTags = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    private Bitmap image = null;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
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
    public String getGenericName() {
        return genericName;
    }

    @JsonProperty("generic_name")
    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    @JsonProperty("brands")
    public String getBrands() {
        return brands;
    }

    @JsonProperty("brands")
    public void setBrands(String brands) {
        this.brands = brands;
    }

    @JsonProperty("traces")
    public String getTraces() {
        return traces;
    }

    @JsonProperty("traces")
    public void setTraces(String traces) {
        this.traces = traces;
    }

    @JsonProperty("serving_size")
    public String getServingSize() {
        return servingSize;
    }

    @JsonProperty("serving_size")
    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    @JsonProperty("ingredients_from_palm_oil")
    public String getIngredientsFromPalmOil() {
        return ingredientsFromPalmOil;
    }

    @JsonProperty("ingredients_from_palm_oil")
    public void setIngredientsFromPalmOil(String ingredientsFromPalmOil) {
        this.ingredientsFromPalmOil = ingredientsFromPalmOil;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("energy")
    public String getEnergy() {
        return energy;
    }

    @JsonProperty("energy")
    public void setEnergy(String energy) {
        this.energy = energy;
    }

    @JsonProperty("proteins")
    public String getProteins() {
        return proteins;
    }

    @JsonProperty("proteins")
    public void setProteins(String proteins) {
        this.proteins = proteins;
    }

    @JsonProperty("carbohydrates")
    public String getCarbohydrates() {
        return carbohydrates;
    }

    @JsonProperty("carbohydrates")
    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @JsonProperty("sugars")
    public String getSugars() {
        return sugars;
    }

    @JsonProperty("sugars")
    public void setSugars(String sugars) {
        this.sugars = sugars;
    }

    @JsonProperty("fat")
    public String getFat() {
        return fat;
    }

    @JsonProperty("fat")
    public void setFat(String fat) {
        this.fat = fat;
    }

    @JsonProperty("quantity")
    public String getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("manufacturing_places")
    public String getManufacturingPlaces() {
        return manufacturingPlaces;
    }

    @JsonProperty("manufacturing_places")
    public void setManufacturingPlaces(String manufacturingPlaces) {
        this.manufacturingPlaces = manufacturingPlaces;
    }

    @JsonProperty("stores")
    public String getStores() {
        return stores;
    }

    @JsonProperty("stores")
    public void setStores(String stores) {
        this.stores = stores;
    }

    @JsonProperty("nutritionScore")
    public String getNutritionScore() {
        return nutritionScore;
    }

    @JsonProperty("nutritionScore")
    public void setNutritionScore(String nutritionScore) {
        this.nutritionScore = nutritionScore;
    }

    @JsonProperty("labels_tags")
    public List<String> getLabelsTags() {
        return labelsTags;
    }

    @JsonProperty("labels_tags")
    public void setLabelsTags(List<String> labelsTags) {
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
    public List<String> getIngredientsText() {
        return ingredientsText;
    }

    @JsonProperty("ingredients_text")
    public void setIngredientsText(List<String> ingredientsText) {
        this.ingredientsText = ingredientsText;
    }

    @JsonProperty("allergens")
    public List<String> getAllergens() {
        return allergens;
    }

    @JsonProperty("allergens")
    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
    }

    @JsonProperty("label_tags")
    public List<String> getLabelTags() {
        return labelTags;
    }

    @JsonProperty("label_tags")
    public void setLabelTags(List<String> labelTags) {
        this.labelTags = labelTags;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap bitmap) {
        this.image = bitmap;
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

