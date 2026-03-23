package com.example.parking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        List<ParkingSlot> slots = new ArrayList<>();

        // floor 1: small and medium slots
        slots.add(makeSlot("S-1", SlotType.SMALL, 1, 10, 50));
        slots.add(makeSlot("S-2", SlotType.SMALL, 1, 20, 40));
        slots.add(makeSlot("M-1", SlotType.MEDIUM, 1, 15, 45));
        slots.add(makeSlot("M-2", SlotType.MEDIUM, 1, 25, 35));

        // floor 2: medium and large slots
        slots.add(makeSlot("M-3", SlotType.MEDIUM, 2, 30, 20));
        slots.add(makeSlot("L-1", SlotType.LARGE, 2, 40, 10));
        slots.add(makeSlot("L-2", SlotType.LARGE, 2, 45, 15));

        ParkingLot lot = new ParkingLot(slots);

        System.out.println("=== Multilevel Parking Lot ===\n");
        System.out.println("Initial status: " + lot.status());

        // 1. park a car from gate A
        Vehicle car = new Vehicle("KA-01-1234", VehicleType.CAR);
        LocalDateTime carEntry = LocalDateTime.of(2025, 1, 15, 10, 0);
        ParkingTicket carTicket = lot.park(car, carEntry, SlotType.MEDIUM, "GATE-A");
        System.out.println("\nParked: " + carTicket);

        // 2. park a bike from gate B
        Vehicle bike = new Vehicle("KA-02-5678", VehicleType.TWO_WHEELER);
        LocalDateTime bikeEntry = LocalDateTime.of(2025, 1, 15, 10, 30);
        ParkingTicket bikeTicket = lot.park(bike, bikeEntry, SlotType.SMALL, "GATE-B");
        System.out.println("Parked: " + bikeTicket);

        // 3. park a 2-wheeler in medium slot (smaller vehicle in larger slot)
        Vehicle bike2 = new Vehicle("KA-03-9999", VehicleType.TWO_WHEELER);
        LocalDateTime bike2Entry = LocalDateTime.of(2025, 1, 15, 11, 0);
        ParkingTicket bike2Ticket = lot.park(bike2, bike2Entry, SlotType.MEDIUM, "GATE-A");
        System.out.println("Parked (bike in medium slot): " + bike2Ticket);

        // 4. park a bus from gate B
        Vehicle bus = new Vehicle("KA-04-0001", VehicleType.BUS);
        LocalDateTime busEntry = LocalDateTime.of(2025, 1, 15, 9, 0);
        ParkingTicket busTicket = lot.park(bus, busEntry, SlotType.LARGE, "GATE-B");
        System.out.println("Parked: " + busTicket);

        System.out.println("\nStatus after parking: " + lot.status());

        // 5. car exits after 3 hours
        LocalDateTime carExit = LocalDateTime.of(2025, 1, 15, 13, 0);
        int carBill = lot.exit(carTicket, carExit);
        System.out.println("\nCar exited. Bill: Rs " + carBill
                + " (3 hrs x Rs " + SlotType.MEDIUM.getHourlyRate() + "/hr)");

        // 6. bike in medium slot exits after 2 hours
        // billing should be at medium rate since it used a medium slot
        LocalDateTime bike2Exit = LocalDateTime.of(2025, 1, 15, 13, 0);
        int bike2Bill = lot.exit(bike2Ticket, bike2Exit);
        System.out.println("Bike (in medium slot) exited. Bill: Rs " + bike2Bill
                + " (2 hrs x Rs " + SlotType.MEDIUM.getHourlyRate() + "/hr)");

        // 7. bus exits after 5 hours
        LocalDateTime busExit = LocalDateTime.of(2025, 1, 15, 14, 0);
        int busBill = lot.exit(busTicket, busExit);
        System.out.println("Bus exited. Bill: Rs " + busBill
                + " (5 hrs x Rs " + SlotType.LARGE.getHourlyRate() + "/hr)");

        System.out.println("\nFinal status: " + lot.status());
    }

    private static ParkingSlot makeSlot(String id, SlotType type, int floor,
                                        int distA, int distB) {
        Map<String, Integer> distances = new HashMap<>();
        distances.put("GATE-A", distA);
        distances.put("GATE-B", distB);
        return new ParkingSlot(id, type, floor, distances);
    }
}
