package com.wordpress.httpspandareaktor.mechanism.PHY;

import android.text.Spanned;

import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by Brian on 3/8/2017.
 */

public class PHY5 {
    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!

    //descriptionGeneral is a general description of the equation for the ArrayAdapter
    public final String descriptionGeneral = "One of the basic equations of motion relating final velocity, " +
            "initial velocity, acceleration, and time.";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescFinalVelocity,
            PHYutils.PHYvarDescInitialVelocity,
            PHYutils.PHYvarDescAverageAcceleration,
            PHYutils.PHYvarDescTime};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymFinalVelocity;
    public Spanned symbolValB = PHYutils.PHYvarSymInitialVelocity;
    public Spanned symbolValC = PHYutils.PHYvarSymAverageAcceleration;
    public Spanned symbolValD = PHYutils.PHYvarSymTime;

    public Spanned unitValA = PHYutils.PHYvarUnitFinalVelocity;
    public Spanned unitValB = PHYutils.PHYvarUnitInitialVelocity;
    public Spanned unitValC = PHYutils.PHYvarUnitAverageAcceleration;
    public Spanned unitValD = PHYutils.PHYvarUnitTime;


    public static String solveMissing(String arrayCode, double param1, double param2, double param3){
        switch (arrayCode) {
            case "0111":
                return  String.valueOf((param1)+(param2 * param3));
            case "1011":
                return String.valueOf((-1 * param2 * param3) + param1);
            case "1101":
                return String.valueOf((param1 - param2) / param3);
            case "1110":
                return String.valueOf((param1 - param2) / param3);
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

    public PHY5() {
    }
}
