package com.filloasoft.android.androeat.recipe;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.Recipe;

import java.util.List;

public class HomeFragment extends Fragment {

    OnClickHowTo mCallback;
    OnReloadRecipe mCallbackReload;
    private TextView mTextMessage;
    GridView grid;
    Toast toast;

    private List<Recipe> recipesList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View homeView = inflater.inflate(R.layout.fragment_home, null);

        Bundle bundle = getArguments();
        recipesList = bundle.getParcelableArrayList("list");

        HomeListAdapter adapter = new HomeListAdapter(getActivity(), recipesList);

        grid = homeView.findViewById(R.id.home_grid);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               mCallback.onRecipeSelected(homeView, position);
            }
        });

        Button discoverButton = homeView.findViewById(R.id.discoverButton);

        discoverButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  mCallbackReload.onReloadRecipes();
              }
          }
        );

        mTextMessage = (TextView) homeView.findViewById(R.id.message);

        return homeView;
    }

    public interface OnClickHowTo{
        void onRecipeSelected(View view, int position);
    }

    public interface OnReloadRecipe{
        void onReloadRecipes();
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            mCallback = (OnClickHowTo) activity;
            mCallbackReload = (OnReloadRecipe) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }

    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        try{
//            if (context instanceof OnClickHowTo) {
//                mCallback = (OnClickHowTo) context;
//            }
//        }catch (ClassCastException e){
//            throw new ClassCastException(context.toString());
//        }
//
//    }

}
