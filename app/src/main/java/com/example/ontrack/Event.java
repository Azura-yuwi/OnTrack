package com.example.ontrack;

public class Event {
    public String name;
    public String description;

    public Event(String name)
    {
        this.name = name;
    }
    public Event(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
}
