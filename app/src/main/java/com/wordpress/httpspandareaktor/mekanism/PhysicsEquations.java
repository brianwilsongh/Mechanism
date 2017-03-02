package com.wordpress.httpspandareaktor.mekanism;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wordpress.httpspandareaktor.mekanism.solvers.PhysMotion1;
import com.wordpress.httpspandareaktor.mekanism.solvers.PhysMotion1x;
import com.wordpress.httpspandareaktor.mekanism.solvers.PhysMotion2;
import com.wordpress.httpspandareaktor.mekanism.solvers.PhysMotion3;
import com.wordpress.httpspandareaktor.mekanism.solvers.PhysMotion4;
import com.wordpress.httpspandareaktor.mekanism.solvers.PhysMotion5;
import com.wordpress.httpspandareaktor.mekanism.solvers.PhysMotion6;
import com.wordpress.httpspandareaktor.mekanism.solvers.ThreeVar;
import com.wordpress.httpspandareaktor.mekanism.solvers.physicsAccelerationSolver1;
import com.wordpress.httpspandareaktor.mekanism.solvers.physicsVelocitySolver1;

import java.util.ArrayList;

public class PhysicsEquations extends AppCompatActivity {

    private EquationAdapter equationAdapter;

    //instantiate physicsEquationList ArrayList for the array adapter
    final ArrayList<Equation> physicsEquationList = new ArrayList<>();

    //instantiate the equationList for later manipulation
    ListView equationList;

    //instantiate all the individual equation objects here
    Equation test;
    Equation motion6; //equation 6 -- def displacement
    Equation velocity1; //equation 7
    Equation acceleration1; //equation 8
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

        test = new Equation("PHY1", ThreeVar.class, R.drawable.phys_disp_eqn1);

        motion6 = new Equation(PhysMotion6.class, "PHY1", R.drawable.phys_disp_eqn1, "3", getString(R.string.phys_eqn_disp1), Utils.displacement);

        velocity1 = new Equation(physicsVelocitySolver1.class, "PHY2", R.drawable.phys_velo_eqn1, "3", getString(R.string.phys_eqn_accel1), Utils.constantVelocity);

        acceleration1 = new Equation(physicsAccelerationSolver1.class, "PHY3", R.drawable.phys_accel_eqn1, "3", getString(R.string.phys_eqn_accel1), Utils.constantAcceleration);

//        motion1 = new Equation(PhysMotion1.class, "PHY4", R.drawable.phys_kin_eqn1, "4", getString(R.string.eqn_kin1), getString(R.string.distance_desc) + "\n" + getString(R.string.init_velo_desc) + "\n" + getString(R.string.accel_desc) + "\n" + getString(R.string.time_desc));
//
//        motion1x = new Equation(PhysMotion1x.class, "PHY5", R.drawable.phys_kin_eqn1x, "4", getString(R.string.eqn_kin2), getString(R.string.distance_desc) + "\n" + getString(R.string.init_displacement_desc) + "\n" + getString(R.string.velocity_desc) + "\n" + getString(R.string.time_desc));
//
//        motion2 = new Equation(PhysMotion2.class, "PHY6", R.drawable.phys_kin_eqn2, "4", getString(R.string.eqn_kin3), getString(R.string.final_velo_desc)+ "\n" + getString(R.string.init_velo_desc) + "\n" + getString(R.string.accel_desc) + "\n" + getString(R.string.time_desc));
//
//        motion3 = new Equation(PhysMotion3.class, "PHY7", R.drawable.phys_kin_eqn3, "4", getString(R.string.eqn_kin4), getString(R.string.final_velo_desc) + "\n" + getString(R.string.init_velo_desc) + "\n" + getString(R.string.accel_desc) + "\n" + getString(R.string.distance_desc));
//
//        motion4 = new Equation(PhysMotion4.class, "PHY8", R.drawable.phys_kin_eqn4, "4", getString(R.string.eqn_kin5), getString(R.string.distance_desc) + "\n" + getString(R.string.final_velo_desc) + "\n" + getString(R.string.init_velo_desc) + "\n" + getString(R.string.time_desc));
//
//        motion5 = new Equation(PhysMotion5.class, "PHY9", R.drawable.phys_kin_eqn5, "4", getString(R.string.eqn_kin6), getString(R.string.distance_desc) + "\n" + getString(R.string.final_velo_desc) + "\n" + getString(R.string.accel_desc) + "\n" + getString(R.string.time_desc));

        //by default, we will add the kinematics
        physicsEquationList.add(test);
        physicsEquationList.add(motion6);
        physicsEquationList.add(velocity1);
        physicsEquationList.add(acceleration1);


        //create and set up list view, adapters
        equationList = (ListView) findViewById(R.id.physics_list);
        equationAdapter = new EquationAdapter(this, physicsEquationList);
        equationList.setAdapter(equationAdapter);

        equationList.setDividerHeight(3);

        // set on item click listener for the ListView to create intents to send user to solvers
        equationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //find the item that was clicked on, when clicked send intent to correct solver with code
                Equation equation = physicsEquationList.get(position);
                Class destinationClass = equation.getSolver();
                String equationCode = equation.getCode();
                int imageId = equation.getImageResourceId();
                Intent i = new Intent(PhysicsEquations.this, destinationClass);
                i.putExtra("equationCode", equationCode);
                i.putExtra("imageResourceId", imageId);
                Log.v("PhysicsEquations.class ", "deflects to " + destinationClass + " with code "
                        + equationCode + "and image" + imageId);
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
