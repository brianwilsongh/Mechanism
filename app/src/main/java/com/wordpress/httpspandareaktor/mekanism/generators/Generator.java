package com.wordpress.httpspandareaktor.mekanism.generators;

import android.graphics.drawable.Drawable;

/**
 * Created by brian on 6/13/17.
 */

public interface Generator {
    public String getFormattedQuestion();
    public String getTrueAnswer();
    public String getHint();
}
