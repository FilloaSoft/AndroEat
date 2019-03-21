package com.filloasoft.android.androeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_profile);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
}
