package com.hn.onemonthcalendarview;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarUtil {
    static List<DataObject> buildShortData(Date date, List<Integer> selectedDates) {
        if (date == null) {
            date = new Date();
        }
        List<DataObject> result = new ArrayList<>();
        result.addAll(titles());

        int dayOfWeek = dayOfWeek(date);
        int dayOfMonth = dayOfMonth(date);
        Logger.debug("dayofweek = " + dayOfWeek);
        Logger.debug("dayOfMonth = " + dayOfMonth);

        int day;
        for (int i = 1; i <= 7; i++) {
            DataObject object = new DataObject();
            if(i <= dayOfWeek){
                day = dayOfMonth - dayOfWeek + i;
                object.isToday = (i == dayOfWeek);
            }
            else{
                day = (i + dayOfMonth - dayOfWeek);
            }
            object.text = String.valueOf(day);
            object.isSelected = (selectedDates != null && selectedDates.contains(day));
            result.add(object);
        }

        return result;
    }
    private static int dayOfWeekOfTheFirstDayOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return dayOfWeek(calendar.getTime());
    }

    public static List<DataObject> buildData(Date date1, List<Integer> selectedDates){
        List<DataObject> result = new ArrayList<>();
        result.addAll(titles());

        int indexOfTheFirstDayOfMonth = dayOfWeekOfTheFirstDayOfMonth(date1);
        for (int i = 0; i < indexOfTheFirstDayOfMonth - 1; i++) {
            DataObject object = new DataObject();
            object.text = "";
            result.add(object);
        }
        int total = numOfMonthDays(date1);

        int today = dayOfMonth(date1);

        for (int i = 1; i <= total; i++) {
            DataObject object = new DataObject();
            object.isSelected = (selectedDates != null && selectedDates.contains(i));
            object.text = "" + i;
            object.isToday = (today == i);
            result.add(object);
        }
        return result;
    }

    public static int dayOfWeek(String dateString) {
        return dayOfWeek(dateFromString(dateString));
    }

    public static int dayOfMonth(Date date) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        return c1.get(Calendar.DAY_OF_MONTH);
    }

    public static int dayOfWeek(Date date) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        return c1.get(Calendar.DAY_OF_WEEK);
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static Date dateFromString(String sourceDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate;
        try {
            myDate = format.parse(sourceDate);
        } catch (ParseException e) {
            e.printStackTrace();
            myDate = new Date();
        }
        return myDate;
    }

    public static int numOfMonthDays(Date date) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        return c1.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static String dayOfWeekString(String dateString) {
        return dayOfWeekString(dateFromString(dateString));
    }

    public static String dayOfWeekString(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("EEE", Locale.getDefault());
        return fmt.format(date).toUpperCase();
    }

    public static List<DataObject> titles() {
        String sourceDate = "2013-02-17";
        Date myDate = dateFromString(sourceDate);
        List<DataObject> result = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("EEE", Locale.getDefault());
        for (int i = 0; i < 7; i++) {
            DataObject dataObject = new DataObject();
            dataObject.text = fmt.format(myDate).toUpperCase();
            result.add(dataObject);
            myDate = addDays(myDate, 1);
        }
        return result;
    }
}
