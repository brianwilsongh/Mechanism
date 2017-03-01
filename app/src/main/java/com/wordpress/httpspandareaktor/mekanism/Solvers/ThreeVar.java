package com.wordpress.httpspandareaktor.mekanism.solvers;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wordpress.httpspandareaktor.mekanism.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static android.R.attr.value;
import static android.R.attr.x;
import static java.lang.Class.forName;

/**
 * Created by Brian on 2/28/2017.
 */

public class ThreeVar extends AppCompatActivity{
    /* This is a generic THREE variable solver object, meant to fetch user input (2 fields) from the matching
    layout file and send it to the appropriate [SUB][CODE] equation object along with solver code. Will receive the
    return values and then send to the solution page.
     */

    //declare/init the vars and presence booleans
    private double varA;
    private double varB;
    private double varC;
    private boolean presenceVarA = false;
    private boolean presenceVarB = false;
    private boolean presenceVarC = false;

    // declare variable names for the fields
    TextView symbolA;
    TextView symbolB;
    TextView symbolC;
    EditText fieldA;
    EditText fieldB;
    EditText fieldC;
    TextView unitsA;
    TextView unitsB;
    TextView unitsC;

    ImageView equationImage;
    TextView variableDescriptor;
    TextView equationCodeViewer;
    Button calcButton;

    //other variables for other functions
    MediaPlayer mMediaPlayer;
    String shareString;
    String resultType;
    String resultVal;
    String resultVal2;

    //array coding for variable presence
    int[] presenceArray;
    String arrayCode;

    //equation code ex. "PHY1"
    String equationCode;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solver_three_var);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        presenceArray = new int[3];

        // match all declared variables to matching view in the layout file
        equationImage = (ImageView) findViewById(R.id.threeVarImage);
        variableDescriptor = (TextView) findViewById(R.id.threeVarDescriptor);
        equationCodeViewer = (TextView) findViewById(R.id.threeVarCode);
        calcButton = (Button) findViewById(R.id.threeVarButton);

        symbolA = (TextView) findViewById(R.id.threeVarSymbolA);
        symbolB = (TextView) findViewById(R.id.threeVarSymbolB);
        symbolC = (TextView) findViewById(R.id.threeVarSymbolC);

        fieldA = (EditText) findViewById(R.id.threeVarFieldA);
        fieldB = (EditText) findViewById(R.id.threeVarFieldB);
        fieldC = (EditText) findViewById(R.id.threeVarFieldC);

        unitsA = (TextView) findViewById(R.id.threeVarUnitsA);
        unitsB = (TextView) findViewById(R.id.threeVarUnitsB);
        unitsC = (TextView) findViewById(R.id.threeVarUnitsC);

        //retrieve info from intent to set up the matching class
        Intent i = getIntent();
        equationCode = i.getStringExtra("equationCode");
        equationCodeViewer.setText(equationCode);


        //set up the views given data from the class and other stuff
        int newImageId = (i.getIntExtra("imageResourceId", 0));
        if (newImageId != 0) {
        equationImage.setImageResource(newImageId); }

        //set on click listener for the button
        mMediaPlayer = MediaPlayer.create(this, R.raw.electron_hi);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

        //retrieve symbols, units from the unique equation class, set TextViews accordingly


        //retrieve the class given equationCode and set views
        try { findClass();
        } catch (Exception e) {e.printStackTrace();}


        }



    private void sendData() {
        //vet and send data with extras
        if (validateInput()){
            //do stuff
        }
    }


    private boolean validateInput() {
        //not-blank fields are mapped to the presenceArray, build a string out of the presence array
        if (!fieldA.getText().toString().equals("")){
            presenceArray[0] = 1;
        }
        if (!fieldB.getText().toString().equals("")){
            presenceArray[1] = 1;
        }
        if (!fieldC.getText().toString().equals("")){
            presenceArray[2] = 1;
        }

        arrayCode = generateArrayCode(presenceArray);

        switch (arrayCode) {
            case "011":
            case "101":
            case "001":
                Log.v("ThreeVar.class", " validates presenceArray with code" + arrayCode);
                return true;
            default:
                presenceArray[0] = 0;
                presenceArray[1] = 0;
                presenceArray[2] = 0;
                Log.v("ThreeVar.class", " failed validation with code" + arrayCode);
                return false;

        }

    }

    private String generateArrayCode(int[] array){
        //takes array to generate array code for processing
        int length = array.length;
        String returnString = "";
        for (int itr = 0; itr < length; itr++){
            returnString += array[length - 1];
        }
        return returnString;
    }

    public void setSymbolsAndUnits(String symA, String symB, String symC, String unitA, String unitB, String unitC){
        symbolA.setText(symA);
        symbolB.setText(symB);
        symbolC.setText(symC);
        unitsA.setText(unitA);
        unitsB.setText(unitB);
        unitsC.setText(unitC);
    }

    //retrieve class based on the code string,
    public void findClass() throws Exception {
        //retrieves the specific equation class based on equationCode, sets views with appropriate values
        Class equationClass = Class.forName("com.wordpress.httpspandareaktor.mekanism.physics." + equationCode);
        Log.v("ThreeVar.findClass", "fetched " + equationClass.getCanonicalName());
        //field should be array list called "paramArray" in equationCode's class
        Field paramArray = equationClass.getDeclaredField("paramArray");
        Log.v("ThreeVar.findClass", "got field " + paramArray.toString());
        //create array to store values, use them
        String[] valuesArray;
        Object temp = equationClass.newInstance();
        valuesArray = (String[]) paramArray.get(temp);
        Log.v("ThreeVar.findClass", "found params " + valuesArray.toString());
        setSymbolsAndUnits(valuesArray[0], valuesArray[1], valuesArray[2], valuesArray[3],
                valuesArray[4], valuesArray[5]);



        }


}
