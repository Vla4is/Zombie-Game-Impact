package com.game.knight.entity;

import com.game.knight.model.CharacterData;

public abstract class Enemy extends Combatant {
    private static final float REACTION_DISTANCE_BONUS = 20f;
    private static final float ATTACK_COOLDOWN = 0.9f;
    private static final float SWAY_DISTANCE = 24f;
    private static final float SWAY_SPEED = 2.4f;
    private static final float Y_ALIGNMENT_LIMIT = 60f;

    private float movementTime;

    protected Enemy(CharacterData data, float x, float y) {
        super(data, x, y);
    }

    public int updateAgainst(PlayerCharacter player, float delta) {
        update(delta);
        movementTime += delta;

        if (!isAlive()) {
            return 0;
        }

        moveToward(player, delta);
        if (isInAttackRange(player)) {
            return attack(player, ATTACK_COOLDOWN);
        }
        return 0;
    }

    protected void moveToward(PlayerCharacter player, float delta) {
        float targetX = player.centerX();
        float targetY = player.centerY() + (float) Math.sin(movementTime * SWAY_SPEED) * SWAY_DISTANCE;
        float deltaX = targetX - centerX();
        float deltaY = targetY - centerY();
        float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if (distance == 0f) {
            return;
        }

        float speed = getMoveSpeed() * 0.72f;
        move(deltaX / distance * speed * delta, deltaY / distance * speed * delta);
    }

    protected boolean isInAttackRange(PlayerCharacter player) {
        float horizontalDistance = Math.abs(player.centerX() - centerX());
        float verticalDistance = Math.abs(player.centerY() - centerY());
        return horizontalDistance <= getAttackDistance() - REACTION_DISTANCE_BONUS
            && verticalDistance <= Y_ALIGNMENT_LIMIT;
    }
}
