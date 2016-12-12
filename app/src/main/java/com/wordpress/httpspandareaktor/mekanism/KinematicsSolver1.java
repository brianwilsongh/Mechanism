package com.wordpress.httpspandareaktor.mekanism;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Brian on 12/11/2016.
 */

public class KinematicsSolver1 extends AppCompatActivity{

    String finalDisplacement;
    boolean displacementExists = false;
    String finalVelocity;
    boolean velocityExists = false;
    String finalAcceleration;
    boolean accelerationExists = false;
    String finalTime;
    boolean timeExists = false;
    private MediaPlayer mMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kinematics_solver_1);

        // create the onClickListener for button that will trigger the calculation
        mMediaPlayer = MediaPlayer.create(this, R.raw.electron_hi);
        Button calcButton = (Button) findViewById(R.id.trigger_button);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineAlgo();
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
    public void sendData(String algo) throws NullPointerException {
        //figure out the final variables
        if (vetData()) {
            mMediaPlayer.start();
            try {
                Intent i = new Intent(this, ShowCalculation.class);
                i.putExtra("positionData", finalDisplacement);
                i.putExtra("velocityData", finalVelocity);
                i.putExtra("accelerationData", finalAcceleration);
                i.putExtra("timeData", finalTime);
                i.putExtra("algo", algo);
                startActivity(i);
            } catch (NullPointerException e) {
                Log.v("MainActivity.java", "Null Pointer Exception" + e);
            }
        } else {
            Toast.makeText(this, "Need three fields filled with numbers", Toast.LENGTH_SHORT).show();
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

    public void determineAlgo() {

        //see if position field has value, if yes set it and make existence boolean true

        EditText posField = (EditText) findViewById(R.id.input_position);
        if (posField.length() != 0) {
            displacementExists = true;
            finalDisplacement = posField.getText().toString();
        } else {
            displacementExists = false;
        }

        //see if velo field has value, if yes set it and make existence boolean true
        EditText veloField = (EditText) findViewById(R.id.input_velocity);
        if (veloField.length() != 0) {
            velocityExists = true;
            finalVelocity = veloField.getText().toString();
        } else {
            velocityExists = false;
        }

        //see if accel field has value, if yes set it and make existence boolean true
        EditText accelField = (EditText) findViewById(R.id.input_acceleration);
        if (accelField.length() != 0) {
            accelerationExists = true;
            finalAcceleration = accelField.getText().toString();
        } else {
            accelerationExists = false;
        }

        //see if time field has value, if yes set it and make existence boolean true
        EditText timeField = (EditText) findViewById(R.id.input_time);
        if (timeField.length() != 0) {
            timeExists = true;
            finalTime = timeField.getText().toString();
        } else {
            timeExists = false;
        }


        // determine algo array which codes for method to arrive at solution in next activity
        // algo array position 0 is displacement, position 1 is velocity, position 2 is accel, pos 3 is time
        int[] algo = new int[4];
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
        sendData(finalString);


    }

}
