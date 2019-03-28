package com.filloasoft.android.androeat.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.product.ShoppingBasketListAdapter;

public class ShoppingBasketFragment extends Fragment {

    private TextView mTextMessage;
    ListView list;
    Toast toast;

    String[] productName = {
            "Apple", "Milk",
            "Eggs",
    };

    String[] productDescription = {
            "Apple description", "Milk description",
            "Eggs description",
    };

    Integer[] productImage = {
            R.drawable.apples, R.drawable.milk,
            R.drawable.eggs
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View basketView = inflater.inflate(R.layout.fragment_basket, null);

        ShoppingBasketListAdapter adapter = new ShoppingBasketListAdapter(getActivity(), productName, productDescription, productImage);
        list = (ListView) basketView.findViewById(R.id.basketList);
        list.setAdapter(adapter);

        mTextMessage = (TextView) basketView.findViewById(R.id.message);

        toast = Toast.makeText(getActivity(),
                "Pantalla de la cesta de la compra!", Toast.LENGTH_SHORT);

        toast.show();
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        return basketView;
    }


}
