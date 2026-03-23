package com.example.pen;

public class Refill {
    private final InkColor color;
    private int inkLevel;
    private final int maxInk;

    public Refill(InkColor color, int maxInk) {
        this.color = color;
        this.inkLevel = maxInk;
        this.maxInk = maxInk;
    }

    public InkColor getColor() { return color; }
    public int getInkLevel()   { return inkLevel; }
    public int getMaxInk()     { return maxInk; }

    public boolean isEmpty() {
        return inkLevel <= 0;
    }

    // uses up ink, returns how many units were actually consumed
    public int consumeInk(int amount) {
        int consumed = Math.min(amount, inkLevel);
        inkLevel -= consumed;
        return consumed;
    }

    // refills ink back to max
    public void refillInk() {
        this.inkLevel = maxInk;
    }

    @Override
    public String toString() {
        return color + " ink [" + inkLevel + "/" + maxInk + "]";
    }
}
