package com.example.pen;

public class BallPointPen implements Pen {
    private Refill refill;
    private boolean open;

    public BallPointPen(Refill refill) {
        this.refill = refill;
        this.open = false;
    }

    @Override
    public void start() {
        if (open) {
            System.out.println("[BallPoint] Already clicked open.");
            return;
        }
        open = true;
        System.out.println("[BallPoint] Clicked open. Ready to write with " + refill.getColor() + " ink.");
    }

    @Override
    public void write(String text) {
        if (!open) {
            System.out.println("[BallPoint] Pen is closed. Call start() first.");
            return;
        }
        if (refill.isEmpty()) {
            System.out.println("[BallPoint] Ink is empty. Cannot write.");
            return;
        }

        int inkNeeded = text.length();
        int consumed = refill.consumeInk(inkNeeded);

        // write as many characters as ink allows
        String written = text.substring(0, consumed);
        System.out.println("[BallPoint] Wrote: \"" + written + "\" | " + refill);

        if (consumed < inkNeeded) {
            System.out.println("[BallPoint] Ran out of ink mid-write!");
        }
    }

    @Override
    public void close() {
        if (!open) {
            System.out.println("[BallPoint] Already closed.");
            return;
        }
        open = false;
        System.out.println("[BallPoint] Clicked closed.");
    }

    // replaces the old cartridge with a new one
    @Override
    public void refill(Refill newRefill) {
        this.refill = newRefill;
        System.out.println("[BallPoint] Cartridge replaced. New refill: " + newRefill);
    }

    @Override
    public boolean isOpen() { return open; }
}
