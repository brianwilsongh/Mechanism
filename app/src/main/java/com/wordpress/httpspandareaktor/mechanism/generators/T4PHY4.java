package com.wordpress.httpspandareaktor.mechanism.generators;

import com.wordpress.httpspandareaktor.mechanism.PHY.PHY4;
import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by brian on 6/16/17.
 */

public class T4PHY4 implements Generator {
    //this is a tier 4 generator applying the PHY4 equation
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

    public T4PHY4(){
        //generate a random code for the 3-var
        String randomizedPHY4 = GenUtils.generateRandomCode(4);
        //store the unit of the answer manually here
        switch (randomizedPHY4) {
            case "0111":
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 5);
                thirdVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitDisplacement.toString();
                break;
            case "1011":
                firstVar = GenUtils.generateRandomInRange(1, 20);
                secondVar = GenUtils.generateRandomInRange(1, 5);
                thirdVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitVelocity.toString();
                break;
            case "1101":
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 20);
                thirdVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitAverageAcceleration.toString();
                break;
            case "1110":
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 20);
                thirdVar = GenUtils.generateRandomInRange(1, 5);
                trueAnswerUnit = PHYutils.PHYvarUnitTime.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomizedPHY4);

        trueAnswer = Double.parseDouble(PHY4.solveMissing(randomizedPHY4, firstVar, secondVar, thirdVar));


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "0111":
                base = "How far would a %s moving with an initial velocity of %s meters/second and accelerating at a rate " +
                        "of %s meters/second^2 travel after %s seconds?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar, thirdVar);
                break;
            case "1011":
                base = "Determine the initial velocity of a %s that moved %s meters after %s seconds with a constant acceleration of %s meters/second^2.";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, thirdVar, secondVar);
                break;
            case "1101":
                base = "A %s moved %s meters after %s seconds, with a starting velocity of %s meters/second. Determine the constant acceleration of the object throughout this time interval.";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, thirdVar, secondVar);
                break;
            case "1110":
                base = "How long would it take a %s to travel %s meters given an initial velocity of %s meters/second and a constant acceleration of %s meters/second^2?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar, thirdVar);
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
        return "phy4";
    }
}
