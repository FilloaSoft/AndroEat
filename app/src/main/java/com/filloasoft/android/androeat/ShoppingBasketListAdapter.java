package com.filloasoft.android.androeat;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShoppingBasketListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] productName;
    private final String[] productDescription;
    private final Integer[] productImage;

    public ShoppingBasketListAdapter(Activity context, String[] productName,String[] productDescription, Integer[] productImage) {
        super(context, R.layout.product_list_row, productName);
        this.context=context;
        this.productName=productName;
        this.productDescription=productDescription;
        this.productImage=productImage;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.product_list_row, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.productName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.productImage);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.productDescription);

        titleText.setText(productName[position]);
        imageView.setImageResource(productImage[position]);
        subtitleText.setText(productDescription[position]);

        return rowView;
    };
}
