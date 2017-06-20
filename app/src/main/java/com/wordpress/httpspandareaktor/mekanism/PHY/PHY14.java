package com.wordpress.httpspandareaktor.mekanism.PHY;

import android.text.Spanned;

import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by Brian on 3/12/2017.
 */

public class PHY14 {
//!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!

    //descriptionGeneral is a general description of the equation for the ArrayAdapter
    public final String descriptionGeneral = "A formula used to solve for the friction force vector, which" +
            " is proportional to the normal force vector";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescForceFriction,
            PHYutils.PHYvarDescCoefficientFriction,
            PHYutils.PHYvarDescForceNormal,};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymForceFriction;
    public Spanned symbolValB = PHYutils.PHYvarSymCoefficientFriction;
    public Spanned symbolValC = PHYutils.PHYvarSymForceNormal;

    public Spanned unitValA = PHYutils.PHYvarUnitForceFriction;
    public Spanned unitValB = PHYutils.PHYvarUnitCoefficientFriction;
    public Spanned unitValC = PHYutils.PHYvarUnitForceNormal;

    public static String solveMissing(String arrayCode, double param1, double param2){
        switch (arrayCode) {
            case "011":
                return  String.valueOf(param1 * param2);
            case "101":
                return String.valueOf(param1 / param2);
            case "110":
                return String.valueOf(param1 / param2);
            default:
                return "error in solution method";

        }
    }


    //!!!DONE DEFINING UNIQUE STUFF !!!

    //variables for the actual inputs
    double varA;
    double varB;
    double varC;
    double varD;

    //array of the parameters to fill ThreeVar form
    public Spanned[] resourceArray = {symbolValA, symbolValB, symbolValC,
            unitValA, unitValB, unitValC};

    public PHY14() {
    }
}


