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
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;

public class HowToFragment extends Fragment {


    private TextView mTextMessage;
    private Toast toast;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View howToView = inflater.inflate(R.layout.fragment_howto, null);

        return  howToView;
    }

    public void addToFavorite(View view) {
        toast = Toast.makeText(getActivity(),
                "FAVORITE!", Toast.LENGTH_SHORT);
        toast.show();

    }

    public void shareRecipe(View view) {
        toast = Toast.makeText(getActivity(),
                "SHARING RECIPE!", Toast.LENGTH_SHORT);
        toast.show();

    }

}