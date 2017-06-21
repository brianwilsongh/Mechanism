package com.wordpress.httpspandareaktor.mekanism.generators;

import com.wordpress.httpspandareaktor.mekanism.PHY.PHY23;
import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/20/17.
 */

public class T4PHY23 implements Generator {
    //this is a tier 4 generator applying PHY20
    public static byte tier = 4;
    public static boolean image = false;


    public Double firstVar;
    public Double secondVar;
    public String formatted;

    public Double trueAnswer;
    public String trueAnswerUnit;
    public Double falseAnswer;
    public Double falseAnswerTwo;

    public T4PHY23(){
        String randomized = GenUtils.generateRandomCode(3);
        //store the unit of the answer manually here
        switch (randomized) {
            case "011":
                //make final position and initial position, not displacement
                firstVar = GenUtils.generateRandomInRange(900000, 9000000);
                secondVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitGravFieldFunc.toString();
                break;
            case "101":
                //displacement and initial
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 2000);
                trueAnswerUnit = PHYutils.PHYvarUnitMass.toString();
                break;
            case "110":
                //displacement and final
                firstVar = GenUtils.generateRandomInRange(1, 5);
                secondVar = GenUtils.generateRandomInRange(900000, 9000000);
                trueAnswerUnit = PHYutils.PHYvarUnitRadius.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomized);

        trueAnswer = Double.parseDouble(PHY23.solveMissing(randomized, firstVar, secondVar));


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "Calculate the gravitational field strength %s meters from the center of planet %s, which has a mass of " +
                        "%s kilograms.";
                formatted = String.format(base, secondVar, GenUtils.randomPlanet(), firstVar);
                break;
            case "101":
                base = "If you know that the gravitational field strength %s meters from the center of planet %s is %s newtons/kilogram, " +
                        "determine the mass of the planet.";
                formatted = String.format(base, secondVar, GenUtils.randomPlanet(), firstVar);
                break;
            case "110":
                base = "You are on a spherical planet named %s and you know that its mass is %s kilograms. If the gravitational field strength " +
                        "at your exact location is %s newtons/kilogram, how far are you from the center of its core?";
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
        return "phy23";
    }
}
