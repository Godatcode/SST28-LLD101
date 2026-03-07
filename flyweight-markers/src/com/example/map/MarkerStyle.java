package com.example.map;

/**
 * Intrinsic state for a map marker — shared across many markers.
 * Immutable so it is safe to reuse the same instance for every marker
 * that has the same shape/color/size/filled combination.
 */
public final class MarkerStyle {

    private final String shape;   // PIN, CIRCLE, SQUARE
    private final String color;   // RED, BLUE, GREEN, ORANGE
    private final int size;
    private final boolean filled;

    public MarkerStyle(String shape, String color, int size, boolean filled) {
        this.shape = shape;
        this.color = color;
        this.size = size;
        this.filled = filled;
    }

    public String getShape() { return shape; }
    public String getColor() { return color; }
    public int getSize()     { return size; }
    public boolean isFilled() { return filled; }

    @Override
    public String toString() {
        return shape + "|" + color + "|" + size + "|" + (filled ? "F" : "O");
    }
}
