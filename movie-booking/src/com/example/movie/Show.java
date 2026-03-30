package com.example.movie;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Show {
    private final String showId;
    private final Movie movie;
    private final String screenName;
    private final LocalDateTime startTime;
    private final List<Seat> seats;

    public Show(String showId, Movie movie, String screenName,
                LocalDateTime startTime, List<Seat> seats) {
        this.showId = showId;
        this.movie = movie;
        this.screenName = screenName;
        this.startTime = startTime;
        this.seats = new ArrayList<>(seats);
    }

    public String getShowId()           { return showId; }
    public Movie getMovie()             { return movie; }
    public String getScreenName()       { return screenName; }
    public LocalDateTime getStartTime() { return startTime; }
    public List<Seat> getSeats()        { return seats; }

    // find a seat by id within this show
    public Seat findSeat(String seatId) {
        for (Seat seat : seats) {
            if (seat.getSeatId().equals(seatId)) {
                return seat;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return showId + " | " + movie.getTitle() + " | " + screenName + " | " + startTime;
    }
}
