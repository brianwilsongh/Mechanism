package com.wordpress.httpspandareaktor.mekanism.solvers;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.httpspandareaktor.mekanism.PHYutils;
import com.wordpress.httpspandareaktor.mekanism.R;
import com.wordpress.httpspandareaktor.mekanism.ShowCalculation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Brian on 3/15/2017.
 */

public class Triangle extends AppCompatActivity {

    /* This is a unique solver class for solving triangles
     */

    //declare/init the variables
    private Double angleA;
    private Double angleB;
    private Double angleC;
    private Double sideA;
    private Double sideB;
    private Double sideC;

    // declare/init the fields
    EditText angleAField;
    EditText angleBField;
    EditText angleCField;
    EditText sideAField;
    EditText sideBField;
    EditText sideCField;

    ImageView equationImage;
    TextView equationDescriptor;
    Button calcButton;

    //other variables for other functions
    MediaPlayer mMediaPlayer;
    String shareString;
    String resultType;
    String resultVal;
    String symSolution;

    //array coding for variable presence
    String arrayCode = "";

    //equation code ex. "phy1"
    String equationCode;

    //stores subject based on equationCode
    String subjectCode;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solver_four_var);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // match all declared variables to matching view in the layout file
        equationImage = (ImageView) findViewById(R.id.fourVarImage);
        equationDescriptor = (TextView) findViewById(R.id.fourVarDescriptor);
        calcButton = (Button) findViewById(R.id.fourVarButton);

        //find the right edit text fields, assign them to local variable declarations
        angleAField = (EditText) findViewById(R.id.triangleDegreesA);
        angleBField = (EditText) findViewById(R.id.triangleDegreesB);
        angleCField = (EditText) findViewById(R.id.triangleDegreesC);
        sideAField = (EditText) findViewById(R.id.triangleSideA);
        sideBField = (EditText) findViewById(R.id.triangleSideB);
        sideCField = (EditText) findViewById(R.id.triangleSideC);


        //retrieve info from intent to set up the matching class
        Intent i = getIntent();
        equationCode = i.getStringExtra("equationCode");
        setTitle("Equation ID:  " + equationCode);
        determineSubjectCode();

        //set up the views given data from the class and other stuff
        int newImageId = (i.getIntExtra("imageResourceId", 0));
        if (newImageId != 0) {
            equationImage.setImageResource(newImageId);
        }

        //set on click listener for the button
        mMediaPlayer = MediaPlayer.create(this, R.raw.electron_hi);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    attemptSolve();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }


    private void attemptSolve() throws Exception {
        //method that is called on Calculate button click
        if (validateInput()) {
            //if the input is good, play sound and get stuff ready for the intent
            mMediaPlayer.start();

            //here is the intent to travel to the solution page
            Intent i = new Intent(this, ShowCalculation.class);
            i.putExtra("resultSym", symSolution);
            i.putExtra("resultUnits", resultType);
            i.putExtra("resultValue", resultVal);
            i.putExtra("shareString", shareString + resultVal);
            i.putExtra("subjectCode", subjectCode);
            // send extra as a string
            i.putExtra("eqnType", "Physics");
            Log.v("4var attemptSolve meth ", "sent " + resultType + " " + resultVal + " sharestring: " + shareString);

            //reset that array in case they want to go back later
            arrayCode = "";

            startActivity(i);
        } else {
            Toast.makeText(this, "fill 3/4 fields to solve for missing variable", Toast.LENGTH_LONG).show();
        }
    }


    private boolean validateInput() {
        //set up shareString in case user is returning here
        shareString = "(" + getString(R.string.app_name) + " - " + subjectCode + ") -- Given variables ";



        String validationString = buildValidationString();
        switch (validationString) {
            case "111000":
                Toast.makeText(this, "Cannot solve sides of the triangle with just angles!", Toast.LENGTH_LONG);
                return false;

        }

        //assign edittext field values to the local variables for angles and sides
        angleA = Double.parseDouble(angleAField.getText().toString());
        return false;
    }


    public void determineSubjectCode() {
        //determines the subject code given the equation code, basically first three letters stored as var
        subjectCode = String.valueOf(equationCode.charAt(0)) + String.valueOf(equationCode.charAt(1)) +
                String.valueOf(equationCode.charAt(2));
        Log.v("determineSubjectCode", "determined that the subject code is" + subjectCode);
    }

    public String buildValidationString() {

        //returns a string like "001010" for the validateInput method
        String returnString = "";
        if (angleAField.getText().toString() == "") {
            returnString += 0;
        } else { returnString += 1;}
        if (angleBField.getText().toString() == "") {
            returnString += 0;
        } else { returnString += 1;}
        if (angleCField.getText().toString() == "") {
            returnString += 0;
        } else { returnString += 1;}
        if (sideAField.getText().toString() == "") {
            returnString += 0;
        } else { returnString += 1;}
        if (sideBField.getText().toString() == "") {
            returnString += 0;
        } else { returnString += 1;}
        if (sideCField.getText().toString() == "") {
            returnString += 0;
        } else { returnString += 1;}
        return returnString;
    }

    public boolean getThirdAngle() {
        //if there are 2 angles, fill in the third
        int[] pattern = new int[3];
        if (angleAField.getText().toString() == "") {
            pattern[0] = 0;
        } else { pattern[0] = 1; }

        if (angleBField.getText().toString() == "") {
            pattern[1] = 0;
        } else { pattern[1] = 1; }

        if (angleCField.getText().toString() == "") {
            pattern[2] = 0;
        } else { pattern[2] = 1; }

        Log.v("Triangle.java", "pattern array for angles is" + pattern);
        switch (Arrays.toString(pattern)){

            //depending on which angles are in place, find the third by subtracting the other 2 from 180
            case "[0, 1, 1]":
                angleA = (180 - (Double.parseDouble(angleBField.getText().toString())) -
                        (Double.parseDouble(angleCField.getText().toString())));
                angleAField.setText(angleA.toString());
                return true;

            case "[1, 0, 1]":
                angleB = (180 - (Double.parseDouble(angleAField.getText().toString())) -
                        (Double.parseDouble(angleCField.getText().toString())));
                angleBField.setText(angleB.toString());
                return true;

            case "[1, 1, 0]":
                angleC = (180 - (Double.parseDouble(angleAField.getText().toString())) -
                        (Double.parseDouble(angleBField.getText().toString())));
                angleCField.setText(angleC.toString());
                return true;

            default:
                return false;
        }
    }

    public double lawOfCosines(double c, double a, double b, double angleC){
        return 0;
    }



}

