package com.filloasoft.android.androeat.model;

import android.graphics.Bitmap;

public class ProductListView {
    public final String productName;
    public final String productDescription;
    public final Bitmap productImage;

    public ProductListView(String productName, String productDescription, Bitmap productImage) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImage = productImage;
    }
}
