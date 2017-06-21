package com.wordpress.httpspandareaktor.mechanism.generators;

import com.wordpress.httpspandareaktor.mechanism.PHY.PHY15;
import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by brian on 6/20/17.
 */

public class T4PHY15 implements Generator {
    //this is a tier 4 generator applying PHY15
    public static byte tier = 4;
    public static boolean image = false;


    public Double firstVar;
    public Double secondVar;
    public String formatted;

    public Double trueAnswer;
    public String trueAnswerUnit;
    public Double falseAnswer;
    public Double falseAnswerTwo;

    public T4PHY15(){
        //generate a random code for the 3-var
        String randomizedPHY15 = GenUtils.generateRandomCode(3);
        //store the unit of the answer manually here
        switch (randomizedPHY15) {
            case "011":
                //make final position and initial position, not displacement
                firstVar = GenUtils.generateRandomInRange(10, 50);
                secondVar = GenUtils.generateRandomInRange(1, 60);
                trueAnswerUnit = PHYutils.PHYvarUnitMomentum.toString();
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
        buildQuestionString(randomizedPHY15);

        trueAnswer = Double.parseDouble(PHY15.solveMissing(randomizedPHY15, firstVar, secondVar));


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "What is the momentum of a %s with a mass of %s kilograms moving at a velocity of %s meters/second?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "101":
                base = "A %s with a velocity of %s meters/second has a momentum of %s kilogram-meters/second. What must its mass be?";
                formatted = String.format(base, GenUtils.normalObject(), secondVar, firstVar);
                break;
            case "110":
                base = "An object with a momentum of %s kilogram-meters/second and a mass of %s kilograms must be moving at a speed of...";
                formatted = String.format(base, firstVar, secondVar);
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
        return "phy15";
    }
}
