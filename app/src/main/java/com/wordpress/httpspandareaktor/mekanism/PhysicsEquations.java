package com.wordpress.httpspandareaktor.mekanism;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wordpress.httpspandareaktor.mekanism.Solvers.PhysMotion1;
import com.wordpress.httpspandareaktor.mekanism.Solvers.PhysMotion1x;
import com.wordpress.httpspandareaktor.mekanism.Solvers.PhysMotion2;
import com.wordpress.httpspandareaktor.mekanism.Solvers.PhysMotion3;
import com.wordpress.httpspandareaktor.mekanism.Solvers.PhysMotion4;
import com.wordpress.httpspandareaktor.mekanism.Solvers.PhysMotion5;
import com.wordpress.httpspandareaktor.mekanism.Solvers.PhysMotion6;
import com.wordpress.httpspandareaktor.mekanism.Solvers.physicsVelocitySolver1;

import java.util.ArrayList;

public class PhysicsEquations extends AppCompatActivity {

    private EquationAdapter equationAdapter;

    //instantiate physicsEquationList ArrayList for the array adapter
    final ArrayList<Equation> physicsEquationList = new ArrayList<>();

    //instantiate the equationList for later manipulation
    ListView equationList;

    //instantiate all the individual equation objects here
    Equation motion6; //equation 6 -- def displacement
    Equation velocity1; //equation 7
    Equation acceleration1; //equation 8
    Equation motion1; //equation 1
    Equation motion2; //equation 2
    Equation motion3; //equation 3
    Equation motion4; //equation 4
    Equation motion5; //equation 5
    Equation motion1x; //equation 1 X
    Equation physGrav1; // grav equation 1 -- universal grav


    // from stack overflow, how to super/subscript:
    // ((TextView)findViewById(R.id.text)).setText(Html.fromHtml("X<sup><small>2</small></sup>"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics_equations);

        Toast.makeText(this, "Scroll and tap to select", Toast.LENGTH_LONG).show();

        //create Equation objects for future use
        //KINEMATIC AND MOTION EQUATIONS

        motion6 = new Equation(PhysMotion6.class, R.drawable.phys_disp_eqn1, "3", getString(R.string.phys_eqn_disp1), PhysicsUtils.displacement);

        velocity1 = new Equation(physicsVelocitySolver1.class, R.drawable.phys_velo_eqn1, "3", getString(R.string.phys_eqn_accel1), PhysicsUtils.constantVelocity);

        acceleration1 = new Equation(PhysMotion6.class, R.drawable.phys_accel_eqn1, "3", getString(R.string.phys_eqn_accel1), PhysicsUtils.constantAcceleration);

        motion1 = new Equation(PhysMotion1.class, R.drawable.phys_kin_eqn1, "4", getString(R.string.eqn_kin1), getString(R.string.distance_desc) + "\n" + getString(R.string.init_velo_desc) + "\n" + getString(R.string.accel_desc) + "\n" + getString(R.string.time_desc));

        motion1x = new Equation(PhysMotion1x.class, R.drawable.phys_kin_eqn1x, "4", getString(R.string.eqn_kin2), getString(R.string.distance_desc) + "\n" + getString(R.string.init_displacement_desc) + "\n" + getString(R.string.velocity_desc) + "\n" + getString(R.string.time_desc));

        motion2 = new Equation(PhysMotion2.class, R.drawable.phys_kin_eqn2, "4", getString(R.string.eqn_kin3), getString(R.string.final_velo_desc)+ "\n" + getString(R.string.init_velo_desc) + "\n" + getString(R.string.accel_desc) + "\n" + getString(R.string.time_desc));

        motion3 = new Equation(PhysMotion3.class, R.drawable.phys_kin_eqn3, "4", getString(R.string.eqn_kin4), getString(R.string.final_velo_desc) + "\n" + getString(R.string.init_velo_desc) + "\n" + getString(R.string.accel_desc) + "\n" + getString(R.string.distance_desc));

        motion4 = new Equation(PhysMotion4.class, R.drawable.phys_kin_eqn4, "4", getString(R.string.eqn_kin5), getString(R.string.distance_desc) + "\n" + getString(R.string.final_velo_desc) + "\n" + getString(R.string.init_velo_desc) + "\n" + getString(R.string.time_desc));

        motion5 = new Equation(PhysMotion5.class, R.drawable.phys_kin_eqn5, "4", getString(R.string.eqn_kin6), getString(R.string.distance_desc) + "\n" + getString(R.string.final_velo_desc) + "\n" + getString(R.string.accel_desc) + "\n" + getString(R.string.time_desc));

        //by default, we will add the kinematics
        physicsEquationList.add(motion6);
        physicsEquationList.add(velocity1);
        physicsEquationList.add(acceleration1);
        physicsEquationList.add(motion1);
        physicsEquationList.add(motion1x);
        physicsEquationList.add(motion2);
        physicsEquationList.add(motion3);
        physicsEquationList.add(motion4);
        physicsEquationList.add(motion5);

        //create and set up list view, adapters
        equationList = (ListView) findViewById(R.id.physics_list);
        equationAdapter = new EquationAdapter(this, physicsEquationList);
        equationList.setAdapter(equationAdapter);

        equationList.setDividerHeight(3);

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

    //method to rearrange the list for kinematics equations only
    public void arrangeList1 (){
        physicsEquationList.clear();
        physicsEquationList.add(motion6);
        physicsEquationList.add(velocity1);
        physicsEquationList.add(acceleration1);
        physicsEquationList.add(motion1);
        physicsEquationList.add(motion1x);
        physicsEquationList.add(motion2);
        physicsEquationList.add(motion3);
        physicsEquationList.add(motion4);
        physicsEquationList.add(motion5);
        equationList.setAdapter(equationAdapter);

    }

    //method to rearrange the list to show gravity equations only
    public void arrangeList2 () {
        physicsEquationList.clear();
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

            case R.id.physics_select_kinematics:
                arrangeList1();
                return true;

            case R.id.physics_select_gravity:
                arrangeList2();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
