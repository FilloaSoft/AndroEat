package com.filloasoft.android.androeat.recipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.Recipe;
import com.filloasoft.android.androeat.product.ShoppingBasketListAdapter;

public class RecipeDetailsFragment extends Fragment {

    ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View recipeDetailsView = inflater.inflate(R.layout.fragment_recipe_details, null);
        Bundle bundle = getArguments();
        Recipe recipe = (Recipe) bundle.getSerializable("recipe");

        IngredientListAdapter adapter = new IngredientListAdapter(getActivity(), recipe.getRecipeIngredients());


        if (recipe.getCookingTimeMinutes()!=null) {
            TextView timeText = (TextView) recipeDetailsView.findViewById(R.id.recipeTime);
            timeText.setText(android.text.TextUtils.concat(recipe.getCookingTimeMinutes()," minutes"));
        }
        if (recipe.getRecipeDiets()!=null) {
            TextView alertText = (TextView) recipeDetailsView.findViewById(R.id.recipeAlerts);
            //imageView.setImageResource(productImage[position]);
        }
        if (recipe.getRecipeDiets()!=null) {
            TextView dietText = (TextView) recipeDetailsView.findViewById(R.id.recipeDiets);
            String diets = android.text.TextUtils.join(", ", recipe.getRecipeDiets());
            if (!diets.isEmpty()) {
                diets = diets.substring(0, 1).toUpperCase() + diets.substring(1);
            }
            dietText.setText(diets);
        }

        if (recipe.getRecipeURL()!=null){
            TextView url = (TextView) recipeDetailsView.findViewById(R.id.recipeLink);
            url.setText(recipe.getRecipeURL());
        }

        return recipeDetailsView;
    }

}