package com.wordpress.httpspandareaktor.mekanism.PHY;

import android.text.Spanned;
import android.util.Log;

import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/16/17.
 */

public class PHY22 {
    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!
    //descriptionGeneral is a general description of the equation for the ArrayAdapter

    public final String descriptionGeneral = "The centripetal force of an object traveling in uniform circular motion";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescCentripetalForce,
            PHYutils.PHYvarDescMass,
            PHYutils.PHYvarDescVelocity,
            PHYutils.PHYvarDescRadius};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymCentripetalForce;
    public Spanned symbolValB = PHYutils.PHYvarSymMass;
    public Spanned symbolValC = PHYutils.PHYvarSymVelocity;
    public Spanned symbolValD = PHYutils.PHYvarSymRadius;

    public Spanned unitValA = PHYutils.PHYvarUnitCentripetalForce;
    public Spanned unitValB = PHYutils.PHYvarUnitMass;
    public Spanned unitValC = PHYutils.PHYvarUnitVelocity;
    public Spanned unitValD = PHYutils.PHYvarUnitRadius;

    public String solveMissing(String arrayCode, double param1, double param2, double param3) {
        Log.v("PHY22.class", " receives: " + param1 + param2 + param3);
        switch (arrayCode) {
            case "0111":
                return String.valueOf(param1 * (Math.pow(param2, 2) / param3));
            case "1011":
                return String.valueOf((param1 * param3) / (Math.pow(param2, 2)));
            case "1101":
                return String.valueOf(Math.sqrt((param3 * param1) / param2));
            case "1110":
                return String.valueOf((param2 * Math.pow(param3, 2))/ param1);
            default:
                Log.v("PHY22.class", " calculator is FUBAR");
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

    public PHY22() {
    }
}
