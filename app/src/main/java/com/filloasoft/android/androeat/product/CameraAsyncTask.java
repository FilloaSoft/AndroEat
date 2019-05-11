package com.filloasoft.android.androeat.product;

import android.app.Activity;
import android.content.Intent;
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


public class CameraAsyncTask extends AsyncTask<Object, Void, ResponseEntity<String>> {

    private String currentPath;

        public CameraAsyncTask() {
        }

        @Override
        protected ResponseEntity<String> doInBackground(Object... params) {
            this.currentPath = (String) params[0];

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

                return response;
            } catch (Exception e) {
                Log.e("Activity", e.getMessage(), e);
            }
            return null;
        }


        @Override
        protected void onPostExecute(ResponseEntity response) {
            // Do something in response to button
            Log.e("Response","response status: " + response.getStatusCode());
            Log.e("Response","response body: " + response.getBody());
//            Toast toast = Toast.makeText(activity.get(),
//                    response.getBody().toString(), Toast.LENGTH_SHORT);
//            toast.show();
//            ProductListView pdLview = new ProductListView(product.getGenericName(), product.getProductName(), product.getImage(), product.getLabelsTags(), product.getIngredientsText());
//              this.dialog.showProgress(false);

//            mAdapter.addItem(pdLview);
//            listener.onTaskCompleted(false);
        }

    }