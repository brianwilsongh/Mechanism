package com.wordpress.httpspandareaktor.mekanism;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
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

import com.wordpress.httpspandareaktor.mekanism.solvers.FourVar;
import com.wordpress.httpspandareaktor.mekanism.solvers.ThreeVar;
import com.wordpress.httpspandareaktor.mekanism.solvers.TwoVar;

import java.util.ArrayList;

public class PhysicsEquations extends AppCompatActivity {

    private EquationAdapter equationAdapter;

    //instantiate physicsEquationList ArrayList for the array adapter
    final ArrayList<Equation> physicsEquationList = new ArrayList<>();

    //instantiate the equationList for later manipulation
    ListView equationList;

    //instantiate all the individual equation objects here
    Equation PHY1;    Equation PHY2;    Equation PHY3;    Equation PHY4;
    Equation PHY5;    Equation PHY6;    Equation PHY7;    Equation PHY8;
    Equation PHY9;    Equation PHY10;    Equation PHY11;    Equation PHY12;
    Equation PHY13;
    Equation PHY14;
    Equation PHY15;
    Equation PHY16;
    Equation PHY17;
    Equation PHY18;
    Equation PHY19;


    // from stack overflow, how to super/subscript:
    // ((TextView)findViewById(R.id.text)).setText(Html.fromHtml("X<sup><small>2</small></sup>"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics_equations);

        Toast.makeText(this, "Scroll and tap to select", Toast.LENGTH_LONG).show();

        //create Equation objects for future use
        //KINEMATIC AND MOTION EQUATIONS

        PHY1 = new Equation("PHY1", ThreeVar.class, R.drawable.phy1); //definition displacement
        PHY2 = new Equation("PHY2", ThreeVar.class, R.drawable.phy2); //definition velocity
        PHY3 = new Equation("PHY3", ThreeVar.class, R.drawable.phy3); //definition acceleration
        PHY4 = new Equation("PHY4", FourVar.class, R.drawable.phy4); //kin equation
        PHY5 = new Equation("PHY5", FourVar.class, R.drawable.phy5); //kin equation
        PHY6 = new Equation("PHY6", FourVar.class, R.drawable.phy6); //kin equation
        PHY7 = new Equation("PHY7", FourVar.class, R.drawable.phy7); //kin equation
        PHY8 = new Equation("PHY8", FourVar.class, R.drawable.phy8); //kin equation
        PHY9 = new Equation("PHY9", ThreeVar.class, R.drawable.phy9); //alt definition avg velo

        PHY13 = new Equation("PHY13", ThreeVar.class, R.drawable.phy13); //centripetal accel

        PHY10 = new Equation("PHY10", ThreeVar.class, R.drawable.phy10); //newton's second
        PHY11 = new Equation("PHY11", TwoVar.class, R.drawable.phy11); //weight
        PHY12 = new Equation("PHY12", FourVar.class, R.drawable.phy12); //universal gravitation
        PHY14 = new Equation("PHY14", ThreeVar.class, R.drawable.phy14); //force of friction
        PHY15 = new Equation("PHY15", ThreeVar.class, R.drawable.phy15); //momentum definition
        PHY16 = new Equation("PHY16", ThreeVar.class, R.drawable.phy16); //impulse-momentum 1
        PHY17 = new Equation("PHY17", ThreeVar.class, R.drawable.phy17); //impulse-momentum 2
        PHY18 = new Equation("PHY18", FourVar.class, R.drawable.phy18); //work definition with costheta

        PHY19 = new Equation("PHY19", ThreeVar.class, R.drawable.phy19); //kinetic energy equation


        //create and set up list view, adapters
        equationList = (ListView) findViewById(R.id.physics_list);
        equationAdapter = new EquationAdapter(this, physicsEquationList);
        //by default we will add the kinematics motion equations in list 1 first
        arrangeList1();
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

    //first list arrangement, motion equations
    public void arrangeList1 (){
        //list that contains motion
        physicsEquationList.clear();
        physicsEquationList.add(PHY1);
        physicsEquationList.add(PHY2);
        physicsEquationList.add(PHY3);
        physicsEquationList.add(PHY4);
        physicsEquationList.add(PHY5);
        physicsEquationList.add(PHY6);
        physicsEquationList.add(PHY7);
        physicsEquationList.add(PHY8);
        physicsEquationList.add(PHY9);

        equationList.setAdapter(equationAdapter);

    }

    //second list, contains circular motion equations
    public void arrangeList2 () {
        physicsEquationList.clear();

        physicsEquationList.add(PHY13);


        equationList.setAdapter(equationAdapter);
    }

    //third arrangelist func
    public void arrangeList3 () {
        physicsEquationList.clear();
        physicsEquationList.add(PHY10);
        physicsEquationList.add(PHY11);
        physicsEquationList.add(PHY12);
        physicsEquationList.add(PHY14);

        physicsEquationList.add(PHY15);
        physicsEquationList.add(PHY16);
        physicsEquationList.add(PHY17);
        physicsEquationList.add(PHY18);

        equationList.setAdapter(equationAdapter);
    }

    //fourth arrangelist func
    public void arrangeList4 () {
        physicsEquationList.clear();
        physicsEquationList.add(PHY19);

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

            case R.id.physics_select_2:
                arrangeList2();
                return true;

            case R.id.physics_select_3:
                arrangeList3();
                return true;

            case R.id.physics_select_4:
                arrangeList4();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
