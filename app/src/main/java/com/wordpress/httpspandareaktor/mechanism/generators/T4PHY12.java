package com.wordpress.httpspandareaktor.mechanism.generators;

import com.wordpress.httpspandareaktor.mechanism.PHY.PHY12;
import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by brian on 6/19/17.
 */

public class T4PHY12 implements Generator {
    //this is a tier 4 generator applying PHY12
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

    public T4PHY12(){
        //generate a random code for the 3-var
        String randomizedPHY12 = GenUtils.generateRandomCode(4);
        //store the unit of the answer manually here
        switch (randomizedPHY12) {
            case "0111":
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 10);
                thirdVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitForce.toString();
                break;
            case "1011":
                firstVar = GenUtils.generateRandomInRange(20, 100);
                secondVar = GenUtils.generateRandomInRange(1, 10);
                thirdVar = GenUtils.generateRandomInRange(1, 10);
                trueAnswerUnit = PHYutils.PHYvarUnitMass.toString();
                break;
            case "1101":
                firstVar = GenUtils.generateRandomInRange(20, 70);
                secondVar = GenUtils.generateRandomInRange(1, 20);
                thirdVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitMass.toString();
                break;
            case "1110":
                firstVar = GenUtils.generateRandomInRange(20, 100);
                secondVar = GenUtils.generateRandomInRange(10, 80);
                thirdVar = GenUtils.generateRandomInRange(1, 10);
                trueAnswerUnit = PHYutils.PHYvarUnitDistance.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomizedPHY12);

        trueAnswer = Double.parseDouble(PHY12.solveMissing(randomizedPHY12, firstVar, secondVar, thirdVar));

    }

    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "0111":
                base = "What is the gravitational force that object A of mass %s kilograms and object B of mass %s kilograms exert on each " +
                        "other given that they are %s meters apart?";
                formatted = String.format(base, secondVar, firstVar, thirdVar);
                break;
            case "1011":
                base = "An object exerts a gravitational force of %s newtons on a %s with a mass of %s kilograms. If they are %s meters apart, what is the mass of " +
                        "the object?";
                formatted = String.format(base, firstVar, GenUtils.normalObject(), secondVar, thirdVar);
                break;
            case "1101":
                base = "Two objects are exerting a gravitational force of %s newtons upon each other. If the mass of one object is %s kilograms, " +
                        "what is the mass of the second object if the distance between them is %s meters?";
                formatted = String.format(base, firstVar, secondVar, thirdVar);
                break;
            case "1110":
                base = "Two objects of mass %s and %s kilograms exert a gravitational force of %s newtons on each other. Based on this " +
                        "information, how far apart are they?";
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
        return "phy12";
    }
}
