package com.wordpress.httpspandareaktor.mechanism.PHY;

import android.text.Spanned;
import android.util.Log;

import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by brian on 6/16/17.
 */

public class PHY24 {
    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!
    //descriptionGeneral is a general description of the equation for the ArrayAdapter

    public final String descriptionGeneral = "The change in the gravitational potential energy of an object on earth given a change in its height off the ground";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescDeltaGravPotentialEnergy,
            PHYutils.PHYvarDescMass,
            PHYutils.PHYvarDescGravField,
            PHYutils.PHYvarDescDeltaHeight};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymDeltaGravPotentialEnergy;
    public Spanned symbolValB = PHYutils.PHYvarSymMass;
    public Spanned symbolValC = PHYutils.PHYvarSymGravField;
    public Spanned symbolValD = PHYutils.PHYvarSymDeltaHeight;

    public Spanned unitValA = PHYutils.PHYvarUnitDeltaGravPotentialEnergy;
    public Spanned unitValB = PHYutils.PHYvarUnitMass;
    public Spanned unitValC = PHYutils.PHYvarUnitGravField;
    public Spanned unitValD = PHYutils.PHYvarUnitDeltaHeight;

    public Double defaultC = 9.807;

    public static String solveMissing(String arrayCode, double param1, double param2, double param3) {
        Log.v("PHY24.class", " receives: " + param1 + param2 + param3);
        switch (arrayCode) {
            case "0111":
                return String.valueOf(param1 * param2 * param3);
            case "1011":
                return String.valueOf(param1 / (param2 * param3));
            case "1101":
                return String.valueOf(param1 / (param2 * param3));
            case "1110":
                return String.valueOf(param1 / (param2 * param3));
            default:
                Log.v("PHY24.class", " calculator is FUBAR");
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

    public PHY24() {
    }
}
