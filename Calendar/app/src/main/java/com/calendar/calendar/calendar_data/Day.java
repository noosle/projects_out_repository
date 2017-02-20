package com.calendar.calendar.calendar_data;

import java.io.Serializable;


public class Day implements Serializable {

    private String number;
    private boolean weekend;
    private boolean preWeekend;

    public Day(int i, boolean weekend, boolean preWeekend) {
        if (i == 0)
            setNumber("");
        else
            setNumber(String.valueOf(i));
        setWeekend(weekend);
        setPreWeekend(preWeekend);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isWeekend() {
        return weekend;
    }

    public void setWeekend(boolean weekend) {
        this.weekend = weekend;
    }

    public boolean isPreWeekend() {
        return preWeekend;
    }

    public void setPreWeekend(boolean preWeekend) {
        this.preWeekend = preWeekend;
    }
}
