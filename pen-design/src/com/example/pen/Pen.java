package com.example.pen;

public interface Pen {
    void start();
    void write(String text);
    void close();
    void refill(Refill newRefill);
    boolean isOpen();
}
