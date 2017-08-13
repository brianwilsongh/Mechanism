package com.wordpress.httpspandareaktor.mechanism;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.wordpress.httpspandareaktor.mechanism.generators.GenUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private AdView mAdView;


    //animation experimental below:
    ImageView imageHolder;
    byte animCount = 0;
    RelativeLayout whiteSpace;
    int whiteSpaceHeight;
    int whiteSpaceWidth;
    private Thread thread;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Firebase code
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // init facebook for analytics, set login button and informational textview

        //identify the imageviews for the animation
        // Refer the first animation ImageView like this
        imageHolder = (ImageView) findViewById(R.id.mainimg1);


        //create EqThread and run it
        thread = new Thread(new EqThread());
        thread.start();

        whiteSpace = (RelativeLayout) findViewById(R.id.mainMenuWhitespace);

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //do animation when Handler receives message
                doAnimation();
            }
        };


        ViewTreeObserver vto = whiteSpace.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                whiteSpace.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                //TODO: determine best location for this
                whiteSpaceHeight = whiteSpace.getHeight();
                whiteSpaceWidth = whiteSpace.getWidth();

                //set title to include Xi
                TextView title = (TextView) findViewById(R.id.title);
                title.setText(getString(R.string.app_name));

                //move gears into position 
                Double WSwidth = Double.parseDouble(String.valueOf(whiteSpace.getWidth()));
                Double WSheight = Double.parseDouble(String.valueOf(whiteSpace.getHeight()));

                //move the title words within whiteSpace
                LinearLayout titleArea = (LinearLayout) findViewById(R.id.titleArea);
                titleArea.setY((WSheight.floatValue() * 0.618f));

                Log.v("Main", " widthxheight: " + WSwidth + " x " + WSheight);

                //create gears and set positions

                ImageView gear1 = new ImageView(getApplicationContext());
                gear1.setImageResource(R.drawable.pinion);
                gear1.setAlpha(0.2f);
                RelativeLayout.LayoutParams gear1params = new RelativeLayout.LayoutParams(((Double) (WSwidth * .382)).intValue(),
                        ((Double) (WSwidth * .382)).intValue());
                gear1params.leftMargin = ((Double) (WSwidth * 0.32)).intValue();
                gear1params.rightMargin = WSwidth.intValue();
                gear1params.topMargin = -((Double) (WSheight * 0.05)).intValue();
                whiteSpace.addView(gear1, gear1params);

                ImageView gear2 = new ImageView(getApplicationContext());
                gear2.setImageResource(R.drawable.pinion2);
                gear2.setAlpha(0.2f);
                RelativeLayout.LayoutParams gear2params = new RelativeLayout.LayoutParams(((Double) (WSwidth * .382 * 1.618)).intValue(),
                        ((Double) (WSwidth * .382 * 1.618)).intValue());
                gear2params.leftMargin = ((Double) (WSwidth * 0.71)).intValue();
                gear2params.rightMargin = WSwidth.intValue();
                gear2params.topMargin = -((Double) (WSheight * 0.27)).intValue();
                whiteSpace.addView(gear2, gear2params);

                ImageView gear3 = new ImageView(getApplicationContext());
                gear3.setImageResource(R.drawable.pinion3);
                gear3.setAlpha(0.2f);
                RelativeLayout.LayoutParams gear3params = new RelativeLayout.LayoutParams(((Double) (WSwidth * .382 * 1.618 * 1.618)).intValue(),
                        ((Double) (WSwidth * .382 * 1.618)).intValue());
                gear3params.leftMargin = -((Double) (WSwidth * 0.4)).intValue();
                gear3params.rightMargin = WSwidth.intValue() / 2;
                gear3params.topMargin = -((Double) (WSheight * 0.42)).intValue();
                whiteSpace.addView(gear3, gear3params);

//                ImageView gear1 = (ImageView) findViewById(R.id.gearLogo);
//                ImageView gear2 = (ImageView) findViewById(R.id.gearLogo2);
//                ImageView gear3 = (ImageView) findViewById(R.id.gearLogo3);

                //widths and heights are both proportional to WIDTH of parentview
//                gear1.getLayoutParams().width = ((Double) (WSwidth * .235 * 2)).intValue();
//                gear1.getLayoutParams().height = ((Double) (WSwidth * .235 * 2)).intValue();

//                gear2.getLayoutParams().width = ((Double) (WSwidth * .338 * 2)).intValue();
//                gear2.getLayoutParams().height = ((Double) (WSwidth * .338 * 2)).intValue();
//
//                gear3.getLayoutParams().width = ((Double) (WSwidth * .426 * 2)).intValue();
//                gear3.getLayoutParams().height = ((Double) (WSwidth * .426 * 2)).intValue();


                //Create rotation animation for the primary gear
                RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setInterpolator(new LinearInterpolator());
                rotateAnimation.setDuration(15000);
                rotateAnimation.setRepeatCount(Animation.INFINITE);

                gear1.startAnimation(rotateAnimation);

                //Create rotation animation for the second gear
                RotateAnimation rotateAnimation2 = new RotateAnimation(360f, 0,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation2.setInterpolator(new LinearInterpolator());
                rotateAnimation2.setDuration(21562);
                rotateAnimation2.setRepeatCount(Animation.INFINITE);

                gear2.startAnimation(rotateAnimation2);

                //Create rotation animation for the third gear
                RotateAnimation rotateAnimation3 = new RotateAnimation(360f, 0,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation3.setInterpolator(new LinearInterpolator());
                rotateAnimation3.setDuration(23159);
                rotateAnimation3.setRepeatCount(Animation.INFINITE);

                gear3.startAnimation(rotateAnimation3);

            }
        });


    }



    class EqThread implements Runnable {
        //Thread to call
        @Override
        public void run() {
            int sleepyTime;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    //generate a sleep value
                    sleepyTime = GenUtils.generateRandomInRange(1, 3).intValue() * 350;
                    Thread.sleep(sleepyTime);
                    //send message to handler to cause the animation to happen
                    Message message = Message.obtain();
                    message.arg1 = 0;
                    mHandler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void doAnimation(){

        animCount++;

            //if there are more than 12 animations, don't do anything?
            //generate a random number to find random drawable from equation images
            int randomEqnNum = (int) Math.floor(Math.random() * 26) + 1;

            //get a drawable resource id based on the random
            int generatedId = getResources().getIdentifier("phy" + randomEqnNum, "drawable", getPackageName());

            //create a view iv with the randomzied resource
            final ImageView iv = new ImageView(this);
            iv.setImageResource(generatedId);
            whiteSpace.addView(iv);

            iv.setImageResource(generatedId);
            byte alphaMultiplier = GenUtils.generateRandomInRange(1, 4).byteValue();
            iv.setAlpha(0.12f * alphaMultiplier);

            // Load the animation like this
//        Animation animSlide = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.slider);

            int randomHeight = (int) Math.floor(Math.random() * whiteSpaceHeight);

            TranslateAnimation animSlide;
            //generate randomly left-to-right or right-to-left
            byte direction = GenUtils.generateRandomInRange(0, 2).byteValue();
            if (direction == 0) {
                //translate animation args: fromXposition, toXPosition, fromYPosition, toYPosition
                animSlide = new TranslateAnimation(-whiteSpaceWidth, whiteSpaceWidth, randomHeight / 4, randomHeight / 4);
            } else {
                animSlide = new TranslateAnimation(whiteSpaceWidth, -whiteSpaceWidth, randomHeight / 4, randomHeight / 4);
            }
            //generate a random duration for the animation
            int randomDuration = GenUtils.generateRandomInRange(9, 17).intValue();
            animSlide.setDuration(randomDuration * 1000);


            // Start the animation like this
            iv.startAnimation(animSlide);

            animSlide.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation arg0) {
                    iv.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation arg0) {
                }

                @Override
                public void onAnimationEnd(Animation arg0) {
                    iv.setVisibility(View.GONE);
                    ((ViewGroup) iv.getParent()).removeView(iv);
                    animCount--;
                }
            });

    }


    public void startPhysics(View view) {
        Intent intent = new Intent(this, PhysicsEquations.class);
        startActivity(intent);
    }

    public void startMath(View view) {
        Intent intent = new Intent(this, MathEquations.class);
        startActivity(intent);
    }

    public void startQuestionCell(View view) {
        Intent intent = new Intent(this, QuestionCell.class);
        startActivity(intent);
    }
}
