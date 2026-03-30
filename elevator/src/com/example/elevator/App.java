package com.example.elevator;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // building setup: 10 floors, 3 elevators starting at different floors
        List<Elevator> elevators = new ArrayList<>();
        elevators.add(new Elevator("E1", 1));
        elevators.add(new Elevator("E2", 5));
        elevators.add(new Elevator("E3", 10));

        ElevatorController controller = new ElevatorController(elevators);
        Building building = new Building(10, controller);

        System.out.println("=== Elevator System ===\n");
        System.out.println("Initial state:");
        building.printStatus();

        // submit requests from different floors
        System.out.println("\n-- Submitting requests --");
        building.requestElevator(3, Direction.UP);   // E1 is on floor 1, closest
        building.requestElevator(7, Direction.DOWN);  // E2 is on floor 5, E3 on 10 - E2 costs 102, E3 costs 3
        building.requestElevator(2, Direction.UP);    // E1 should get this (on its way up)

        System.out.println("\nAfter dispatch:");
        building.printStatus();

        // simulate step by step
        System.out.println("\n-- Running simulation --");
        for (int step = 1; step <= 8; step++) {
            System.out.println("Step " + step + ":");
            building.step();
            building.printStatus();
        }

        // new requests after elevators have served some floors
        System.out.println("\n-- New requests mid-simulation --");
        building.requestElevator(9, Direction.UP);
        building.requestElevator(1, Direction.UP);

        System.out.println("\nState after new dispatch:");
        building.printStatus();

        System.out.println("\n-- Continuing simulation --");
        for (int step = 1; step <= 6; step++) {
            System.out.println("Step " + step + ":");
            building.step();
            building.printStatus();
        }
    }
}
