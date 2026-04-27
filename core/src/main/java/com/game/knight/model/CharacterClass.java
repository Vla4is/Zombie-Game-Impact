package com.game.knight.model;

public enum CharacterClass {
    KNIGHT("Knight", "Balanced fighter with a shield bash."),
    MAGE("Mage", "Ranged spellcaster with a powerful burst."),
    ARCHER("Archer", "Fast attacker with precise arrows.");

    private final String displayName;
    private final String description;

    CharacterClass(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}
