package com.game.knight.entity;

import com.game.knight.model.CharacterData;

/** Represents the playable character during the arena scene. */
public class PlayerCharacter extends Combatant {
    public PlayerCharacter(CharacterData data, float x, float y) {
        super(data, x, y);
    }

    public int attack(Enemy enemy) {
        return super.attack(enemy);
    }
}
