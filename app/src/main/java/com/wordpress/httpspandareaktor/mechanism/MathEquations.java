package com.wordpress.httpspandareaktor.mechanism;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wordpress.httpspandareaktor.mechanism.solvers.FourVar;
import com.wordpress.httpspandareaktor.mechanism.solvers.Triangle;

import java.util.ArrayList;

/**
 * Created by Brian on 3/15/2017.
 */

public class MathEquations extends AppCompatActivity {

    private EquationAdapter equationAdapter;

    //instantiate physicsEquationList ArrayList for the array adapter
    final ArrayList<Equation> mathEquationList = new ArrayList<>();

    //instantiate the equationList for later manipulation
    ListView equationList;

    //instantiate all the individual equation objects here
    Equation MAT1;
    Equation MAT2;
    Equation Triangle;


    // from stack overflow, how to super/subscript:
    // ((TextView)findViewById(R.id.text)).setText(Html.fromHtml("X<sup><small>2</small></sup>"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics_equations);

        Toast.makeText(this, "Scroll and tap to select", Toast.LENGTH_LONG).show();

        //create Equation objects for future use
        //KINEMATIC AND MOTION EQUATIONS

        MAT1 = new Equation("MAT1", FourVar.class, R.drawable.mat1); //definition displacement
        MAT2 = new Equation("MAT2", FourVar.class, R.drawable.mat2); //law of cosines
        Triangle = new Equation("MAT1", Triangle.class, R.drawable.mat_abc_triangle); //unique triangle solution

        //create and set up list view, adapters
        equationList = (ListView) findViewById(R.id.physics_list);
        equationAdapter = new EquationAdapter(this, mathEquationList);
        //by default we will add the kinematics motion equations in list 1 first
        arrangeList1();
        equationList.setAdapter(equationAdapter);

        equationList.setDividerHeight(3);

        // set on item click listener for the ListView to create intents to send user to solvers
        equationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //find the item that was clicked on, when clicked send intent to correct solver with code
                Equation equation = mathEquationList.get(position);
                Class destinationClass = equation.getSolver();
                String equationCode = equation.getCode();
                int imageId = equation.getImageResourceId();
                Intent i = new Intent(MathEquations.this, destinationClass);
                i.putExtra("equationCode", equationCode);
                i.putExtra("imageResourceId", imageId);
                Log.v("PhysicsEquations.class ", "deflects to " + destinationClass + " with code "
                        + equationCode + "and image" + imageId);
                startActivity(i);
            }
        });



    }

    //method to rearrange the list for math list 1
    public void arrangeList1 (){
        //list that contains motion
        mathEquationList.clear();
        mathEquationList.add(MAT1);
        mathEquationList.add(MAT2);

        equationList.setAdapter(equationAdapter);
    }



    // this override will handle what happens after the options menu is created on the bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.physics_selection, menu);
        return true;
    }

    // this override will handle what happens once an item in the menu is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.physics_select_1:
                arrangeList1();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}

