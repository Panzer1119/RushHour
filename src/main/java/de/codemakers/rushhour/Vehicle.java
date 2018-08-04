package de.codemakers.rushhour;

import java.awt.*;

public enum Vehicle {
    EMPTY((byte) 0x0, true, Color.WHITE),
    CAR_RED((byte) 0x2, true, Color.RED),
    CAR_GREEN((byte) 0x4, true, Color.GREEN),
    CAR_BLACK((byte) 0x6, true, Color.BLACK),
    CAR_GRAY((byte) 0x8, true, Color.GRAY),
    CAR_BLUE((byte) 0xA, true, new Color(100, 156, 228)),
    CAR_ORANGE((byte) 0xC, true, Color.ORANGE),
    CAR_YELLOW((byte) 0xE, true, Color.YELLOW),
    CAR_PINK((byte) 0x10, true, Color.PINK),
    CAR_BEIGE((byte) 0x12, true, new Color(216, 192, 156)),
    CAR_BROWN((byte) 0x14, true, new Color(156, 100, 92)),
    CAR_LIGHT_GREEN((byte) 0x16, true, new Color(156, 192, 156)),
    CAR_BAD_GREEN((byte) 0x18, true, new Color(75, 75, 42)),
    TRUCK_YELLOW((byte) 0x1A, false, Color.YELLOW),
    TRUCK_BLUE((byte) 0x1C, false, Color.BLUE),
    TRUCK_TURQUOISE((byte) 0x1E, false, new Color(80, 200, 200)),
    TRUCK_PURPLE((byte) 0x20, false, new Color(200, 160, 200));
    
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
    
    @Override
    public final String toString() {
        return "Vehicle{" + "id=" + id + ", car=" + car + ", color=" + color + '}';
    }
    
}
