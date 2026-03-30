package com.example.elevator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    private final List<Elevator> elevators;

    public ElevatorController(List<Elevator> elevators) {
        this.elevators = new ArrayList<>(elevators);
    }

    // assigns the request to the most suitable elevator
    public void dispatch(Request request) {
        Elevator best = null;
        int bestCost = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int cost = computeCost(elevator, request);
            if (cost < bestCost) {
                bestCost = cost;
                best = elevator;
            }
        }

        if (best != null) {
            best.addDestination(request.getFloor());
            System.out.println("Dispatched " + request + " -> " + best.getId());
        }
    }

    // lower cost = better choice for this request
    private int computeCost(Elevator elevator, Request request) {
        int requestFloor = request.getFloor();
        int currentFloor = elevator.getCurrentFloor();

        if (elevator.isIdle()) {
            // idle elevators: cost is just the distance
            return Math.abs(currentFloor - requestFloor);
        }

        // elevator already heading in the same direction toward the request
        if (elevator.getState() == ElevatorState.MOVING_UP
                && request.getDirection() == Direction.UP
                && requestFloor >= currentFloor) {
            return requestFloor - currentFloor;
        }

        if (elevator.getState() == ElevatorState.MOVING_DOWN
                && request.getDirection() == Direction.DOWN
                && requestFloor <= currentFloor) {
            return currentFloor - requestFloor;
        }

        // elevator moving away or in opposite direction - less preferred
        return 100 + Math.abs(currentFloor - requestFloor);
    }

    // advances all elevators one step
    public void step() {
        for (Elevator elevator : elevators) {
            elevator.step();
        }
    }

    public void printStatus() {
        for (Elevator elevator : elevators) {
            System.out.println("  " + elevator);
        }
    }
}
