package com.elysey.daybook.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getCurrentTime(long currentMills){

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date resultdate = new Date(currentMills);
        return sdf.format(resultdate);
    }

    public static String getCurrentDate(long currentMills){

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date resultdate = new Date(currentMills);
        return sdf.format(resultdate);
    }

}
