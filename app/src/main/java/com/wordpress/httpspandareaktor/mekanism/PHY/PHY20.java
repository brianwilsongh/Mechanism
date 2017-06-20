package com.wordpress.httpspandareaktor.mekanism.PHY;

import android.text.Spanned;

import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/15/17.
 */

public class PHY20 {
    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!

    //descriptionGeneral is a general description of the equation for the ArrayAdapter
    public final String descriptionGeneral = "Determine angular velocity, the ratio of the angle traversed " +
            "to the amount of time it takes to traverse that angle. (Expressed in radians)";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescAngularVelocity,
            PHYutils.PHYvarDescDeltaTheta,
            PHYutils.PHYvarDescDeltaTime};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymAngularVelocity;
    public Spanned symbolValB = PHYutils.PHYvarSymDeltaTheta;
    public Spanned symbolValC = PHYutils.PHYvarSymDeltaTime;
    public Spanned unitValA = PHYutils.PHYvarUnitAngularVelocity;
    public Spanned unitValB = PHYutils.PHYvarUnitDeltaTheta;
    public Spanned unitValC = PHYutils.PHYvarUnitTime;

    public static String solveMissing(String arrayCode, double firstVar, double secondVar) {
        switch (arrayCode) {
            case "011":
                return String.valueOf(Math.toRadians(firstVar) / secondVar);
            case "101":
                return String.valueOf(Math.toDegrees(firstVar * secondVar));
            case "110":
                return String.valueOf((Math.toRadians(secondVar))/firstVar);
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

    public PHY20() {
    }



}
