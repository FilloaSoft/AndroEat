package com.filloasoft.android.androeat.product;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.filloasoft.android.androeat.model.Product;
import com.filloasoft.android.androeat.model.ProductListView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

public class RapidEatAsyncTask extends AsyncTask<Object, Void, Product> {

    private String barcode;
    ShoppingBasketListAdapter mAdapter;


    public RapidEatAsyncTask(ShoppingBasketListAdapter adapter) {
            this.mAdapter = adapter;
    }

    @Override
    protected Product doInBackground(Object... params) {
        barcode = (String) params[0];

        try {
            //TODO make url a config param
            final String url;
            url = "http://androeat.dynu.net/product/" + barcode;

            RestTemplate restTemplate = new RestTemplate();

            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Product product = restTemplate.getForObject(url, Product.class);

            try {
                URL newurl = new URL(product.getImageUrl());
                Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                product.setImage(mIcon_val);
            } catch (Exception e) {
                Log.e("URL Error", e.getMessage(), e);
            }


            return product;
        } catch (Exception e) {
            Log.e("Activity", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Product product) {
        ProductListView pdLview = new ProductListView(product.getGenericName(), product.getProductName(), product.getImage(), product.getLabelsTags(), product.getIngredientsText());

        mAdapter.addItem(pdLview);
    }

}
