package com.game.knight.entity;

import com.game.knight.model.CharacterData;

public class Zombie extends Enemy {
    public Zombie(
        CharacterData data,
        float x,
        float y,
        float moveSpeedMultiplier,
        float reactionDistanceBonus,
        float attackCooldownSeconds,
        float swayDistance,
        float swaySpeed,
        float verticalAttackRange
    ) {
        super(
            data,
            x,
            y,
            moveSpeedMultiplier,
            reactionDistanceBonus,
            attackCooldownSeconds,
            swayDistance,
            swaySpeed,
            verticalAttackRange
        );
    }
}
