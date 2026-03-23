package com.example.pen;

public class MarkerPen implements Pen {
    private final Refill refill;
    private boolean open;

    public MarkerPen(Refill refill) {
        this.refill = refill;
        this.open = false;
    }

    @Override
    public void start() {
        if (open) {
            System.out.println("[Marker] Already uncapped.");
            return;
        }
        open = true;
        System.out.println("[Marker] Cap removed. Ready to write with " + refill.getColor() + " ink.");
    }

    @Override
    public void write(String text) {
        if (!open) {
            System.out.println("[Marker] Pen is capped. Call start() first.");
            return;
        }
        if (refill.isEmpty()) {
            System.out.println("[Marker] Ink dried out. Cannot write.");
            return;
        }

        // markers use the most ink per character (thick tip)
        int inkNeeded = text.length() * 3;
        int consumed = refill.consumeInk(inkNeeded);
        int charsWritten = consumed / 3;

        String written = text.substring(0, Math.min(charsWritten, text.length()));
        System.out.println("[Marker] Wrote: \"" + written + "\" | " + refill);

        if (charsWritten < text.length()) {
            System.out.println("[Marker] Ran out of ink mid-write!");
        }
    }

    @Override
    public void close() {
        if (!open) {
            System.out.println("[Marker] Already capped.");
            return;
        }
        open = false;
        System.out.println("[Marker] Cap on.");
    }

    // markers are disposable and cannot be refilled
    @Override
    public void refill(Refill newRefill) {
        throw new UnsupportedOperationException("Marker pens are disposable and cannot be refilled.");
    }

    @Override
    public boolean isOpen() { return open; }
}
