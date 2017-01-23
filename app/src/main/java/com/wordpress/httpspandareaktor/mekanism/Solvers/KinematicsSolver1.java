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
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.httpspandareaktor.mekanism.R;
import com.wordpress.httpspandareaktor.mekanism.ShowCalculation;

/**
 * Created by Brian on 12/11/2016.
 */

public class KinematicsSolver1 extends AppCompatActivity {

    private double finalDisplacement;
    private boolean displacementExists = false;

    private double finalVelocity;
    private boolean velocityExists = false;

    private double finalAcceleration;
    private boolean accelerationExists = false;

    private double finalTime;
    private boolean timeExists = false;

    //String for sending text/email messages with the problem
    private String shareString;

    //declare parameters that will be sent by the intent to ShowCalculation
    private String resultType;
    private String resultValue;
    private String resultValue2;

    private MediaPlayer mMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kinematics_solver_1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // create the onClickListener for button that will trigger the calculation
        mMediaPlayer = MediaPlayer.create(this, R.raw.electron_hi);
        Button calcButton = (Button) findViewById(R.id.trigger_button);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

    }


    public void positionEnable(View view) {
        //this method is declared for the first checkbox to allow input_position view to work
        EditText editText = (EditText) findViewById(R.id.input_position);
        CheckBox checkBox = (CheckBox) findViewById(R.id.check_deltax);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            displacementExists = false;
        }
    }

    public void velocityEnable(View view) {
        //this method is declared for the first checkbox to allow input_position view to work
        EditText editText = (EditText) findViewById(R.id.input_velocity);
        CheckBox checkBox = (CheckBox) findViewById(R.id.check_velocity);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            velocityExists = false;

        }
    }

    public void accelerationEnable(View view) {
        //this method is declared for the first checkbox to allow input_position view to work
        EditText editText = (EditText) findViewById(R.id.input_acceleration);
        CheckBox checkBox = (CheckBox) findViewById(R.id.check_acceleration);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            accelerationExists = false;

        }
    }

    public void timeEnable(View view) {
        //this method is declared for the first checkbox to allow input_position view to work
        EditText editText = (EditText) findViewById(R.id.input_time);
        CheckBox checkBox = (CheckBox) findViewById(R.id.check_deltat);
        if (checkBox.isChecked()) {
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
            editText.setHint("unknown");
            timeExists = false;

        }
    }


    /**
     * The final method for sending the accumulated data to the ShowCalculation activity
     */
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

                // send extra as serializable
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
        if (displacementExists && velocityExists && accelerationExists && !timeExists) {
            return true;
        } else if (displacementExists && velocityExists && !accelerationExists && timeExists) {
            return true;
        } else if (displacementExists && !velocityExists && accelerationExists && timeExists) {
            return true;
        } else if (!displacementExists && velocityExists && accelerationExists && timeExists) {
            return true;
        } else {
            return false;
        }
    }

    private String determineAlgo() {

        //see if position field has value, if yes set it and make existence boolean true

        EditText posField = (EditText) findViewById(R.id.input_position);
        if (posField.length() != 0) {
            displacementExists = true;
            finalDisplacement = Double.parseDouble(posField.getText().toString());
        } else {
            displacementExists = false;
        }

        //see if velo field has value, if yes set it and make existence boolean true
        EditText veloField = (EditText) findViewById(R.id.input_velocity);
        if (veloField.length() != 0) {
            velocityExists = true;
            finalVelocity = Double.parseDouble(veloField.getText().toString());
        } else {
            velocityExists = false;
        }

        //see if accel field has value, if yes set it and make existence boolean true
        EditText accelField = (EditText) findViewById(R.id.input_acceleration);
        if (accelField.length() != 0) {
            accelerationExists = true;
            finalAcceleration = Double.parseDouble(accelField.getText().toString());
        } else {
            accelerationExists = false;

        }

        //see if time field has value, if yes set it and make existence boolean true
        EditText timeField = (EditText) findViewById(R.id.input_time);
        if (timeField.length() != 0) {
            timeExists = true;
            finalTime = Double.parseDouble(timeField.getText().toString());
        } else {
            timeExists = false;
        }

        // the algorithm for solving stored as an array
        int[] algo = new int[4];

        // determine algo array which codes for method to arrive at solution in next activity
        // algo array position 0 is displacement, position 1 is velocity, position 2 is accel, pos 3 is time
        if (displacementExists) {
            algo[0] = 1;
        } else {
            algo[0] = 0;
        }
        if (velocityExists) {
            algo[1] = 1;
        } else {
            algo[1] = 0;
        }
        if (accelerationExists) {
            algo[2] = 1;
        } else {
            algo[2] = 0;
        }
        if (timeExists) {
            algo[3] = 1;
        } else {
            algo[3] = 0;
        }

        String finalString = "";
        finalString += String.valueOf(algo[0]);
        finalString += String.valueOf(algo[1]);
        finalString += String.valueOf(algo[2]);
        finalString += String.valueOf(algo[3]);

        Log.v("Algo is", finalString);
        Log.v("Values ", "d " + finalDisplacement + "v " + finalVelocity + "a " + finalAcceleration + "t " + finalTime);
        return finalString;


    }

    private void calculateAnswer() {

        //retrieve solution method with key algo
        String solutionMethod = determineAlgo();
        Log.v("calculateAnswer", "the algo in calculateAnswer is " + solutionMethod);
        switch (solutionMethod) {
            case "0111":
                resultType = "displacement (d) =  ";
                resultValue = String.valueOf((finalVelocity * finalTime) + ((0.5) * finalAcceleration * (Math.pow(finalTime, 2))));

                break;
            case "1011":
                resultType = "velocity (v) = ";
                resultValue = String.valueOf((finalDisplacement / finalTime) - (0.5 * finalAcceleration * finalTime));

                break;
            case "1101":
                resultType = "acceleration (a) = ";
                resultValue = String.valueOf(((2 * finalDisplacement) / (Math.pow(finalTime, 2))) - ((2 * finalVelocity) / finalTime));

                break;
            case "1110":
                resultType = "time (t) = ";
                resultValue = String.valueOf(quadraticConvert(finalDisplacement, finalVelocity, finalAcceleration, true));
                resultValue2 = String.valueOf(quadraticConvert(finalDisplacement, finalVelocity, finalAcceleration, false));
                if (Double.parseDouble(resultValue) < 0) {
                    resultValue = "";
                }

                if (Double.parseDouble(resultValue2) < 0) {
                    resultValue2 = "";
                }
                break;
            default:
                Log.v("Broken switch statement", " " + solutionMethod);

        }
    }

    private double quadraticConvert(double d, double v, double a, boolean plusBeforeSqrt) {
        double quadA = (0.5) * a;
        double quadB = v;
        double quadC = -d;
        if (plusBeforeSqrt) {
            double solution =
                    //TOP PART
                    (-quadB +
                            Math.sqrt((Math.pow(quadB, 2)) - ((4) * (quadA) * (quadC)))
                    )
                            //END TOP
                            / (2 * quadA);
            return solution;
        } else {
            double solution =
                    //TOP PART
                    (-quadB -
                            Math.sqrt((Math.pow(quadB, 2)) - ((4) * (quadA) * (quadC)))
                    )
                            //END TOP
                            / (2 * quadA);
            return solution;
        }
    }

    private void buildShareString(String solutionMethod) {
        switch (solutionMethod) {
            // create the string for sharing via email or text message
            case "0111":
                shareString = "[Mekanism]: given velocity (" + finalVelocity + ") , acceleration " +
                        "(" + finalAcceleration + ") , time (" + finalTime + "); Displacement =";
                break;

            case "1011":
                shareString = "[Mekanism]: given displacement (" + finalDisplacement + ") , acceleration " +
                        "(" + finalAcceleration + ") , time (" + finalTime + "); Velocity =";
                break;

            case "1101":
                shareString = "[Mekanism]: given displacement (" + finalDisplacement + ") , velocity " +
                        "(" + finalVelocity + ") , time (" + finalTime + "); Acceleration =";
                break;

            case "1110":
                shareString = "[Mekanism]: given displacement (" + finalDisplacement + ") , velocity " +
                        "(" + finalVelocity + ") , acceleration (" + finalAcceleration + "); Time =";
                break;

            default:
                Log.v("Switch buildShareString", " " + solutionMethod);
        }
    }

}
