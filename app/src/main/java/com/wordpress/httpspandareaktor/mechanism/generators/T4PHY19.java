package com.wordpress.httpspandareaktor.mechanism.generators;

import com.wordpress.httpspandareaktor.mechanism.PHY.PHY19;
import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by brian on 6/20/17.
 */

public class T4PHY19 implements Generator{
    //this is a tier 4 generator applying PHY19
    public static byte tier = 4;
    public static boolean image = false;


    public Double firstVar;
    public Double secondVar;
    public String formatted;

    public Double trueAnswer;
    public String trueAnswerUnit;
    public Double falseAnswer;
    public Double falseAnswerTwo;

    public T4PHY19(){
        //generate a random code for the 3-var
        String randomizedPHY19 = GenUtils.generateRandomCode(3);
        //store the unit of the answer manually here
        switch (randomizedPHY19) {
            case "011":
                //make final position and initial position, not displacement
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 20);
                trueAnswerUnit = PHYutils.PHYvarUnitKineticEnergy.toString();
                break;
            case "101":
                //displacement and initial
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 20);
                trueAnswerUnit = PHYutils.PHYvarUnitMass.toString();
                break;
            case "110":
                //displacement and final
                firstVar = GenUtils.generateRandomInRange(1, 60);
                secondVar = GenUtils.generateRandomInRange(1, 30);
                trueAnswerUnit = PHYutils.PHYvarUnitVelocity.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomizedPHY19);

        trueAnswer = GenUtils.truncateDecimals(Double.parseDouble(PHY19.solveMissing(randomizedPHY19, firstVar, secondVar)), 5);


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "What is the kinetic energy of a %s of mass %s kilograms moving at a speed of %s meters/second?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "101":
                base = "The kinetic energy of a moving object is %s joules. If the velocity of the object is %s meters/second, what " +
                        "is its mass?";
                formatted = String.format(base, firstVar, secondVar);
                break;
            case "110":
                base = "A %s with a mass of %s kilograms strikes a sensor that measures that the kinetic energy of the object while it was " +
                        "in motion was %s joules. What was the velocity of that object?";
                formatted = String.format(base, GenUtils.normalObject(), secondVar, firstVar);
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
        return "phy19";
    }
}
