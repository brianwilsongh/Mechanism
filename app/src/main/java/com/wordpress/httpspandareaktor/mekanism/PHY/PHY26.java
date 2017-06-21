package com.wordpress.httpspandareaktor.mekanism.PHY;

import android.text.Spanned;
import android.util.Log;

import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/20/17.
 */

public class PHY26 {
    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!
    //descriptionGeneral is a general description of the equation for the ArrayAdapter

    public final String descriptionGeneral = "Equation to find the period of a simple pendulum given a length and gravitational field strength";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescPeriod,
            PHYutils.PHYvarDescLength,
            PHYutils.PHYvarDescGravField};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymPeriod;
    public Spanned symbolValB = PHYutils.PHYvarSymLength;
    public Spanned symbolValC = PHYutils.PHYvarSymGravField;

    public Spanned unitValA = PHYutils.PHYvarUnitPeriod;
    public Spanned unitValB = PHYutils.PHYvarUnitLength;
    public Spanned unitValC = PHYutils.PHYvarUnitGravField;

    public Double defaultC = 9.807;

    public String solveMissing(String arrayCode, double param1, double param2) {
        Log.v("PHY26.class", " receives: " + param1 + param2);
        switch (arrayCode) {
            case "011":
                return String.valueOf(2 * Math.PI * Math.sqrt(param1 / param2));
            case "101":
                return String.valueOf(param2 * Math.pow((param1 / (Math.PI * 2)), 2));
            case "110":
                return String.valueOf(param2 / Math.pow((param1 / (Math.PI* 2)), 2));
            default:
                Log.v("PHY26.class", " calculator is FUBAR");
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

    public PHY26() {
    }
}
