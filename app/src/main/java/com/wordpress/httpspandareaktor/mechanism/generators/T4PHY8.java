package com.wordpress.httpspandareaktor.mechanism.generators;

import com.wordpress.httpspandareaktor.mechanism.PHY.PHY8;
import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by brian on 6/19/17.
 */

public class T4PHY8 implements Generator {
    //this is a tier 4 generator applying the PHY8 equation
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

    public T4PHY8(){
        //generate a random code for the 3-var
        String randomizedPHY8 = GenUtils.generateRandomCode(4);
        //store the unit of the answer manually here
        switch (randomizedPHY8) {
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
                secondVar = GenUtils.generateRandomInRange(10, 80);
                thirdVar = GenUtils.generateRandomInRange(1, 10);
                trueAnswerUnit = PHYutils.PHYvarUnitTime.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomizedPHY8);

        trueAnswer = Double.parseDouble(PHY8.solveMissing(randomizedPHY8, firstVar, secondVar, thirdVar));

    }

    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "0111":
                base = "What is the distance covered by an object as it accelerates from %s to %s meters/second in %s seconds?";
                formatted = String.format(base, secondVar, firstVar, thirdVar);
                break;
            case "1011":
                base = "A %s with an intial velocity of %s meters/second covers %s meters as it accelerates for %s seconds. What is the " +
                        "final velocity?";
                formatted = String.format(base, GenUtils.normalObject(), secondVar, firstVar, thirdVar);
                break;
            case "1101":
                base = "A %s travels %s meters as it accelerates from an unknown initial velocity to a final velocity of %s over the course " +
                        "of %s seconds. Determine the initial velocity.";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar, thirdVar);
                break;
            case "1110":
                base = "How many seconds would it take an object to accelerate from %s to %s meters/second, knowing that the object " +
                        "covered %s meters during this period of acceleration?";
                formatted = String.format(base, secondVar, thirdVar, firstVar);
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
        return "phy8";
    }
}
