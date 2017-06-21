package com.wordpress.httpspandareaktor.mechanism;

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


/**
 * Created by Brian on 11/5/2016.
 */

public class ShowCalculation extends AppCompatActivity {

    private String answerUnits;
    private String answerSym;
    private String answerVal;
    private String shareString;
    private String subjectCode;

    //variables for sharing on FB
    private String sharingURL = "https://play.google.com/store/apps/details?id=com.wordpress.httpspandareaktor.mekanism";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_calculation);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //retrieve strings from intent extra data, set texts
        Intent i = getIntent();
        if (i.getStringExtra("resultUnits") != null) {
            answerUnits = i.getStringExtra("resultUnits");
        }
        if (i.getStringExtra("resultSym") != null) {
            answerSym = i.getStringExtra("resultSym") + " = ";
        }
        if (i.getStringExtra("resultValue") != null) {
            answerVal = i.getStringExtra("resultValue");
        }
        if (i.getStringExtra("shareString") != null) {
            shareString = i.getStringExtra("shareString");
        }
        if (i.getStringExtra("subjectCode") != null) {
            subjectCode = i.getStringExtra("subjectCode");
        }

        TextView resultTypeText = (TextView) findViewById(R.id.result_type);
        TextView resultValueText = (TextView) findViewById(R.id.result_value);
        TextView rootButton = (TextView) findViewById(R.id.returnRootButton);


        Log.v("ShowCalculation", "retrieved: " + answerUnits + answerVal);

        resultTypeText.setText(answerUnits);
        resultValueText.setText(answerSym);
        resultValueText.setText(answerSym);
        resultValueText.append(answerVal);
        rootButton.setText("Return to " + subjectCode);
    }

    public void sendSMS(View view) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:"));
        sendIntent.putExtra("sms_body", shareString);

        startActivity(sendIntent);

    }

    public void sendEmail(View view) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("message/rfc822");
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareString);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Calculation result");
        try {
            startActivity(Intent.createChooser(sendIntent, "Send mail"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void returnMain(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void returnRoot(View view) {
        switch (subjectCode) {
            case "PHY":
                Intent i = new Intent(this, PhysicsEquations.class);
                startActivity(i);
            default:
                break;
        }
    }



}
