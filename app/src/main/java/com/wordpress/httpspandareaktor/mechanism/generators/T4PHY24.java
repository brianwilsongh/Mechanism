package com.wordpress.httpspandareaktor.mechanism.generators;

import com.wordpress.httpspandareaktor.mechanism.PHY.PHY24;
import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by brian on 6/21/17.
 */

public class T4PHY24 implements Generator {
    //this is a tier 4 generator applying the PHY24 equation
    public static byte tier = 4;
    public static boolean image = false;


    public Double firstVar;
    public Double secondVar;
    public Double thirdVar;
    public String formatted;

    public Double trueAnswer;
    public String trueAnswerUnit;
    public Double falseAnswer;
    public Double falseAnswerTwo;

    public T4PHY24(){
        //generate a random code for the 4-var
        String randomized = GenUtils.generateRandomCode(4);
        //store the unit of the answer manually here
        switch (randomized) {
            case "0111":
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 50);
                thirdVar = GenUtils.generateRandomInRange(1, 900);
                trueAnswerUnit = PHYutils.PHYvarUnitDeltaGravPotentialEnergy.toString();
                break;
            case "1011":
                firstVar = GenUtils.generateRandomInRange(1, 200);
                secondVar = GenUtils.generateRandomInRange(1, 5);
                thirdVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitMass.toString();
                break;
            case "1101":
                firstVar = GenUtils.generateRandomInRange(1, 500);
                secondVar = GenUtils.generateRandomInRange(1, 20);
                thirdVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitGravField.toString();
                break;
            case "1110":
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 90);
                thirdVar = GenUtils.generateRandomInRange(1, 500);
                trueAnswerUnit = PHYutils.PHYvarUnitDeltaHeight.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomized);

        trueAnswer = GenUtils.truncateDecimals(Double.parseDouble(PHY24.solveMissing(randomized, firstVar, secondVar, thirdVar)), 5);


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "0111":
                base = "What is the change in gravitational potential energy of a %s kilogram %s in a gravitational " +
                        "field of %s newtons/kilogram if it is raised %s meters off the ground?";
                formatted = String.format(base, firstVar, GenUtils.normalObject(), secondVar, thirdVar);
                break;
            case "1011":
                base = "If a %s is raised an additional %s meters off the ground, the gravitational potential energy of it changes " +
                        "by %s joules. If the gravitational field is %s newtons/kilogram, what must the mass of the object be?";
                formatted = String.format(base, GenUtils.normalObject(), thirdVar, firstVar, secondVar);
                break;
            case "1101":
                base = "Calculate the strength of the gravitational field that a %s weighing %s kilograms is in. Consider that its gravitational potential " +
                        "energy was changed by %s joules after it was moved by %s meters off the ground.";
                formatted = String.format(base, GenUtils.normalObject(), secondVar, firstVar, thirdVar);
                break;
            case "1110":
                base = "In a gravitational field of %s newtons/kilogram, an object weighing %s kilograms has its gravitational potential " +
                        "energy changed by %s joules. What must've been its change in height?";
                formatted = String.format(base, thirdVar, secondVar, firstVar);
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
        return "phy24";
    }
}
