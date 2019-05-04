package com.filloasoft.android.androeat.product;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.Product;
import com.filloasoft.android.androeat.model.ProductListView;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ShoppingBasketListAdapter extends ArrayAdapter<ProductListView> {

    private final Activity context;
    ArrayList<ProductListView> productListViews;
//    ArrayList<Boolean> checkedList = new ArrayList<>();
    Map<Integer, Boolean> checkedList = new HashMap<>();
//    ArrayList<String> selectedStrings = new ArrayList<String>();




    public ShoppingBasketListAdapter(Activity context, ArrayList<ProductListView> productListView) {
        super(context,R.layout.product_list_row, productListView);
        this.context = context;
        this.productListViews = productListView;
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
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        final View rowView=inflater.inflate(R.layout.product_list_row, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.productName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.productImage);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.productDescription);
        final CheckBox checkBoxView = (CheckBox) rowView.findViewById(R.id.checkBox);

        ProductListView pdLview = getItem(position);

        titleText.setText(pdLview.productName);
        imageView.setImageBitmap(pdLview.productImage);
        subtitleText.setText(pdLview.productDescription);

//        pdLview.checked

        checkBoxView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkedList.put(position, true);
                }else{
                    checkedList.put(position, false);
                }

            }
        });

        return rowView;
    };

        @Override
        public int getCount() {
            if(productListViews == null){
                return 0;
            } else {
                return productListViews.isEmpty()? 0 : productListViews.size();
            }
        }

        public Map<Integer, Boolean> getCheckedList(){
            return checkedList;
        }


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
