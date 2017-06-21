package com.wordpress.httpspandareaktor.mechanism.generators;

import com.wordpress.httpspandareaktor.mechanism.PHY.PHY10;
import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by brian on 6/19/17.
 */

public class T4PHY10 implements Generator {


    //this is a tier 4 generator applying PHY10
    public static byte tier = 4;
    public static boolean image = false;


    public Double firstVar;
    public Double secondVar;
    public String formatted;

    public Double trueAnswer;
    public String trueAnswerUnit;
    public Double falseAnswer;
    public Double falseAnswerTwo;

    public T4PHY10(){
        //generate a random code for the 3-var
        String randomizedPHY10 = GenUtils.generateRandomCode(3);
        //store the unit of the answer manually here
        switch (randomizedPHY10) {
            case "011":
                //make final position and initial position, not displacement
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 10);
                trueAnswerUnit = PHYutils.PHYvarUnitForce.toString();
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
                trueAnswerUnit = PHYutils.PHYvarUnitAverageAcceleration.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomizedPHY10);

        trueAnswer = Double.parseDouble(PHY10.solveMissing(randomizedPHY10, firstVar, secondVar));


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "A %s with a mass of %s kilograms accelerating at a rate of %s meters/second^2 has how many newtons of force moving it?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "101":
                base = "An object accelerating at a rate of %s meters/second^2, being moved by a force of %s newtons, must have a mass of...";
                formatted = String.format(base, secondVar, firstVar);
                break;
            case "110":
                base = "If a %s with a mass of %s kilograms is being acted on by a force of %s Newtons, and if there are no " +
                        "other forces acting on the object, what must its acceleration be?";
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
        return "phy10";
    }
}
