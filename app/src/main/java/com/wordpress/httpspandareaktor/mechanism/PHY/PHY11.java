package com.wordpress.httpspandareaktor.mechanism.PHY;

import android.text.Spanned;

import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by Brian on 3/9/2017.
 */

public class PHY11 {
    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!

    //descriptionGeneral is a general description of the equation for the ArrayAdapter
    public final String descriptionGeneral = "Equation to determine weight as a force, where g defaults to the gravitational acceleration on the surface of Earth";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescWeight,
            PHYutils.PHYvarDescMass,
    PHYutils.PHYvarDescGravField};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymWeight;
    public Spanned symbolValB = PHYutils.PHYvarSymMass;
    public Spanned symbolValC = PHYutils.PHYvarSymGravField;

    public Spanned unitValA = PHYutils.PHYvarUnitWeight;
    public Spanned unitValB = PHYutils.PHYvarUnitMass;
    public Spanned unitValC = PHYutils.PHYvarUnitGravField;

    public Double defaultC = 9.807;

    public static String solveMissing(String arrayCode, double firstVar, double secondVar){
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

    public PHY11() {
    }
}


