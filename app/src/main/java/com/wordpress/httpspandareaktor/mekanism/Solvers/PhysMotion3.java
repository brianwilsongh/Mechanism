package com.wordpress.httpspandareaktor.mekanism.solvers;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.wordpress.httpspandareaktor.mekanism.R;
import com.wordpress.httpspandareaktor.mekanism.ShowCalculation;

/**
 * Created by Brian on 12/31/2016.
 */

public class PhysMotion3 extends AppCompatActivity {

    private double finalFinalVelocity;
    private boolean finalVelocityExists = false;

    private double finalInitialVelocity;
    private boolean initialVelocityExists = false;

    private double finalAcceleration;
    private boolean accelerationExists = false;

    private double finalDisplacement;
    private boolean displacementExists = false;

    //String for sending text/email messages with the problem
    String shareString;

    //declare parameters that will be sent by the intent to ShowCalculation
    String resultType;
    String resultValue;
    String resultValue2;

    private MediaPlayer mMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kinematics_solver_3);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // create the onClickListener for button that will trigger the calculation
        mMediaPlayer = MediaPlayer.create(this, R.raw.electron_hi);
        Button calcButton = (Button) findViewById(R.id.eq3_trigger_button);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

    }


    public void EQ3finalVelocityEnable(View view) {
//this method is declared for the first checkbox to allow input_position view to work
        EditText editText = (EditText) findViewById(R.id.eq3_input_final_velocity);
        CheckBox checkBox = (CheckBox) findViewById(R.id.eq3_check_final_velocity);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            finalVelocityExists = false;
        }
    }

    public void EQ3initialVelocityEnable(View view) {
//this method is declared for the first checkbox to allow input_position view to work
        EditText editText = (EditText) findViewById(R.id.eq3_input_initial_velocity);
        CheckBox checkBox = (CheckBox) findViewById(R.id.eq3_check_initial_velocity);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            initialVelocityExists = false;

        }
    }

    public void EQ3accelerationEnable(View view) {
//this method is declared for the first checkbox to allow input_position view to work
        EditText editText = (EditText) findViewById(R.id.eq3_input_acceleration);
        CheckBox checkBox = (CheckBox) findViewById(R.id.eq3_check_acceleration);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            accelerationExists = false;

        }
    }

    public void EQ3displacementEnable(View view) {
//this method is declared for the first checkbox to allow input_position view to work
        EditText editText = (EditText) findViewById(R.id.eq3_input_displacement);
        CheckBox checkBox = (CheckBox) findViewById(R.id.eq3_check_displacement);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            displacementExists = false;

        }
    }


    //The final method for sending the accumulated data to the ShowCalculation activity

    public void sendData() throws NullPointerException {
        //figure out the final variables
        calculateAnswer();
        if (vetData()) {
            mMediaPlayer.start();
            buildShareString(determineAlgo());
            Log.v("sendData", "Share string sent is " + shareString);
            try {
                Intent i = new Intent(this, ShowCalculation.class);
                i.putExtra("resultType", resultType);
                i.putExtra("resultValue", resultValue);
                i.putExtra("resultValue2", resultValue2);
                i.putExtra("shareString", shareString);
                // send extra as a string
                i.putExtra("rootClass", "Physics");
                Log.v("sendData method", "sent" + resultType + resultValue + resultValue2);
                startActivity(i);
            } catch (NullPointerException e) {
                Log.v("MainActivity.java", "Null Pointer Exception" + e);
            }
        } else {
            Toast.makeText(this, "Need exactly 3 fields filled with numbers", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean vetData() {
        if (finalVelocityExists && initialVelocityExists && accelerationExists && !displacementExists) {
            return true;
        } else if (finalVelocityExists && initialVelocityExists && !accelerationExists && displacementExists) {
            return true;
        } else if (finalVelocityExists && !initialVelocityExists && accelerationExists && displacementExists) {
            return true;
        } else if (!finalVelocityExists && initialVelocityExists && accelerationExists && displacementExists) {
            return true;
        } else {
            return false;
        }
    }


    private String determineAlgo() {

        // the algorithm for solving stored as an array
        int[] algo = new int[4];

        //see if position field has value, if yes set it and make existence boolean true, mark in algo

        EditText finalVeloField = (EditText) findViewById(R.id.eq3_input_final_velocity);
        if (finalVeloField.length() != 0) {
            finalVelocityExists = true;
            finalFinalVelocity = Double.parseDouble(finalVeloField.getText().toString());
            algo[0] = 1;
        } else {
            algo[0] = 0;
            finalVelocityExists = false;
        }

        //see if velo field has value, if yes set it and make existence boolean true
        EditText initialVeloField = (EditText) findViewById(R.id.eq3_input_initial_velocity);
        if (initialVeloField.length() != 0) {
            initialVelocityExists = true;
            finalInitialVelocity = Double.parseDouble(initialVeloField.getText().toString());
            algo[1] = 1;
        } else {
            initialVelocityExists = false;
            algo[1] =0;
        }

        //see if accel field has value, if yes set it and make existence boolean true
        EditText accelField = (EditText) findViewById(R.id.eq3_input_acceleration);
        if (accelField.length() != 0) {
            accelerationExists = true;
            finalAcceleration = Double.parseDouble(accelField.getText().toString());
            algo[2] = 1;
        } else {
            accelerationExists = false;
            algo[2] = 0;
        }

        //see if time field has value, if yes set it and make existence boolean true
        EditText timeField = (EditText) findViewById(R.id.eq3_input_displacement);
        if (timeField.length() != 0) {
            displacementExists = true;
            finalDisplacement = Double.parseDouble(timeField.getText().toString());
            algo[3] = 1;
        } else {
            displacementExists = false;
            algo[3] = 0;
        }


        String finalString = "";
        finalString += String.valueOf(algo[0]);
        finalString += String.valueOf(algo[1]);
        finalString += String.valueOf(algo[2]);
        finalString += String.valueOf(algo[3]);

        Log.v("Algo is", finalString);
        Log.v("Values ", "v\u209c" + finalFinalVelocity + "v\u2080 " + finalInitialVelocity + "a " + finalAcceleration + "t " + finalDisplacement);
        return finalString;

    }


    private void calculateAnswer() {

        //retrieve solution method with key algo
        String solutionMethod = determineAlgo();
        Log.v("calculateAnswer", "the algo in calculateAnswer is " + solutionMethod);
        switch (solutionMethod) {
            case "0111":
                resultType = "final velocity / velocity @ time=t (v\u209c) =  ";
                resultValue = String.valueOf(Math.sqrt(Math.pow(finalInitialVelocity, 2) + (2 * finalAcceleration * finalDisplacement)));

                break;
            case "1011":
                resultType = "initial velocity (v\u2080) = ";
                resultValue = String.valueOf(Math.sqrt(Math.pow(finalFinalVelocity, 2) - (2 * finalAcceleration * finalDisplacement)));

                break;
            case "1101":
                resultType = "acceleration (a) = ";
                resultValue = String.valueOf((Math.pow(finalFinalVelocity, 2) - Math.pow(finalInitialVelocity, 2))/(2 * finalDisplacement));

                break;
            case "1110":
                resultType = "displacement (d) = ";
                resultValue = String.valueOf((Math.pow(finalFinalVelocity, 2) - Math.pow(finalInitialVelocity, 2))/(2 * finalAcceleration));
                break;
            default:
                Log.v("Broken switch statement", " " + solutionMethod);

        }
    }


    private void buildShareString(String solutionMethod) {
        switch (solutionMethod) {
            // create the string for sharing via email or text message
            case "0111":
                shareString = "[Mekanism]: given initial velocity (" + finalInitialVelocity + ") , acceleration " +
                        "(" + finalAcceleration + ") , time (" + finalDisplacement + "); Final velocity (velocity at time=t) =";
                break;

            case "1011":
                shareString = "[Mekanism]: given final velocity (" + finalFinalVelocity + ") , acceleration " +
                        "(" + finalAcceleration + ") , displacement (" + finalDisplacement + "); Initial velocity =";
                break;

            case "1101":
                shareString = "[Mekanism]: given final velocity (" + finalFinalVelocity + ") , initial velocity " +
                        "(" + finalInitialVelocity + ") , displacement (" + finalDisplacement + "); Acceleration =";
                break;

            case "1110":
                shareString = "[Mekanism]: given final velocity (" + finalFinalVelocity + ") , initial velocity " +
                        "(" + finalInitialVelocity + ") , acceleration (" + finalAcceleration + "); Displacement =";
                break;

            default:
                Log.v("Switch buildShareString", " " + solutionMethod);
        }
    }


}
