package com.wordpress.httpspandareaktor.mekanism;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static android.R.attr.resource;
import static android.R.attr.x;

/**
 * Created by Brian on 12/11/2016.
 */

public class EquationAdapter extends ArrayAdapter<Equation> {
    private Class equationClass;
    private Spanned[] descriptorArray;

    public EquationAdapter(Context context, ArrayList<Equation> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //see if list item is null, otherwise inflate a view to populate
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.equation_item, parent, false);
        }

        // get and name the Event object at this position
        Equation thisEquation = getItem(position);

        // retrieve the params of the equation from the codeEquation class using the code
        String equationCode = thisEquation.getCode();
        String subjectCode = String.valueOf(equationCode.charAt(0)) + String.valueOf(equationCode.charAt(1)) +
                String.valueOf(equationCode.charAt(2));
        try {
            equationClass = Class.forName("com.wordpress.httpspandareaktor.mekanism." + subjectCode + "." + equationCode);
            Field descriptorField = equationClass.getDeclaredField("descriptorArray");
            descriptorField.setAccessible(true);
            Object temp = equationClass.newInstance();
            descriptorArray = (Spanned[]) descriptorField.get(temp);
        } catch (Exception e) {e.printStackTrace();}



        //set the image
        ImageView equation = (ImageView) listItemView.findViewById(R.id.equation_image);
        equation.setImageResource(thisEquation.getImageResourceId());

        //set the text values

        TextView varcountText = (TextView) listItemView.findViewById(R.id.phys_equation_varcount);
        varcountText.setText(thisEquation.getVarcount());

        //retrieve and set vals from descriptorArray from the equationCode class and set it here
        TextView variableText = (TextView) listItemView.findViewById(R.id.equation_variables);
        for (int i = 1; i < descriptorArray.length; i ++){
            variableText.append(descriptorArray[i-1]);
        }

        TextView descText = (TextView) listItemView.findViewById(R.id.equation_descriptor);
        descText.setText(thisEquation.getDesc());

        return listItemView;


    }
}
