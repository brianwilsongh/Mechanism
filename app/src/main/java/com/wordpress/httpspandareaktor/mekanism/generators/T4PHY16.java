package com.wordpress.httpspandareaktor.mekanism.generators;

import com.wordpress.httpspandareaktor.mekanism.PHY.PHY16;
import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/20/17.
 */

public class T4PHY16 implements Generator {
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

    public T4PHY16(){
        //generate a random code for the 3-var PHY1
        String randomizedPHY16 = GenUtils.generateRandomCode(3);
        //store the unit of the answer manually here
        switch (randomizedPHY16) {
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
                trueAnswerUnit = PHYutils.PHYvarUnitForce.toString();
                break;
            case "110":
                //displacement and final
                firstVar = GenUtils.generateRandomInRange(1, 60);
                secondVar = GenUtils.generateRandomInRange(10, 40);
                trueAnswerUnit = PHYutils.PHYvarUnitTime.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomizedPHY16);

        trueAnswer = Double.parseDouble(PHY16.solveMissing(randomizedPHY16, firstVar, secondVar));


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "A force of %s newtons acts on a %s for %s seconds. Calculate the impulse of this force.";
                formatted = String.format(base, firstVar, GenUtils.normalObject(), secondVar);
                break;
            case "101":
                base = "A force acts on a %s for %s seconds. A sensor tells you that the impulse of this interaction " +
                        "was %s newton-seconds. What was the magnitude of that force, assuming that it was constant throughout " +
                        "this time interval?";
                formatted = String.format(base, GenUtils.normalObject(), secondVar, firstVar);
                break;
            case "110":
                base = "A force of %s newtons acts on an object. If the impulse of the force was %s newton-seconds, how long " +
                        "did the force act on the object?";
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
        return "phy16";
    }
}
