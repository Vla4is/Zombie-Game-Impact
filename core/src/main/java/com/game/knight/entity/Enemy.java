package com.game.knight.entity;

import com.game.knight.model.CharacterData;

public abstract class Enemy extends Combatant {
    protected Enemy(CharacterData data, float x, float y) {
        super(data, x, y);
    }

    public abstract int updateAgainst(PlayerCharacter player, float delta);
}
