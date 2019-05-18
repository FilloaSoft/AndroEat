package com.filloasoft.android.androeat.user;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.MainActivity;
import com.filloasoft.android.androeat.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment implements View.OnClickListener{

    private TextView mTextMessage;
    private Toast toast;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View profileView = inflater.inflate(R.layout.fragment_profile, null);
        Bundle args = getArguments();
        String email = args.getString("email", "");
        TextView textView = profileView.findViewById(R.id.userEmail);
        textView.setText(email);

        String username = args.getString("username", "");
        TextView usernameTextView = profileView.findViewById(R.id.userName);
        usernameTextView.setText(username);

        //Retrieve user profile image from storage and load into imageview
        ImageView profileImage = profileView.findViewById(R.id.profile_image);
        ContextWrapper cw = new ContextWrapper(getActivity());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File myImageFile = new File(directory, "my_image.jpeg");
        Picasso.with(getContext()).load(myImageFile).into(profileImage);

        updateUserPreferences(profileView);

        final SharedPreferences preferences = getContext().getSharedPreferences(
                "com.filloasoft.android.androeat", Context.MODE_PRIVATE);

        final SwitchCompat veganSwitch = (SwitchCompat) profileView.findViewById(R.id.veganSwitch);
        veganSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (veganSwitch.isChecked()){
                    preferences.edit().putString("vegan", "vegan").apply();
                }
                else{
                    preferences.edit().putString("vegan", null).apply();
                }
            }
        });

        final SwitchCompat vegetarianSwitch = (SwitchCompat) profileView.findViewById(R.id.vegetarianSwitch);
        vegetarianSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (vegetarianSwitch.isChecked()){
                    preferences.edit().putString("vegetarian", "vegetarian").apply();
                }
                else{
                    preferences.edit().putString("vegetarian", null).apply();
                }
            }
        });

        final SwitchCompat glutenFreeSwitch = (SwitchCompat) profileView.findViewById(R.id.glutenfreeSwitch);
        glutenFreeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (glutenFreeSwitch.isChecked()){
                    preferences.edit().putString("glutenfree", "gluten+free").apply();
                }
                else{
                    preferences.edit().putString("glutenfree", null).apply();
                }
            }
        });

        final SwitchCompat dairyFreeSwitch = (SwitchCompat) profileView.findViewById(R.id.dairyfreeSwitch);
        dairyFreeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (dairyFreeSwitch.isChecked()){
                    preferences.edit().putString("dairyfree", "dairy+free").apply();
                }
                else{
                    preferences.edit().putString("dairyfree", null).apply();
                }
            }
        });

        final SwitchCompat sustainableSwitch = (SwitchCompat) profileView.findViewById(R.id.sustainableSwitch);
        sustainableSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (sustainableSwitch.isChecked()){
                    preferences.edit().putString("sustainable", "sustainable").apply();
                }
                else{
                    preferences.edit().putString("sustainable", null).apply();
                }
            }
        });


        AppCompatButton appCompatButtonLogin = (AppCompatButton) profileView.findViewById(R.id.sign_out_button);

        appCompatButtonLogin.setOnClickListener(this);

        return profileView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_out_button:
                ((MainActivity)getActivity()).signOut();
                // Navigate to LoginFragment
                LoginFragment newLoginFragment = new LoginFragment();
                loadFragment(newLoginFragment, false);
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

    private void updateUserPreferences(View profileView) {
        SharedPreferences preferences = getActivity().getSharedPreferences(
                "com.filloasoft.android.androeat", Context.MODE_PRIVATE);

        List<String> preferencesList = new ArrayList<>();

        String vegan = preferences.getString("vegan", "");
        if (!vegan.isEmpty()) {
            final SwitchCompat veganSwitch = (SwitchCompat) profileView.findViewById(R.id.veganSwitch);
            veganSwitch.setChecked(true);
        }
        String vegetarian = preferences.getString("vegetarian", "");
        if (!vegetarian.isEmpty()) {
            final SwitchCompat vegetarianSwitch = (SwitchCompat) profileView.findViewById(R.id.vegetarianSwitch);
            vegetarianSwitch.setChecked(true);
        }
        String glutenFree = preferences.getString("glutenfree", "");
        if (!glutenFree.isEmpty()) {
            final SwitchCompat glutenFreeSwitch = (SwitchCompat) profileView.findViewById(R.id.glutenfreeSwitch);
            glutenFreeSwitch.setChecked(true);
        }
        String dairyFree = preferences.getString("dairyfree", "");
        if (!dairyFree.isEmpty()) {
            final SwitchCompat dairyFreeSwitch = (SwitchCompat) profileView.findViewById(R.id.dairyfreeSwitch);
            dairyFreeSwitch.setChecked(true);
        }
        String sustainable = preferences.getString("sustainable", "");
        if (!sustainable.isEmpty()) {
            final SwitchCompat sustainableSwitch = (SwitchCompat) profileView.findViewById(R.id.sustainableSwitch);
            sustainableSwitch.setChecked(true);
        }
    }

}
