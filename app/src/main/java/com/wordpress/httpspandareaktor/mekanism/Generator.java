package com.wordpress.httpspandareaktor.mekanism;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wordpress.httpspandareaktor.mekanism.generators.T4PHY1;

/**
 * Created by brian on 6/13/17.
 */

public class Generator extends AppCompatActivity {

    TextView displayQuestion;
    TextView displayAnswers;
    Button revealButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generator);

        displayQuestion = (TextView) findViewById(R.id.generatorDisplayQuestion);
        revealButton = (Button) findViewById(R.id.generatorRevealButton);
        displayAnswers = (TextView) findViewById(R.id.generatorDisplayAnswers);
        setQuestion();

    }

    public void setQuestion(){
        T4PHY1 a = new T4PHY1();
        Log.v("Generator", "new instance of T4PHY1:" + a.toString());
        Log.v("object", " formatted/trueanswer: " + a.formatted + a.trueAnswer);

        displayQuestion.setText(a.formatted);
        displayAnswers.setText(String.valueOf(a.trueAnswer));

    }

    public void displayAnswer(View view){
        revealButton.setVisibility(View.GONE);
        displayAnswers.setVisibility(View.VISIBLE);
    }


}
