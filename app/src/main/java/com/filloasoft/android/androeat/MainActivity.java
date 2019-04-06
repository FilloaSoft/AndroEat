package com.filloasoft.android.androeat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.filloasoft.android.androeat.recipe.RecipeDetailsFragment;
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

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(myToolbar);

        //loading the default fragment
        loadFragment(new HomeFragment(), true);
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
                Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
                myToolbar.setTitleTextColor(Color.YELLOW);
                fragment = new LoginFragment();
                break;
        }
        return loadFragment(fragment, false);
    }

    private boolean loadFragment(Fragment fragment, boolean firstFragment) {
        //switching fragment
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, fragment);
            if (!firstFragment) {
                transaction.addToBackStack(null);
            }
            transaction.commit();
            return true;
        }
        return false;
    }

    public void onRecipeSelected(View view){
        RecipeDetailsFragment recipeDetailsFragment = (RecipeDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.recipe_details);
        if (recipeDetailsFragment != null){
            //Manage two pane layout
        }
        else{
            RecipeDetailsFragment newRecipeDetailsFragment = new RecipeDetailsFragment();
            loadFragment(newRecipeDetailsFragment, false);
        }
    }

    public void scan(View view){
        ScannerFragment scannerFragment = (ScannerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_scanner);
        if (scannerFragment != null){
            //Manage two pane layout
        }
        else{
            ScannerFragment newScannerFragment = new ScannerFragment();
            loadFragment(newScannerFragment, false);
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
            //Manage two pane layout
        }
        else{
            ProfileFragment newProfileFragment = new ProfileFragment();
            loadFragment(newProfileFragment, false);
        }
    }

    /** Called when the user taps the Send button */
    public void register(View view) {
        SignupFragment signupFragment = (SignupFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_signup);
        if (signupFragment != null){
            //Manage two pane layout
        }
        else{
            SignupFragment newSignupFragment = new SignupFragment();
            loadFragment(newSignupFragment, false);
        }
    }



    /**
     * Called when the user taps the Send button
     */

    public void signUp(View view) {
        toast = Toast.makeText(this,
                "Registered button clicked!", Toast.LENGTH_SHORT);
        toast.show();
    }
}
