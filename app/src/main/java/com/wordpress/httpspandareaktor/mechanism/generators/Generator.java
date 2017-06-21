package com.wordpress.httpspandareaktor.mechanism.generators;

/**
 * Created by brian on 6/13/17.
 */

public interface Generator {
    public String getFormattedQuestion();
    public String getTrueAnswer();
    public String getHint();
}
