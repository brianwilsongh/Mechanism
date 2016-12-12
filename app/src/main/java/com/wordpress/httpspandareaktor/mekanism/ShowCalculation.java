package com.wordpress.httpspandareaktor.mekanism;

/**
 * Created by Brian on 12/11/2016.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.math.*;


/**
 * Created by Brian on 11/5/2016.
 */

public class ShowCalculation extends AppCompatActivity {

    private double displacement;
    private double velocity;
    private double acceleration;
    private double time;
    private String answerType;
    private String answerValue;
    private String answerValue2;
    private String solutionMethod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_calculation);

        Intent intent = getIntent();

        try {
            String distanceString = intent.getStringExtra("positionData");
            displacement = Double.parseDouble(distanceString);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        try {
            String velocityString = intent.getStringExtra("velocityData");
            velocity = Double.parseDouble(velocityString);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        try {
            String accelerationString = intent.getStringExtra("accelerationData");
            acceleration = Double.parseDouble(accelerationString);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        try {
            String timeString = intent.getStringExtra("timeData");
            time = Double.parseDouble(timeString);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        Log.v("ShowCalc", "params are " + displacement + velocity + acceleration + time);
        calculateAnswer();
    }

    private void calculateAnswer() {

        TextView resultNameBox = (TextView) findViewById(R.id.result_name);
        TextView resultValueBox = (TextView) findViewById(R.id.result_value);
        TextView resultValueBox2 = (TextView) findViewById(R.id.result_value2);
        //retrieve solution method with key algo
        Intent intent = getIntent();
        solutionMethod = intent.getStringExtra("algo");
        Log.v("calculateAnswer", "the algo is " + solutionMethod);
        switch (solutionMethod) {
            case "0111":
                answerType = "displacement (d) =  ";
                answerValue = String.valueOf((velocity * time) + ((0.5) * acceleration * (Math.pow(time, 2))));
                resultNameBox.setText(answerType);
                resultValueBox.setText(answerValue);
                break;
            case "1011":
                answerType = "velocity (v) = ";
                answerValue = String.valueOf((displacement / time) - (0.5 * acceleration * time));
                resultNameBox.setText(answerType);
                resultValueBox.setText(answerValue);
                break;
            case "1101":
                answerType = "acceleration (a) = ";
                answerValue = String.valueOf(((2 * displacement) / (Math.pow(time, 2)))-((2*velocity)/time));
                resultNameBox.setText(answerType);
                resultValueBox.setText(answerValue);
                break;
            case "1110":
                answerType = "time (t) = ";
                answerValue = String.valueOf(quadraticConvert(displacement, velocity, acceleration, true));
                answerValue2 = String.valueOf(quadraticConvert(displacement, velocity, acceleration, false));
                if (Double.parseDouble(answerValue) > 0) {
                    resultNameBox.setText(answerType);
                    resultValueBox.setText(answerValue);
                    Toast.makeText(this, "Because of quadratic, time may have 2 answers.", Toast.LENGTH_LONG).show();
                }

                if (Double.parseDouble(answerValue2) > 0) {
                    resultValueBox2.setText(answerValue2);
                }
                break;
            default:
                Log.v("Broken switch statement", " " + answerValue);

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

    public void returnMain(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
