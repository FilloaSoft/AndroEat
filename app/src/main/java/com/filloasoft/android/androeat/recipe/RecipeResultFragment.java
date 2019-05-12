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
import com.filloasoft.android.androeat.model.Recipe;

import java.util.List;

public class RecipeResultFragment  extends Fragment {

    HomeFragment.OnClickHowTo mCallback;
    private TextView mTextMessage;
    GridView grid;
    Toast toast;

    private List<Recipe> recipesList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View resultView = inflater.inflate(R.layout.fragment_recipe_result, null);

        Bundle bundle = getArguments();
        recipesList = bundle.getParcelableArrayList("list");

        HomeListAdapter adapter = new HomeListAdapter(getActivity(), recipesList);

        grid = resultView.findViewById(R.id.result_grid);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.onRecipeSelected(resultView, position);
            }
        });

        return resultView;
    }

    public interface OnClickHowTo{
        public void onRecipeSelected(View view, int position);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            mCallback = (HomeFragment.OnClickHowTo) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }

    }



}