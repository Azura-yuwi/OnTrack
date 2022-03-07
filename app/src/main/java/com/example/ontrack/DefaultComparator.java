package com.example.ontrack;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Comparator;

public class DefaultComparator implements Comparator<Event> {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public int compare(Event a, Event b)
    {
        if(!a.date.equals(b.date))
        {
            return a.date.compareTo(b.date);
        }

        return a.name.compareTo(b.name);
    }
}
