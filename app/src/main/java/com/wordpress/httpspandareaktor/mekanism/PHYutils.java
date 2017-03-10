package com.wordpress.httpspandareaktor.mekanism;

import android.os.Build;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Brian on 1/22/2017.
 */

public class PHYutils {

    //descriptions of variables
    //PHYSICS


    //symbols must not use <sub></sub> until I can figure out how to prevent cutoff in TextViews on solvers

    public static Spanned PHYvarDescDistance = fromHtml("<br><b>d</b> : distance between the center of two objects</br>");
    public static Spanned PHYvarSymDistance = fromHtml("<b>d</b>");
    public static Spanned PHYvarUnitDistance = fromHtml("meters");

    public static Spanned PHYvarDescDisplacement = fromHtml("<br><b>d</b> : displacement, change of position of object (also known as Δx)</br>");
    public static Spanned PHYvarSymDisplacement = fromHtml("<b>d</b>");
    public static Spanned PHYvarUnitDisplacement = fromHtml("meters");

    public static Spanned PHYvarDescInitialPosition = fromHtml("<br><b>x₀</b> or <b>x<sub><small>initial</small></sub></b> : initial position, or position at time=0</br>");
    public static Spanned PHYvarSymInitialPosition = fromHtml("<b>x₀</b>");
    public static Spanned PHYvarUnitInitialPosition = fromHtml("meters");

    public static Spanned PHYvarDescFinalPosition = fromHtml("<br><b>xₜ</b> or <b>x<sub><small>final</small></sub></b> : final position, or position at time=t</br>");
    public static Spanned PHYvarSymFinalPosition = fromHtml("<b>xₜ</b>");
    public static Spanned PHYvarUnitFinalPosition = fromHtml("meters");

    public static Spanned PHYvarDescAverageVelocity = fromHtml("<br><b>v<sub><small>average</small></sub></b> or <b>v</b> : averaged velocity/speed for object from time=0 to time=t</br>");
    public static Spanned PHYvarSymAverageVelocity = fromHtml("<b><sup>v</sup><small>average</small></b>");
    public static Spanned PHYvarUnitAverageVelocity = fromHtml("meters/second");

    public static Spanned PHYvarDescDeltaVelocity = fromHtml("<br><b>Δv</b> : change in velocity, difference between final and initial values</br>");
    public static Spanned PHYvarSymDeltaVelocity = fromHtml("<b>Δv</b>");
    public static Spanned PHYvarUnitDeltaVelocity = fromHtml("meters/second");

    public static Spanned PHYvarDescInitialVelocity = fromHtml("<br><b>v₀</b> or <b>v<sub><small>initial</small></sub></b> : velocity/speed for object at time=0 (beginning)</br>");
    public static Spanned PHYvarSymInitialVelocity = fromHtml("<b>v₀</b>");
    public static Spanned PHYvarUnitInitialVelocity = fromHtml("meters/second");

    public static Spanned PHYvarDescFinalVelocity = fromHtml("<br><b>vₜ</b> or <b>x<sub><small>final</small></sub></b> : velocity/speed for object at time=t (end)</br>");
    public static Spanned PHYvarSymFinalVelocity = fromHtml("<b>vₜ</b>");
    public static Spanned PHYvarUnitFinalVelocity = fromHtml("meters/second");

    public static Spanned PHYvarDescAverageAcceleration = fromHtml("<br><b>a<sub><small>average</small></sub></b> or <b>a</b>: averaged change in velocity/speed for object from time=0 to time=t</br>");
    public static Spanned PHYvarSymAverageAcceleration = fromHtml("<b><sup>a</sup><small>average</small></b>");
    public static Spanned PHYvarUnitAverageAcceleration = fromHtml("meters/second<sup><small>2</small></sup>");

    public static Spanned PHYvarDescTime = fromHtml("<br><b>t</b> : time, representing elapsed amount of time (Δt for 'change in time')</br>");
    public static Spanned PHYvarSymTime = fromHtml("<b>t</b>");
    public static Spanned PHYvarUnitTime = fromHtml("seconds");

    public static Spanned PHYvarDescMass = fromHtml("<br><b>m</b> : mass, measurement of the amount of matter contained within an object</br>");
    public static Spanned PHYvarSymMass = fromHtml("<b>m</b>");
    public static Spanned PHYvarUnitMass = fromHtml("kilograms");

    public static Spanned PHYvarDescForce = fromHtml("<br><b>F</b> : force, an interaction that has the potential to change the motion of an object</br>");
    public static Spanned PHYvarSymForce = fromHtml("<b>F</b>");
    public static Spanned PHYvarUnitForce = fromHtml("kilograms x meters/second<sup><small>2</small></sup>");

    public static Spanned PHYvarDescForceFriction = fromHtml("<br><b>F<sub><small>f</small></sub></b> : force, an interaction that has the potential to change the motion of an object</br>");
    public static Spanned PHYvarSymForceFriction = fromHtml("<b>F<sub><small>f</small></sub></b>");
    public static Spanned PHyvarUnitForceFriction = fromHtml("kilograms x meters/second<sup><small>2</small></sup>");

    public static Spanned PHYvarDescWeight = fromHtml("<br><b>W</b> : weight, a term used to describe the force exerted by gravity" +
            " on any object on earth's surface");
    public static Spanned PHYvarSymWeight = fromHtml("<b>W</b>");
    public static Spanned PHYvarUnitWeight = fromHtml("kilograms x meters/second<sup><small>2</small></sup>");

    public static Spanned PHYvarDescForceGravity = fromHtml("<br><b>F<sub><small>g</small></sub></b> : force caused by the influence of gravity</br>");
    public static Spanned PHYvarSymForceGravity = fromHtml("<b>F<sub><small>g</small></sub></b>");
    public static Spanned PHYvarUnitForceGravity = fromHtml("kilograms x meters/second<sup><small>2</small></sup>");

    //following are special m1 and m2 symbols for universal gravitation equation
    public static Spanned PHYvarSymMass1 = fromHtml("<b><sup>m1</sup></b>");
    public static Spanned PHYvarSymMass2 = fromHtml("<b><sup>m2</sup></b>");


    // CONSTANTS

    public static double PHYconstantValGravAccelEarth = 9.807;
    public static Spanned PHYconstantDescGravAccelEarth = fromHtml("<br><b>g</b> : symbol used to represent the gravitational acceleration on earth, equal to ~9.8 m/s<sup>2</sup></br>");
    public static Spanned PHYconstantSymGravAccelEarth = fromHtml("<b>g</b>");
    public static Spanned PHYconstantUnitGravAccelEarth = fromHtml("meters/second<sup><small>2</small></sup>");

    public static double PHYconstantValUniversalGravitation = 0.0000000000667408;
    public static Spanned PHYcsontantDescUniversalGravitation = fromHtml("<br><b>G</b> : symbol used to represent the universal gravitational constant, used to calculate force in a gravitational field</br>");






    public static final Spanned displacement = fromHtml("<br>d = displacement</br>" +
            "<br>x<sub><small>t</small></sub> = position at time=t, or x<sub><small>final</small></sub></br>" +
            "<br>x<sub><small>0</small></sub> = initial position, position at time = 0");

    public static final Spanned constantVelocity = fromHtml("<br>v<sub><small>constant</small></sub> = constant velocity</br>" +
            "<br>Δd = positional change</br><br>Δt = change in time</br>");

    public static final Spanned constantAcceleration = fromHtml("<br>a<sub><small>constant</small></sub> = constant acceleration</br>" +
            "<br>Δv = change in velocity</br><br>Δt = change in time</br>");


    //static utility methods go in here to help the physics equations

    private void PhysicsUtils() {
    }

    public static Spanned fromHtml(String html) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    //quadratic equation solver for PHY4, returns the positive only because it's time
    public static double PHY4quadratic(double a, double v, double d) {
        Log.v("PHYutils", " recieved a, v, d : " + a + ", " + v + ", " + d);
        double A = 0.5 * a;
        double B = v;
        double C = -d;
        double solution1 =
                //TOP PART
                (-B +
                        Math.sqrt((Math.pow(B, 2)) - ((4) * A * C))
                )
                        //END TOP
                        / (2 * A);

        Log.v("PHYutils", " got solution1 as " + solution1);
        double solution2 =
                //TOP PART
                (-B -
                        Math.sqrt((Math.pow(B, 2)) - ((4) * A * C))
                )
                        //END TOP
                        / (2 * A);

        Log.v("PHYutils", " got solution2 as " + solution2);
        if (solution1 > 0) {
            return solution1;
        } else {
            return solution2;
        }
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("\\d*.\\d*(E)?(-)?\\d*");  //use RegEx to match a number with optional '-' and decimal.
    }
}