package com.wordpress.httpspandareaktor.mekanism.generators;
import com.wordpress.httpspandareaktor.mekanism.PHY.PHY1;

/**
 * Created by brian on 6/12/17.
 */

public class T4PHY1 {

//
//    String s = PHY1.solveMissing();

    //this is a tier 4 question generator applying PHY1
    public static byte tier = 4;
    public static boolean image = false;


    public static Double firstVar;
    public static Double secondVar;
    public static String formatted;

    public static Double trueAnswer;
    public static Double falseAnswer;
    public static Double falseAnswerTwo;

    public static void QPHY1(){
        //generate a random code for the 3-var PHY1

        String randomizedPHY1 = GenUtils.generateRandomCode(3);

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


    public static void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "After a few seconds, a(n) %s moving in a straight line ends up %s meters away from a point of reference. " +
                        "At the beginning, it was %s meters away from the point of reference. Find the displacement?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "101":
                base = "A(n) %s is moving away from a point of reference in a straight line. " +
                        "Its position was measured at two time intervals - an initial and final one." +
                        "You know that its displacement was %s and that the initial position was %s away from the point of reference." +
                        "What is the location of final position relative to the point of origin?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
            case "110":
                base = "A(n) %s is moving from a point of reference in a straight line. " +
                        "Its displacement is %s from its initial position - %s from the point of reference." +
                        "Where is the final position relative to the point of reference?";
                formatted = String.format(base, GenUtils.normalObject(), firstVar, secondVar);
                break;
        }
    }



}
