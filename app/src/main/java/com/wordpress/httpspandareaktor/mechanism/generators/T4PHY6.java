package com.wordpress.httpspandareaktor.mechanism.generators;

import com.wordpress.httpspandareaktor.mechanism.PHY.PHY6;
import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by brian on 6/18/17.
 */

public class T4PHY6 implements Generator {
    //this is a tier 4 generator applying the PHY6 equation
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

    public T4PHY6(){
        //generate a random code for the 3-var
        String randomizedPHY6 = GenUtils.generateRandomCode(4);
        //store the unit of the answer manually here
        switch (randomizedPHY6) {
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
                trueAnswerUnit = PHYutils.PHYvarUnitDisplacement.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomizedPHY6);

        trueAnswer = Double.parseDouble(PHY6.solveMissing(randomizedPHY6, firstVar, secondVar, thirdVar));

    }

    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "0111":
                base = "If a %s has a starting velocity of %s meters/second, and a constant acceleration of %s meters/second^2, " +
                        "what will the velocity be after the object has traveled %s meters?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar, thirdVar);
                break;
            case "1011":
                base = "A moving %s has a final velocity of %s meters/second after being displaced %s meters. If the acceleration " +
                        "of the object throughout the displacement was %s meters/second^2, what was the initial velocity?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, thirdVar, secondVar);
                break;
            case "1101":
                base = "After moving %s meters, an accelerating %s has a velocity of %s meters/second. Before this movement, its velocity was " +
                        "%s meters/second. What was the acceleration during this period of movement?";
                formatted = String.format(base, thirdVar, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "1110":
                base = "Accelerating at a constant rate of %s meters/second^2, a %s goes from %s to %s meters/second. How much " +
                        "distance would be covered during this period of acceleration?";
                formatted = String.format(base, thirdVar, GenUtils.normalObject(), secondVar, firstVar);
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
        return "phy6";
    }
}
