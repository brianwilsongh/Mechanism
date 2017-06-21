package com.wordpress.httpspandareaktor.mechanism.generators;

import com.wordpress.httpspandareaktor.mechanism.PHY.PHY26;
import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by brian on 6/21/17.
 */

public class T4PHY26 implements Generator {
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

    public T4PHY26(){
        String randomized = GenUtils.generateRandomCode(3);
        //store the unit of the answer manually here
        switch (randomized) {
            case "011":
                //make final position and initial position, not displacement
                firstVar = GenUtils.generateRandomInRange(0, 100);
                secondVar = GenUtils.generateRandomInRange(0, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitPeriod.toString();
                break;
            case "101":
                //displacement and initial
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(0, 80);
                trueAnswerUnit = PHYutils.PHYvarUnitLength.toString();
                break;
            case "110":
                //displacement and final
                firstVar = GenUtils.generateRandomInRange(0, 60);
                secondVar = GenUtils.generateRandomInRange(0, 30);
                trueAnswerUnit = PHYutils.PHYvarUnitGravField.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomized);

        trueAnswer = GenUtils.truncateDecimals(Double.parseDouble(PHY26.solveMissing(randomized, firstVar, secondVar)), 5);


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "What is the period of a simple pendulum in a gravitational field of %s newtons/kilogram if the length of the " +
                        "arm is %s meters?";
                formatted = String.format(base, secondVar, firstVar);
                break;
            case "101":
                base = "A simple pendulum in a gravitational field of %s newtons/kilogram has a period of %s seconds. What must be the" +
                        " length of its arm?";
                formatted = String.format(base, secondVar, firstVar);
                break;
            case "110":
                base = "A pendulum with an arm length of %s meters has a period of %s seconds. What is the gravitational acceleration in " +
                        "the location where the pendulum is swinging?";
                formatted = String.format(base, secondVar, firstVar);
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
        return "phy26";
    }
}
