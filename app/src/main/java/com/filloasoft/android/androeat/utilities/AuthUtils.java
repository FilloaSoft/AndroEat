package com.filloasoft.android.androeat.utilities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.filloasoft.android.androeat.MainActivity;
import com.filloasoft.android.androeat.sql.DatabaseHelper;
import com.filloasoft.android.androeat.user.LoginFragment;
import com.filloasoft.android.androeat.user.ProfileFragment;
import com.google.firebase.auth.FirebaseUser;

public class AuthUtils {

    public static Fragment getLoginOrProfileFragment(SharedPreferences preferences, FirebaseUser firebaseUser, DatabaseHelper databaseHelper) {
        Fragment fragment;
        if (firebaseUser!=null){
            String firebaseEmail = firebaseUser.getEmail();
            String firebaseUserName = firebaseUser.getDisplayName();
            if (firebaseEmail!=null) {
                if (databaseHelper.checkUser(firebaseEmail)) {
                    fragment = new ProfileFragment();
                    Bundle args = new Bundle();
                    args.putString("email", firebaseEmail);
                    args.putString("username", firebaseUserName);
                    fragment.setArguments(args);
                    return fragment;
                }
            }
        }
        else {
            //Get saved user credentials
            String email = preferences.getString("email", null);
            String password = preferences.getString("password", null);
            if (email != null && password != null) {
                if (databaseHelper.checkUser(email, password)) {
                    fragment = new ProfileFragment();
                    Bundle args = new Bundle();
                    args.putString("email", email);
                    fragment.setArguments(args);
                    return fragment;
                }
            }
        }
        fragment = new LoginFragment();
        return fragment;
    }

}
