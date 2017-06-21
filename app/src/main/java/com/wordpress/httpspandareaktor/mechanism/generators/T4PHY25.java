package com.wordpress.httpspandareaktor.mechanism.generators;

import com.wordpress.httpspandareaktor.mechanism.PHY.PHY25;
import com.wordpress.httpspandareaktor.mechanism.PHYutils;

/**
 * Created by brian on 6/21/17.
 */

public class T4PHY25 implements Generator {
    //this is a tier 4 generator applying PHY25
    public static byte tier = 4;
    public static boolean image = false;


    public Double firstVar;
    public Double secondVar;
    public String formatted;

    public Double trueAnswer;
    public String trueAnswerUnit;
    public Double falseAnswer;
    public Double falseAnswerTwo;

    public T4PHY25(){
        String randomized = GenUtils.generateRandomCode(3);
        //store the unit of the answer manually here
        switch (randomized) {
            case "011":
                //make final position and initial position, not displacement
                firstVar = GenUtils.generateRandomInRange(0, 100);
                secondVar = GenUtils.generateRandomInRange(-50, 50);
                trueAnswerUnit = PHYutils.PHYvarUnitForce.toString();
                break;
            case "101":
                //displacement and initial
                firstVar = GenUtils.generateRandomInRange(1, 50);
                secondVar = GenUtils.generateRandomInRange(-10, 80);
                trueAnswerUnit = PHYutils.PHYvarUnitSpringConstant.toString();
                break;
            case "110":
                //displacement and final
                firstVar = GenUtils.generateRandomInRange(0, 60);
                secondVar = GenUtils.generateRandomInRange(0, 30);
                trueAnswerUnit = PHYutils.PHYvarUnitDisplacement.toString();
                break;
        }
        //call buildQuestionString after the randomized vars have been created
        buildQuestionString(randomized);

        trueAnswer = GenUtils.truncateDecimals(Double.parseDouble(PHY25.solveMissing(randomized, firstVar, secondVar)), 5);


    }


    public void buildQuestionString(String code){
        String base;

        switch (code){
            case "011":
                base = "What is the force exerted by a spring that is displaced by %s meters if it has a spring constant of " +
                        "%s newtons/meter?";
                formatted = String.format(base, secondVar, firstVar);
                break;
            case "101":
                base = "A spring-like system is displaced by %s meters, and fights the displacement with a force of %s newtons. What " +
                        "is the spring constant?";
                formatted = String.format(base, secondVar, firstVar);
                break;
            case "110":
                base = "A spring has a constant of %s newtons/meter. After being displaced, it attempts to correct itself with a force of " +
                        "%s newtons. How many meters was it displaced?";
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
        return "phy25";
    }
}
