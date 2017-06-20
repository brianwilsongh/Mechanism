package com.wordpress.httpspandareaktor.mekanism.generators;

import com.wordpress.httpspandareaktor.mekanism.PHY.PHY13;
import com.wordpress.httpspandareaktor.mekanism.PHYutils;

/**
 * Created by brian on 6/19/17.
 */

public class T4PHY13 implements Generator {
    //this is a tier 4 generator applying PHY11
    public static byte tier = 4;
    public static boolean image = false;


    public Double firstVar;
    public Double secondVar;
    public String formatted;

    public Double trueAnswer;
    public String trueAnswerUnit;
    public Double falseAnswer;
    public Double falseAnswerTwo;

    public T4PHY13(){
        //generate a random code for the 3-var PHY1
        String randomizedPHY13 = GenUtils.generateRandomCode(3);
        //store the unit of the answer manually here
        switch (randomizedPHY13) {
            case "011":
                //make final position and initial position, not displacement
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 5000);
                trueAnswerUnit = PHYutils.PHYvarUnitAverageAcceleration.toString();
                break;
            case "101":
                //displacement and initial
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitVelocity.toString();
                break;
            case "110":
                //displacement and final
                firstVar = GenUtils.generateRandomInRange(1, 60);
                secondVar = GenUtils.generateRandomInRange(1, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitRadius.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomizedPHY13);

        trueAnswer = Double.parseDouble(PHY13.solveMissing(randomizedPHY13, firstVar, secondVar));


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "What is the centripetal acceleration of a %s moving in a perfectly round orbit around a planet at a constant " +
                        "velocity of %s meters/second, and %s meters away from the center of the orbit?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "101":
                base = "An object in uniform circular motion has a centripetal acceleration %s meters/second^2 with a radius " +
                        "of %s meters. What is the object's velocity?";
                formatted = String.format(base, firstVar, secondVar);
                break;
            case "110":
                base = "A %s is being spun around on a rope, undergoing uniform circular motion. If its centripetal " +
                        "acceleration is %s meters/second^2, and if its velocity is %s meters/second, how long is the rope?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
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
        return "phy13";
    }
}
