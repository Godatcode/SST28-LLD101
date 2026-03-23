package com.example.pen;

public class FountainPen implements Pen {
    private Refill refill;
    private boolean open;

    public FountainPen(Refill refill) {
        this.refill = refill;
        this.open = false;
    }

    @Override
    public void start() {
        if (open) {
            System.out.println("[Fountain] Already uncapped.");
            return;
        }
        open = true;
        System.out.println("[Fountain] Cap removed. Ready to write with " + refill.getColor() + " ink.");
    }

    @Override
    public void write(String text) {
        if (!open) {
            System.out.println("[Fountain] Pen is capped. Call start() first.");
            return;
        }
        if (refill.isEmpty()) {
            System.out.println("[Fountain] Ink reservoir is empty. Cannot write.");
            return;
        }

        // fountain pens use more ink per character (broader nib)
        int inkNeeded = text.length() * 2;
        int consumed = refill.consumeInk(inkNeeded);
        int charsWritten = consumed / 2;

        String written = text.substring(0, Math.min(charsWritten, text.length()));
        System.out.println("[Fountain] Wrote: \"" + written + "\" | " + refill);

        if (charsWritten < text.length()) {
            System.out.println("[Fountain] Ran out of ink mid-write!");
        }
    }

    @Override
    public void close() {
        if (!open) {
            System.out.println("[Fountain] Already capped.");
            return;
        }
        open = false;
        System.out.println("[Fountain] Cap on.");
    }

    // replenishes the existing ink reservoir (like filling from an ink bottle)
    @Override
    public void refill(Refill newRefill) {
        refill.refillInk();
        System.out.println("[Fountain] Ink reservoir refilled from ink bottle. " + refill);
    }

    @Override
    public boolean isOpen() { return open; }
}
