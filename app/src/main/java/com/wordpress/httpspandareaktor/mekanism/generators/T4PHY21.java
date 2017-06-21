package com.wordpress.httpspandareaktor.mekanism.generators;

import com.wordpress.httpspandareaktor.mekanism.PHY.PHY21;
import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/20/17.
 */

public class T4PHY21 implements Generator {
    //this is a tier 4 generator applying PHY21
    public static byte tier = 4;
    public static boolean image = false;


    public Double firstVar;
    public Double secondVar;
    public String formatted;

    public Double trueAnswer;
    public String trueAnswerUnit;
    public Double falseAnswer;
    public Double falseAnswerTwo;

    public T4PHY21(){
        //generate a random code for the 3-var
        String randomized = GenUtils.generateRandomCode(3);
        //store the unit of the answer manually here
        switch (randomized) {
            case "011":
                //make final position and initial position, not displacement
                firstVar = GenUtils.generateRandomInRange(1, 20);
                secondVar = GenUtils.generateRandomInRange(1, 5);
                trueAnswerUnit = PHYutils.PHYvarUnitVelocity.toString();
                break;
            case "101":
                //displacement and initial
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 5);
                trueAnswerUnit = PHYutils.PHYvarUnitRadius.toString();
                break;
            case "110":
                //displacement and final
                firstVar = GenUtils.generateRandomInRange(1, 400);
                secondVar = GenUtils.generateRandomInRange(1, 1000);
                trueAnswerUnit = PHYutils.PHYvarUnitAngularVelocity.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomized);

        trueAnswer = Double.parseDouble(PHY21.solveMissing(randomized, firstVar, secondVar));


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "What is the tangential velocity of an object in uniform circular motion with a radius of %s meters and an " +
                        "angular velocity of %s radians/second?";
                formatted = String.format(base, firstVar, secondVar);
                break;
            case "101":
                base = "A %s is being swung in a circle by a rope at a constant speed. If you know that the tangential velocity is %s meters/second " +
                        "and that the angular velocity is %s radians/second, how long is the cable?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "110":
                base = "A satellite is in a continuous orbit around the planet %s, %s meters from the center " +
                        "of its gravitational field. If you know that its tangential velocity is %s meters/second, what is its " +
                        "angular velocity?";
                formatted = String.format(base, GenUtils.randomPlanet(), secondVar, firstVar);
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
        return "phy21";
    }
}
