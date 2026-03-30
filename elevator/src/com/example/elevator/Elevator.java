package com.example.elevator;

import java.util.TreeSet;

public class Elevator {
    private final String id;
    private int currentFloor;
    private ElevatorState state;
    private final TreeSet<Integer> destinations;

    public Elevator(String id, int startFloor) {
        this.id = id;
        this.currentFloor = startFloor;
        this.state = ElevatorState.IDLE;
        this.destinations = new TreeSet<>();
    }

    public String getId()           { return id; }
    public int getCurrentFloor()    { return currentFloor; }
    public ElevatorState getState() { return state; }
    public boolean isIdle()         { return state == ElevatorState.IDLE; }

    public void addDestination(int floor) {
        destinations.add(floor);
        // update state based on where the new destination is
        if (floor > currentFloor) {
            state = ElevatorState.MOVING_UP;
        } else if (floor < currentFloor) {
            state = ElevatorState.MOVING_DOWN;
        }
    }

    // moves one floor toward the next destination
    public void step() {
        if (destinations.isEmpty()) {
            state = ElevatorState.IDLE;
            return;
        }

        if (state == ElevatorState.MOVING_UP || currentFloor < destinations.first()) {
            currentFloor++;
        } else if (state == ElevatorState.MOVING_DOWN || currentFloor > destinations.last()) {
            currentFloor--;
        }

        // arrived at a destination
        if (destinations.contains(currentFloor)) {
            destinations.remove(currentFloor);
            System.out.println("  " + id + " arrived at floor " + currentFloor);
        }

        // update state for remaining destinations
        if (destinations.isEmpty()) {
            state = ElevatorState.IDLE;
        } else if (destinations.first() > currentFloor) {
            state = ElevatorState.MOVING_UP;
        } else {
            state = ElevatorState.MOVING_DOWN;
        }
    }

    @Override
    public String toString() {
        return id + " [floor=" + currentFloor + ", state=" + state
                + ", destinations=" + destinations + "]";
    }
}
