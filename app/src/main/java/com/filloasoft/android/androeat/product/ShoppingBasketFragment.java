package com.filloasoft.android.androeat.product;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.Product;
import com.filloasoft.android.androeat.model.ProductListView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ShoppingBasketFragment extends Fragment {

    ListView list;
    Toast toast;
    private static ShoppingBasketFragment single_instance = null;


    static ShoppingBasketListAdapter adapter;
    static ArrayList<ProductListView> products = new ArrayList<>();
    public rapidEat apiCall = new rapidEat();

//    public static ShoppingBasketFragment getInstance()
//    {
//        if (single_instance == null)
//            single_instance = new ShoppingBasketFragment();
//
//        return single_instance;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View basketView = inflater.inflate(R.layout.fragment_basket, null);


        this.adapter = new ShoppingBasketListAdapter(getActivity(), this.products);
        list = basketView.findViewById(R.id.basketList);

        list.setAdapter(this.adapter);
        toast = Toast.makeText(getActivity(),
                "Pantalla de la cesta de la compra!", Toast.LENGTH_SHORT);

        toast.show();

        return basketView;
    }

     public class rapidEat extends AsyncTask<Object, Void, Product> {

        private String barcode;


        public rapidEat() {
        }

        @Override
        protected Product doInBackground(Object... params) {
//            Log.e("Activity", barcode);
            //Try to authenticate against an external rest service
            barcode = (String) params[0];
            Log.e("Activityyyyyyyyyyyyyy", barcode);

            try {
                //TODO make url a config param
                final String url;
                url = "http://androeat.dynu.net/product/" + barcode;

                RestTemplate restTemplate = new RestTemplate();

                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Product product = restTemplate.getForObject(url, Product.class);
                Log.e("Activityyyyyyyyyyyyyy", product.toString());

                try {
                    URL newurl = new URL(product.getImageUrl());
                    Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
                    product.setImage(mIcon_val);
                } catch (Exception e){
                    //pass
                }


                return product;
            } catch (Exception e) {
                Log.e("Activity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Product product) {
            addItems(product);

        }

    }
    public void addItems(Product product){
        ProductListView pdLview = new ProductListView(product.getGenericName(), product.getProductName(), product.getImage());
//        ShoppingBasketListAdapter adapter = new ShoppingBasketListAdapter(getActivity(), this.products);
        adapter.add(pdLview);
        adapter.notifyDataSetChanged();

    }

}
