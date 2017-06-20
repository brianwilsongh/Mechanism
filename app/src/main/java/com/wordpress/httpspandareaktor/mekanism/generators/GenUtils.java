package com.wordpress.httpspandareaktor.mekanism.generators;

import android.util.Log;

import java.util.Random;

/**
 * Created by brian on 6/12/17.
 */

public class GenUtils {

    public static String generateRandomCode(int eqLength){
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        int n = r.nextInt(eqLength);

        for (int ctr = 0; ctr < eqLength; ctr ++) {
            if (ctr != n) {
                sb.append("1");
            } else {
                sb.append("0");
            }
        }
        return sb.toString();
    }


    public static Double generateRandomInRange(Double floor, Double ceil){
        Random random = new Random();
        Double n = floor + ((ceil + floor * -1) * random.nextDouble());
        Log.v("GenUtils.java", " generated random " + n + " from range min/max: " + floor + ceil);
        return n;

    }

    public static Double generateRandomInRange(int floor, int ceil){
        //will not hit the ceiling #
        Random random = new Random();
        int n = floor + random.nextInt(ceil + floor * -1);
        return Double.parseDouble(String.valueOf(n));

    }

    public static Double truncateDecimals(Double num, int places){
        int tens = ((Double) Math.pow(10, places)).intValue();
        return (Math.floor(num * tens) / tens);

    }


    public static String quantumObject(){
        //EXTREMELY small numbers, should be used in Tier1/2 exclusively
        String[] objects = new String[]{"electron", "proton", "hadron", "particle", "nucleon", "meson"};
        Random r = new Random();
        int idx = r.nextInt(objects.length);
        return objects[idx];
    }

    public static String normalObject(){
        String[] objects = new String[]{"dog", "cat", "hamster", "bird", "box", "human", "car",
                "sailboat", "bike", "moose", "mouse", "block", "raccoon", "velociraptor", "monkey", "elephant",
        "deer", "baseball", "penguin", "truck", "tank", "wolf"};
        Random r = new Random();
        int idx = r.nextInt(objects.length);
        return objects[idx];
    }

    public static String massiveObject(){
        String[] objects = new String[]{"planet", "asteroid", "alien mothership", "moon", "comet",
        "star", "quasar", "UFO"};
        Random r = new Random();
        int idx = r.nextInt(objects.length);
        return objects[idx];
    }
}
