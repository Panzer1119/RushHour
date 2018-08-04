package de.codemakers.rushhour;

import java.awt.*;

public enum Vehicle {
    EMPTY((byte) 0x0, true, Color.WHITE),
    CAR_RED((byte) 0x2, true, Color.WHITE),
    CAR_GREEN((byte) 0x4, true, Color.WHITE),
    CAR_BLACK((byte) 0x6, true, Color.WHITE),
    CAR_GRAY((byte) 0x8, true, Color.WHITE),
    CAR_BLUE((byte) 0xA, true, Color.WHITE),
    CAR_ORANGE((byte) 0xC, true, Color.WHITE),
    CAR_YELLOW((byte) 0xE, true, Color.WHITE),
    CAR_PINK((byte) 0x10, true, Color.WHITE),
    CAR_BEIGE((byte) 0x12, true, Color.WHITE),
    CAR_BROWN((byte) 0x14, true, Color.WHITE),
    CAR_LIGHT_GREEN((byte) 0x16, true, Color.WHITE),
    CAR_BAD_GREEN((byte) 0x18, true, Color.WHITE),
    TRUCK_YELLOW((byte) 0x1A, false, Color.WHITE),
    TRUCK_BLUE((byte) 0x1C, false, Color.WHITE),
    TRUCK_TURQUOISE((byte) 0x1E, false, Color.WHITE),
    TRUCK_PURPLE((byte) 0x20, false, Color.WHITE);
    
    private final byte id;
    private final boolean car;
    private final Color color;
    
    Vehicle(byte id, boolean car, Color color) {
        this.id = id;
        this.car = car;
        this.color = color;
    }
    
    public static final Vehicle ofId(byte id) {
        for (Vehicle vehicle : values()) {
            if (vehicle.id == id) {
                return vehicle;
            }
        }
        return null;
    }
    
    public final byte getId() {
        return id;
    }
    
    public final boolean isCar() {
        return car;
    }
    
    public final Color getColor() {
        return color;
    }
    
}
