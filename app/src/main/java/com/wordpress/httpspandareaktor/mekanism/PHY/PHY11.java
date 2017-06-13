package com.wordpress.httpspandareaktor.mekanism.PHY;

import android.text.Spanned;

import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by Brian on 3/9/2017.
 */

public class PHY11 {
    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!

    //descriptionGeneral is a general description of the equation for the ArrayAdapter
    public final String descriptionGeneral = "Equation that applies Newton's Second Law to weight, " +
            PHYutils.PHYconstantDescGravAccelEarth;

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescWeight,
            PHYutils.PHYvarDescMass};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymWeight;
    public Spanned symbolValB = PHYutils.PHYvarSymMass;
    public Spanned unitValA = PHYutils.PHYvarUnitWeight;
    public Spanned unitValB = PHYutils.PHYvarUnitMass;

    public String solveMissing(String arrayCode, double firstVar){
        switch (arrayCode) {
            case "01":
                return  String.valueOf(firstVar * PHYutils.PHYconstantValGravAccelEarth);
            case "10":
                return String.valueOf(firstVar / PHYutils.PHYconstantValGravAccelEarth);
            default:
                return "error in solution method";

        }
    }


    //!!!DONE DEFINING UNIQUE STUFF !!!

    //variables for the actual inputs
    double varA;
    double varB;

    //array of the parameters to fill ThreeVar form
    public Spanned[] resourceArray = {symbolValA, symbolValB, unitValA, unitValB};

    public PHY11() {
    }
}


