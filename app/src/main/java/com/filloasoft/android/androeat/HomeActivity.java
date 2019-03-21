package com.filloasoft.android.androeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        HomeListAdapter adapter = new HomeListAdapter(this, recipeName, recipeDescription, recipeImage);
        grid = findViewById(R.id.home_grid);
        grid.setAdapter(adapter);

        mTextMessage = (TextView) findViewById(R.id.message);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toast = Toast.makeText(getApplicationContext(),
                "Pantalla de inicio!", Toast.LENGTH_SHORT);

        toast.show();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_recipe:
                    toast = Toast.makeText(getApplicationContext(),
                            "Navegando hacia la pantalla principal...", Toast.LENGTH_SHORT);

                    toast.show();
                    Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(homeIntent);
                    return true;
                case R.id.navigation_basket:
                    toast = Toast.makeText(getApplicationContext(),
                            "Navegando hacia la pantalla de cesta de la compra...", Toast.LENGTH_SHORT);

                    toast.show();
                    Intent basketIntent = new Intent(getApplicationContext(), ShoppingBasketActivity.class);
                    startActivity(basketIntent);
                    return true;
                case R.id.navigation_fav:
                    toast = Toast.makeText(getApplicationContext(),
                            "Navegando hacia la pantalla de recetas favoritas...", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent favIntent = new Intent(getApplicationContext(), FavouriteRecipesActivity.class);
                    startActivity(favIntent);
                    return true;
                case R.id.navigation_profile:
                    toast = Toast.makeText(getApplicationContext(),
                            "Navegando hacia la pantalla de login...", Toast.LENGTH_SHORT);

                    toast.show();
                    Intent profileIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(profileIntent);
                    return true;
            }
            return false;
        }
    };

    public void scan(View view){
        Intent scannerIntent = new Intent(this, ScannerActivity.class);
        startActivity(scannerIntent);
    }

    public void findRecipes(View view) {

        toast = Toast.makeText(getApplicationContext(),
                "Buscando recetas...", Toast.LENGTH_SHORT);
        // Do something in response to button
        toast.show();
    }

    public void openAddDialog(View view) {

        toast = Toast.makeText(getApplicationContext(),
                "Abriendo diálogo de adición de productos...", Toast.LENGTH_SHORT);
        // Do something in response to button
        toast.show();
    }

}
