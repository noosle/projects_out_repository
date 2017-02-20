package com.calendar.calendar.utils;

import android.content.Context;

import com.calendar.calendar.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by noosle on 18.02.2017.
 */
public class CalendarManager {

    public static Calendar calendar;
    public static HashMap<Integer, ArrayList<Integer>> weekendsData;

    public static Calendar getCalendar() {
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        return calendar;
    }

    public static String getMonthNameById(Context context, int id) {

        switch (id) {
            case 0:
                return context.getString(R.string.january);
            case 1:
                return context.getString(R.string.february);
            case 2:
                return context.getString(R.string.march);
            case 3:
                return context.getString(R.string.april);
            case 4:
                return context.getString(R.string.may);
            case 5:
                return context.getString(R.string.june);
            case 6:
                return context.getString(R.string.july);
            case 7:
                return context.getString(R.string.august);
            case 8:
                return context.getString(R.string.september);
            case 9:
                return context.getString(R.string.october);
            case 10:
                return context.getString(R.string.november);
            case 11:
                return context.getString(R.string.december);
        }
        return "";
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static void initWeekeddsData() {
        ArrayList<Integer> january = new ArrayList<>();
        january.add(1);
        january.add(2);
        january.add(3);
        january.add(4);
        january.add(5);
        january.add(6);
        january.add(7);
        january.add(8);
        ArrayList<Integer> february = new ArrayList<>();
        february.add(23);
        ArrayList<Integer> march = new ArrayList<>();
        march.add(8);
        ArrayList<Integer> may = new ArrayList<>();
        may.add(1);
        may.add(9);
        ArrayList<Integer> june = new ArrayList<>();
        june.add(12);
        ArrayList<Integer> november = new ArrayList<>();
        november.add(4);
        weekendsData = new HashMap<>();
        weekendsData.put(0, january);
        weekendsData.put(1, february);
        weekendsData.put(2, march);
        weekendsData.put(4, may);
        weekendsData.put(5, june);
        weekendsData.put(10, november);

    }

    public static boolean checkIsWeekend(int month, int day) {

        if (weekendsData == null)
            initWeekeddsData();

        if (weekendsData.get(month) == null)
            return false;

        ArrayList<Integer> weekends = weekendsData.get(month);
        for (int i = 0; i < weekends.size(); i++) {
            if (weekends.get(i) == day)
                return true;

        }

        return false;
    }


}
