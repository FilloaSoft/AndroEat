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

import com.filloasoft.android.androeat.MainActivity;
import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {

    FavouriteFragment.OnClickHowTo mCallback;
    private TextView mTextMessage;
    GridView grid;
    Toast toast;

    String[] recipeName = {
            "Espaguetis","Tortilla"
    };

    String[] recipeDescription = {
            "Espaguetis description", "Tortilla description"
    };

    Integer[] recipeImage = {
            R.drawable.espaguetis, R.drawable.tortilla
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View favouriteView = inflater.inflate(R.layout.fragment_favourites, null);

        Bundle bundle = getArguments();
        //List<Recipe> recipes = bundle.getParcelableArrayList("list");

        HomeListAdapter adapter = new HomeListAdapter(getActivity(), new ArrayList<Recipe>());
        grid = favouriteView.findViewById(R.id.home_grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.onRecipeSelected(favouriteView, position);
            }
        });
        mTextMessage = (TextView) favouriteView.findViewById(R.id.message);

        toast = Toast.makeText(getActivity(),
                "Pantalla de inicio!", Toast.LENGTH_SHORT);
        toast.show();

        return favouriteView;
    }

    public interface OnClickHowTo{
        public void onRecipeSelected(View view, int position);
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

