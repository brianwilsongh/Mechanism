package com.wordpress.httpspandareaktor.mekanism;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.SpannedString;
import android.widget.TextView;

/**
 * Created by Brian on 1/22/2017.
 */

public class Utils {

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


}
