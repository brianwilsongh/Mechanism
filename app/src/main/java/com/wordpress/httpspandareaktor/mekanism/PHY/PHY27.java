package com.wordpress.httpspandareaktor.mekanism.PHY;

import android.text.Spanned;
import android.util.Log;

import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/20/17.
 */

public class PHY27 {
    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!
    //descriptionGeneral is a general description of the equation for the ArrayAdapter

    public final String descriptionGeneral = "Calculates the escape velocity required to exit the gravitational " +
            "field of an object with mass m, from radius r as the distance from the center. " + PHYutils.PHYconstantDescUniversalGravitation;

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescVelocity,
            PHYutils.PHYvarDescMass,
            PHYutils.PHYvarDescRadius};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymVelocity;
    public Spanned symbolValB = PHYutils.PHYvarSymMass;
    public Spanned symbolValC = PHYutils.PHYvarSymRadius;

    public Spanned unitValA = PHYutils.PHYvarUnitVelocity;
    public Spanned unitValB = PHYutils.PHYvarUnitMass;
    public Spanned unitValC = PHYutils.PHYvarUnitRadius;


    public static String solveMissing(String arrayCode, double param1, double param2) {
        Log.v("PHY27.class", " receives: " + param1 + param2);
        switch (arrayCode) {
            case "011":
                return String.valueOf(Math.sqrt((2 * PHYutils.PHYconstantValUniversalGravitation * param1) / param2));
            case "101":
                return String.valueOf((Math.pow(param1, 2) * param2) / (2 * PHYutils.PHYconstantValUniversalGravitation));
            case "110":
                return String.valueOf((2 * param2 * PHYutils.PHYconstantValUniversalGravitation) / Math.pow(param1, 2));
            default:
                Log.v("PHY27.class", " calculator is FUBAR");
                return "error in solution method";

        }
    }


    //!!!DONE DEFINING UNIQUE STUFF !!!

    //variables for the actual inputs
    double varA;
    double varB;
    double varC;

    //array of the parameters to fill ThreeVar form
    public Spanned[] resourceArray = {symbolValA, symbolValB, symbolValC,
            unitValA, unitValB, unitValC};

    public PHY27() {
    }
}
