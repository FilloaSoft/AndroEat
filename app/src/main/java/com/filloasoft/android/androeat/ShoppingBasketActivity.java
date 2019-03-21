package com.filloasoft.android.androeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingBasketActivity extends AppCompatActivity {

    private TextView mTextMessage;
    ListView list;
    Toast toast;

    String[] productName = {
            "Apple", "Milk",
            "Eggs",
    };

    String[] productDescription = {
            "Apple description", "Milk description",
            "Eggs description",
    };

    Integer[] productImage = {
            R.drawable.apples, R.drawable.milk,
            R.drawable.eggs
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_basket);

        ShoppingBasketListAdapter adapter = new ShoppingBasketListAdapter(this, productName, productDescription, productImage);
        list = (ListView) findViewById(R.id.basketList);
        list.setAdapter(adapter);

        mTextMessage = (TextView) findViewById(R.id.message);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_basket);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toast = Toast.makeText(getApplicationContext(),
                "Pantalla de la cesta de la compra!", Toast.LENGTH_SHORT);

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
                    Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(homeIntent);
                    return true;
                case R.id.navigation_basket:
                    return true;
                case R.id.navigation_fav:
                    toast = Toast.makeText(getApplicationContext(),
                                    "Navegando hacia la pantalla de recetas favoritas...", Toast.LENGTH_SHORT);

                    toast.show();
                    Intent favIntent = new Intent(getApplicationContext(), ShoppingBasketActivity.class);
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