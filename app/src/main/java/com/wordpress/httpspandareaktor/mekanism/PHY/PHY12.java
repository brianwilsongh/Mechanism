package com.wordpress.httpspandareaktor.mekanism.PHY;

import android.text.Spanned;
import android.util.Log;

import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by Brian on 3/9/2017.
 */

public class PHY12 {
    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!
    //descriptionGeneral is a general description of the equation for the ArrayAdapter

    public final String descriptionGeneral = "Equation of universal gravitation. Relates the masses " +
            "and distances between the centers of two objects to the force of the gravitational pull towards their " +
            "common center of mass. " +
            PHYutils.PHYconstantDescUniversalGravitation;

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescForceGravity,
            PHYutils.PHYvarDescMass,
            PHYutils.PHYvarDescMass,
            PHYutils.PHYvarDescDistance};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymForceGravity;
    public Spanned symbolValB = PHYutils.PHYvarSymMass1;
    public Spanned symbolValC = PHYutils.PHYvarSymMass2;
    public Spanned symbolValD = PHYutils.PHYvarSymDistance;

    public Spanned unitValA = PHYutils.PHYvarUnitForceGravity;
    public Spanned unitValB = PHYutils.PHYvarUnitMass;
    public Spanned unitValC = PHYutils.PHYvarUnitMass;
    public Spanned unitValD = PHYutils.PHYvarUnitDistance;

    public static String solveMissing(String arrayCode, double param1, double param2, double param3) {
        Log.v("PHY12.class", " receives: " + param1 + param2 + param3);
        switch (arrayCode) {
            case "0111":
                Log.v("PHY12 code 0111", " " + String.valueOf((param1 * param2 * PHYutils.PHYconstantValUniversalGravitation) / Math.pow(param3, 2)));
                return String.valueOf((param1 * param2 * PHYutils.PHYconstantValUniversalGravitation) / Math.pow(param3, 2));
            case "1011":
                Log.v("PHY12 code 1011", " " + String.valueOf((param1 * Math.pow(param3, 2)) / (PHYutils.PHYconstantValUniversalGravitation  * param2)));
                return String.valueOf((param1 * Math.pow(param3, 2)) / (PHYutils.PHYconstantValUniversalGravitation  * param2));
            case "1101":
                Log.v("PHY12 code 1101", " " + String.valueOf((param1 * Math.pow(param3, 2)) / (PHYutils.PHYconstantValUniversalGravitation  * param2)));
                return String.valueOf((param1 * Math.pow(param3, 2)) / (PHYutils.PHYconstantValUniversalGravitation  * param2));
            case "1110":
                Log.v("PHY12 code 1110", " " + String.valueOf(Math.sqrt((PHYutils.PHYconstantValUniversalGravitation * param2 * param3)/param1)));
                return String.valueOf(Math.sqrt((PHYutils.PHYconstantValUniversalGravitation * param2 * param3)/param1));
            default:
                Log.v("PHY12.class", " calculator is FUBAR");
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
    public Spanned[] resourceArray = {symbolValA, symbolValB, symbolValC, symbolValD,
            unitValA, unitValB, unitValC, unitValD};

    public PHY12() {
    }
}