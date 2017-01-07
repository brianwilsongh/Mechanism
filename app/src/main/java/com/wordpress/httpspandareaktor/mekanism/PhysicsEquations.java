package com.wordpress.httpspandareaktor.mekanism;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class PhysicsEquations extends AppCompatActivity {

    private EquationAdapter equationAdapter;

    // from stack overflow, how to super/subscript:
    // ((TextView)findViewById(R.id.text)).setText(Html.fromHtml("X<sup><small>2</small></sup>"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics_equations);

        Toast.makeText(this, "Scroll and tap to select", Toast.LENGTH_LONG).show();


        //create Equation objects for future use
        Equation kinematics1 = new Equation(KinematicsSolver1.class, R.drawable.eqn_kin1, "Variables: 4", "Kinematics", getString(R.string.distance_desc) + "\n" + getString(R.string.init_velo_desc) + "\n" + getString(R.string.accel_desc) + "\n" + getString(R.string.time_desc));

        Equation kinematics2 = new Equation(KinematicsSolver2.class, R.drawable.eqn_kin2, "Variables: 4", "Kinematics", getString(R.string.final_velo_desc)+ "\n" + getString(R.string.init_velo_desc) + "\n" + getString(R.string.accel_desc) + "\n" + getString(R.string.time_desc));

        Equation kinematics3 = new Equation(KinematicsSolver3.class, R.drawable.eqn_kin3, "Variables: 4", "Kinematics", getString(R.string.final_velo_desc) + "\n" + getString(R.string.init_velo_desc) + "\n" + getString(R.string.accel_desc) + "\n" + getString(R.string.distance_desc));

        Equation kinematics4 = new Equation(KinematicsSolver4.class, R.drawable.eqn_kin4, "Variables: 4", "Kinematics", getString(R.string.distance_desc) + "\n" + getString(R.string.final_velo_desc) + "\n" + getString(R.string.init_velo_desc) + "\n" + getString(R.string.time_desc));

        Equation kinematics5 = new Equation(KinematicsSolver5.class, R.drawable.eqn_kin5, "Variables: 4", "Kinematics", getString(R.string.distance_desc) + "\n" + getString(R.string.final_velo_desc) + "\n" + getString(R.string.accel_desc) + "\n" + getString(R.string.time_desc));

        //create ArrayList for adapter that will display equations to choose from
        final ArrayList<Equation> physicsEquationList = new ArrayList<>();
        physicsEquationList.add(kinematics1);
        physicsEquationList.add(kinematics2);
        physicsEquationList.add(kinematics3);
        physicsEquationList.add(kinematics4);
        physicsEquationList.add(kinematics5);

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
