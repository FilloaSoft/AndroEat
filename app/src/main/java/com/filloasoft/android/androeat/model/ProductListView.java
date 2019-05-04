package com.filloasoft.android.androeat.model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

public class ProductListView implements Serializable {


    public final String productName;
    public final String productDescription;
    public final Bitmap productImage;
    public final List<String> productLabelTags;
    public final List<String> ingredients;
//    public final boolean checked;

    public ProductListView(String productName, String productDescription, Bitmap productImage, List<String> productLabelTags, List<String> ingredients) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.productLabelTags = productLabelTags;
        this.ingredients = ingredients;
//        this.checked = checked;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Bitmap getProductImage() {
        return productImage;
    }

    public List<String> getProductLabelTags() {
        return productLabelTags;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}
