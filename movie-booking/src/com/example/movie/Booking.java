package com.example.movie;

import java.util.ArrayList;
import java.util.List;

public class Booking {
    private final String bookingId;
    private final String userName;
    private final String showId;
    private final List<Seat> seats;
    private final int totalAmount;

    public Booking(String bookingId, String userName, String showId,
                   List<Seat> seats, int totalAmount) {
        this.bookingId = bookingId;
        this.userName = userName;
        this.showId = showId;
        this.seats = new ArrayList<>(seats);
        this.totalAmount = totalAmount;
    }

    public String getBookingId()  { return bookingId; }
    public String getUserName()   { return userName; }
    public String getShowId()     { return showId; }
    public List<Seat> getSeats()  { return seats; }
    public int getTotalAmount()   { return totalAmount; }

    @Override
    public String toString() {
        return "Booking#" + bookingId
                + " | User: " + userName
                + " | Show: " + showId
                + " | Seats: " + seats
                + " | Total: Rs " + totalAmount;
    }
}
