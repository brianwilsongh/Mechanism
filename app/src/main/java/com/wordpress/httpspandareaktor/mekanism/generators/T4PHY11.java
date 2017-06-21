package com.wordpress.httpspandareaktor.mekanism.generators;

import com.wordpress.httpspandareaktor.mekanism.PHY.PHY11;
import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/19/17.
 */

public class T4PHY11 implements Generator{
    //this is a tier 4 generator applying PHY11
    public static byte tier = 4;
    public static boolean image = false;


    public Double firstVar;
    public Double secondVar;
    public String formatted;

    public Double trueAnswer;
    public String trueAnswerUnit;
    public Double falseAnswer;
    public Double falseAnswerTwo;

    public T4PHY11(){
        //generate a random code for the 3-var
        String randomizedPHY11 = GenUtils.generateRandomCode(3);
        //store the unit of the answer manually here
        switch (randomizedPHY11) {
            case "011":
                //make final position and initial position, not displacement
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 20);
                trueAnswerUnit = PHYutils.PHYvarUnitForce.toString();
                break;
            case "101":
                //displacement and initial
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 20);
                trueAnswerUnit = PHYutils.PHYvarUnitMass.toString();
                break;
            case "110":
                //displacement and final
                firstVar = GenUtils.generateRandomInRange(1, 60);
                secondVar = GenUtils.generateRandomInRange(1, 30);
                trueAnswerUnit = PHYutils.PHYvarUnitGravField.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomizedPHY11);

        trueAnswer = Double.parseDouble(PHY11.solveMissing(randomizedPHY11, firstVar, secondVar));


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "How many newtons of weight will a %s with a mass of %s kilograms have in a gravitational field " +
                        "of %s newtons/kilogram?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "101":
                base = "An object with a weight of %s newtons experiencing a gravitational acceleration of %s meters/second^2 " +
                        "must have a mass of...";
                formatted = String.format(base, firstVar, secondVar);
                break;
            case "110":
                base = "If a %s has a weight of %s newtons, and if you know its mass is %s kilograms, calculate the " +
                        "strength of the gravitational field.";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
        }
    }

    @Override
    public String getFormattedQuestion() {
        return formatted;
    }

    @Override
    public String getTrueAnswer() {
        return String.valueOf(trueAnswer) + " " + trueAnswerUnit;
    }

    @Override
    public String getHint() {
        //set to "null" if no hint is to be included
        return "phy11";
    }
}
