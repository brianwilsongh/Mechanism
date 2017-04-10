package com.wordpress.httpspandareaktor.mekanism;

import android.support.v7.app.AppCompatActivity;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import static android.provider.LiveFolders.INTENT;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private AdView mAdView;

    private CallbackManager fbCallbackManager;
    private LoginButton facebookLoginButton;
    private TextView facebookLoginInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Firebase code
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // init facebook for analytics, set login button and informational textview
        AppEventsLogger.activateApp(this);
        fbCallbackManager = CallbackManager.Factory.create();
        facebookLoginButton = (LoginButton) findViewById(R.id.fb_login_button);
        facebookLoginInfo = (TextView) findViewById(R.id.fb_login_info);

        //handle the callback with registerCallback, change textview appropriately

        facebookLoginButton.registerCallback(fbCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // if login callback sends success signal, set button height to zero and set text to userId
                facebookLoginButton.setHeight(0);
                facebookLoginInfo.setText("Logged in as " + loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                // if login attempt is cancelled...
                facebookLoginInfo.setText("Login attempt cancelled");
            }

            @Override
            public void onError(FacebookException e) {
                //if there is an error
                facebookLoginInfo.setText("Login attempt failed");

            }
        });

        //

        //Set main text to include the awesome Xi
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getString(R.string.app_name));

        //Create rotation animation for the primary gear
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(15000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);

        findViewById(R.id.gearLogo).startAnimation(rotateAnimation);

        //Create rotation animation for the second gear
        RotateAnimation rotateAnimation2 = new RotateAnimation(360f, 0,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation2.setInterpolator(new LinearInterpolator());
        rotateAnimation2.setDuration(21562);
        rotateAnimation2.setRepeatCount(Animation.INFINITE);

        findViewById(R.id.gearLogo2).startAnimation(rotateAnimation2);

        //Create rotation animation for the third gear
        RotateAnimation rotateAnimation3 = new RotateAnimation(360f, 0,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation3.setInterpolator(new LinearInterpolator());
        rotateAnimation3.setDuration(23159);
        rotateAnimation3.setRepeatCount(Animation.INFINITE);

        findViewById(R.id.gearLogo3).startAnimation(rotateAnimation3);


    }

    //tapping the login button starts new activity, to receive and handle result we need the following:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        fbCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void startPhysics(View view) {
        Intent intent = new Intent(this, PhysicsEquations.class);
        startActivity(intent);
    }

    public void startMath(View view) {
        Intent intent = new Intent(this, MathEquations.class);
        startActivity(intent);
    }
}
