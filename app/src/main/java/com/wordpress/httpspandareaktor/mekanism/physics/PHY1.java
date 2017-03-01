package com.wordpress.httpspandareaktor.mekanism.physics;

import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spanned;
import android.widget.TextView;
import android.view.View;

import com.wordpress.httpspandareaktor.mekanism.R;
import com.wordpress.httpspandareaktor.mekanism.Utils;
import com.wordpress.httpspandareaktor.mekanism.solvers.ThreeVar;

import java.util.ArrayList;

import static com.wordpress.httpspandareaktor.mekanism.Utils.displacement;

/**
 * Created by Brian on 2/28/2017.
 */

public class PHY1 extends AppCompatActivity{

    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!

    public final Spanned descriptorText = Utils.fromHtml("<br>d = displacement</br>" +
            "<br>x<sub><small>t</small></sub> = position at time=t, or x<sub><small>final</small></sub></br>" +
            "<br>x<sub><small>0</small></sub> = initial position, position at time = 0");


    //set up the solver page with the following
    String rootSubject = "Physics";
    String solverForm = "ThreeVar";

    public String symbolValA = "phy_sym_displacement";
    public String symbolValB = "phy_sym_final_position";
    public String symbolValC = "phy_sym_initial_position";
    public String unitValA = "phy_unit_meters";
    public String unitValB = "phy_unit_meters";
    public String unitValC = "phy_unit_meters";

    public String solveMissing(String arrayCode, double firstVar, double secondVar){
        switch (arrayCode) {
            case "011":
                return  String.valueOf(firstVar - secondVar);
            case "101":
                return String.valueOf(firstVar + secondVar);
            case "110":
                return String.valueOf(secondVar - firstVar);
            default:
                return "error in solution method";

        }
    }


    //!!!DONE DEFINING UNIQUE STUFF !!!

    //variables for the actual inputs
    double varA;
    double varB;
    double varC;

    //array of the parameters to fill ThreeVar form
    public String[] resourceArray = {symbolValA, symbolValB, symbolValC, unitValA, unitValB, unitValC};

    public PHY1() {
    }
}
