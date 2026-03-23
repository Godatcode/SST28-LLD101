package com.example.parking;

import java.util.HashMap;
import java.util.Map;

public class ParkingSlot {
    private final String slotId;
    private final SlotType type;
    private final int floor;
    private final Map<String, Integer> distanceFromGate;
    private boolean occupied;

    public ParkingSlot(String slotId, SlotType type, int floor,
                       Map<String, Integer> distanceFromGate) {
        this.slotId = slotId;
        this.type = type;
        this.floor = floor;
        this.distanceFromGate = new HashMap<>(distanceFromGate);
        this.occupied = false;
    }

    public String getSlotId()   { return slotId; }
    public SlotType getType()   { return type; }
    public int getFloor()       { return floor; }
    public boolean isOccupied() { return occupied; }

    public int getDistanceFrom(String gateId) {
        return distanceFromGate.getOrDefault(gateId, Integer.MAX_VALUE);
    }

    public void occupy()  { this.occupied = true; }
    public void release() { this.occupied = false; }

    @Override
    public String toString() {
        return slotId + " (" + type + ", floor=" + floor + ", occupied=" + occupied + ")";
    }
}
