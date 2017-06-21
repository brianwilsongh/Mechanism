package com.wordpress.httpspandareaktor.mechanism.PHY;

import android.text.Spanned;

import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by Brian on 3/8/2017.
 */

public class PHY3 {

    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!

    //descriptionGeneral is a general description of the equation for the ArrayAdapter
    public final String descriptionGeneral = "Formally defines acceleration as the change in velocity " +
            "divided by the change in time";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescAverageAcceleration,
            PHYutils.PHYvarDescDeltaVelocity,
            PHYutils.PHYvarDescTime};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymAverageAcceleration;
    public Spanned symbolValB = PHYutils.PHYvarSymDeltaVelocity;
    public Spanned symbolValC = PHYutils.PHYvarSymTime;
    public Spanned unitValA = PHYutils.PHYvarUnitAverageAcceleration;
    public Spanned unitValB = PHYutils.PHYvarUnitDeltaVelocity;
    public Spanned unitValC = PHYutils.PHYvarUnitTime;

    public static String solveMissing(String arrayCode, double firstVar, double secondVar){
        switch (arrayCode) {
            case "011":
                return  String.valueOf(firstVar / secondVar);
            case "101":
                return String.valueOf(firstVar * secondVar);
            case "110":
                return String.valueOf(secondVar / firstVar);
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
    public Spanned[] resourceArray = {symbolValA, symbolValB, symbolValC, unitValA, unitValB, unitValC};

    public PHY3() {
    }
}

