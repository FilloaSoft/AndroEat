package com.filloasoft.android.androeat.recipe;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.filloasoft.android.androeat.R;

public class HomeListAdapter extends ArrayAdapter<String> {

        private final Activity context;
        private final String[] recipeName;
        private final String[] recipeDescription;
        private final Integer[] recipeImage;

        public HomeListAdapter(Activity context, String[] recipeName,String[] recipeDescription, Integer[] recipeImage) {
            super(context, R.layout.product_list_row, recipeName);
            this.context=context;
            this.recipeName=recipeName;
            this.recipeDescription=recipeDescription;
            this.recipeImage=recipeImage;

        }

        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.recipe_list_row, null,true);

            TextView titleText = (TextView) rowView.findViewById(R.id.recipeName);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.recipeImage);
            TextView subtitleText = (TextView) rowView.findViewById(R.id.recipeDescription);

            titleText.setText(recipeName[position]);
            imageView.setImageResource(recipeImage[position]);
            subtitleText.setText(recipeDescription[position]);

            return rowView;
        };
}

