package com.wordpress.httpspandareaktor.mechanism.generators;

import com.wordpress.httpspandareaktor.mechanism.PHY.PHY7;
import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by brian on 6/19/17.
 */

public class T4PHY7 implements Generator {
    //this is a tier 4 generator applying the PHY7 equation
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

    public T4PHY7(){
        //generate a random code for the 3-var
        String randomizedPHY7 = GenUtils.generateRandomCode(4);
        //store the unit of the answer manually here
        switch (randomizedPHY7) {
            case "0111":
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 10);
                thirdVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitFinalPosition.toString();
                break;
            case "1011":
                firstVar = GenUtils.generateRandomInRange(20, 100);
                secondVar = GenUtils.generateRandomInRange(1, 10);
                thirdVar = GenUtils.generateRandomInRange(1, 10);
                trueAnswerUnit = PHYutils.PHYvarUnitInitialPosition.toString();
                break;
            case "1101":
                firstVar = GenUtils.generateRandomInRange(20, 70);
                secondVar = GenUtils.generateRandomInRange(1, 20);
                thirdVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitVelocity.toString();
                break;
            case "1110":
                firstVar = GenUtils.generateRandomInRange(20, 100);
                secondVar = GenUtils.generateRandomInRange(1, 20);
                thirdVar = GenUtils.generateRandomInRange(1, 5);
                trueAnswerUnit = PHYutils.PHYvarUnitTime.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomizedPHY7);

        trueAnswer = Double.parseDouble(PHY7.solveMissing(randomizedPHY7, firstVar, secondVar, thirdVar));

    }

    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "0111":
                base = "A %s moving in a line starts %s meters away from a point. If it continues " +
                        "moving away at a rate of %s meters/second for %s seconds, how far will it be from the point?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar, thirdVar);
                break;
            case "1011":
                base = "A %s has a final position of %s meters, after having moved away from a point of reference at a rate of " +
                        "%s meters/second for %s seconds. What was its initial position relative to the point of reference?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar, thirdVar);
                break;
            case "1101":
                base = "If an object moves from %s to %s meters away from a specified point in %s seconds, what was its average " +
                        "velocity?";
                formatted = String.format(base, secondVar, firstVar, thirdVar);
                break;
            case "1110":
                base = "How many seconds would it take an object moving at a constant rate of %s meters/second to travel from %s to %s meters away from " +
                        "a point of reference?";
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
        return "phy7";
    }
}
