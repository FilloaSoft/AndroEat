package com.filloasoft.android.androeat.recipe;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.filloasoft.android.androeat.R;

import java.util.List;

public class HowToListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> steps;

    public HowToListAdapter(Activity context, List<String> steps) {
        super(context, R.layout.steps_list_row, steps);
        this.context=context;
        this.steps=steps;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.steps_list_row, null,true);

        TextView stepNumber = (TextView) rowView.findViewById(R.id.stepNumber);
        TextView stepContent = (TextView) rowView.findViewById(R.id.stepContent);

        stepNumber.setText(android.text.TextUtils.concat("Step ",String.valueOf(position+1)));
        stepContent.setText(steps.get(position));

        return rowView;
    };
}
