package com.filloasoft.android.androeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ScannerActivity extends AppCompatActivity {
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner);

        toast = Toast.makeText(getApplicationContext(),
                "Pantalla de escaneado!", Toast.LENGTH_SHORT);

        toast.show();

    }

    public void scan(View view) {

        toast = Toast.makeText(getApplicationContext(),
                "Escaneando codigo...", Toast.LENGTH_SHORT);
        // Do something in response to button
        toast.show();
    }

}
