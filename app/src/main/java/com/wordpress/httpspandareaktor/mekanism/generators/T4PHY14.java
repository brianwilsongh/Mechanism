package com.wordpress.httpspandareaktor.mekanism.generators;

import com.wordpress.httpspandareaktor.mekanism.PHY.PHY14;
import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/20/17.
 */

public class T4PHY14 implements Generator {
    //this is a tier 4 generator applying PHY14
    public static byte tier = 4;
    public static boolean image = false;


    public Double firstVar;
    public Double secondVar;
    public String formatted;

    public Double trueAnswer;
    public String trueAnswerUnit;
    public Double falseAnswer;
    public Double falseAnswerTwo;

    public T4PHY14(){
        //generate a random code for the 3-var
        String randomizedPHY14 = GenUtils.generateRandomCode(3);
        //store the unit of the answer manually here
        switch (randomizedPHY14) {
            case "011":
                //make final position and initial position, not displacement
                firstVar = GenUtils.truncateDecimals(GenUtils.generateRandomInRange(0.0, 1.0), 2);
                secondVar = GenUtils.generateRandomInRange(1, 60);
                trueAnswerUnit = PHYutils.PHYvarUnitForce.toString();
                break;
            case "101":
                //displacement and initial
                firstVar = GenUtils.generateRandomInRange(1, 10);
                secondVar = GenUtils.generateRandomInRange(20, 50);
                trueAnswerUnit = "";
                break;
            case "110":
                //displacement and final
                firstVar = GenUtils.generateRandomInRange(1, 60);
                secondVar = GenUtils.truncateDecimals(GenUtils.generateRandomInRange(0.0, 1.0), 2);
                trueAnswerUnit = PHYutils.PHYvarUnitForce.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomizedPHY14);

        trueAnswer = Double.parseDouble(PHY14.solveMissing(randomizedPHY14, firstVar, secondVar));


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "What is the magnitude of the force of friction acting on a %s sliding on a surface? " +
                        "Assume %s as the kinetic coefficient of friction, and a normal force of %s newtons.";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "101":
                base = "What is the coefficient of friction given a moving object with a normal force of %s newtons" +
                        " and a frictional force of %s newtons opposing motion?";
                formatted = String.format(base, secondVar, firstVar);
                break;
            case "110":
                base = "If a still object requires %s newtons of force to move it, and if we know that the static coefficient of friction " +
                        "is %s, what is the magnitude of the normal force?";
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
        return "phy14";
    }
}
