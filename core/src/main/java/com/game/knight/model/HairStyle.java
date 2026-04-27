package com.game.knight.model;

public enum HairStyle {
    SHORT("Short"),
    SPIKY("Spiky"),
    PONYTAIL("Ponytail");

    private final String displayName;

    HairStyle(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
