package com.filloasoft.android.androeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ScannerActivity extends AppCompatActivity {
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner);

        toast = Toast.makeText(getApplicationContext(),
                "Pantalla de escaneado!", Toast.LENGTH_SHORT);

        toast.show();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_fav);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    public void scan(View view) {

        toast = Toast.makeText(getApplicationContext(),
                "Escaneando codigo...", Toast.LENGTH_SHORT);
        // Do something in response to button
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
                case R.id.navigation_profile:
                    toast = Toast.makeText(getApplicationContext(),
                            "Navegando hacia la pantalla de login...", Toast.LENGTH_SHORT);

                    toast.show();
                    Intent profileIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(profileIntent);
                    return true;
                case R.id.navigation_fav:
                    return true;
                case R.id.navigation_basket:
                    toast = Toast.makeText(getApplicationContext(),
                            "Navegando hacia la pantalla de cesta...", Toast.LENGTH_SHORT);

                    toast.show();
                    Intent basketIntent = new Intent(getApplicationContext(), ShoppingBasketActivity.class);
                    startActivity(basketIntent);
                    return true;
            }
            return false;
        }
    };
}
