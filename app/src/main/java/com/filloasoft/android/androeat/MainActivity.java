package com.filloasoft.android.androeat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.model.Recipe;
import com.filloasoft.android.androeat.product.ScannerFragment;
import com.filloasoft.android.androeat.product.ShoppingBasketFragment;
import com.filloasoft.android.androeat.recipe.FavouriteFragment;
import com.filloasoft.android.androeat.recipe.HomeFragment;
import com.filloasoft.android.androeat.recipe.RecipeFragment;
import com.filloasoft.android.androeat.user.LoginFragment;
import com.filloasoft.android.androeat.user.ProfileFragment;
import com.filloasoft.android.androeat.user.SignupFragment;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, HomeFragment.OnClickHowTo, FavouriteFragment.OnClickHowTo{

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private RecipeTask mRecipeTask = null;

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
        RecipeFragment recipeFragment = (RecipeFragment) getSupportFragmentManager().findFragmentById(R.id.recipe_details);
        if (recipeFragment != null){
            //Manage two pane layout
        }
        else{
            showProgress(true);
            mRecipeTask = new RecipeTask(123L);
            mRecipeTask.execute((Void) null);
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


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        final View mProgressView = findViewById(R.id.progress);
        final View mContainerView = findViewById(R.id.fragment_container);

        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mContainerView.setVisibility(show ? View.GONE : View.VISIBLE);
        mContainerView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mContainerView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }


    public class RecipeTask extends AsyncTask<Void, Void, Recipe> {

        private final Long mId;
        private Recipe recipe;

        RecipeTask(Long id) {
            mId = id;
        }

        @Override
        protected Recipe doInBackground(Void... params) {
            try {
                final String url;
                //url = getResources().getString(R.string.recipe_url)+mId;
                url = "http://androeat.dynu.net/recipe/262682";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                recipe = restTemplate.getForObject(url, Recipe.class);
                URL newurl = null;
                try {
                    newurl = new URL(recipe.getRecipeImage());
                    Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                    recipe.setRecipeBitmapImage(mIcon_val);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return recipe;
            } catch (Exception e) {
                Log.e("Error getting recipe -", e.getMessage(), e);
            }
            return null;
        }


        @Override
        protected void onPostExecute(final Recipe recipe) {
            mRecipeTask = null;
            showProgress(false);
            Bundle args = new Bundle();
            args.putSerializable("recipe", recipe);
            RecipeFragment newRecipeFragment = new RecipeFragment();
            newRecipeFragment.setArguments(args);
            loadFragment(newRecipeFragment, false);
        }

        @Override
        protected void onCancelled() {

        }
    }
}
