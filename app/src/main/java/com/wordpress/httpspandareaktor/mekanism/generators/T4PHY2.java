package com.wordpress.httpspandareaktor.mekanism.generators;

import com.wordpress.httpspandareaktor.mekanism.PHY.PHY2;
import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/15/17.
 */

public class T4PHY2 implements Generator {
    //this is a tier 4 generator applying PHY1
    public static byte tier = 4;
    public static boolean image = false;


    public Double firstVar;
    public Double secondVar;
    public String formatted;

    public Double trueAnswer;
    public String trueAnswerUnit;
    public Double falseAnswer;
    public Double falseAnswerTwo;

    public T4PHY2(){
        //generate a random code for the 3-var PHY1
        String randomizedPHY1 = GenUtils.generateRandomCode(3);
        //store the unit of the answer manually here
        switch (randomizedPHY1) {
            case "011":
                //make final position and initial position, not displacement
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitVelocity.toString();
                break;
            case "101":
                //displacement and initial
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitDistance.toString();
                break;
            case "110":
                //displacement and final
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitTime.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomizedPHY1);

        trueAnswer = Double.parseDouble(PHY2.solveMissing(randomizedPHY1, firstVar, secondVar));


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "A %s moves a distance of %s meters over a period of %s seconds. What was the average velocity " +
                        "over this interval of time?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "101":
                base = "A %s moves at a velocity of %s meters per second over a period of %s seconds. How many meters will it travel?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "110":
                base = "If a %s were traveling at a constant velocity of %s meters/second, how long would it take to travel %s meters?";
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
        return "phy2";
    }
}
