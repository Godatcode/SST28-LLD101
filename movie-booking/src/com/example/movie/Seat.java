package com.example.movie;

public class Seat {
    private final String seatId;
    private final int row;
    private final int number;
    private final SeatCategory category;
    private boolean booked;

    public Seat(String seatId, int row, int number, SeatCategory category) {
        this.seatId = seatId;
        this.row = row;
        this.number = number;
        this.category = category;
        this.booked = false;
    }

    public String getSeatId()         { return seatId; }
    public int getRow()               { return row; }
    public int getNumber()            { return number; }
    public SeatCategory getCategory() { return category; }
    public boolean isBooked()         { return booked; }

    public void book()    { this.booked = true; }
    public void release() { this.booked = false; }

    @Override
    public String toString() {
        return seatId + "(" + category + ", Rs " + category.getPrice()
                + ", " + (booked ? "booked" : "free") + ")";
    }
}
