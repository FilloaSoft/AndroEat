package com.filloasoft.android.androeat.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;

public class ProfileFragment extends Fragment {

    private TextView mTextMessage;
    private Toast toast;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View profileView = inflater.inflate(R.layout.fragment_profile, null);
        return profileView;
    }
}
