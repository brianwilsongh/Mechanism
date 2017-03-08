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

import com.wordpress.httpspandareaktor.mekanism.R;
import com.wordpress.httpspandareaktor.mekanism.ShowCalculation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Brian on 3/8/2017.
 */

public class FourVar extends AppCompatActivity {

    /* This is a generic FOUR variable solver object, meant to fetch user input (3 fields) from the matching
    layout file and send it to the appropriate [SUB][CODE] equation object along with solver code. Will receive the
    return values and then send to the solution page.
     */

    //declare/init the vars and presence booleans
    private double varA;
    private double varB;
    private double varC;
    private double varD;
    private boolean presenceVarA = false;
    private boolean presenceVarB = false;
    private boolean presenceVarC = false;
    private boolean presenceVarD = false;

    // declare variable names for the fields
    TextView symbolA;
    TextView symbolB;
    TextView symbolC;
    TextView symbolD;

    EditText fieldA;
    EditText fieldB;
    EditText fieldC;
    EditText fieldD;

    TextView unitsA;
    TextView unitsB;
    TextView unitsC;
    TextView unitsD;

    ImageView equationImage;
    TextView equationDescriptor;
    Button calcButton;

    //other variables for other functions
    MediaPlayer mMediaPlayer;
    String shareString;
    String resultType;
    String resultVal;
    String symSolution;

    //2 parameters for the solution method sitting in equationCode class
    double solveMethodParam1;
    double solveMethodParam2;
    double solveMethodParam3;

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

        symbolA = (TextView) findViewById(R.id.fourVarSymbolA);
        symbolB = (TextView) findViewById(R.id.fourVarSymbolB);
        symbolC = (TextView) findViewById(R.id.fourVarSymbolC);
        symbolD = (TextView) findViewById(R.id.fourVarSymbolD);

        fieldA = (EditText) findViewById(R.id.fourVarFieldA);
        fieldB = (EditText) findViewById(R.id.fourVarFieldB);
        fieldC = (EditText) findViewById(R.id.fourVarFieldC);
        fieldD = (EditText) findViewById(R.id.fourVarFieldD);

        unitsA = (TextView) findViewById(R.id.fourVarUnitsA);
        unitsB = (TextView) findViewById(R.id.fourVarUnitsB);
        unitsC = (TextView) findViewById(R.id.fourVarUnitsC);
        unitsD = (TextView) findViewById(R.id.fourVarUnitsD);

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

        try {
            setClassInput();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void attemptSolve() throws Exception {
        //method that is called on Calculate button click
        if (validateInput()) {
            //if the input is good, play sound and get stuff ready for the intent
            mMediaPlayer.start();

            //get solve method from equationCode class
            Class equationClass = Class.forName("com.wordpress.httpspandareaktor.mekanism." + subjectCode + "." + equationCode);
            Log.v("FourVar.attemptSolve", "fetched " + equationClass.getCanonicalName());

            //time to retrieve the method and store the solution given parameters
            //getDeclaredMethod requires name of the method and the type classes of all params it takes...
            Method solveMissing = equationClass.getDeclaredMethod("solveMissing", String.class, double.class, double.class, double.class);
            solveMissing.setAccessible(true);
            Object temp = equationClass.newInstance();
            resultVal = (String) solveMissing.invoke(temp, arrayCode, solveMethodParam1, solveMethodParam2, solveMethodParam3);

            //here is the intent to travel to the solution page
            Intent i = new Intent(this, ShowCalculation.class);
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
        arrayCode = "";

        //not-blank fields are mapped to the presenceArray, build a string out of the presence array
        if (!fieldA.getText().toString().equals("")) {
            arrayCode += "1";
        } else {
            arrayCode += "0";
        }

        if (!fieldB.getText().toString().equals("")) {
            arrayCode += "1";
        } else {
            arrayCode += "0";
        }

        if (!fieldC.getText().toString().equals("")) {
            arrayCode += "1";
        } else {
            arrayCode += "0";
        }

        if (!fieldD.getText().toString().equals("")) {
            arrayCode += "1";
        } else {
            arrayCode += "0";
        }

        Log.v("FourVar.validateInput", "I think the code is..." + arrayCode);

        switch (arrayCode) {
            case "0111":
                //set the params as values in the appropriate fields
                solveMethodParam1 = Double.parseDouble(fieldB.getText().toString());
                solveMethodParam2 = Double.parseDouble(fieldC.getText().toString());
                solveMethodParam3 = Double.parseDouble(fieldD.getText().toString());

                //build the sharestring using the new info
                shareString += symbolB.getText() + "=" + solveMethodParam1 + " & ";
                shareString += symbolC.getText() + "=" + solveMethodParam2 + " & ";
                shareString += symbolD.getText() + "=" + solveMethodParam3 + " ; ";
                resultType = unitsA.getText().toString();
                symSolution = symbolA.getText().toString();
                shareString += symSolution + "=";
                return true;
            case "1011":
                solveMethodParam1 = Double.parseDouble(fieldA.getText().toString());
                solveMethodParam2 = Double.parseDouble(fieldC.getText().toString());
                solveMethodParam3 = Double.parseDouble(fieldD.getText().toString());

                shareString += symbolA.getText() + "=" + solveMethodParam1 + " & ";
                shareString += symbolC.getText() + "=" + solveMethodParam2 + " & ";
                shareString += symbolD.getText() + "=" + solveMethodParam3 + " ; ";
                resultType = unitsB.getText().toString();
                symSolution = symbolB.getText().toString();
                shareString += symSolution + "=";
                return true;
            case "1101":
                solveMethodParam1 = Double.parseDouble(fieldA.getText().toString());
                solveMethodParam2 = Double.parseDouble(fieldB.getText().toString());
                solveMethodParam3 = Double.parseDouble(fieldD.getText().toString());

                shareString += symbolA.getText() + "=" + solveMethodParam1 + " & ";
                shareString += symbolB.getText() + "=" + solveMethodParam2 + " & ";
                shareString += symbolD.getText() + "=" + solveMethodParam3 + " ; ";
                resultType = unitsC.getText().toString();
                symSolution = symbolC.getText().toString();
                shareString += symSolution + "=";
                return true;
            case "1110":
                solveMethodParam1 = Double.parseDouble(fieldA.getText().toString());
                solveMethodParam2 = Double.parseDouble(fieldB.getText().toString());
                solveMethodParam3 = Double.parseDouble(fieldC.getText().toString());

                shareString += symbolA.getText() + "=" + solveMethodParam1 + " & ";
                shareString += symbolB.getText() + "=" + solveMethodParam2 + " & ";
                shareString += symbolC.getText() + "=" + solveMethodParam3 + " ; ";
                resultType = unitsD.getText().toString();
                symSolution = symbolD.getText().toString();
                shareString += symSolution + "=";
                return true;

            default:
                //reset arrayCode, there was something wrong here so return false
                arrayCode = "";
                Log.v("FourVar.class", " failed the validation with code " + arrayCode);
                return false;

        }

    }

    public void setSymbolsAndUnits(Spanned symA, Spanned symB, Spanned symC, Spanned symD,
                                   Spanned unitA, Spanned unitB, Spanned unitC, Spanned unitD) {

        //retrieve appropriate strings with resource name string by getting resources and finding
        //getIdentifier takes (String resourceName, String resourceType, String package)
        symbolA.setText(symA);
        symbolB.setText(symB);
        symbolC.setText(symC);
        symbolD.setText(symD);

        unitsA.setText(unitA);
        unitsB.setText(unitB);
        unitsC.setText(unitC);
        unitsD.setText(unitD);
    }

    public void determineSubjectCode() {
        //determines the subject code given the equation code, basically first three letters stored as var
        subjectCode = String.valueOf(equationCode.charAt(0)) + String.valueOf(equationCode.charAt(1)) +
                String.valueOf(equationCode.charAt(2));
        Log.v("determineSubjectCode", "determined that the subject code is" + subjectCode);
    }

    //retrieve class based on the code string,
    public void setClassInput() throws Exception {
        //retrieves the specific equation class based on equationCode, sets views with appropriate values
        Class equationClass = Class.forName("com.wordpress.httpspandareaktor.mekanism." + subjectCode + "." + equationCode);
        Log.v("FourVar.findClass", " just fetched equationClass: " + equationClass.getCanonicalName());

        //field should be array list called "paramArray" in equationCode's class
        Field resourceArray = equationClass.getDeclaredField("resourceArray");
        Log.v("FourVar.findClass", "got field " + resourceArray.toString());

        //create array to store values, get them from Field object, use them
        Spanned[] valuesArray;
        Object temp = equationClass.newInstance();
        valuesArray = (Spanned[]) resourceArray.get(temp);
        Log.v("FourVar.findClass", "found resource array, item count: " + valuesArray.length);
        setSymbolsAndUnits(valuesArray[0], valuesArray[1], valuesArray[2], valuesArray[3],
                valuesArray[4], valuesArray[5], valuesArray[6], valuesArray[7]);

        //use another Field object to retrieve the descriptor array stored in the class
        Field descriptor = equationClass.getDeclaredField("descriptorArray");
        Spanned[] descriptorArray = (Spanned[]) descriptor.get(temp);
        equationDescriptor.setText(null);
        //the iterator will append the variables one at a time, had issue concatenating chars while retaining style
        for (int i = 0; i < descriptorArray.length; i++) {
            equationDescriptor.append(descriptorArray[i]);
        }

    }


}
