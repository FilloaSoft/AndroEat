package com.filloasoft.android.androeat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class EULAActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eula);
    }

    public void confirmEULA(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
