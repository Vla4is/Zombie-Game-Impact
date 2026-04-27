package com.game.knight.entity;

import com.game.knight.model.CharacterData;

public abstract class Enemy extends Combatant {
    private final float reactionDistanceBonus;
    private final float attackCooldownSeconds;
    private final float swayDistance;
    private final float swaySpeed;
    private final float verticalAttackRange;
    private float movementTime;

    protected Enemy(
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
        super(data, x, y, moveSpeedMultiplier);
        this.reactionDistanceBonus = reactionDistanceBonus;
        this.attackCooldownSeconds = attackCooldownSeconds;
        this.swayDistance = swayDistance;
        this.swaySpeed = swaySpeed;
        this.verticalAttackRange = verticalAttackRange;
    }

    public int updateAgainst(PlayerCharacter player, float delta) {
        update(delta);
        movementTime += delta;

        if (!isAlive()) {
            return 0;
        }

        moveToward(player, delta);
        if (isInAttackRange(player)) {
            return attack(player, attackCooldownSeconds);
        }
        return 0;
    }

    protected void moveToward(PlayerCharacter player, float delta) {
        float targetX = player.centerX();
        float targetY = player.centerY() + (float) Math.sin(movementTime * swaySpeed) * swayDistance;
        float deltaX = targetX - centerX();
        float deltaY = targetY - centerY();
        float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if (distance == 0f) {
            return;
        }

        move(deltaX / distance * getMoveSpeed() * delta, deltaY / distance * getMoveSpeed() * delta);
    }

    protected boolean isInAttackRange(PlayerCharacter player) {
        float horizontalDistance = Math.abs(player.centerX() - centerX());
        float verticalDistance = Math.abs(player.centerY() - centerY());
        return horizontalDistance <= getAttackDistance() - reactionDistanceBonus
            && verticalDistance <= verticalAttackRange;
    }
}
