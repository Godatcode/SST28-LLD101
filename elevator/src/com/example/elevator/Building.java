package com.example.elevator;

public class Building {
    private final int totalFloors;
    private final ElevatorController controller;

    public Building(int totalFloors, ElevatorController controller) {
        this.totalFloors = totalFloors;
        this.controller = controller;
    }

    public int getTotalFloors() { return totalFloors; }

    // someone on a floor presses UP or DOWN
    public void requestElevator(int floor, Direction direction) {
        if (floor < 1 || floor > totalFloors) {
            throw new IllegalArgumentException("Floor " + floor + " is out of range.");
        }
        controller.dispatch(new Request(floor, direction));
    }

    // advance simulation by one step
    public void step() {
        controller.step();
    }

    public void printStatus() {
        controller.printStatus();
    }
}
