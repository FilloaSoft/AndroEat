package com.filloasoft.android.androeat.product;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.filloasoft.android.androeat.MainActivity;
import com.filloasoft.android.androeat.model.Product;
import com.filloasoft.android.androeat.model.ProductListView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URL;


public class CameraAsyncTask extends AsyncTask<Object, Void, Product> {

    private String currentPath;
    private Context context;
    private ShoppingBasketListAdapter mAdapter;
    private OnProductPosiblityListener callback;
    String url = null;

    public CameraAsyncTask() {
        }

    public void setOnProductPosiblityListener(OnProductPosiblityListener callback) {
        this.callback = callback;
    }

    public interface OnProductPosiblityListener{
        void onTaskCompleted(Boolean bool, String msg);
    }

        @Override
        protected Product doInBackground(Object... params) {
            this.currentPath = (String) params[0];
            this.context = (Context) params[1];
            this.mAdapter = (ShoppingBasketListAdapter) params[2];

            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.MULTIPART_FORM_DATA);
                headers.setAcceptEncoding(ContentCodingType.GZIP);
                FileSystemResource file =new FileSystemResource(currentPath);
                LinkedMultiValueMap<String, Object> params2 = new LinkedMultiValueMap<>();
                params2.add("file",file );//get file resource
                HttpEntity requestEntity = new HttpEntity<>(params2, headers);
                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<String> response = restTemplate.exchange("http://gooeat.dynu.net/", HttpMethod.POST, requestEntity, String.class);



                url = "http://androeat.dynu.net/product/image?name=" + response.getBody();

                RestTemplate restTemplateProduct = new RestTemplate();

                restTemplateProduct.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
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
            try{
                ProductListView pdLview = new ProductListView(product.getGenericName(), product.getProductName(), product.getImage(), product.getLabelsTags(), product.getIngredientsText());
                mAdapter.addItem(pdLview);
                callback.onTaskCompleted(false, "Product Added!");
            } catch (Exception e){
                callback.onTaskCompleted(false, "Product not found!");

            }

        }

    }