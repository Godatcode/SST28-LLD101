package com.example.parking;

public enum VehicleType {
    TWO_WHEELER,
    CAR,
    BUS;

    // checks if this vehicle type can fit in the given slot type
    public boolean fitsIn(SlotType slotType) {
        if (this == TWO_WHEELER) return true;
        if (this == CAR) return slotType != SlotType.SMALL;
        return slotType == SlotType.LARGE;
    }
}
