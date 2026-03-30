package com.example.movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService {
    private final Map<String, Booking> bookings;
    private int bookingCounter;

    public BookingService() {
        this.bookings = new HashMap<>();
        this.bookingCounter = 0;
    }

    // validates and books the requested seats; returns a confirmed booking
    public Booking bookSeats(Show show, List<String> seatIds, String userName) {
        // first pass: check everything before touching any seat
        List<Seat> toBook = new ArrayList<>();
        for (String seatId : seatIds) {
            Seat seat = show.findSeat(seatId);
            if (seat == null) {
                throw new IllegalArgumentException("Seat " + seatId + " not found in show " + show.getShowId());
            }
            if (seat.isBooked()) {
                throw new IllegalStateException("Seat " + seatId + " is already booked.");
            }
            toBook.add(seat);
        }

        // second pass: mark seats and compute total
        int total = 0;
        for (Seat seat : toBook) {
            seat.book();
            total += seat.getCategory().getPrice();
        }

        bookingCounter++;
        String bookingId = "B-" + bookingCounter;
        Booking booking = new Booking(bookingId, userName, show.getShowId(), toBook, total);
        bookings.put(bookingId, booking);

        System.out.println("Booking confirmed: " + booking);
        return booking;
    }

    // cancels a booking and frees its seats for others
    public void cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking " + bookingId + " not found.");
        }
        for (Seat seat : booking.getSeats()) {
            seat.release();
        }
        bookings.remove(bookingId);
        System.out.println("Booking " + bookingId + " cancelled. Seats released.");
    }

    // returns all seats that are still available for a show
    public List<Seat> getAvailableSeats(Show show) {
        List<Seat> available = new ArrayList<>();
        for (Seat seat : show.getSeats()) {
            if (!seat.isBooked()) {
                available.add(seat);
            }
        }
        return available;
    }
}
