package com.game.knight.model;

public enum CharacterClass {
    KNIGHT("Knight", "Balanced fighter with a shield bash.", "Shield Bash", 180, 90f, 0.32f, 8),
    MAGE("Mage", "Ranged spellcaster with a powerful burst.", "Arcane Burst", 90, 110f, 0.2f, 14),
    ARCHER("Archer", "Fast attacker with precise arrows.", "Quick Shot", 105, 280f, 0.45f, 10),
    ZOMBIE("Zombie", "Relentless undead attacker.", "Infect", 140, 110f, 0.9f, 8);

    private final String displayName;
    private final String description;
    private final String skillName;
    private final int maxHealth;
    private final float moveSpeed;
    private final float attackCooldownSeconds;
    private final int attackDamageBonus;

    CharacterClass(
        String displayName,
        String description,
        String skillName,
        int maxHealth,
        float moveSpeed,
        float attackCooldownSeconds,
        int attackDamageBonus
    ) {
        this.displayName = displayName;
        this.description = description;
        this.skillName = skillName;
        this.maxHealth = maxHealth;
        this.moveSpeed = moveSpeed;
        this.attackCooldownSeconds = attackCooldownSeconds;
        this.attackDamageBonus = attackDamageBonus;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public String getSkillName() {
        return skillName;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public float getAttackCooldownSeconds() {
        return attackCooldownSeconds;
    }

    public int attack(WeaponType weaponType) {
        return weaponType.getBaseDamage() + attackDamageBonus;
    }
}
