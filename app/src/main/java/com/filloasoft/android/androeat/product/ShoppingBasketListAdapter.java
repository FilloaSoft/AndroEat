package com.filloasoft.android.androeat.product;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.Product;
import com.filloasoft.android.androeat.model.ProductListView;

import java.util.ArrayList;

public class ShoppingBasketListAdapter extends ArrayAdapter<ProductListView> {

    private final Activity context;


    public ShoppingBasketListAdapter(Activity context, ArrayList<ProductListView> productListView) {
        super(context,R.layout.product_list_row, productListView);
        this.context = context;
//        this.productName = productName;
//        this.productDescription = productDescription;
//        this.productImage = productImage;
    }

//    public ShoppingBasketListAdapter(Activity context, ArrayList<String> productName, ArrayList<String> productDescription, ArrayList<Integer> productImage) {
//        super(context, R.layout.product_list_row, productName);
//        this.context=context;
//        this.productName=productName;
//        this.productDescription=productDescription;
//        this.productImage=productImage;
//
//    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.product_list_row, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.productName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.productImage);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.productDescription);

        ProductListView pdLview = getItem(position);

        titleText.setText(pdLview.productName);
        imageView.setImageBitmap(pdLview.productImage);
        subtitleText.setText(pdLview.productDescription);

        return rowView;
    };

//    public void addItems(String productName, String productDescription, Integer image){
//        LayoutInflater inflater=context.getLayoutInflater();
//        View rowView=inflater.inflate(R.layout.product_list_row, null,true);
//
//        TextView titleText = (TextView) rowView.findViewById(R.id.productName);
//        ImageView imageView = (ImageView) rowView.findViewById(R.id.productImage);
//        TextView subtitleText = (TextView) rowView.findViewById(R.id.productDescription);
//
//        this.productName.add(productName);
//        this.productImage.add(image);
//        subtitleText.setText(this.productDescription.get(position));
//    }
}
