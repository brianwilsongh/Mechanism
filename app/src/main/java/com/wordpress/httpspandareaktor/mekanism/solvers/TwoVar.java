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

/**
 * Created by Brian on 3/9/2017.
 */

public class TwoVar extends AppCompatActivity {
/* This is a generic TWO variable solver object, meant to fetch user input (1 fields) from the matching
    layout file and send it to the appropriate [SUB][CODE] equation object along with solver code. Will receive the
    return values and then send to the solution page.
     */

    //declare/init the vars and presence booleans
    private double varA;
    private double varB;
    private boolean presenceVarA = false;
    private boolean presenceVarB = false;

    // declare variable names for the fields
    TextView symbolA;
    TextView symbolB;
    EditText fieldA;
    EditText fieldB;
    TextView unitsA;
    TextView unitsB;

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

    //array coding for variable presence
    String arrayCode = "";

    //equation code ex. "phy1"
    String equationCode;

    //stores subject based on equationCode
    String subjectCode;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solver_two_var);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // match all declared variables to matching view in the layout file
        equationImage = (ImageView) findViewById(R.id.twoVarImage);
        equationDescriptor = (TextView) findViewById(R.id.twoVarDescriptor);
        calcButton = (Button) findViewById(R.id.twoVarButton);

        symbolA = (TextView) findViewById(R.id.twoVarSymbolA);
        symbolB = (TextView) findViewById(R.id.twoVarSymbolB);

        fieldA = (EditText) findViewById(R.id.twoVarFieldA);
        fieldB = (EditText) findViewById(R.id.twoVarFieldB);

        unitsA = (TextView) findViewById(R.id.twoVarUnitsA);
        unitsB = (TextView) findViewById(R.id.twoVarUnitsB);

        //retrieve info from intent to set up the matching class
        Intent i = getIntent();
        equationCode = i.getStringExtra("equationCode");
        setTitle("Equation ID:  " + equationCode);
        determineSubjectCode();

        //set up the views given data from the class and other stuff
        int newImageId = (i.getIntExtra("imageResourceId", 0));
        if (newImageId != 0) {
            equationImage.setImageResource(newImageId); }

        //set on click listener for the button
        mMediaPlayer = MediaPlayer.create(this, R.raw.electron_hi);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {attemptSolve(); } catch (Exception e) {e.printStackTrace();}
            }
        });

        try {setClassInput(); } catch (Exception e) {e.printStackTrace();}

    }




    private void attemptSolve() throws Exception {
        //method that is called on Calculate button click
        if (validateInput()){
            //if input is good, play the sound and figure out stuff for the intent
            mMediaPlayer.start();

            //get solve method from equationCode class
            Class equationClass = Class.forName("com.wordpress.httpspandareaktor.mekanism." + subjectCode + "." + equationCode);
            Log.v("TwoVar.attemptSolve", "fetched " + equationClass.getCanonicalName());

            //time to retrieve the method and store the solution given parameters
            //getDeclaredMethod requires name of the method and the type classes of all params it takes...
            Method solveMissing = equationClass.getDeclaredMethod("solveMissing", String.class, double.class);
            solveMissing.setAccessible(true);
            Object temp = equationClass.newInstance();
            resultVal = (String) solveMissing.invoke(temp, arrayCode, solveMethodParam1);

            //if the resultVal is not numeric, something went wrong so throw the toast and exit
            if (!PHYutils.isNumeric(resultVal)){
                Toast.makeText(this, "ERROR: failed to calculate given parameters, please check values!", Toast.LENGTH_LONG).show();
                return;
            }

            //here is the intent to travel to the solution page
            Intent i = new Intent(this, ShowCalculation.class);
            i.putExtra("resultSym", symSolution);
            i.putExtra("resultUnits", resultType);
            i.putExtra("resultValue", resultVal);
            i.putExtra("shareString", shareString + resultVal);
            i.putExtra("subjectCode", subjectCode);
            // send extra as a string
            i.putExtra("eqnType", "Physics");
            Log.v("attemptSolve method", "sent "  + resultType + " " + resultVal + " sharestring: " + shareString);

            //reset that array in case they want to go back later
            arrayCode = "";

            startActivity(i);
        } else {
            Toast.makeText(this, "fill 1 field to solve for the other variable", Toast.LENGTH_LONG).show();
        }
    }


    private boolean validateInput() {
        //set up shareString in case user is returning here
        shareString = "(" + getString(R.string.app_name) + " - " + subjectCode + ") -- Given variables ";

        //not-blank fields are mapped to the presenceArray, build a string out of the presence array
        if (!fieldA.getText().toString().equals("")){
            arrayCode += "1";
        } else { arrayCode += "0"; }

        if (!fieldB.getText().toString().equals("")){
            arrayCode += "1";
        } else { arrayCode += "0"; }

        Log.v("ThreeVar.validateInput", "I think the code is..." + arrayCode);

        switch (arrayCode) {
            case "01":
                solveMethodParam1 = Double.parseDouble(fieldB.getText().toString());
                shareString += symbolB.getText() + "=" + solveMethodParam1 + " ; ";
                resultType = unitsA.getText().toString();
                symSolution = symbolA.getText().toString();
                shareString += symSolution + "=";
                return true;
            case "10":
                solveMethodParam1 = Double.parseDouble(fieldA.getText().toString());
                shareString += symbolA.getText() + "=" + solveMethodParam1 + " ; ";
                resultType = unitsB.getText().toString();
                symSolution = symbolB.getText().toString();
                shareString += symSolution + "=";
                return true;

            default:
                arrayCode = "";
                Log.v("ThreeVar.class", " failed the validation with code " + arrayCode);
                return false;

        }

    }

    public void setSymbolsAndUnits(Spanned symA, Spanned symB, Spanned unitA, Spanned unitB){

        //retrieve appropriate strings with resource name string by getting resources and finding
        //getIdentifier takes (String resourceName, String resourceType, String package)
        symbolA.setText(symA);
        symbolB.setText(symB);
        unitsA.setText(unitA);
        unitsB.setText(unitB);
    }

    public void determineSubjectCode() {
        //determines the subject code given the equation code, basically first three letters stored as var
        subjectCode = String.valueOf(equationCode.charAt(0)) + String.valueOf(equationCode.charAt(1)) +
                String.valueOf(equationCode.charAt(2));
        Log.v("determineSubjectCode", "determined that the subject code is" + subjectCode);
    }

    public void setDefaults(Class equationClass, Object o){
        //set defaults contained within the equation class into the appropriate fields
        try {
            if (equationClass.getDeclaredField("defaultA") != null) {
                fieldA.setText(String.valueOf(equationClass.getDeclaredField("defaultA").get(o)));
            }
        } catch (Exception e){
            Log.v("ThreeVar.setDefault", " no field found for " + equationClass.toString(), e);
        }

        try {
            if (equationClass.getDeclaredField("defaultB") != null) {
                fieldB.setText(String.valueOf(equationClass.getDeclaredField("defaultB").get(o)));
            }
        } catch (Exception e){
            Log.v("ThreeVar.setDefault", " no field found for " + equationClass.toString(), e);
        }


    }

    //retrieve class based on the code string,
    public void setClassInput() throws Exception {
        //retrieves the specific equation class based on equationCode, sets views with appropriate values
        Class equationClass = Class.forName("com.wordpress.httpspandareaktor.mekanism." + subjectCode + "." + equationCode);
        Log.v("ThreeVar.findClass", " just fetched equationClass: " + equationClass.getCanonicalName());

        //field should be array list called "paramArray" in equationCode's class
        Field resourceArray = equationClass.getDeclaredField("resourceArray");
        Log.v("ThreeVar.findClass", "got field " + resourceArray.toString());

        //create array to store values, get them from Field object, use them
        Spanned[] valuesArray;
        Object temp = equationClass.newInstance();

        setDefaults(equationClass, temp);

        valuesArray = (Spanned[]) resourceArray.get(temp);
        Log.v("ThreeVar.findClass", "found resource array, item count: " + valuesArray.length);
        setSymbolsAndUnits(valuesArray[0], valuesArray[1], valuesArray[2], valuesArray[3]);

        //use another Field object to retrieve the descriptor array stored in the class
        Field descriptor = equationClass.getDeclaredField("descriptorArray");
        Spanned[] descriptorArray = (Spanned[]) descriptor.get(temp);
        equationDescriptor.setText(null);
        //the iterator will append the variables one at a time, had issue concatenating chars while retaining style
        for (int i = 0; i < descriptorArray.length; i ++){
            equationDescriptor.append(descriptorArray[i]);
        }

    }


}

