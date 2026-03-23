package com.example.parking;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private final List<ParkingSlot> slots;
    private int ticketCounter = 0;

    public ParkingLot(List<ParkingSlot> slots) {
        this.slots = new ArrayList<>(slots);
    }

    // finds nearest compatible slot from the given gate, parks the vehicle, returns ticket
    public ParkingTicket park(Vehicle vehicle, LocalDateTime entryTime,
                              SlotType requestedSlotType, String entryGateId) {
        ParkingSlot best = null;
        int bestDistance = Integer.MAX_VALUE;

        for (ParkingSlot slot : slots) {
            if (slot.isOccupied()) continue;
            if (slot.getType() != requestedSlotType) continue;
            if (!vehicle.getType().fitsIn(slot.getType())) continue;

            int dist = slot.getDistanceFrom(entryGateId);
            if (dist < bestDistance) {
                bestDistance = dist;
                best = slot;
            }
        }

        if (best == null) {
            throw new IllegalStateException("No available " + requestedSlotType
                    + " slot for " + vehicle);
        }

        best.occupy();
        ticketCounter++;
        String ticketId = "T-" + ticketCounter;
        return new ParkingTicket(ticketId, vehicle, best, entryTime);
    }

    // calculates bill based on slot type rate and parking duration
    public int exit(ParkingTicket ticket, LocalDateTime exitTime) {
        long hours = Duration.between(ticket.getEntryTime(), exitTime).toHours();
        if (hours < 1) hours = 1;

        int rate = ticket.getSlot().getType().getHourlyRate();
        int amount = (int) (hours * rate);

        ticket.getSlot().release();
        return amount;
    }

    // returns count of free slots for each slot type
    public Map<SlotType, Integer> status() {
        Map<SlotType, Integer> availability = new HashMap<>();
        for (SlotType type : SlotType.values()) {
            availability.put(type, 0);
        }
        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied()) {
                availability.put(slot.getType(), availability.get(slot.getType()) + 1);
            }
        }
        return availability;
    }
}
