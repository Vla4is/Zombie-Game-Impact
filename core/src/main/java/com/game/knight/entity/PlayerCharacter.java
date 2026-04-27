package com.game.knight.entity;

import com.game.knight.model.CharacterData;

/** Represents the playable character during the arena scene. */
public class PlayerCharacter extends Combatant {
    private static final float ATTACK_COOLDOWN = 0.32f;

    public PlayerCharacter(CharacterData data, float x, float y) {
        super(data, x, y);
    }

    public int attack(Enemy enemy) {
        return attack(enemy, ATTACK_COOLDOWN);
    }
}
