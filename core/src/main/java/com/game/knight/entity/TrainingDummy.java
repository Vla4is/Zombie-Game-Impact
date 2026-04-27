package com.game.knight.entity;

import com.badlogic.gdx.math.Rectangle;

public class TrainingDummy {
    private final Rectangle bounds;
    private final int maxHealth;
    private int health;

    public TrainingDummy(float x, float y, float width, float height, int maxHealth) {
        this.bounds = new Rectangle(x, y, width, height);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = maxHealth;
        }
    }
}
