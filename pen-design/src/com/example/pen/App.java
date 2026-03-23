package com.example.pen;

public class App {
    public static void main(String[] args) {

        // --- BallPoint Pen Demo ---
        System.out.println("=== BallPoint Pen ===");
        Refill blueRefill = new Refill(InkColor.BLUE, 20);
        Pen ballPoint = new BallPointPen(blueRefill);

        ballPoint.write("test");           // should warn: pen is closed
        ballPoint.start();
        ballPoint.write("Hello World");    // uses 11 ink
        ballPoint.write("More text");      // uses 9 ink, should deplete
        ballPoint.write("nothing");        // should warn: ink empty

        // refill with a new cartridge
        ballPoint.refill(new Refill(InkColor.BLACK, 20));
        ballPoint.write("Back in action");
        ballPoint.close();

        // --- Fountain Pen Demo ---
        System.out.println("\n=== Fountain Pen ===");
        Refill blackRefill = new Refill(InkColor.BLACK, 30);
        Pen fountain = new FountainPen(blackRefill);

        fountain.start();
        fountain.write("Elegant");         // 7 chars x 2 = 14 ink
        fountain.write("Writing");         // 7 chars x 2 = 14 ink
        fountain.write("More");            // 4 chars x 2 = 8 ink, only 2 left

        // refill from ink bottle (replenishes existing reservoir)
        fountain.refill(null);
        fountain.write("Refilled");
        fountain.close();

        // --- Marker Pen Demo ---
        System.out.println("\n=== Marker Pen ===");
        Refill redRefill = new Refill(InkColor.RED, 30);
        Pen marker = new MarkerPen(redRefill);

        marker.start();
        marker.write("BOLD");             // 4 chars x 3 = 12 ink
        marker.write("HEADING");          // 7 chars x 3 = 21 ink, only 18 left

        // try to refill a marker (should throw exception)
        try {
            marker.refill(new Refill(InkColor.RED, 30));
        } catch (UnsupportedOperationException e) {
            System.out.println("[Marker] Refill failed: " + e.getMessage());
        }

        marker.close();
    }
}
