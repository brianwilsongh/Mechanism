package com.wordpress.httpspandareaktor.mekanism;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class PhysicsEquations extends AppCompatActivity {

    private EquationAdapter equationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics_equations);


        //create Equation objects for future use
        Equation kinematics1 = new Equation(KinematicsSolver1.class, "Variables: 4", "Kinematics", "Takes: " + "Displacement (\u0394x), Initial Velocity (v\u2080), Constant Acceleration (a), Time (t)");

        //create ArrayList for adapter that will display equations to choose from
        final ArrayList<Equation> physicsEquationList = new ArrayList<>();
        physicsEquationList.add(kinematics1);

        //create and set up list view, adapters
        ListView equationList = (ListView) findViewById(R.id.physics_list);
        equationAdapter = new EquationAdapter(this, physicsEquationList);
        equationList.setAdapter(equationAdapter);

        // set on item click listener for the ListView to create intents to send user to solvers
        equationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //find the item that was clicked on
                Equation equation = physicsEquationList.get(position);
                Class destinationClass = equation.getSolver();
                Intent i = new Intent(PhysicsEquations.this, destinationClass);
                startActivity(i);
            }
        });





    }
}
