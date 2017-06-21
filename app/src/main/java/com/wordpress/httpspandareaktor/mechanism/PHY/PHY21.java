package com.wordpress.httpspandareaktor.mechanism.PHY;

import android.text.Spanned;

import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by brian on 6/16/17.
 */

public class PHY21 {

    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!

    //descriptionGeneral is a general description of the equation for the ArrayAdapter
    public final String descriptionGeneral = "Determine the average tangential velocity in an object moving in uniform " +
            "circular motion with the angular velocity and radius";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescVelocity,
            PHYutils.PHYvarDescRadius,
            PHYutils.PHYvarDescAngularVelocity};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymVelocity;
    public Spanned symbolValB = PHYutils.PHYvarSymRadius;
    public Spanned symbolValC = PHYutils.PHYvarSymAngularVelocity;
    public Spanned unitValA = PHYutils.PHYvarUnitVelocity;
    public Spanned unitValB = PHYutils.PHYvarUnitRadius;
    public Spanned unitValC = PHYutils.PHYvarUnitAngularVelocity;

    public static String solveMissing(String arrayCode, double firstVar, double secondVar) {
        switch (arrayCode) {
            case "011":
                return String.valueOf(firstVar * secondVar);
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

    public PHY21() {
    }



}
