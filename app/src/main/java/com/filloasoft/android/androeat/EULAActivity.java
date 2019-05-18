package com.filloasoft.android.androeat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class EULAActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = this.getSharedPreferences(
                "com.filloasoft.android.androeat", Context.MODE_PRIVATE);

        Boolean isEULAAccepted = preferences.getBoolean("eulaAccepted", false);

        if (isEULAAccepted){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else{
            setContentView(R.layout.activity_eula);
        }
    }

    public void confirmEULA(View view) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.filloasoft.android.androeat", Context.MODE_PRIVATE);

        preferences.edit().putBoolean("eulaAccepted", true).apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
