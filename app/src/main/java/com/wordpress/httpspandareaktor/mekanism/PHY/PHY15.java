package com.wordpress.httpspandareaktor.mekanism.PHY;

import android.text.Spanned;

import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by Brian on 3/12/2017.
 */

public class PHY15 {
    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!

    //descriptionGeneral is a general description of the equation for the ArrayAdapter
    public final String descriptionGeneral = "The definition of a momentum, which quantifies the " +
            "current motion of an object by multiplying its mass and velocity";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescMomentum,
            PHYutils.PHYvarDescMass,
            PHYutils.PHYvarDescVelocity};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymMomentum;
    public Spanned symbolValB = PHYutils.PHYvarSymMass;
    public Spanned symbolValC = PHYutils.PHYvarSymVelocity;
    public Spanned unitValA = PHYutils.PHYvarUnitMomentum;
    public Spanned unitValB = PHYutils.PHYvarUnitMass;
    public Spanned unitValC = PHYutils.PHYvarUnitVelocity;

    public String solveMissing(String arrayCode, double firstVar, double secondVar){
        switch (arrayCode) {
            case "011":
                return  String.valueOf(firstVar * secondVar);
            case "101":
                return String.valueOf(firstVar / secondVar);
            case "110":
                return String.valueOf(firstVar / secondVar);
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

    public PHY15() {
    }
}

