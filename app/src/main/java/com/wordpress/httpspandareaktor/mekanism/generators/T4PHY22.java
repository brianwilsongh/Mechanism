package com.wordpress.httpspandareaktor.mekanism.generators;

import com.wordpress.httpspandareaktor.mekanism.PHY.PHY22;
import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/20/17.
 */

public class T4PHY22 implements Generator{
    //this is a tier 4 generator applying the PHY22 equation
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

    public T4PHY22(){
        //generate a random code for the 3-var
        String randomized = GenUtils.generateRandomCode(4);
        //store the unit of the answer manually here
        switch (randomized) {
            case "0111":
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 500);
                thirdVar = GenUtils.generateRandomInRange(1, 9000);
                trueAnswerUnit = PHYutils.PHYvarUnitForce.toString();
                break;
            case "1011":
                firstVar = GenUtils.generateRandomInRange(1, 20);
                secondVar = GenUtils.generateRandomInRange(1, 5);
                thirdVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitMass.toString();
                break;
            case "1101":
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 20);
                thirdVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitVelocity.toString();
                break;
            case "1110":
                firstVar = GenUtils.generateRandomInRange(1, 500);
                secondVar = GenUtils.generateRandomInRange(1, 400);
                thirdVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitRadius.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomized);

        trueAnswer = GenUtils.truncateDecimals(Double.parseDouble(PHY22.solveMissing(randomized, firstVar, secondVar, thirdVar)), 5);


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "0111":
                base = "A %s kg satellite orbiting the planet %s forms a uniform circular motion. The satellite moves at a speed of %s " +
                        "meters/second %s meters away from the center of the circle. What is the centripetal force?";
                formatted = String.format(base, firstVar, GenUtils.randomPlanet(), secondVar, thirdVar);
                break;
            case "1011":
                base = "A %s in uniform circular motion, moving at a speed of %s meters/second %s meters away from the center " +
                        "of the circle has a centripetal force of %s newtons. What must be its mass?";
                formatted = String.format(base, GenUtils.normalObject(), secondVar, firstVar, thirdVar);
                break;
            case "1101":
                base = "A %s in uniform circular motion, held in place by a centriptal force of %s newtons. The object is %s kilograms " +
                        "and circles %s meters away from the center. Determine the speed of the object.";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar, thirdVar);
                break;
            case "1110":
                base = "If a satellite orbits the planet %s, being held in place by a centripetal force of %s newtons, how far from " +
                        "the center of the planet's gravitational field is it orbiting? Consider that the satellite has a mass of %s kilograms and " +
                        "moves at a speed of %s meters/second.";
                formatted = String.format(base, GenUtils.randomPlanet(), firstVar, secondVar, thirdVar);
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
        return "phy22";
    }
}
