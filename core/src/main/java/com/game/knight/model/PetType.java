package com.game.knight.model;

public enum PetType {
    SPARK("Spark"),
    MOCHI("Mochi"),
    NOVA("Nova"),
    PEBBLE("Pebble"),
    PIXEL("Pixel");

    private final String displayName;

    PetType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
