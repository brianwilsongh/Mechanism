package com.wordpress.httpspandareaktor.mekanism.PHY;

import android.text.Spanned;

import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by Brian on 3/15/2017.
 */

public class PHY18 {
    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!

    //descriptionGeneral is a general description of the equation for the ArrayAdapter
    public final String descriptionGeneral = "Equation defining Work as the force exerted on the object " +
            "multiplied by the displacement, multiplied by the cosine of the angle between the force and " +
            "displacement vectors (0° if in the same direction)";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescWork,
            PHYutils.PHYvarDescForce,
            PHYutils.PHYvarDescDisplacement,
            PHYutils.PHYvarDescTheta};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymWork;
    public Spanned symbolValB = PHYutils.PHYvarSymForce;
    public Spanned symbolValC = PHYutils.PHYvarSymDisplacement;
    public Spanned symbolValD = PHYutils.PHYvarSymTheta;

    public Spanned unitValA = PHYutils.PHYvarUnitWork;
    public Spanned unitValB = PHYutils.PHYvarUnitForce;
    public Spanned unitValC = PHYutils.PHYvarUnitDisplacement;
    public Spanned unitValD = PHYutils.PHYvarUnitTheta;

    public static String solveMissing(String arrayCode, double param1, double param2, double param3){
        switch (arrayCode) {
            case "0111":
                return  String.valueOf((param1 * param2) * Math.cos(Math.toRadians(param3)));
            case "1011":
                return String.valueOf(param1 / (param2 * Math.cos(Math.toRadians(param3))));
            case "1101":
                return String.valueOf(param1 / (param2 * Math.cos(Math.toRadians(param3))));
            case "1110":
                return String.valueOf(Math.toDegrees(Math.acos((param1)/(param2 * param3))));
            default:
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

    public PHY18() {
    }
}
