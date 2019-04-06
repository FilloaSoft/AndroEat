package com.filloasoft.android.androeat.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.product.ShoppingBasketListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HowToFragment extends Fragment {


    private TextView mTextMessage;
    ListView list;
    Toast toast;

    String[] productName = {
            "Apple", "Milk",
            "Eggs", "Apple", "Milk",
            "Eggs", "Apple", "Milk",
            "Eggs",
    };

    String[] productDescription = {
            "Apple description", "Milk description",
            "Eggs description", "Apple description", "Milk description",
            "Eggs description", "Apple description", "Milk description",
            "Eggs description",
    };

    Integer[] productImage = {
            R.drawable.apples, R.drawable.milk,
            R.drawable.eggs, R.drawable.apples, R.drawable.milk,
            R.drawable.eggs, R.drawable.apples, R.drawable.milk,
            R.drawable.eggs
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View howToView = inflater.inflate(R.layout.fragment_howto, null);
        ShoppingBasketListAdapter adapter = new ShoppingBasketListAdapter(getActivity(), productName, productDescription, productImage);
        list = (ListView) howToView.findViewById(R.id.stepsList);
        list.setAdapter(adapter);

        return  howToView;
    }

}