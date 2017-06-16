package com.wordpress.httpspandareaktor.mekanism.PHY;

import android.text.Spanned;
import android.util.Log;

import com.wordpress.httpspandareaktor.mekanism.PHYutils;

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
            PHYutils.PHYvarDescDeltaHeight};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymDeltaGravPotentialEnergy;
    public Spanned symbolValB = PHYutils.PHYvarSymMass;
    public Spanned symbolValC = PHYutils.PHYvarSymDeltaHeight;

    public Spanned unitValA = PHYutils.PHYvarUnitDeltaGravPotentialEnergy;
    public Spanned unitValB = PHYutils.PHYvarUnitMass;
    public Spanned unitValC = PHYutils.PHYvarUnitDeltaHeight;

    public String solveMissing(String arrayCode, double param1, double param2) {
        Log.v("PHY24.class", " receives: " + param1 + param2);
        switch (arrayCode) {
            case "011":
                return String.valueOf(param1 * PHYutils.PHYconstantValGravAccelEarth * param2);
            case "101":
                return String.valueOf(param1 / (param2 * PHYutils.PHYconstantValGravAccelEarth));
            case "110":
                return String.valueOf(param1 / (param2 * PHYutils.PHYconstantValGravAccelEarth));
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

    //array of the parameters to fill ThreeVar form
    public Spanned[] resourceArray = {symbolValA, symbolValB, symbolValC,
            unitValA, unitValB, unitValC};

    public PHY24() {
    }
}
