package com.filloasoft.android.androeat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.filloasoft.android.androeat.model.Recipe;
import com.filloasoft.android.androeat.product.ShoppingBasketFragment;
import com.filloasoft.android.androeat.recipe.FavouriteFragment;
import com.filloasoft.android.androeat.recipe.HomeFragment;
import com.filloasoft.android.androeat.recipe.RecipeFragment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.filloasoft.android.androeat.model.Product;
import com.filloasoft.android.androeat.model.User;
import com.filloasoft.android.androeat.product.CameraActivity;
import com.filloasoft.android.androeat.product.ScannerActivity;
import com.filloasoft.android.androeat.product.ShoppingBasketFragment;
import com.filloasoft.android.androeat.recipe.FavouriteFragment;
import com.filloasoft.android.androeat.recipe.HomeFragment;
import com.filloasoft.android.androeat.recipe.HowToFragment;
import com.filloasoft.android.androeat.sql.DatabaseHelper;
import com.filloasoft.android.androeat.user.LoginFragment;
import com.filloasoft.android.androeat.user.ProfileFragment;
import com.filloasoft.android.androeat.user.SignupFragment;
import com.filloasoft.android.androeat.utilities.AuthUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, HomeFragment.OnClickHowTo, FavouriteFragment.OnClickHowTo{

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private RecipeTask mRecipeTask = null;
    private RecipesTask mRecipesTask = null;

    private TextView mTextMessage;
    private Toast toast;
    private DatabaseHelper databaseHelper;

    private FirebaseAuth mAuth;

    private static final String TAG = "MainActivity";
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private User usuario;
    private static final int REQUEST_CODE = 123;
//    ShoppingBasketFragment shoppingBasketFragment = new ShoppingBasketFragment();
    private List<Recipe> recipesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTextMessage = (TextView) findViewById(R.id.message);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        databaseHelper = new DatabaseHelper(this);
        usuario = new User();

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("555057426274-57ciso932745dfc5dsedcftfvnc46qp9.apps.googleusercontent.com")
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mAuth = FirebaseAuth.getInstance();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(myToolbar);

        //loading the default fragment
        showProgress(true);
        mRecipesTask = new RecipesTask(123L);
        mRecipesTask.execute((Void) null);
        //loadFragment(new HomeFragment(), true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        Bundle bundle = null;
        switch (item.getItemId()) {
            case R.id.navigation_recipe:
                if(recipesList==null){
                    showProgress(true);
                    mRecipesTask = new RecipesTask(123L);
                    mRecipesTask.execute((Void) null);
                    return true;
                }
                else{
                    Bundle args = new Bundle();
                    ArrayList<Recipe> casted = new ArrayList<Recipe>(recipesList);
                    args.putParcelableArrayList("list", casted);

                    fragment = new HomeFragment();
                    fragment.setArguments(args);
                }
                break;
            case R.id.navigation_basket:
                fragment = new ShoppingBasketFragment();
                break;
            case R.id.navigation_fav:
                fragment = new FavouriteFragment();
                break;
            case R.id.navigation_profile:
                SharedPreferences preferences = this.getSharedPreferences(
                        "com.filloasoft.android.androeat", Context.MODE_PRIVATE);
                FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
                databaseHelper = new DatabaseHelper(this);
                fragment = AuthUtils.getLoginOrProfileFragment(preferences, firebaseUser, databaseHelper);
        }
        return loadFragment(fragment, false);
    }

    public boolean loadFragment(Fragment fragment, boolean firstFragment) {
        //switching fragment
        //comprobar que si es homeFragment se hayan cargado las recetas primero


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

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK){
            String barcode = data.getStringExtra("barcode");

                ShoppingBasketFragment apiCall = new ShoppingBasketFragment();
                apiCall.apiCall.execute(barcode);

            }
        }
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                updateUI(null);
            }

        }
    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        showProgressDialog();

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //Show message: AUTHENTICATION FAILED
                            updateUI(null);
                        }
                        hideProgressDialog();
                    }
                });
    }

    public void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void signOut() {
        // Firebase sign out
        mAuth.signOut();

        toast = Toast.makeText(this,
                "Logging out...", Toast.LENGTH_SHORT);
        // Do something in response to button
        toast.show();

        SharedPreferences preferences = this.getSharedPreferences(
                "com.filloasoft.android.androeat", Context.MODE_PRIVATE);

        preferences.edit().putString("email", null).apply();
        preferences.edit().putString("password", null).apply();


        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
        Intent intent = new Intent(this.getBaseContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.getBaseContext().startActivity(intent);
    }

    @VisibleForTesting
    public ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("loading");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void hideKeyboard(View view) {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }

    private void revokeAccess() {
        // Firebase sign out
        mAuth.signOut();

        // Google revoke access
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        Fragment fragment = null;
        String email = "";
        String name = "";
        if (user != null) {
            FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
            if(firebaseUser!=null) {
                email = firebaseUser.getEmail().trim();
                name = firebaseUser.getDisplayName().trim();
            }
                if (!databaseHelper.checkUser(email)) {
                    usuario.setName(name);
                    usuario.setEmail(email);

                    databaseHelper.addUser(usuario);

                    SharedPreferences preferences = this.getSharedPreferences(
                            "com.filloasoft.android.androeat", Context.MODE_PRIVATE);
                    //Save it
                    preferences.edit().putString("email", user.getEmail()).apply();
                    // show success message that record saved successfully
                    toast = Toast.makeText(this,"Registered succesfully!", Toast.LENGTH_SHORT);
                    toast.show();

                   // HomeFragment homeFragment = new HomeFragment();
                    //loadFragment(homeFragment,false);
                    if(recipesList==null){
                        showProgress(true);
                        mRecipesTask = new RecipesTask(123L);
                        mRecipesTask.execute((Void) null);
                    }
                    else{
                        Bundle args = new Bundle();
                        ArrayList<Recipe> casted = new ArrayList<Recipe>(recipesList);
                        args.putParcelableArrayList("list", casted);

                        HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setArguments(args);
                        loadFragment(homeFragment,false);
                    }
                } else {
                    SharedPreferences preferences = this.getSharedPreferences(
                            "com.filloasoft.android.androeat", Context.MODE_PRIVATE);
                    /*if (email!=null) {
                        databaseHelper = new DatabaseHelper(this);
                        if (databaseHelper.checkUser(email)) {
                            fragment = new ProfileFragment();
                            Bundle args = new Bundle();
                            args.putString("email", email);
                            fragment.setArguments(args);
                            loadFragment(fragment, false);
                        }
                    }*/
                }
        } else {
           // fragment = new HomeFragment();
           // loadFragment(fragment,false);
            if(recipesList==null){
                showProgress(true);
                mRecipesTask = new RecipesTask(123L);
                mRecipesTask.execute((Void) null);
            }
            else{
                Bundle args = new Bundle();
                ArrayList<Recipe> casted = new ArrayList<Recipe>(recipesList);
                args.putParcelableArrayList("list", casted);

                fragment = new HomeFragment();
                fragment.setArguments(args);
                loadFragment(fragment,false);
            }
        }
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
        Intent scanIntent = new Intent(getApplicationContext(), ScannerActivity.class);
        startActivityForResult(scanIntent, REQUEST_CODE);
    }

    public void takePhoto(View view){
        Intent cameraIntent = new Intent(getApplicationContext(), CameraActivity.class);
        startActivity(cameraIntent);
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

    public void logout(View signupView) {
        SharedPreferences preferences = this.getSharedPreferences(
                "com.filloasoft.android.androeat", Context.MODE_PRIVATE);

        //Remove credentians from shared preferences
        preferences.edit().putString("email", null).apply();
        preferences.edit().putString("password", null).apply();

        loadFragment(new LoginFragment(), false);
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


    public class RecipesTask extends AsyncTask<Void, Void, List<Recipe>> {

        private final Long mId;
        private List<Recipe> recipes;

        RecipesTask(Long id) {
            mId = id;
        }

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected List<Recipe> doInBackground(Void... params) {
            try {
                final String url;
                url = "http://androeat.dynu.net/recipe/random?tags=vegetarian";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<List<Recipe>> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Recipe>>() {
                        });
                recipes = response.getBody();
                URL newurl = null;
                for (Recipe r : recipes) {
                    try {
                        newurl = new URL(r.getRecipeImage());
                        Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                        r.setRecipeBitmapImage(mIcon_val);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //recipes.add(r);
                }
                recipesList=recipes;
                return recipes;
            } catch (Exception e) {
                Log.e("Error getting recipe -", e.getMessage(), e);
            }
            return null;
        }


        @Override
        protected void onPostExecute(final List<Recipe> recipes) {
            mRecipesTask = null;
            showProgress(false);
            Bundle args = new Bundle();
            ArrayList<Recipe> casted = new ArrayList<Recipe>(recipes);
            args.putParcelableArrayList("list", casted);

             HomeFragment homeFragment = new HomeFragment();
             homeFragment.setArguments(args);
             loadFragment(homeFragment,true);
        }

        @Override
        protected void onCancelled() {

        }
    }

}
