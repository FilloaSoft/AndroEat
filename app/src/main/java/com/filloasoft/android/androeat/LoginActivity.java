package com.filloasoft.android.androeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        toast = Toast.makeText(getApplicationContext(),
                "Pantalla de login!", Toast.LENGTH_SHORT);

        toast.show();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_profile);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

        /** Called when the user taps the Send button */
    public void login(View view) {

        EditText emailText = (EditText) findViewById(R.id.email_text);
        EditText passwordText = (EditText) findViewById(R.id.password_text);
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        toast = Toast.makeText(getApplicationContext(),
                email + ' ' + password, Toast.LENGTH_SHORT);
        // Do something in response to button
        toast.show();
    }

    /** Called when the user taps the Send button */
    public void register(View view) {
        Intent singupIntent = new Intent(getApplicationContext(), SingupActivity.class);
        startActivity(singupIntent);
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
