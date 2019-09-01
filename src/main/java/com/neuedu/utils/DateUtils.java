package com.neuedu.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateUtils {

    private static final String STANDART_FORMATE = "yyyy-MM-dd HH:mm:ss";

    /**
     * date-->string
     * */
    public static String dateToStr(Date date){

        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDART_FORMATE);
    }
    public static String dateToStr(Date date,String formate){
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formate);
    }

    /**
     * string-->date
     * */
    public static Date strToDate(String str){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDART_FORMATE);
        DateTime dateTime = dateTimeFormatter.parseDateTime(str);
        return dateTime.toDate();
    }
    public static Date strToDate(String str,String formate){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formate);
        DateTime dateTime = dateTimeFormatter.parseDateTime(str);
        return dateTime.toDate();
    }


    public static void main(String[] args) {
        System.out.println(dateToStr(new Date()));
    }

}
