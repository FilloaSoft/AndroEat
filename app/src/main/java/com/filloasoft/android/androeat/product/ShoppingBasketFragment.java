package com.filloasoft.android.androeat.product;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.Product;
import com.filloasoft.android.androeat.model.ProductListView;
import com.filloasoft.android.androeat.recipe.HowToFragment;
import com.filloasoft.android.androeat.recipe.RecipeFragment;
import com.filloasoft.android.androeat.user.LoginFragment;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppingBasketFragment extends Fragment {

    ListView listView;
    Toast toast;
//    OnClickProduct mCallback;


    static ShoppingBasketListAdapter adapter;
    static ArrayList<ProductListView> products = new ArrayList<>();
    public rapidEat apiCall = new rapidEat();

//    public static ShoppingBasketFragment getInstance()
//    {
//        if (single_instance == null)
//            single_instance = new ShoppingBasketFragment();
//
//        return single_instance;
//


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View basketView = (View) inflater.inflate(R.layout.fragment_basket, container , false);

        this.adapter = new ShoppingBasketListAdapter(getActivity(), this.products);
        listView = basketView.findViewById(R.id.basketList);
        listView.setAdapter(this.adapter);
//        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mCallback.onProductSelected(basketView);

                ProductListView product = (ProductListView) listView.getItemAtPosition(position);

                ProductDetailsFragment nextFrag = new ProductDetailsFragment().newInstance(product);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });

//        for (int i = 0; i < listView.getAdapter().getCount() ; i++) {
//            ProductListView product = (ProductListView) listView.getItemAtPosition(i);
//            if (checked.containsKey(i)) {
//                if (checked.get(i)){
//                    markedList.add(product.getProductName());
//                }
//            }
//        }

        final Button button = (Button) basketView.findViewById(R.id.findButtonBasket);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
//                toast = Toast.makeText(getContext(),
//                        listView.getAdapter().getCount(), Toast.LENGTH_SHORT);
//                toast.show();
                getRecipes();
            }
        });

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
        ProductListView pdLview = new ProductListView(product.getGenericName(), product.getProductName(), product.getImage(), product.getLabelsTags(), product.getIngredientsText());
//        ShoppingBasketListAdapter adapter = new ShoppingBasketListAdapter(getActivity(), this.products);
        adapter.add(pdLview);
        adapter.notifyDataSetChanged();

    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }




    public void getRecipes(){
        List<String> markedList = new ArrayList<>();
//        list = (ListView) findViewById(R.id.listagem);
//        listView.setAdapter(adapter);
        Map<Integer, Boolean> checked = adapter.getCheckedList();

//        toast = Toast.makeText(getContext(),
//                Integer.toString(listView.getAdapter().getCount()), Toast.LENGTH_SHORT);
//        toast.show();
//        toast = Toast.makeText(getContext(),
//                checked.toString(), Toast.LENGTH_SHORT);
//        toast.show();
//
//            if (checked.get(0)) {
//
//                markedList.add("aaa");
//            }


        for (int i = 0; i < listView.getAdapter().getCount() ; i++) {
                ProductListView product = (ProductListView) listView.getItemAtPosition(i);
            if (checked.containsKey(i)) {
                if (checked.get(i)){
                    markedList.add(product.getProductName());
                }
            }
        }

        toast = Toast.makeText(getContext(),
                markedList.toString(), Toast.LENGTH_SHORT);
        toast.show();



//        int outdata = listView.getAdapter().getCount();
//
//            if (checked == null){
//                toast = Toast.makeText(getContext(),
//                        "suuuuu", Toast.LENGTH_SHORT);
//                toast.show();
//        } else{
//                toast = Toast.makeText(getContext(),
//                        checked.get(i), Toast.LENGTH_SHORT);
//                toast.show();
//            }

//        } else {
//            toast = Toast.makeText(getContext(),
//                    checked(), Toast.LENGTH_SHORT);
//            toast.show();
//        }



//        return peticion.toString();
    }

}
