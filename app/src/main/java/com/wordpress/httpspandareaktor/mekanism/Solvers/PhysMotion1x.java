package com.wordpress.httpspandareaktor.mekanism.Solvers;

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
 * Created by Brian on 1/20/2017.
 */


public class PhysMotion1x extends AppCompatActivity {

    private double finalDisplacement;
    private boolean finalDisplacementExists = false;

    private double finalInitialDisplacement;
    private boolean finalInitialDisplacementExists = false;

    private double finalVelocity;
    private boolean finalVelocityExists = false;

    private double finalTime;
    private boolean timeExists = false;

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
        setContentView(R.layout.kinematics_solver_1x);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // create the onClickListener for button that will trigger the calculation
        mMediaPlayer = MediaPlayer.create(this, R.raw.electron_hi);
        Button calcButton = (Button) findViewById(R.id.eq1x_trigger_button);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

    }


    public void EQ1xEnableDisplacement(View view) {
//this method is declared for the first checkbox to allow input_position view to work
        EditText editText = (EditText) findViewById(R.id.eq1x_input_displacement);
        CheckBox checkBox = (CheckBox) findViewById(R.id.eq1x_check_displacement);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            finalDisplacementExists = false;
        }
    }

    public void EQ1xinitialDisplacementEnable(View view) {
//this method is declared for the first checkbox to allow input_position view to work
        EditText editText = (EditText) findViewById(R.id.eq1x_input_initial_displacement);
        CheckBox checkBox = (CheckBox) findViewById(R.id.eq1x_check_initial_displacement);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            finalInitialDisplacementExists = false;

        }
    }

    public void EQ1xvelocityEnable(View view) {
//this method is declared for the first checkbox to allow input_position view to work
        EditText editText = (EditText) findViewById(R.id.eq1x_input_velocity);
        CheckBox checkBox = (CheckBox) findViewById(R.id.eq1x_check_velocity);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            finalVelocityExists = false;

        }
    }

    public void EQ1xtimeEnable(View view) {
//this method is declared for the first checkbox to allow input_position view to work
        EditText editText = (EditText) findViewById(R.id.eq1x_input_time);
        CheckBox checkBox = (CheckBox) findViewById(R.id.eq1x_check_time);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            timeExists = false;

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
        if (finalDisplacementExists && finalInitialDisplacementExists && finalVelocityExists && !timeExists) {
            return true;
        } else if (finalDisplacementExists && finalInitialDisplacementExists && !finalVelocityExists && timeExists) {
            return true;
        } else if (finalDisplacementExists && !finalInitialDisplacementExists && finalVelocityExists && timeExists) {
            return true;
        } else if (!finalDisplacementExists && finalInitialDisplacementExists && finalVelocityExists && timeExists) {
            return true;
        } else {
            return false;
        }
    }


    private String determineAlgo() {

        // the algorithm for solving stored as an array
        int[] algo = new int[4];

        //see if position field has value, if yes set it and make existence boolean true, mark in algo

        EditText firstField = (EditText) findViewById(R.id.eq1x_input_displacement);
        if (firstField.length() != 0) {
            finalDisplacementExists = true;
            finalDisplacement = Double.parseDouble(firstField.getText().toString());
            algo[0] = 1;
        } else {
            algo[0] = 0;
            finalVelocityExists = false;
        }

        //see if velo field has value, if yes set it and make existence boolean true
        EditText secondField = (EditText) findViewById(R.id.eq1x_input_initial_displacement);
        if (secondField.length() != 0) {
            finalInitialDisplacementExists = true;
            finalInitialDisplacement = Double.parseDouble(secondField.getText().toString());
            algo[1] = 1;
        } else {
            finalInitialDisplacementExists = false;
            algo[1] =0;
        }

        //see if accel field has value, if yes set it and make existence boolean true
        EditText thirdField = (EditText) findViewById(R.id.eq1x_input_velocity);
        if (thirdField.length() != 0) {
            finalVelocityExists = true;
            finalVelocity = Double.parseDouble(thirdField.getText().toString());
            algo[2] = 1;
        } else {
            finalVelocityExists = false;
            algo[2] = 0;
        }

        //see if time field has value, if yes set it and make existence boolean true
        EditText fourthField = (EditText) findViewById(R.id.eq1x_input_time);
        if (fourthField.length() != 0) {
            timeExists = true;
            finalTime = Double.parseDouble(fourthField.getText().toString());
            algo[3] = 1;
        } else {
            timeExists = false;
            algo[3] = 0;
        }


        String finalString = "";
        finalString += String.valueOf(algo[0]);
        finalString += String.valueOf(algo[1]);
        finalString += String.valueOf(algo[2]);
        finalString += String.valueOf(algo[3]);

        Log.v("Algo is", finalString);
        Log.v("Values ", "v\u209c" + finalDisplacement + "v\u2080 " + finalInitialDisplacement + "a " + finalVelocity + "t " + finalTime);
        return finalString;

    }


    private void calculateAnswer() {

        //retrieve solution method with key algo
        String solutionMethod = determineAlgo();
        Log.v("calculateAnswer", "the algo in calculateAnswer is " + solutionMethod);
        switch (solutionMethod) {
            case "0111":
                resultType = getString(R.string.distance_desc) + " = ";
                resultValue = String.valueOf(finalInitialDisplacement + (finalVelocity * finalTime));

                break;
            case "1011":
                resultType = getString(R.string.init_displacement_desc) + " = ";
                resultValue = String.valueOf((finalVelocity * finalTime) - finalDisplacement);

                break;
            case "1101":
                resultType = getString(R.string.velocity_desc) + " = ";
                resultValue = String.valueOf((finalDisplacement - finalInitialDisplacement) / finalTime);

                break;
            case "1110":
                resultType = getString(R.string.time_desc) + " = ";
                resultValue = String.valueOf((finalDisplacement - finalInitialDisplacement) / finalVelocity);
                break;
            default:
                Log.v("Broken switch statement", " " + solutionMethod);

        }
    }


    private void buildShareString(String solutionMethod) {
        switch (solutionMethod) {
            // create the string for sharing via email or text message
            case "0111":
                shareString = "[Mekanism]: given initial displacement (" + finalInitialDisplacement + ") , velocity " +
                        "(" + finalVelocity + ") , time (" + finalTime + "); Displacement =";
                break;

            case "1011":
                shareString = "[Mekanism]: given displacement (" + finalDisplacement + ") , velocity " +
                        "(" + finalVelocity + ") , time (" + finalTime + "); Initial displacement =";
                break;

            case "1101":
                shareString = "[Mekanism]: given displacement (" + finalDisplacement + ") , initial displacement " +
                        "(" + finalInitialDisplacement + ") , time (" + finalTime + "); Velocity =";
                break;

            case "1110":
                shareString = "[Mekanism]: given displacement (" + finalDisplacement + ") , initial displacement " +
                        "(" + finalInitialDisplacement + ") , velocity (" + finalVelocity + "); Time =";
                break;

            default:
                Log.v("Switch buildShareString", " " + solutionMethod);
        }
    }


}