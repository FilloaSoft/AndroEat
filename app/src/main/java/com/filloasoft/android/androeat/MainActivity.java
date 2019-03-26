package com.filloasoft.android.androeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.product.ScannerFragment;
import com.filloasoft.android.androeat.product.ShoppingBasketFragment;
import com.filloasoft.android.androeat.recipe.FavouriteFragment;
import com.filloasoft.android.androeat.recipe.HomeFragment;
import com.filloasoft.android.androeat.recipe.HowToFragment;
import com.filloasoft.android.androeat.user.LoginFragment;
import com.filloasoft.android.androeat.user.ProfileFragment;
import com.filloasoft.android.androeat.user.SignupFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, HomeFragment.OnClickHowTo, FavouriteFragment.OnClickHowTo{

    private TextView mTextMessage;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);

        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        //loading the default fragment
        loadFragment(new HomeFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_recipe:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_basket:
                fragment = new ShoppingBasketFragment();
                break;
            case R.id.navigation_fav:
                fragment = new FavouriteFragment();
                break;
            case R.id.navigation_profile:
                fragment = new LoginFragment();
                break;
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }
        return false;
    }

    public void onRecipeSelected(View view){
        HowToFragment howToFragment = (HowToFragment) getSupportFragmentManager().findFragmentById(R.id.howto_fragment);
        if (howToFragment != null){

        }
        else{
            HowToFragment newHowToFragment = new HowToFragment();
            loadFragment(newHowToFragment);
        }
    }

    public void scan(View view){
        ScannerFragment scannerFragment = (ScannerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_scanner);
        if (scannerFragment != null){

        }
        else{
            ScannerFragment newScannerFragment = new ScannerFragment();
            loadFragment(newScannerFragment);
        }
    }

    public void findRecipes(View view) {

        toast = Toast.makeText(this,
                "Buscando recetas...", Toast.LENGTH_SHORT);
        // Do something in response to button
        toast.show();
    }

    public void openAddDialog(View view) {

        toast = Toast.makeText(this,
                "Abriendo diálogo de adición de productos...", Toast.LENGTH_SHORT);
        // Do something in response to button
        toast.show();
    }


    public void login(View view){
        ProfileFragment profileFragment = (ProfileFragment) getSupportFragmentManager().findFragmentById(R.id.profile_fragment);
        if (profileFragment != null){

        }
        else{
            ProfileFragment newProfileFragment = new ProfileFragment();
            loadFragment(newProfileFragment);
        }
    }

    /** Called when the user taps the Send button */
    public void register(View view) {
        SignupFragment signupFragment = (SignupFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_signup);
        if (signupFragment != null){

        }
        else{
            SignupFragment newSignupFragment = new SignupFragment();
            loadFragment(newSignupFragment);
        }
    }


    /**
     * Called when the user taps the Send button
     */

    public void signUp(View view) {
        EditText usernameText = (EditText) view.findViewById(R.id.username_singup);
        EditText emailText = (EditText) view.findViewById(R.id.email_text_singup);
        EditText passwordText = (EditText) view.findViewById(R.id.password_text_singup);
        EditText passwordTextConfirmation = (EditText) view.findViewById(R.id.password_text_singup2);

        String username = usernameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String passwordConfirmation = passwordTextConfirmation.getText().toString();


        toast = Toast.makeText(this,
                username + ' ' + email + ' ' + password + ' ' + passwordConfirmation, Toast.LENGTH_SHORT);
        toast.show();

    }
}
