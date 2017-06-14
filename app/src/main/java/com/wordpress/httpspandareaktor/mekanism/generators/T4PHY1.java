package com.wordpress.httpspandareaktor.mekanism.generators;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.wordpress.httpspandareaktor.mekanism.PHY.PHY1;
import com.wordpress.httpspandareaktor.mekanism.PHYutils;


/**
 * Created by brian on 6/12/17.
 */

public class T4PHY1 implements Generator {

//
//    String s = PHY1.solveMissing();

    //this is a tier 4 question question_cell applying PHY1
    public static byte tier = 4;
    public static boolean image = false;


    public Double firstVar;
    public Double secondVar;
    public String formatted;

    public Double trueAnswer;
    public String trueAnswerUnit;
    public Double falseAnswer;
    public Double falseAnswerTwo;

    public T4PHY1(){
        //generate a random code for the 3-var PHY1
        String randomizedPHY1 = GenUtils.generateRandomCode(3);
        trueAnswerUnit = PHYutils.PHYvarUnitDistance.toString();
        switch (randomizedPHY1) {
            case "011":
                //make final position and initial position, not displacement
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 50);
                break;
            case "101":
                //displacement and initial
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 50);
                break;
            case "110":
                //displacement and final
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(1, 50);
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomizedPHY1);

        trueAnswer = Double.parseDouble(PHY1.solveMissing(randomizedPHY1, firstVar, secondVar));


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "After a few seconds, a %s moving in a straight line ends up %s meters away from a point of reference. " +
                        "Before, it was %s meters away from the point of reference. What is the displacement?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "101":
                base = "A %s is moving away from a point of reference in a straight line. " +
                        "Its position was measured at two time intervals - an initial and final one." +
                        "You know that its displacement was %s meters and that the initial position was %s meters away from the point of reference." +
                        "What is the location of final position relative to the point of origin?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "110":
                base = "A %s is moving from a point of reference in a straight line. " +
                        "Its displacement is %s from its initial position. The final position is %s meters." +
                        "Where is the initial position relative to the point of reference?";
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
        return String.valueOf(trueAnswer);
    }

    @Override
    public String getHint() {
        //set to "null" if no hint is to be included
        return "phy1";
    }
}
