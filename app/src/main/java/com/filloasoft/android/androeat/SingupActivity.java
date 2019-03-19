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

public class SingupActivity extends AppCompatActivity {
    Toast toast;
    public EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singup);

        toast = Toast.makeText(getApplicationContext(),
                "Pantalla de registro!", Toast.LENGTH_SHORT);

        toast.show();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_profile);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    /**
     * Called when the user taps the Send button
     */

    public void singUp(View view) {
        EditText usernameText = (EditText) findViewById(R.id.username_singup);
        EditText emailText = (EditText) findViewById(R.id.email_text_singup);
        EditText passwordText = (EditText) findViewById(R.id.password_text_singup);
        EditText passwordTextConfirmation = (EditText) findViewById(R.id.password_text_singup2);

        String username = usernameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String passwordConfirmation = passwordTextConfirmation.getText().toString();


        toast = Toast.makeText(getApplicationContext(),
                username + ' ' + email + ' ' + password + ' ' + passwordConfirmation, Toast.LENGTH_SHORT);
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
                    return true;
                case R.id.navigation_fav:
                    toast = Toast.makeText(getApplicationContext(),
                            "Navegando hacia la pantalla de recetas favoritas...", Toast.LENGTH_SHORT);

                    toast.show();
                    Intent favIntent = new Intent(getApplicationContext(), ShoppingBasketActivity.class);
                    startActivity(favIntent);
                    return true;
                case R.id.navigation_basket:
                    toast = Toast.makeText(getApplicationContext(),
                            "Navegando hacia la pantalla de cesta...", Toast.LENGTH_SHORT);

                    toast.show();
                    Intent profileIntent = new Intent(getApplicationContext(), ShoppingBasketActivity.class);
                    startActivity(profileIntent);
                    return true;
            }
            return false;
        }
    };
}
