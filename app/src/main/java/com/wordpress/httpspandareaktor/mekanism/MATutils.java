package com.wordpress.httpspandareaktor.mekanism;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

/**
 * Created by Brian on 3/15/2017.
 */

public class MATutils {

    //descriptions of variables
    //PHYSICS


    //symbols must not use <sub></sub> until I can figure out how to prevent cutoff in TextViews on solvers

    public static Spanned MATvarDescY = fromHtml("<br><b>y</b> : in a function, the y coordinate (on the y-axis) at the given x coordinate. Can also be f(x)</br>");
    public static Spanned MATvarSymY = fromHtml("<b>y</b>");
    public static Spanned MATvarUnitsY = fromHtml("units");

    public static Spanned MATvarDescM = fromHtml("<br><b>m</b> : refers to the slope of the function, rate of change in y relative to x</br>");
    public static Spanned MATvarSymM = fromHtml("<b>m</b>");
    public static Spanned MATvarUnitsM = fromHtml("");

    public static Spanned MATvarDescX = fromHtml("<br><b>x</b> : the coordinate being referenced on the horizontal x-axis</br>");
    public static Spanned MATvarSymX = fromHtml("<b>x</b>");
    public static Spanned MATvarUnitsX = fromHtml("units");

    public static Spanned MATvarDescB = fromHtml("<br><b>b</b> : the y-intercept of the function, the value of y when x=0</br>");
    public static Spanned MATvarSymB = fromHtml("<b>b</b>");
    public static Spanned MATvarUnitsB = fromHtml("units");



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
    public static double quadraticEquation(double a, double b, double c) {
        Log.v("MATutls.quadEquation", " got a, b, c : " + a + ", " + b + ", " + c);
        double solution1 =
                //TOP PART
                (-b +
                        Math.sqrt((Math.pow(b, 2)) - ((4) * a * c))
                )
                        //END TOP
                        / (2 * a);

        Log.v("PHYutils", " got solution1 as " + solution1);
        double solution2 =
                //TOP PART
                (-b -
                        Math.sqrt((Math.pow(b, 2)) - ((4) * a * c))
                )
                        //END TOP
                        / (2 * a);

        Log.v("MATutls.quadEquation", " got solution2 as " + solution2);
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
