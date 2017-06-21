package com.wordpress.httpspandareaktor.mekanism.generators;

import com.wordpress.httpspandareaktor.mekanism.PHY.PHY17;
import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/20/17.
 */

public class T4PHY17 implements Generator {
    //this is a tier 4 generator applying PHY17
    public static byte tier = 4;
    public static boolean image = false;


    public Double firstVar;
    public Double secondVar;
    public String formatted;

    public Double trueAnswer;
    public String trueAnswerUnit;
    public Double falseAnswer;
    public Double falseAnswerTwo;

    public T4PHY17(){
        //generate a random code for the 3-var
        String randomizedPHY17 = GenUtils.generateRandomCode(3);
        //store the unit of the answer manually here
        switch (randomizedPHY17) {
            case "011":
                //make final position and initial position, not displacement
                firstVar = GenUtils.generateRandomInRange(10, 60);
                secondVar = GenUtils.generateRandomInRange(1, 60);
                trueAnswerUnit = PHYutils.PHYvarUnitImpulse.toString();
                break;
            case "101":
                //displacement and initial
                firstVar = GenUtils.generateRandomInRange(1, 40);
                secondVar = GenUtils.generateRandomInRange(20, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitMass.toString();
                break;
            case "110":
                //displacement and final
                firstVar = GenUtils.generateRandomInRange(1, 60);
                secondVar = GenUtils.generateRandomInRange(10, 40);
                trueAnswerUnit = PHYutils.PHYvarUnitVelocity.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomizedPHY17);

        trueAnswer = GenUtils.truncateDecimals(Double.parseDouble(PHY17.solveMissing(randomizedPHY17, firstVar, secondVar)), 5);


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "A force acts on a %s with a mass of %s kilograms, changing its velocity by %s meters/second. " +
                        "What is the impulse of this force?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "101":
                base = "A force with an impulse of %s newton-seconds acts on a %s, causing its velocity to change by %s meters/second. " +
                        "Its mass must be...";
                formatted = String.format(base, firstVar, GenUtils.normalObject(), secondVar);
                break;
            case "110":
                base = "A %s kilogram object is hit by a force with an impulse of %s newton-seconds. Determine the change in its velocity.";
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
        return "phy17";
    }
}
