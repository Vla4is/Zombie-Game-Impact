package com.game.knight.entity;

import com.game.knight.model.CharacterData;

public class Zombie extends Enemy {
    private static final float REACTION_DISTANCE_BONUS = 20f;
    private static final float SWAY_DISTANCE = 24f;
    private static final float SWAY_SPEED = 2.4f;
    private static final float VERTICAL_ATTACK_RANGE = 60f;

    private float movementTime;

    public Zombie(CharacterData data, float x, float y) {
        super(data, x, y);
    }

    @Override
    public int updateAgainst(PlayerCharacter player, float delta) {
        update(delta);
        movementTime += delta;

        if (!isAlive()) {
            return 0;
        }

        moveToward(player, delta);
        if (isInAttackRange(player)) {
            return attack(player);
        }
        return 0;
    }

    private void moveToward(PlayerCharacter player, float delta) {
        float targetX = player.centerX();
        float targetY = player.centerY() + (float) Math.sin(movementTime * SWAY_SPEED) * SWAY_DISTANCE;
        float deltaX = targetX - centerX();
        float deltaY = targetY - centerY();
        float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if (distance == 0f) {
            return;
        }

        move(deltaX / distance * getMoveSpeed() * delta, deltaY / distance * getMoveSpeed() * delta);
    }

    private boolean isInAttackRange(PlayerCharacter player) {
        float horizontalDistance = Math.abs(player.centerX() - centerX());
        float verticalDistance = Math.abs(player.centerY() - centerY());
        return horizontalDistance <= getAttackDistance() - REACTION_DISTANCE_BONUS
            && verticalDistance <= VERTICAL_ATTACK_RANGE;
    }
}
