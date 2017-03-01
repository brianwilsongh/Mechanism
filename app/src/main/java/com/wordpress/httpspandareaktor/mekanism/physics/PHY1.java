package com.wordpress.httpspandareaktor.mekanism.physics;

import android.text.Spanned;
import android.widget.TextView;
import android.view.View;

import com.wordpress.httpspandareaktor.mekanism.R;
import com.wordpress.httpspandareaktor.mekanism.Utils;
import com.wordpress.httpspandareaktor.mekanism.solvers.ThreeVar;

import java.util.ArrayList;

/**
 * Created by Brian on 2/28/2017.
 */

public class PHY1 {
    TextView tView;
    public final Spanned descriptorText = Utils.fromHtml("<br>d = displacement</br>" +
            "<br>x<sub><small>t</small></sub> = position at time=t, or x<sub><small>final</small></sub></br>" +
            "<br>x<sub><small>0</small></sub> = initial position, position at time = 0");


    //set up the solver page with the following
    String solverForm = "ThreeVar";
    String symbolValA = "1";
    String symbolValB = "2";
    String symbolValC = "3";
    String unitValA = "4";
    String unitValB = "5";
    String unitValC = "6";

    //array of the parameters to fill ThreeVar form
    public String[] paramArray = {symbolValA, symbolValB, symbolValC, unitValA, unitValB, unitValC};

    //declare view
    TextView symbolA;

    public PHY1() {
    }

    //solve the equation given input below

    public String solve(){
        return "whoa";
    }

}
