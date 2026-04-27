package com.game.knight.entity;

import com.game.knight.model.CharacterData;

/** Represents the playable character during the arena scene. */
public class PlayerCharacter extends Combatant {
    private final float attackCooldownSeconds;

    public PlayerCharacter(CharacterData data, float x, float y, float moveSpeedMultiplier, float attackCooldownSeconds) {
        super(data, x, y, moveSpeedMultiplier);
        this.attackCooldownSeconds = attackCooldownSeconds;
    }

    public int attack(Enemy enemy) {
        return attack(enemy, attackCooldownSeconds);
    }
}
