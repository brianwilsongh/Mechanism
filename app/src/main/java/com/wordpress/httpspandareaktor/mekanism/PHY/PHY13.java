package com.wordpress.httpspandareaktor.mekanism.PHY;

import android.text.Spanned;

import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by Brian on 3/12/2017.
 */

public class PHY13 {

    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!

    //descriptionGeneral is a general description of the equation for the ArrayAdapter
    public final String descriptionGeneral = "Formally defines displacement as the difference between" +
            " final and initial position";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {PHYutils.PHYvarDescCentripetalAcceleration,
            PHYutils.PHYvarDescVelocity,
            PHYutils.PHYvarDescRadius};

    //set up the solver page with the following

    public Spanned symbolValA = PHYutils.PHYvarSymCentripetalAcceleration;
    public Spanned symbolValB = PHYutils.PHYvarSymVelocity;
    public Spanned symbolValC = PHYutils.PHYvarSymRadius;
    public Spanned unitValA = PHYutils.PHYvarUnitCentripetalAcceleration;
    public Spanned unitValB = PHYutils.PHYvarUnitVelocity;
    public Spanned unitValC = PHYutils.PHYvarUnitRadius;

    public String solveMissing(String arrayCode, double firstVar, double secondVar){
        switch (arrayCode) {
            case "011":
                return  String.valueOf(Math.pow(firstVar, 2) / secondVar);
            case "101":
                return String.valueOf(Math.sqrt(firstVar * secondVar));
            case "110":
                return String.valueOf((Math.pow(secondVar, 2) / firstVar));
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

    public PHY13() {
    }
}

