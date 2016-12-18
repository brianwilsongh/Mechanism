package com.wordpress.httpspandareaktor.mekanism;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.resource;

/**
 * Created by Brian on 12/11/2016.
 */

public class EquationAdapter extends ArrayAdapter<Equation> {
    public EquationAdapter(Context context, ArrayList<Equation> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //see if list item is null, otherwise inflate a view to populate
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.equation_item, parent, false);
        }

        // get and name the Event object at this position
        Equation thisEquation = getItem(position);

        //set the image
        ImageView equation = (ImageView) listItemView.findViewById(R.id.equation_image);
        equation.setImageResource(thisEquation.getImageResourceId());

        //set the text values
        TextView subjectText = (TextView) listItemView.findViewById(R.id.equation_subject);
        subjectText.setText(thisEquation.getSubject());

        TextView varcountText = (TextView) listItemView.findViewById(R.id.equation_varcount);
        varcountText.setText(thisEquation.getVarcount());

        TextView variableText = (TextView) listItemView.findViewById(R.id.equation_variables);
        variableText.setText(thisEquation.getParams());

        return listItemView;


    }
}