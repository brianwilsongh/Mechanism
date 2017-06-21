package com.wordpress.httpspandareaktor.mekanism.generators;

import com.wordpress.httpspandareaktor.mekanism.PHY.PHY20;
import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/20/17.
 */

public class T4PHY20 implements Generator {
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

    public T4PHY20(){
        //generate a random code for the 3-var PHY19
        String randomized = GenUtils.generateRandomCode(3);
        //store the unit of the answer manually here
        switch (randomized) {
            case "011":
                //make final position and initial position, not displacement
                firstVar = GenUtils.generateRandomInRange(1, 360);
                secondVar = GenUtils.generateRandomInRange(1, 5);
                trueAnswerUnit = PHYutils.PHYvarUnitAngularVelocity.toString();
                break;
            case "101":
                //displacement and initial
                firstVar = GenUtils.generateRandomInRange(1, 5);
                secondVar = GenUtils.generateRandomInRange(1, 20);
                trueAnswerUnit = PHYutils.PHYvarUnitTheta.toString();
                break;
            case "110":
                //displacement and final
                firstVar = GenUtils.generateRandomInRange(1, 5);
                secondVar = GenUtils.generateRandomInRange(1, 30);
                trueAnswerUnit = PHYutils.PHYvarUnitTime.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomized);

        trueAnswer = Double.parseDouble(PHY20.solveMissing(randomized, firstVar, secondVar));


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "An object orbiting a planet covers %s degrees every %s seconds. What is its angular velocity (in radians/second)?";
                formatted = String.format(base, firstVar, secondVar);
                break;
            case "101":
                base = "A %s is being spun on a rope at an angular velocity of %s radians/second, undergoing uniform circular " +
                        "motion. How many degrees around the circle will the object cover in %s seconds?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "110":
                base = "How long would it take an object in a uniform circular motion to travel %s degrees at an angular velocity of " +
                        "%s radians/second?";
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
        return "phy20";
    }
}
