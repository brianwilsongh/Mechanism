package com.wordpress.httpspandareaktor.mekanism.PHY;

import android.text.Spanned;
import android.util.Log;

import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/16/17.
 */

public class PHY23 {
    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!
    //descriptionGeneral is a general description of the equation for the ArrayAdapter

    public final String descriptionGeneral = "Equation to determine the strength of a gravitational field given" +
            " radius r, the linear distance from the center of the field" + PHYutils.PHYconstantDescUniversalGravitation;

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescGravFieldFunc,
            PHYutils.PHYvarDescMass,
            PHYutils.PHYvarDescRadius};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymGravFieldFunc;
    public Spanned symbolValB = PHYutils.PHYvarSymMass;
    public Spanned symbolValC = PHYutils.PHYvarSymRadius;

    public Spanned unitValA = PHYutils.PHYvarUnitGravFieldFunc;
    public Spanned unitValB = PHYutils.PHYvarUnitMass;
    public Spanned unitValC = PHYutils.PHYvarUnitRadius;

    public String solveMissing(String arrayCode, double param1, double param2) {
        Log.v("PHY23.class", " receives: " + param1 + param2);
        switch (arrayCode) {
            case "011":
                return String.valueOf((PHYutils.PHYconstantValUniversalGravitation * param1) / Math.pow(param2, 2));
            case "101":
                return String.valueOf((param1 * Math.pow(param2, 2)) / PHYutils.PHYconstantValUniversalGravitation);
            case "110":
                return String.valueOf(Math.sqrt((PHYutils.PHYconstantValUniversalGravitation * param2)/param1));
            default:
                Log.v("PHY23.class", " calculator is FUBAR");
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

    public PHY23() {
    }
}
