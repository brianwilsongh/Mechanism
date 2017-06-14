package com.wordpress.httpspandareaktor.mekanism;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wordpress.httpspandareaktor.mekanism.generators.Generator;
import com.wordpress.httpspandareaktor.mekanism.generators.T4PHY1;

import java.lang.reflect.Field;

/**
 * Created by brian on 6/13/17.
 */

public class QuestionCell extends AppCompatActivity {

    //TextViews in the upper bar showing subject and tier
    TextView displayTier;
    TextView displaySubject;

    //code is for testing purposes
    TextView displayCode;

    //TextViews for main question and initially hidden answer
    TextView displayQuestion;
    ImageView displayHint;
    private String hintImageId;
    TextView displayAnswers;

    Button hintButton;
    Button revealButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_cell);

        displayTier = (TextView) findViewById(R.id.generatorDifficulty);
        displaySubject = (TextView) findViewById(R.id.generatorSubject);
        displayCode = (TextView) findViewById(R.id.generatorCode);

        displayQuestion = (TextView) findViewById(R.id.generatorDisplayQuestion);
        revealButton = (Button) findViewById(R.id.generatorRevealButton);
        displayHint = (ImageView) findViewById(R.id.generatorDisplayHint);
        hintButton = (Button) findViewById(R.id.generatorHintButton);
        displayAnswers = (TextView) findViewById(R.id.generatorDisplayAnswers);

        //set the question and solution using a Generator object
        setQuestion();

    }

    public void setQuestion(){
        try {
            Generator g = retrieveGenerator("T4", "PHY1");
            Log.v("Generator", "new instance of T4PHY1:" + g.toString());
            Log.v("object", " formatted/trueanswer: " + g.getFormattedQuestion() + g.getTrueAnswer());

            //this is the code, ie "T4PHY1" for testing
            displayCode.setText(g.getClass().getName());

            //this uses Generator abstract methods to get question and answer strings, set to TextViews
            displayQuestion.setText(g.getFormattedQuestion());
            displayAnswers.setText(String.valueOf(g.getTrueAnswer()));

            //get the image Id of the "hint" image
            hintImageId = g.getHint();


        } catch (Exception e){
            e.printStackTrace();
        }


    }

    public void displayAnswer(View view){
        revealButton.setVisibility(View.GONE);
        displayAnswers.setVisibility(View.VISIBLE);
    }

    public void displayHint(View view){
        //this displays the hint, which is an image
        //getResources().getIdentifier(hintImageId, "drawable", getPackageName())
        displayHint.setImageResource(getResources().getIdentifier(hintImageId, "drawable", getPackageName()));
        displayHint.setVisibility(View.VISIBLE);
        hintButton.setVisibility(View.GONE);
    }

    public Generator retrieveGenerator(String tier, String subject) throws Exception{
        //retrieves the specific equation class based on equationCode, sets views with appropriate values
        Class generatorClass = Class.forName("com.wordpress.httpspandareaktor.mekanism.generators." + tier + subject);
        Log.v("QuestionCell", " just fetched Generator: " + generatorClass.getCanonicalName());
        return (Generator) generatorClass.newInstance();
    }

    
}
