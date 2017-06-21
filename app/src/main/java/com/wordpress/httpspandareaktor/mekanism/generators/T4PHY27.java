package com.wordpress.httpspandareaktor.mekanism.generators;

import com.wordpress.httpspandareaktor.mekanism.PHY.PHY27;
import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/21/17.
 */

public class T4PHY27 implements Generator {
    //this is a tier 4 generator applying PHY25
    public static byte tier = 4;
    public static boolean image = false;


    public Double firstVar;
    public Double secondVar;
    public String formatted;

    public Double trueAnswer;
    public String trueAnswerUnit;
    public Double falseAnswer;
    public Double falseAnswerTwo;

    public T4PHY27(){
        String randomized = GenUtils.generateRandomCode(3);
        //store the unit of the answer manually here
        switch (randomized) {
            case "011":
                //make final position and initial position, not displacement
                firstVar = GenUtils.truncateDecimals(GenUtils.generateRandomInRange(1E10, 1E12), 5);
                secondVar = GenUtils.generateRandomInRange(0, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitVelocity.toString();
                break;
            case "101":
                //displacement and initial
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(10, 80);
                trueAnswerUnit = PHYutils.PHYvarUnitMass.toString();
                break;
            case "110":
                //displacement and final
                firstVar = GenUtils.generateRandomInRange(0, 60);
                secondVar = GenUtils.truncateDecimals(GenUtils.generateRandomInRange(1E10, 1E12), 5);
                trueAnswerUnit = PHYutils.PHYvarUnitRadius.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomized);

        trueAnswer = GenUtils.truncateDecimals(Double.parseDouble(PHY27.solveMissing(randomized, firstVar, secondVar)), 5);


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "Calculate the minimum required velocity to escape planet %s, which has a mass of %s kilograms. Assume you are %s meters away from " +
                        "its exact center.";
                formatted = String.format(base, GenUtils.randomPlanet(), firstVar, secondVar);
                break;
            case "101":
                base = "You are on the surface of planet %s, which is %s meters from the center. If the escape velocity is %s meters/second, " +
                        "what is the mass of the planet?";
                formatted = String.format(base, GenUtils.randomPlanet(), secondVar, firstVar);
                break;
            case "110":
                base = "You're on the planet %s, which has a mass of %s kilograms. If the escape velocity from your location is %s " +
                        "meters/second, how many meters are you from the center of the planet?";
                formatted = String.format(base, GenUtils.randomPlanet(), secondVar, firstVar);
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
        return "phy27";
    }
}
