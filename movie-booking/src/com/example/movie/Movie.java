package com.example.movie;

public class Movie {
    private final String title;
    private final int durationMins;

    public Movie(String title, int durationMins) {
        this.title = title;
        this.durationMins = durationMins;
    }

    public String getTitle()  { return title; }
    public int getDuration()  { return durationMins; }

    @Override
    public String toString() {
        return title + " (" + durationMins + " mins)";
    }
}
