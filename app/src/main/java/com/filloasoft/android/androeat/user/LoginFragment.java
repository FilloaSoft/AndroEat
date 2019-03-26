package com.filloasoft.android.androeat.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;

public class LoginFragment extends Fragment {

    Toast toast;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View loginView = inflater.inflate(R.layout.fragment_login, null);

        toast = Toast.makeText(getActivity(),
                "Pantalla de login!", Toast.LENGTH_SHORT);

        toast.show();

        BottomNavigationView navigation = loginView.findViewById(R.id.navigation);
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        return loginView;
    }
}
