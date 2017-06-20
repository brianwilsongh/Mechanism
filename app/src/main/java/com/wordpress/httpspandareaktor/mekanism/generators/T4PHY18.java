package com.wordpress.httpspandareaktor.mekanism.generators;

import com.wordpress.httpspandareaktor.mekanism.PHY.PHY18;
import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/20/17.
 */

public class T4PHY18 implements Generator {
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

    public T4PHY18(){
        //generate a random code for the 3-var PHY1
        String randomizedPHY18 = GenUtils.generateRandomCode(4);
        //store the unit of the answer manually here
        switch (randomizedPHY18) {
            case "0111":
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 10);
                thirdVar = GenUtils.generateRandomInRange(1, 90);
                trueAnswerUnit = PHYutils.PHYvarUnitWork.toString();
                break;
            case "1011":
                firstVar = GenUtils.generateRandomInRange(20, 100);
                secondVar = GenUtils.generateRandomInRange(1, 10);
                thirdVar = GenUtils.generateRandomInRange(1, 90);
                trueAnswerUnit = PHYutils.PHYvarUnitForce.toString();
                break;
            case "1101":
                firstVar = GenUtils.generateRandomInRange(20, 70);
                secondVar = GenUtils.generateRandomInRange(1, 20);
                thirdVar = GenUtils.generateRandomInRange(1, 90);
                trueAnswerUnit = PHYutils.PHYvarUnitDistance.toString();
                break;
            case "1110":
                firstVar = GenUtils.generateRandomInRange(20, 100);
                secondVar = GenUtils.generateRandomInRange(10, 80);
                thirdVar = GenUtils.generateRandomInRange(1, 10);
                trueAnswerUnit = PHYutils.PHYvarUnitTheta.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomizedPHY18);

        trueAnswer = Double.parseDouble(PHY18.solveMissing(randomizedPHY18, firstVar, secondVar, thirdVar));

    }

    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "0111":
                base = "What is the work performed by a force of %s newtons that moves a %s %s meters if the force acts at an " +
                        "angle %s degrees from the line of motion?";
                formatted = String.format(base, firstVar, GenUtils.normalObject(), secondVar, thirdVar);
                break;
            case "1011":
                base = "%s newton-meters of work is performed on a %s that has been dragged %s meters in a straight line. If the force was applied " +
                        "%s degrees from the line, what was its magnitude?";
                formatted = String.format(base, firstVar, GenUtils.normalObject(), secondVar, thirdVar);
                break;
            case "1101":
                base = "%s newton-meters of work is performed by dragging a %s on a cable. A force of %s newtons applied %s degrees relative to " +
                        "the cable. How far was it moved?";
                formatted = String.format(base, firstVar, GenUtils.normalObject(), secondVar, thirdVar);
                break;
            case "1110":
                base = "A force of %s newtons is applied to move an object by %s meters. If %s newton-meters of work was performed, " +
                        "what angle relative to the vector of motion was the force vector pointing?";
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
        return "phy18";
    }

}
