package com.filloasoft.android.androeat.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;

public class ScannerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View scannerView = inflater.inflate(R.layout.fragment_scanner, null);

        Toast toast = Toast.makeText(getActivity(),
                "Pantalla de escaneado!", Toast.LENGTH_SHORT);

        toast.show();

        return scannerView;
    }
}
