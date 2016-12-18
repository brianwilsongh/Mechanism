package com.wordpress.httpspandareaktor.mekanism;

/**
 * Created by Brian on 12/11/2016.
 */


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.math.*;

import static android.R.attr.x;


/**
 * Created by Brian on 11/5/2016.
 */

public class ShowCalculation extends AppCompatActivity {

    private String answerTypeVal;
    private String answerVal;
    private String answerVal2;
    private String shareString;
    private String fullShareString;
    private String rootClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_calculation);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //retreve strings from intent extra data, set texts
        Intent i;
        i = getIntent();
        if (i.getStringExtra("resultType") != null) {
            answerTypeVal = i.getStringExtra("resultType");
        }
        if (i.getStringExtra("resultValue") != null) {
            answerVal = i.getStringExtra("resultValue");
        }
        if (i.getStringExtra("resultValue2") != null) {
            answerVal2 = i.getStringExtra("resultValue2");
        }
        if (i.getStringExtra("shareString") != null) {
            shareString = i.getStringExtra("shareString");
        }

        rootClass = i.getStringExtra("rootClass");


        TextView resultTypeText = (TextView) findViewById(R.id.result_type);
        TextView resultValueText = (TextView) findViewById(R.id.result_value);
        TextView resultValueText2 = (TextView) findViewById(R.id.result_value2);
        TextView rootButton = (TextView) findViewById(R.id.returnRootButton);


        Log.v("ShowCalculation", "retrieved: " + answerTypeVal + answerVal + answerVal2);

        resultTypeText.setText(answerTypeVal);
        resultValueText.setText(answerVal);
        resultValueText2.setText(answerVal2);
        rootButton.setText("Return to " + rootClass);

//        try {
//            String distanceString = intent.getStringExtra("positionData");
//            displacement = Double.parseDouble(distanceString);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//        try {
//            String velocityString = intent.getStringExtra("velocityData");
//            velocity = Double.parseDouble(velocityString);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//        try {
//            String accelerationString = intent.getStringExtra("accelerationData");
//            acceleration = Double.parseDouble(accelerationString);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//        try {
//            String timeString = intent.getStringExtra("timeData");
//            time = Double.parseDouble(timeString);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//        Log.v("ShowCalc", "params are " + displacement + velocity + acceleration + time);
    }

    public void sendSMS(View view) {
        fullShareString = shareString + " ";
        if (answerVal != null) {
            fullShareString += answerVal + " ";
        }
        if (answerVal2 != null) {
            fullShareString += answerVal2;
        }

        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:"));
        sendIntent.putExtra("sms_body", fullShareString);

        startActivity(sendIntent);

    }

    public void sendEmail(View view) {
        fullShareString = shareString + " ";
        if (answerVal != null) {
            fullShareString += answerVal + " ";
        }
        if (answerVal2 != null) {
            fullShareString += answerVal2;
        }

        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("application/octet-stream");
        sendIntent.putExtra(Intent.EXTRA_TEXT, fullShareString);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Calculation result");

        startActivity(sendIntent);
    }


    public void returnMain(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void returnRoot(View view) {
        if (rootClass.equals("Physics")) {
            Intent i = new Intent(this, PhysicsEquations.class);
            startActivity(i);
        }
    }

}