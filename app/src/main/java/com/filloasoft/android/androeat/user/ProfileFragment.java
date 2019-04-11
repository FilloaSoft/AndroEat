package com.filloasoft.android.androeat.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.MainActivity;
import com.filloasoft.android.androeat.R;

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

}
