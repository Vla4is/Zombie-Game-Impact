package com.game.knight.model;

public enum WeaponType {
    SWORD("Sword", 12, 78f),
    STAFF("Staff", 10, 112f),
    BOW("Bow", 9, 180f);

    private final String displayName;
    private final int baseDamage;
    private final float attackDistance;

    WeaponType(String displayName, int baseDamage, float attackDistance) {
        this.displayName = displayName;
        this.baseDamage = baseDamage;
        this.attackDistance = attackDistance;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public float getAttackDistance() {
        return attackDistance;
    }
}
