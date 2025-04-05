package net.danygames2014.unitweaks.util;

public enum ChestSoundsEnum {

    NONE("None"),
    MODERN("Modern"),
    DOOR("Door"),
    BLOCK("Block"),
    BUTTON("Button");

    final String stringValue;

    ChestSoundsEnum() {
        this.stringValue = "None";
    }

    ChestSoundsEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}