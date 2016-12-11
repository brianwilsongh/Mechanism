package com.wordpress.httpspandareaktor.mekanism;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
