package com.game.knight.model;

import com.badlogic.gdx.graphics.Color;

public enum PaletteColor {
    SKY("Sky", new Color(0.45f, 0.72f, 0.98f, 1f)),
    CORAL("Coral", new Color(0.97f, 0.49f, 0.43f, 1f)),
    LIME("Lime", new Color(0.60f, 0.85f, 0.35f, 1f)),
    GOLD("Gold", new Color(0.95f, 0.77f, 0.25f, 1f)),
    VIOLET("Violet", new Color(0.69f, 0.52f, 0.94f, 1f)),
    BROWN("Brown", new Color(0.47f, 0.30f, 0.19f, 1f)),
    CHARCOAL("Charcoal", new Color(0.20f, 0.22f, 0.28f, 1f)),
    CREAM("Cream", new Color(0.96f, 0.91f, 0.76f, 1f));

    private final String displayName;
    private final Color color;

    PaletteColor(String displayName, Color color) {
        this.displayName = displayName;
        this.color = color;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Color toColor() {
        return new Color(color);
    }
}
