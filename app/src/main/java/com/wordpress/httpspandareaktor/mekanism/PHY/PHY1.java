package com.wordpress.httpspandareaktor.mekanism.PHY;

import android.text.Spanned;

import com.wordpress.httpspandareaktor.mekanism.PHYutils;


/**
 * Created by Brian on 2/28/2017.
 */

public class PHY1 {

    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!

    //descriptionGeneral is a general description of the equation for the ArrayAdapter
    public static final String descriptionGeneral = "Formally defines displacement as the difference between" +
            " final and initial position";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescDisplacement,
            PHYutils.PHYvarDescFinalPosition,
            PHYutils.PHYvarDescInitialPosition};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymDisplacement;
    public Spanned symbolValB = PHYutils.PHYvarSymFinalPosition;
    public Spanned symbolValC = PHYutils.PHYvarSymInitialPosition;
    public Spanned unitValA = PHYutils.PHYvarUnitDisplacement;
    public Spanned unitValB = PHYutils.PHYvarUnitFinalPosition;
    public Spanned unitValC = PHYutils.PHYvarUnitInitialPosition;

    public static String solveMissing(String arrayCode, double firstVar, double secondVar){
        switch (arrayCode) {
            case "011":
                return  String.valueOf(firstVar - secondVar);
            case "101":
                return String.valueOf(firstVar + secondVar);
            case "110":
                return String.valueOf(secondVar - firstVar);
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

    public PHY1() {
    }
}
