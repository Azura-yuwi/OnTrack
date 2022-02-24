package com.example.ontrack;


import android.os.Build;
import androidx.annotation.RequiresApi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Event {
    public String name;
    public String description;
    public LocalDate date;

    public Event(String name)
    {
        this.name = name;
    }

    public Event(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDate(int year, int month, int day)
    {
        date = LocalDate.of(year,month,day);
    }

    public String makeDate()
    {
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek weekday = date.getDayOfWeek();
        String dateToReturn = weekday.toString() + ", " + month.toString() + " " + day + ", " + year;
        return dateToReturn;
    }
}
