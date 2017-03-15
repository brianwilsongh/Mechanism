package com.wordpress.httpspandareaktor.mekanism.PHY;

import android.text.Spanned;

import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by Brian on 3/15/2017.
 */

public class PHY16 {
    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!

    //descriptionGeneral is a general description of the equation for the ArrayAdapter
    public final String descriptionGeneral = "Defines impulse as average force acting on an object multiplied " +
            "by the change in the duration of time. Part of Impulse-Momentum Theorem.";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescImpulse,
            PHYutils.PHYvarDescForce,
            PHYutils.PHYvarDescDeltaTime};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymImpulse;
    public Spanned symbolValB = PHYutils.PHYvarSymForce;
    public Spanned symbolValC = PHYutils.PHYvarSymDeltaTime;
    public Spanned unitValA = PHYutils.PHYvarUnitImpulse;
    public Spanned unitValB = PHYutils.PHYvarUnitForce;
    public Spanned unitValC = PHYutils.PHyvarUnitDeltaTime;

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

    public PHY16() {
    }
}



