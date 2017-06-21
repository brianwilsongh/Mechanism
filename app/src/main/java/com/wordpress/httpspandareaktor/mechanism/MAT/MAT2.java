package com.wordpress.httpspandareaktor.mechanism.MAT;

import android.text.Spanned;
import android.util.Log;

import com.wordpress.httpspandareaktor.mechanism.MATutils;

/**
 * Created by Brian on 3/16/2017.
 */

public class MAT2 {
    //!!!DEFINE VARIABLES & METHODS FOR THIS EQN HERE!!!s
    //descriptionGeneral is a general description of the equation for the ArrayAdapter

    public final String descriptionGeneral = "Equation that finds missing angles, or sides in a triangle with " +
            "sides named a, b, and c. Angle C (∠C) is the angle opposite to side c.";

    //descriptorText contains all the variables used and their descriptions
    public final Spanned[] descriptorArray = {MATutils.fromHtml("<br><b>c</b> : the side opposite of ∠C in a triangle</br>"),
            MATutils.fromHtml("<br><b>a</b> : one of the sides of the triangle, adjacent to ∠C"),
            MATutils.fromHtml("<br><b>b</b> : one of the sides of the triangle, adjacent to ∠C"),
            MATutils.fromHtml("<br><b>C</b> : the angle opposite of side c (∠C)")};

    //set up the solver page with the following

    public Spanned symbolValA = MATutils.fromHtml("<b>c</b>");
    public Spanned symbolValB = MATutils.fromHtml("<b>a</b>");
    public Spanned symbolValC = MATutils.fromHtml("<b>b</b>");
    public Spanned symbolValD = MATutils.fromHtml("<b>C</b>");

    public Spanned unitValA = MATutils.fromHtml("<b>units</b>");
    public Spanned unitValB = MATutils.fromHtml("<b>units</b>");
    public Spanned unitValC = MATutils.fromHtml("<b>units</b>");
    public Spanned unitValD = MATutils.fromHtml("<b>degrees</b>");

    public String solveMissing(String arrayCode, double param1, double param2, double param3) {
        Log.v("MAT2.class", " receives: " + param1 + param2 + param3);
        switch (arrayCode) {
            case "0111":
                return String.valueOf(Math.sqrt((Math.pow(param1, 2) + Math.pow(param2, 2)) - ((2 * param1 * param2) * Math.toDegrees(Math.cos(Math.toRadians(param3))))));
            case "1011":
                return String.valueOf(MATutils.quadraticEquation((1), (-2 * param2 * Math.cos(Math.toRadians(param3))), (-1 * Math.pow(param1, 2) + Math.pow(param2, 2))));
            case "1101":
                return String.valueOf(MATutils.quadraticEquation((1), (-2 * param2 * Math.cos(Math.toRadians(param3))), (-1 * Math.pow(param1, 2) + Math.pow(param2, 2))));
            case "1110":
                return String.valueOf(Math.toDegrees(Math.acos(Math.toRadians((Math.pow(param2, 2) + Math.pow(param3, 2) - Math.pow(param1, 2))/ (2 * param1 * param2)))));
            default:
                Log.v("MAT2.class", " calculator is FUBAR!");
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

    public MAT2() {
    }
}