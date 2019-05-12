package com.filloasoft.android.androeat.recipe;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.Recipe;
import com.filloasoft.android.androeat.model.RecipeIngredient;

import java.util.List;

public class HomeListAdapter extends ArrayAdapter<Recipe> {

        private final Activity context;

        public HomeListAdapter(Activity context, List<Recipe> recipes) {
            super(context, R.layout.product_list_row, recipes);
            this.context=context;
        }

        public View getView(int position, View view, ViewGroup parent) {
            // Get the data item for this position
            Recipe recipe = getItem(position);

            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.recipe_list_row, null,true);

            TextView titleText = (TextView) rowView.findViewById(R.id.recipeName);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.recipeImage);
            TextView subtitleText = (TextView) rowView.findViewById(R.id.recipeDescription);

           // titleText.setText(recipeName[position]);

            String recipeName = recipe.getRecipeName();

            if (recipeName!=null) {
                recipeName = recipeName.substring(0, 1).toUpperCase() + recipeName.substring(1);
                titleText.setText(recipeName);
            }
            if (recipe.getRecipeImage()!=null) {
                //imageView.setImageResource(recipe.getRecipeImage());
                imageView.setImageBitmap(recipe.getRecipeBitmapImage());

            }


            subtitleText.setText("mock");

            return rowView;
        };
}

