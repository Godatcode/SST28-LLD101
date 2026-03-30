# Movie Ticket Booking System

## Class Diagram

```
+---------------------+
|      <<enum>>       |
|    SeatCategory     |
|---------------------|
| REGULAR (Rs 150)    |
| PREMIUM (Rs 250)    |
| VIP     (Rs 400)    |
|---------------------|
| +getPrice(): int    |
+---------------------+
          |
          | has-a
          v
+---------------------+        +---------------------+
|        Seat         |        |       Movie         |
|---------------------|        |---------------------|
| -seatId: String     |        | -title: String      |
| -row: int           |        | -durationMins: int  |
| -number: int        |        +---------------------+
| -category           |                  |
| -booked: boolean    |                  |
|---------------------|                  |
| +book()             |                  |
| +release()          |                  |
| +isBooked()         |                  |
+---------------------+                  |
          |                              |
          +-------------+   +-----------+
                        v   v
                 +---------------------+
                 |        Show         |
                 |---------------------|
                 | -showId: String     |
                 | -movie: Movie       |
                 | -screenName: String |
                 | -startTime          |
                 | -seats: List<Seat>  |
                 |---------------------|
                 | +findSeat(id): Seat |
                 +---------------------+
                         |
                         | used by
                         v
+------------------------------------------------+
|               BookingService                   |
|------------------------------------------------|
| -bookings: Map<String, Booking>                |
| -bookingCounter: int                           |
|------------------------------------------------|
| +bookSeats(show, seatIds, userName): Booking   |
| +cancelBooking(bookingId): void                |
| +getAvailableSeats(show): List<Seat>           |
+------------------------------------------------+
                         |
                         | creates
                         v
+------------------------------------------------+
|                  Booking                       |
|------------------------------------------------|
| -bookingId: String                             |
| -userName: String                              |
| -showId: String                                |
| -seats: List<Seat>                             |
| -totalAmount: int                              |
+------------------------------------------------+
```

## Design Approach

### Entities
A `Movie` is just data (title, duration). A `Show` represents one screening of a movie in a specific screen at a specific time — it owns a list of `Seat` objects. Seats are mutable (they get booked and released). `Booking` is an immutable record of a confirmed reservation.

### Booking Flow
`BookingService.bookSeats()` does a two-pass approach:
1. First checks all requested seats exist and are free (fails atomically if any seat is taken)
2. Then marks them all booked and calculates the total based on seat category

This prevents partial bookings where some seats get booked but others fail.

### Cancellation
`cancelBooking()` releases all seats from the booking back to available, allowing others to book them. The booking record is also removed.

### Seat Pricing
Each seat has a `SeatCategory` (REGULAR/PREMIUM/VIP) with a fixed price. The total for a booking is the sum of each booked seat's category price.

## How to Build and Run

```bash
cd movie-booking
javac -d out src/com/example/movie/*.java
java -cp out com.example.movie.App
```
