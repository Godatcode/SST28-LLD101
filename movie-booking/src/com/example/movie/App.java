package com.example.movie;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // setup: movies
        Movie movie1 = new Movie("Inception", 148);
        Movie movie2 = new Movie("Interstellar", 169);

        // setup: seats for screen 1 (rows A-C, 3 seats each)
        List<Seat> screen1Seats = new ArrayList<>();
        screen1Seats.add(new Seat("A1", 1, 1, SeatCategory.REGULAR));
        screen1Seats.add(new Seat("A2", 1, 2, SeatCategory.REGULAR));
        screen1Seats.add(new Seat("A3", 1, 3, SeatCategory.REGULAR));
        screen1Seats.add(new Seat("B1", 2, 1, SeatCategory.PREMIUM));
        screen1Seats.add(new Seat("B2", 2, 2, SeatCategory.PREMIUM));
        screen1Seats.add(new Seat("C1", 3, 1, SeatCategory.VIP));
        screen1Seats.add(new Seat("C2", 3, 2, SeatCategory.VIP));

        // setup: shows
        Show show1 = new Show("S1", movie1, "Screen-1",
                LocalDateTime.of(2025, 6, 10, 18, 0), screen1Seats);
        Show show2 = new Show("S2", movie2, "Screen-2",
                LocalDateTime.of(2025, 6, 10, 21, 0), buildScreen2Seats());

        BookingService service = new BookingService();

        System.out.println("=== Movie Ticket Booking System ===\n");
        System.out.println("Show: " + show1);
        System.out.println("Available seats: " + service.getAvailableSeats(show1));

        // 1. Alice books A1 and B1
        System.out.println("\n-- Alice books A1, B1 --");
        Booking b1 = service.bookSeats(show1, Arrays.asList("A1", "B1"), "Alice");

        // 2. Bob books C1 (VIP)
        System.out.println("\n-- Bob books C1 --");
        Booking b2 = service.bookSeats(show1, Arrays.asList("C1"), "Bob");

        System.out.println("\nAvailable after bookings: " + service.getAvailableSeats(show1));

        // 3. try to double-book A1
        System.out.println("\n-- Charlie tries to book A1 (already taken) --");
        try {
            service.bookSeats(show1, Arrays.asList("A1"), "Charlie");
        } catch (IllegalStateException e) {
            System.out.println("Blocked: " + e.getMessage());
        }

        // 4. Alice cancels her booking
        System.out.println("\n-- Alice cancels booking " + b1.getBookingId() + " --");
        service.cancelBooking(b1.getBookingId());
        System.out.println("Available after cancel: " + service.getAvailableSeats(show1));

        // 5. Charlie can now book A1
        System.out.println("\n-- Charlie books A1 after cancellation --");
        service.bookSeats(show1, Arrays.asList("A1"), "Charlie");

        // 6. Book for second show
        System.out.println("\n-- Show 2: " + show2 + " --");
        service.bookSeats(show2, Arrays.asList("D1", "D2"), "Diana");
    }

    private static List<Seat> buildScreen2Seats() {
        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat("D1", 1, 1, SeatCategory.REGULAR));
        seats.add(new Seat("D2", 1, 2, SeatCategory.REGULAR));
        seats.add(new Seat("E1", 2, 1, SeatCategory.PREMIUM));
        seats.add(new Seat("F1", 3, 1, SeatCategory.VIP));
        return seats;
    }
}
