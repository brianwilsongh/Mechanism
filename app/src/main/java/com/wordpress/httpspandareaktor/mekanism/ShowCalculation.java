package com.wordpress.httpspandareaktor.mekanism;

/**
 * Created by Brian on 12/11/2016.
 */

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import android.app.Activity;
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

    private String answerUnits;
    private String answerVal;
    private String answerVal2;
    private String shareString;
    private String rootSubject;

    //variables for sharing on FB
    private String sharingURL = "https://play.google.com/store/apps/details?id=com.wordpress.httpspandareaktor.mekanism";
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.show_calculation);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //retrieve strings from intent extra data, set texts
        Intent i = getIntent();
        if (i.getStringExtra("resultUnits") != null) {
            answerUnits = i.getStringExtra("resultUnits");
        }
        if (i.getStringExtra("resultValue") != null) {
            answerVal = i.getStringExtra("resultValue");
        }
        if (i.getStringExtra("shareString") != null) {
            shareString = i.getStringExtra("shareString");
        }
        if (i.getStringExtra("rootSubject") != null) {
            rootSubject = i.getStringExtra("rootSubject");
        }

        rootSubject = i.getStringExtra("rootSubject");

        TextView resultTypeText = (TextView) findViewById(R.id.result_type);
        TextView resultValueText = (TextView) findViewById(R.id.result_value);
        TextView resultValueText2 = (TextView) findViewById(R.id.result_value2);
        TextView rootButton = (TextView) findViewById(R.id.returnRootButton);


        Log.v("ShowCalculation", "retrieved: " + answerUnits + answerVal + answerVal2);

        resultTypeText.setText(answerUnits);
        resultValueText.setText(answerVal);
        resultValueText2.setText(answerVal2);
        rootButton.setText("Return to " + rootSubject);
    }

    public void sendSMS(View view) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:"));
        sendIntent.putExtra("sms_body", shareString);

        startActivity(sendIntent);

    }

    public void sendEmail(View view) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/html");
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareString);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Calculation result");

        startActivity(sendIntent);
    }


    public void returnMain(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void returnRoot(View view) {
        switch (rootSubject) {
            case "Physics":
                Intent i = new Intent(this, PhysicsEquations.class);
                startActivity(i);
            default:
                break;
        }
    }

    public void shareFB(View view) {
        //Build a share for the url of the app
        shareDialog = new ShareDialog(this);
        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setContentTitle(getString(R.string.app_name))
                .setContentDescription(getString(R.string.app_description))
                .setContentUrl(Uri.parse(sharingURL)).build();
        shareDialog.show(linkContent);
    }

}
