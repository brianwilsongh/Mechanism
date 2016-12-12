package com.wordpress.httpspandareaktor.mekanism;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import static android.provider.LiveFolders.INTENT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set main text to include Xi
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("\u039E MEKANISM");

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

    public void startPhysics(View view) {
        Intent intent = new Intent(this, PhysicsEquations.class);
        startActivity(intent);
    }
}
