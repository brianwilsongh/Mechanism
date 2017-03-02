package com.wordpress.httpspandareaktor.mekanism.PHY;

import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;

import com.wordpress.httpspandareaktor.mekanism.Utils;

import static com.wordpress.httpspandareaktor.mekanism.Utils.PHYvarDescFinalPosition;


/**
 * Created by Brian on 2/28/2017.
 */

public class PHY1 extends AppCompatActivity{

    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {Utils.PHYvarDescDisplacement,
            Utils.PHYvarDescInitialPosition,
            Utils.PHYvarDescFinalPosition};

    //set up the solver page with the following

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
