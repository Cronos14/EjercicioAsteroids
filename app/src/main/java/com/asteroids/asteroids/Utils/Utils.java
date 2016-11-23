package com.asteroids.asteroids.Utils;

import java.util.Calendar;

/**
 * Created by Tadeo-developer on 22/11/16.
 */

public class Utils {

    public static String getDate() {

        Calendar calendario = Calendar.getInstance();
        calendario.setTimeInMillis(System.currentTimeMillis());
        int day = calendario.get(Calendar.DAY_OF_MONTH);
        int month = calendario.get(Calendar.MONTH);
        int year = calendario.get(Calendar.YEAR);


        return year + "-" + (month + 1) + "-" + day;
    }
}
