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

    public static Spanned PHYvarDescVelocity = fromHtml("<br><b>v</b> : velocity, the speed of an object represented as a vector that represents both magnitude and direction</br>");
    public static Spanned PHYvarSymVelocity = fromHtml("<b>v</b>");
    public static Spanned PHYvarUnitVelocity = fromHtml("meters/second");

    public static Spanned PHYvarDescDeltaVelocity = fromHtml("<br><b>Δv</b> : change in velocity, difference between final and initial values</br>");
    public static Spanned PHYvarSymDeltaVelocity = fromHtml("<b>Δv</b>");
    public static Spanned PHYvarUnitDeltaVelocity = fromHtml("meters/second");

    public static Spanned PHYvarDescDeltaTime = fromHtml("<br><b>Δt</b> : change in time, specifically referencing the difference between final and initial time</br>");
    public static Spanned PHYvarSymDeltaTime = fromHtml("<b>Δt</b>");
    public static Spanned PHyvarUnitDeltaTime = fromHtml("seconds");

    public static Spanned PHYvarDescInitialVelocity = fromHtml("<br><b>v₀</b> or <b>v<sub><small>initial</small></sub></b> : velocity/speed for object at time=0 (beginning)</br>");
    public static Spanned PHYvarSymInitialVelocity = fromHtml("<b>v₀</b>");
    public static Spanned PHYvarUnitInitialVelocity = fromHtml("meters/second");

    public static Spanned PHYvarDescFinalVelocity = fromHtml("<br><b>v<sub><small>t</small></sub></b> or <b>x<sub><small>final</small></sub></b> : velocity/speed for object at time=t (end)</br>");
    public static Spanned PHYvarSymFinalVelocity = fromHtml("<b>v<sub><small>t</small></sub></b>");
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
    public static Spanned PHYvarUnitForce = fromHtml("Newtons");

    public static Spanned PHYvarDescForceFriction = fromHtml("<br><b>F<sub><small>f</small></sub></b> : a force vector that opposes motion</br>");
    public static Spanned PHYvarSymForceFriction = fromHtml("<b>F<small>f</small></b>");
    public static Spanned PHYvarUnitForceFriction = fromHtml("Newtons");

    public static Spanned PHYvarDescForceNormal = fromHtml("<br><b>F<sub><small>N</small></sub></b> : normal force describes the force - perpendicular to the surface an object is resting on - exerted by the surface on an object to counteract weight</br>");
    public static Spanned PHYvarSymForceNormal = fromHtml("<b>F<small>N</small></b>");
    public static Spanned PHYvarUnitForceNormal = fromHtml("Newtons");

    public static Spanned PHYvarDescWeight = fromHtml("<br><b>W</b> : weight, a term used to describe the force exerted by gravity" +
            " on any object on earth's surface");
    public static Spanned PHYvarSymWeight = fromHtml("<b>W</b>");
    public static Spanned PHYvarUnitWeight = fromHtml("Newtons");

    public static Spanned PHYvarDescForceGravity = fromHtml("<br><b>F<sub><small>g</small></sub></b> : force caused by the influence of gravity</br>");
    public static Spanned PHYvarSymForceGravity = fromHtml("<b>F<small>g</small></b>");
    public static Spanned PHYvarUnitForceGravity = fromHtml("Newtons");

    public static Spanned PHYvarDescCentripetalAcceleration = fromHtml("<br><b><sup>a</sup><small>c</small></b> : acceleration of an object on a curved path, orthogonal to motion of object and directed towards the instantaneous center fo the curvature of the path</br>");
    public static Spanned PHYvarSymCentripetalAcceleration = fromHtml("<b><sup>a</sup><small>c</small></b>");
    public static Spanned PHYvarUnitCentripetalAcceleration = fromHtml("meters/second<sup><small>2</small></sup>");

    public static Spanned PHYvarDescRadius = fromHtml("<br><b>r</b> : the distance between the center of a circle and its perimeter</br>");
    public static Spanned PHYvarSymRadius = fromHtml("<b>r</b>");
    public static Spanned PHYvarUnitRadius = fromHtml("meters");

    public static Spanned PHYvarDescCoefficientFriction = fromHtml("<br><b>μ</b> : coefficient of static friction, multiplier used to determine static (μ<sub><small>s</small></sub>) or kinetic (μ<sub><small>k</small></sub>) friction</br>");
    public static Spanned PHYvarSymCoefficientFriction = fromHtml("<b>μ</b>");
    public static Spanned PHYvarUnitCoefficientFriction = fromHtml("");

    public static Spanned PHYvarDescMomentum = fromHtml("<br><b>p</b> : momentum, a measure of the amount of movement of an object, found by multiplying mass by weight");
    public static Spanned PHYvarSymMomentum = fromHtml("<b>p</b>");
    public static Spanned PHYvarUnitMomentum = fromHtml("Newtons");

    public static Spanned PHYvarDescImpulse = fromHtml("<br><b>J</b> : impulse, vector quantity defined as the change in momentum. Equivalent to the product of average force and the time interval during which the force acts.</br>");
    public static Spanned PHYvarSymImpulse = fromHtml("<b>J</b>");
    public static Spanned PHYvarUnitImpulse = fromHtml("Newton-seconds");

    public static Spanned PHYvarDescWork = fromHtml("<br><b>W</b> : work, the force that exerted on an object that results in its movement</br>");
    public static Spanned PHYvarSymWork = fromHtml("<b>W</b>");
    public static Spanned PHYvarUnitWork = fromHtml("Newton-meters");

    public static Spanned PHYvarDescTheta = fromHtml("<br><b>θ</b> : theta, a symbol used to denote the degrees of an angle between two lines or vectors</br>");
    public static Spanned PHYvarSymTheta = fromHtml("<b>θ</b>");
    public static Spanned PHYvarUnitTheta = fromHtml("degrees");

    public static Spanned PHYvarDescKineticEnergy = fromHtml("<br><b>KE</b> : kinetic energy, the amount of energy stored in the movement of an object</br>");
    public static Spanned PHYvarSymKineticEnergy = fromHtml("<b>KE</b>");
    public static Spanned PHYvarUnitKineticEnergy = fromHtml("Joules");


    //following are special m1 and m2 symbols for universal gravitation equation
    public static Spanned PHYvarSymMass1 = fromHtml("<b>m<small>1</small></b>");
    public static Spanned PHYvarSymMass2 = fromHtml("<b>m<small>2</small></b>");


    // CONSTANTS

    public static double PHYconstantValGravAccelEarth = 9.807;
    public static Spanned PHYconstantDescGravAccelEarth = fromHtml("<br><b>g</b> : symbol used to represent the gravitational acceleration on earth, equal to ~9.8 m/s<sup>2</sup></br>");
    public static Spanned PHYconstantSymGravAccelEarth = fromHtml("<b>g</b>");
    public static Spanned PHYconstantUnitGravAccelEarth = fromHtml("meters/second<sup><small>2</small></sup>");

    public static double PHYconstantValUniversalGravitation = 0.0000000000667408;
    public static Spanned PHYcsontantDescUniversalGravitation = fromHtml("<br><b>G</b> : symbol used to represent the universal gravitational constant, used to calculate force in a gravitational field</br>");



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
        return str.matches("(-)?\\d*.\\d*(-)?(E)?\\d*");  //use RegEx to match, can handle SciNo numbers as well
    }
}