package com.filloasoft.android.androeat.product;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.ProductListView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ProductDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private ProductListView productListView;


    public ProductDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ProductDetailsFragment.
     */
    public static ProductDetailsFragment newInstance(ProductListView param1) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            productListView = (ProductListView) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View productDetailsView = inflater.inflate(R.layout.fragment_product, container, false);
        if (productListView.getProductName() != null) {
            TextView timeText = (TextView) productDetailsView.findViewById(R.id.titleProductName);
            timeText.setText(productListView.getProductName());
        }

        if (productListView.getProductImage() != null) {
            ImageView imageView = (ImageView) productDetailsView.findViewById(R.id.titleProductImage);
            imageView.setImageBitmap(productListView.getProductImage());
            imageView.setAdjustViewBounds(true);
        }

        if (productListView.getProductDescription() != null) {
            TextView descText = (TextView) productDetailsView.findViewById(R.id.product_description);
            descText.setText(productListView.getProductDescription());
        }

        if (productListView.getProductLabelTags() != null) {
            TextView productAlergensText = (TextView) productDetailsView.findViewById(R.id.productAlergens);
            String alergens = android.text.TextUtils.join(", ", productListView.getProductLabelTags());
            alergens = alergens.substring(0, 1).toUpperCase() + alergens.substring(1);
            productAlergensText.setText(alergens);
        }

        if (productListView.getIngredients() != null) {
            ListView ingredientsListView = productDetailsView.findViewById(R.id.ingredient_list);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_expandable_list_item_1,
                    productListView.getIngredients());
            ingredientsListView.setAdapter(adapter);
        }

        return productDetailsView;
    }



}
