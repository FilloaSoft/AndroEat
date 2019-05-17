package com.filloasoft.android.androeat.recipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.Recipe;
import com.filloasoft.android.androeat.product.ShoppingBasketListAdapter;

import java.lang.reflect.Array;

public class HowToFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View howToView = inflater.inflate(R.layout.fragment_recipe_steps, null);
        Bundle bundle = getArguments();
        Recipe recipe = (Recipe) bundle.getSerializable("recipe");
        if (recipe.getRecipeInstructions()!=null && !recipe.getRecipeInstructions().isEmpty()) {
            HowToListAdapter adapter = new HowToListAdapter(getActivity(), recipe.getRecipeInstructions());
            ListView stepsList = (ListView) howToView.findViewById(R.id.stepsList);
            stepsList.setAdapter(adapter);
        }
        return  howToView;
    }

}