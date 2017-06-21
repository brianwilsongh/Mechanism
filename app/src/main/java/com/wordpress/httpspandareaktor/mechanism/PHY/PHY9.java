package com.wordpress.httpspandareaktor.mechanism.PHY;

import android.text.Spanned;

import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by Brian on 3/8/2017.
 */

public class PHY9 {
//!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!

    //descriptionGeneral is a general description of the equation for the ArrayAdapter
    public final String descriptionGeneral = "An equation defining the average velocity between an initial and final velocity";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescAverageVelocity,
            PHYutils.PHYvarDescFinalVelocity,
            PHYutils.PHYvarDescInitialVelocity,};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymAverageVelocity;
    public Spanned symbolValB = PHYutils.PHYvarSymFinalVelocity;
    public Spanned symbolValC = PHYutils.PHYvarSymInitialVelocity;

    public Spanned unitValA = PHYutils.PHYvarUnitAverageVelocity;
    public Spanned unitValB = PHYutils.PHYvarUnitFinalVelocity;
    public Spanned unitValC = PHYutils.PHYvarUnitInitialVelocity;

    public static String solveMissing(String arrayCode, double param1, double param2){
        switch (arrayCode) {
            case "011":
                return  String.valueOf((param1 + param2) / 2);
            case "101":
                return String.valueOf((2 * param1) - param2);
            case "110":
                return String.valueOf((2 * param1) - param2);
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

    public PHY9() {
    }
}

