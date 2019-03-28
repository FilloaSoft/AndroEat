package com.filloasoft.android.androeat.recipe;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;

public class HomeFragment extends Fragment {

    OnClickHowTo mCallback;
    private TextView mTextMessage;
    GridView grid;
    Toast toast;

    String[] recipeName = {
            "Tarta", "Espaguetis",
            "Tortilla",
    };

    String[] recipeDescription = {
            "Tarta de manzana", "Espaguetis description",
            "Tortilla description",
    };

    Integer[] recipeImage = {
            R.drawable.tarta, R.drawable.espaguetis,
            R.drawable.tortilla
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View homeView = inflater.inflate(R.layout.fragment_home, null);

        HomeListAdapter adapter = new HomeListAdapter(getActivity(), recipeName, recipeDescription, recipeImage);
        grid = homeView.findViewById(R.id.home_grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               mCallback.onRecipeSelected(homeView);
            }
        });
        mTextMessage = (TextView) homeView.findViewById(R.id.message);

        toast = Toast.makeText(getActivity(),
                "Pantalla de inicio!", Toast.LENGTH_SHORT);

        toast.show();

        return homeView;
    }

    public interface OnClickHowTo{
        public void onRecipeSelected(View view);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            mCallback = (OnClickHowTo) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }

    }
}