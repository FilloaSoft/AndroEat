package com.filloasoft.android.androeat.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.helpers.InputValidation;
import com.filloasoft.android.androeat.model.User;
import com.filloasoft.android.androeat.recipe.HomeFragment;
import com.filloasoft.android.androeat.sql.DatabaseHelper;

import java.util.List;

public class SignupFragment extends Fragment implements View.OnClickListener {

    Toast toast;
    public EditText editText;

    private AppCompatButton appCompatButtonRegister;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private EditText textInputEditTextName;
    private EditText textInputEditTextEmail;
    private EditText textInputEditTextPassword;
    private EditText textInputEditTextConfirmPassword;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View signupView = inflater.inflate(R.layout.fragment_signup, null);
        toast = Toast.makeText(getActivity(),
                "Pantalla de registro!", Toast.LENGTH_SHORT);
        toast.show();

        textInputLayoutName = (TextInputLayout) signupView.findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) signupView.findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) signupView.findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) signupView.findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextName = (EditText) signupView.findViewById(R.id.input_user_name);
        textInputEditTextEmail = (EditText) signupView.findViewById(R.id.input_email);
        textInputEditTextPassword = (EditText) signupView.findViewById(R.id.input_password);
        textInputEditTextConfirmPassword = (EditText) signupView.findViewById(R.id.input_repeat_password);
        appCompatButtonRegister = (AppCompatButton) signupView.findViewById(R.id.btn_register);

        appCompatButtonRegister.setOnClickListener(this);

        inputValidation = new InputValidation(getActivity());
        databaseHelper = new DatabaseHelper(getActivity());
        user = new User();

        return signupView;
    }

    @Override
    public void onClick(View signupView) {
        postDataToSQLite();
   }


    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, "Name is not filled")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, "Email is not filled")) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, "Email is not valid")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, "Password is not filled")) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, "Password does not match")) {
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);

            SharedPreferences preferences = getActivity().getSharedPreferences(
                    "com.filloasoft.android.androeat", Context.MODE_PRIVATE);

            //Save it
            preferences.edit().putString("email", user.getEmail()).apply();
            preferences.edit().putString("password", user.getPassword()).apply();

            // show success message that record saved successfully
            toast = Toast.makeText(getActivity(),"Registered succesfully!", Toast.LENGTH_SHORT);
            toast.show();
            emptyInputEditText();

            HomeFragment homeFragment = new HomeFragment();
            loadFragment(homeFragment,false);

        } else {
            // show error message that record already exists
            toast = Toast.makeText(getActivity(),"Mail already exists", Toast.LENGTH_SHORT);
            toast.show();
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
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }

}
