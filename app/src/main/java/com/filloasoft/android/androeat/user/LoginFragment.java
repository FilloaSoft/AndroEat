package com.filloasoft.android.androeat.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.MainActivity;
import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.helpers.InputValidation;
import com.filloasoft.android.androeat.recipe.HomeFragment;
import com.filloasoft.android.androeat.sql.DatabaseHelper;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment implements View.OnClickListener {

    Toast toast;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatButton appCompatButtonRegister;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private SignInButton signInButton;

    private EditText textInputEditTextEmail;
    private EditText textInputEditTextPassword;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View loginView = inflater.inflate(R.layout.fragment_login, null);

        /*toast = Toast.makeText(getActivity(),
                "Pantalla de login!", Toast.LENGTH_SHORT);

        toast.show();*/

        BottomNavigationView navigation = loginView.findViewById(R.id.navigation);

        textInputLayoutEmail = (TextInputLayout) loginView.findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) loginView.findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (EditText) loginView.findViewById(R.id.input_email);
        textInputEditTextPassword = (EditText) loginView.findViewById(R.id.input_password);
        //appCompatButtonLogin = (AppCompatButton) loginView.findViewById(R.id.btn_login);
        //appCompatButtonRegister = (AppCompatButton) loginView.findViewById(R.id.btn_signup);

        //appCompatButtonLogin.setOnClickListener(this);
        //appCompatButtonRegister.setOnClickListener(this);

        databaseHelper = new DatabaseHelper(getActivity());
        inputValidation = new InputValidation(getActivity());

        // Button listeners
        loginView.findViewById(R.id.sign_in_button).setOnClickListener(this);

        // Set the dimensions of the sign-in button.
        signInButton = loginView.findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setColorScheme(SignInButton.COLOR_LIGHT);

        return loginView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.btn_login:
                verifyFromSQLite();
                if (checkCredentialsFromSQLite()) {
                    //TODO: falta que cuando el usuario se loguee correctamente se lance un mensaje de éxito
                    //Antes de acceder a HomeFragment
                    HomeFragment homeFragment = new HomeFragment();
                    loadFragment(homeFragment, false);
                    break;
                }
                toast = Toast.makeText(getActivity(),
                        "Credenciales inválidas", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.btn_signup:
                // Navigate to SignupFragment
                SignupFragment newSignupFragment = new SignupFragment();
                loadFragment(newSignupFragment, false);
                break;*/
            case R.id.sign_in_button:
                ((MainActivity)getActivity()).signIn();
                break;
        }
    }

    private boolean checkCredentialsFromSQLite() {
        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {

            SharedPreferences preferences = getActivity().getSharedPreferences(
                    "com.filloasoft.android.androeat", Context.MODE_PRIVATE);

            //Save login credentials on shared preferences
            preferences.edit().putString("email", textInputEditTextEmail.getText().toString().trim()).apply();
            preferences.edit().putString("password", textInputEditTextPassword.getText().toString()).apply();
            //TODO: falta que cuando el usuario se loguee correctamente se lance un mensaje de éxito
            toast = Toast.makeText(getActivity(),"Logged in succesfully!", Toast.LENGTH_SHORT);
            toast.show();
            emptyInputEditText();
            //Access to Home Fragment (inside the case)
            return true;
        } else {
            return false;
        }
    }

    private boolean loadFragment(Fragment fragment, boolean firstFragment) {
        //switching fragment
        if (fragment != null) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, fragment);
            if (!firstFragment) {
                transaction.addToBackStack(null);
            }
            transaction.commit();
            return true;
        }
        return false;
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {

        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, "Email not filled")) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, "Email not valid")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, "Password not filled")) {
            return;
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
