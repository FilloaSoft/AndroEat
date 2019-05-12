package com.filloasoft.android.androeat.recipe;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.RecipeIngredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientListAdapter extends ArrayAdapter<RecipeIngredient> {

    public IngredientListAdapter(Activity context, List<RecipeIngredient> ingredients) {
        super(context, R.layout.product_list_row, ingredients);
    }

    public View getView(int position, View rowView, ViewGroup parent) {

        // Get the data item for this position
        RecipeIngredient ingredient = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (rowView == null) {
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.ingredient_list_row, parent, false);
        }

        TextView titleText = (TextView) rowView.findViewById(R.id.ingredientName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.ingredientImage);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.ingredientDescription);

        String ingredientName = ingredient.getProductName();
        Object genericName = ingredient.getGenericName();

        if (ingredientName!=null) {
            ingredientName = ingredientName.substring(0, 1).toUpperCase() + ingredientName.substring(1);
            titleText.setText(ingredientName);
        }
        if (ingredient.getImageUrl()!=null) {
            imageView.setImageBitmap(ingredient.getIngredientImage());
        }
        if (genericName!=null) {
            genericName = genericName.toString().substring(0, 1).toUpperCase() + genericName.toString().substring(1);
            subtitleText.setText(genericName.toString());
        }

        return rowView;
    };
}

