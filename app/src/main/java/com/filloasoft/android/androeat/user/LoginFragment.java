package com.filloasoft.android.androeat.user;

import android.content.Intent;
import android.os.Bundle;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.helpers.InputValidation;
import com.filloasoft.android.androeat.recipe.HomeFragment;
import com.filloasoft.android.androeat.sql.DatabaseHelper;

public class LoginFragment extends Fragment implements View.OnClickListener {

    Toast toast;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatButton appCompatButtonRegister;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View loginView = inflater.inflate(R.layout.fragment_login, null);

        toast = Toast.makeText(getActivity(),
                "Pantalla de login!", Toast.LENGTH_SHORT);

        toast.show();

        BottomNavigationView navigation = loginView.findViewById(R.id.navigation);

        textInputLayoutEmail = (TextInputLayout) loginView.findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) loginView.findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) loginView.findViewById(R.id.email_text);
        textInputEditTextPassword = (TextInputEditText) loginView.findViewById(R.id.password_text);
        appCompatButtonLogin = (AppCompatButton) loginView.findViewById(R.id.button);
        appCompatButtonRegister = (AppCompatButton) loginView.findViewById(R.id.button2);

        appCompatButtonLogin.setOnClickListener(this);
        appCompatButtonRegister.setOnClickListener(this);

        databaseHelper = new DatabaseHelper(getActivity());
        inputValidation = new InputValidation(getActivity());

        return loginView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                verifyFromSQLite();
                //TODO: falta que cuando el usuario se loguee correctamente se lance un mensaje de éxito
                //Antes de acceder a HomeFragment
                HomeFragment homeFragment = new HomeFragment();
                loadFragment(homeFragment,false);
                break;
            case R.id.button2:
                // Navigate to SignupFragment
                SignupFragment newSignupFragment = new SignupFragment();
                loadFragment(newSignupFragment, false);
                break;
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

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {
        //TODO: falta que cuando el usuario se loguee correctamente se lance un mensaje de éxito
            toast = Toast.makeText(getActivity(),"Logged in succesfully!", Toast.LENGTH_SHORT);
            toast.show();
            emptyInputEditText();
            //Access to Home Fragment (inside the case)
        } else {
            toast = Toast.makeText(getActivity(),
                    "Credenciales inválidas", Toast.LENGTH_SHORT);
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
