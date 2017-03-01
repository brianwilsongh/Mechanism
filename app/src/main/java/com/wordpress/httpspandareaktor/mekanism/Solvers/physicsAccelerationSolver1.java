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
 * Created by Brian on 2/10/2017.
 */

public class physicsAccelerationSolver1 extends AppCompatActivity {

    private double finalAcceleration;
    private boolean finalAccelerationExists = false;

    private double finalVelocity;
    private boolean finalVelocityExists = false;

    private double finalTime;
    private boolean finalTimeExists = false;


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
        setContentView(R.layout.phys_accel_solver_1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // create the onClickListener for button that will trigger the calculation
        mMediaPlayer = MediaPlayer.create(this, R.raw.electron_hi);
        Button calcButton = (Button) findViewById(R.id.eq8_trigger_button);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

    }


    public void EQ8accelerationEnable(View view) {
//this method is declared for the first checkbox to allow inputting of final velocity to work
        EditText editText = (EditText) findViewById(R.id.eq8_input_acceleration);
        CheckBox checkBox = (CheckBox) findViewById(R.id.eq8_check_acceleration);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            finalAccelerationExists = false;
        }
    }

    public void EQ8velocityEnable(View view) {
//this method is declared for the first checkbox to allow input_position view to work
        EditText editText = (EditText) findViewById(R.id.eq8_input_velocity);
        CheckBox checkBox = (CheckBox) findViewById(R.id.eq8_check_velocity);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            finalVelocityExists = false;

        }
    }

    public void EQ8timeEnable(View view) {
//this method is declared for the first checkbox to allow input_position view to work
        EditText editText = (EditText) findViewById(R.id.eq8_input_time);
        CheckBox checkBox = (CheckBox) findViewById(R.id.eq8_check_time);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            finalTimeExists = false;

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
            Toast.makeText(this, "Need exactly 2 fields filled with numbers", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean vetData() {
        if (!finalAccelerationExists && finalVelocityExists && finalTimeExists) {
            return true;
        } else if (finalAccelerationExists && finalVelocityExists && !finalTimeExists) {
            return true;
        } else if (finalAccelerationExists && !finalVelocityExists && finalTimeExists) {
            return true;
        } else  {
            return false;
        }
    }


    private String determineAlgo() {

        // the algorithm for solving stored as an array
        int[] algo = new int[3];

        //see if first field has value, if yes set it and make existence boolean true, mark in algo

        EditText firstField = (EditText) findViewById(R.id.eq8_input_acceleration);
        if (firstField.length() != 0) {
            finalAccelerationExists = true;
            finalAcceleration = Double.parseDouble(firstField.getText().toString());
            algo[0] = 1;
        } else {
            algo[0] = 0;
            finalAccelerationExists = false;
        }

        //see if second field has value, if yes set it and make existence boolean true
        EditText secondField = (EditText) findViewById(R.id.eq8_input_velocity);
        if (secondField.length() != 0) {
            finalVelocityExists = true;
            finalVelocity = Double.parseDouble(secondField.getText().toString());
            algo[1] = 1;
        } else {
            finalVelocityExists = false;
            algo[1] = 0;
        }

        //see if third field has value, if yes set it and make existence boolean true
        EditText thirdField = (EditText) findViewById(R.id.eq8_input_time);
        if (thirdField.length() != 0) {
            finalTimeExists = true;
            finalTime = Double.parseDouble(thirdField.getText().toString());
            algo[2] = 1;
        } else {
            finalTimeExists = false;
            algo[2] = 0;
        }


        String finalString = "";
        finalString += String.valueOf(algo[0]);
        finalString += String.valueOf(algo[1]);
        finalString += String.valueOf(algo[2]);

        Log.v("Algo is", finalString);
        return finalString;

    }


    private void calculateAnswer() {

        //retrieve solution method with key algo
        String solutionMethod = determineAlgo();
        Log.v("calculateAnswer", "the algo in calculateAnswer is " + solutionMethod);
        switch (solutionMethod) {
            case "011":
                resultType = "constant acceleration = ";
                resultValue = String.valueOf(finalVelocity / finalTime);

                break;
            case "101":
                resultType = "velocity = ";
                resultValue = String.valueOf(finalAcceleration * finalTime);

                break;
            case "110":
                resultType = "time = ";
                resultValue = String.valueOf(finalVelocity / finalAcceleration);

                break;
            default:
                Log.v("Broken switch statement", " " + solutionMethod);

        }
    }


    private void buildShareString(String solutionMethod) {
        switch (solutionMethod) {
            // create the string for sharing via email or text message
            case "011":
                shareString = "[Mekanism]: given change in velocity (" + finalVelocity + ") , change in time " +
                        "(" + finalTime + "); constant acceleration =";
                break;

            case "101":
                shareString = "[Mekanism]: given acceleration (" + finalAcceleration + ") , time " +
                        "(" + finalTime + ") , ; velocity =";
                break;

            case "110":
                shareString = "[Mekanism]: given acceleration (" + finalAcceleration + ") , final velocity " +
                        "(" + finalVelocity + "); time =";
                break;

            default:
                Log.v("Switch buildShareString", " " + solutionMethod);
        }
    }
}
