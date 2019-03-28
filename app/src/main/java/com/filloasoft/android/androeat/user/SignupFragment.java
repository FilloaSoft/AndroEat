package com.filloasoft.android.androeat.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;

public class SignupFragment extends Fragment {

    Toast toast;
    public EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View signupView = inflater.inflate(R.layout.fragment_signup, null);
        toast = Toast.makeText(getActivity(),
                "Pantalla de registro!", Toast.LENGTH_SHORT);
        toast.show();

        return signupView;
    }
}
