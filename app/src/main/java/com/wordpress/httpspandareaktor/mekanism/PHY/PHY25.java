package com.wordpress.httpspandareaktor.mekanism.PHY;

import android.text.Spanned;
import android.util.Log;

import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/18/17.
 */

public class PHY25 {
    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!
    //descriptionGeneral is a general description of the equation for the ArrayAdapter

    public final String descriptionGeneral = "The change in the gravitational potential energy of an object on earth given a change in its height off the ground";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescForce,
            PHYutils.PHYvarDescSpringConstant,
            PHYutils.PHYvarDescDisplacement};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymForce;
    public Spanned symbolValB = PHYutils.PHYvarSymSpringConstant;
    public Spanned symbolValC = PHYutils.PHYvarSymDisplacement;

    public Spanned unitValA = PHYutils.PHYvarUnitForce;
    public Spanned unitValB = PHYutils.PHYvarUnitSpringConstant;
    public Spanned unitValC = PHYutils.PHYvarUnitDisplacement;

    public String solveMissing(String arrayCode, double param1, double param2) {
        Log.v("PHY24.class", " receives: " + param1 + param2);
        switch (arrayCode) {
            case "011":
                return String.valueOf(param1 * param2);
            case "101":
                return String.valueOf(param1 / param2);
            case "110":
                return String.valueOf(param1 / param2);
            default:
                Log.v("PHY25.class", " calculator is FUBAR");
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

    public PHY25() {
    }
}
