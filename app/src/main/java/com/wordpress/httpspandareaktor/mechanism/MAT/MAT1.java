package com.wordpress.httpspandareaktor.mechanism.MAT;

import android.text.Spanned;

import com.wordpress.httpspandareaktor.mechanism.MATutils;

/**
 * Created by Brian on 3/15/2017.
 */

public class MAT1 {

    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!

    //descriptionGeneral is a general description of the equation for the ArrayAdapter
    public final String descriptionGeneral = "Equation relating x and y coordinates with slope and y-intercept";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {MATutils.MATvarDescY,
            MATutils.MATvarDescM,
            MATutils.MATvarDescX,
            MATutils.MATvarDescB};

    //set up the solver page with the following

    public Spanned symbolValA = MATutils.MATvarSymY;
    public Spanned symbolValB = MATutils.MATvarSymM;
    public Spanned symbolValC = MATutils.MATvarSymX;
    public Spanned symbolValD = MATutils.MATvarSymB;
    public Spanned unitValA = MATutils.MATvarUnitsY;
    public Spanned unitValB = MATutils.MATvarUnitsM;
    public Spanned unitValC = MATutils.MATvarUnitsX;
    public Spanned unitValD = MATutils.MATvarUnitsB;

    public String solveMissing(String arrayCode, double firstVar, double secondVar, double thirdVar){
        switch (arrayCode) {
            case "0111":
                return  String.valueOf((firstVar * secondVar) + thirdVar);
            case "1011":
                return String.valueOf((firstVar - thirdVar)/secondVar);
            case "1101":
                return String.valueOf((firstVar - thirdVar)/secondVar);
            case "1110":
                return String.valueOf(firstVar - (secondVar * thirdVar));
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
    public Spanned[] resourceArray = {symbolValA, symbolValB, symbolValC, symbolValD, unitValA, unitValB, unitValC, unitValD};

    public MAT1() {
    }
}

