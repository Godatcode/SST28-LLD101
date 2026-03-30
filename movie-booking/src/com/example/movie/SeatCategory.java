package com.example.movie;

public enum SeatCategory {
    REGULAR(150),
    PREMIUM(250),
    VIP(400);

    private final int price;

    SeatCategory(int price) {
        this.price = price;
    }

    public int getPrice() { return price; }
}
