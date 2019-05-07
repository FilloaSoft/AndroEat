package com.filloasoft.android.androeat.recipe;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.Recipe;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    OnClickHowTo mCallback;
    private TextView mTextMessage;
    GridView grid;
    Toast toast;

    private List<Recipe> recipesList;

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

        Bundle bundle = getArguments();
        recipesList = bundle.getParcelableArrayList("list");

        HomeListAdapter adapter = new HomeListAdapter(getActivity(), recipesList);

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
