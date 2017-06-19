package com.wordpress.httpspandareaktor.mekanism.generators;

import com.wordpress.httpspandareaktor.mekanism.PHY.PHY4;
import com.wordpress.httpspandareaktor.mekanism.PHY.PHY5;
import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/18/17.
 */

public class T4PHY5 implements Generator {
    //this is a tier 4 generator applying the PHY5 equation
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

    public T4PHY5(){
    //generate a random code for the 3-var PHY1
    String randomizedPHY5 = GenUtils.generateRandomCode(4);
    //store the unit of the answer manually here
        switch (randomizedPHY5) {
        case "0111":
            firstVar = GenUtils.generateRandomInRange(1, 50);
            secondVar = GenUtils.generateRandomInRange(1, 5);
            thirdVar = GenUtils.generateRandomInRange(1, 50);
            trueAnswerUnit = PHYutils.PHYvarUnitVelocity.toString();
            break;
        case "1011":
            firstVar = GenUtils.generateRandomInRange(20, 100);
            secondVar = GenUtils.generateRandomInRange(1, 5);
            thirdVar = GenUtils.generateRandomInRange(1, 10);
            trueAnswerUnit = PHYutils.PHYvarUnitVelocity.toString();
            break;
        case "1101":
            firstVar = GenUtils.generateRandomInRange(10, 50);
            secondVar = GenUtils.generateRandomInRange(1, 10);
            thirdVar = GenUtils.generateRandomInRange(1, 50);
            trueAnswerUnit = PHYutils.PHYvarUnitAverageAcceleration.toString();
            break;
        case "1110":
            firstVar = GenUtils.generateRandomInRange(20, 100);
            secondVar = GenUtils.generateRandomInRange(1, 10);
            thirdVar = GenUtils.generateRandomInRange(1, 5);
            trueAnswerUnit = PHYutils.PHYvarUnitTime.toString();
            break;
    }
    //call buildQuestionString after the randomized vars have been created
    buildQuestionString(randomizedPHY5);

    trueAnswer = Double.parseDouble(PHY5.solveMissing(randomizedPHY5, firstVar, secondVar, thirdVar));


}

    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "0111":
                base = "A %s starts with an initial velocity of %s meters/second, and accelerates at a rate of %s meters/second^2 for the next " +
                        "%s seconds. What is its final velocity (velocity at time = t) ?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar, thirdVar);
                break;
            case "1011":
                base = "After accelerating at a rate of %s meters/second^2 for %s seconds, a %s ends up with a final velocity of " +
                        "%s meters/second. What was its velocity prior to this period of acceleration?";
                formatted = String.format(base, secondVar, thirdVar, GenUtils.normalObject(), firstVar);
                break;
            case "1101":
                base = "After %s seconds, an accelerating %s ends up with a velocity of %s meters/second. " +
                        "Given an initial velocity of %s meters/second, what was the rate of acceleration?";
                formatted = String.format(base, thirdVar, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "1110":
                base = "How many seconds would it take a %s, starting with a velocity of %s meters/second and accelerating at " +
                        "a rate of %s meters/second^2, to reach a speed of %s meters/second?";
                formatted = String.format(base, GenUtils.normalObject(), secondVar, thirdVar, firstVar);
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
        return "phy5";
    }
}
