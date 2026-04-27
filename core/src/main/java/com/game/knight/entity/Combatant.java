package com.game.knight.entity;

import com.badlogic.gdx.math.Rectangle;
import com.game.knight.model.CharacterData;
import com.game.knight.model.HairStyle;
import com.game.knight.model.PaletteColor;
import com.game.knight.model.PetType;
import com.game.knight.model.WeaponType;
import com.game.knight.role.RoleBehavior;
import com.game.knight.role.RoleFactory;

public abstract class Combatant {
    private static final float ATTACK_ANIMATION_DURATION = 0.18f;

    private final CharacterData data;
    private final RoleBehavior role;
    private final Rectangle bounds;
    private final int maxHealth;
    private int health;
    private float attackAnimationTime;
    private float attackCooldownTime;

    protected Combatant(CharacterData data, float x, float y) {
        this.data = data;
        this.role = RoleFactory.create(data.getCharacterClass());
        this.bounds = new Rectangle(x, y, 48f, 72f);
        this.maxHealth = role.getMaxHealth();
        this.health = maxHealth;
    }

    public void update(float delta) {
        if (attackAnimationTime > 0f) {
            attackAnimationTime = Math.max(0f, attackAnimationTime - delta);
        }
        if (attackCooldownTime > 0f) {
            attackCooldownTime = Math.max(0f, attackCooldownTime - delta);
        }
    }

    public void move(float deltaX, float deltaY) {
        bounds.x += deltaX;
        bounds.y += deltaY;
    }

    public boolean canAttack() {
        return attackCooldownTime <= 0f && isAlive();
    }

    protected int attack(Combatant target, float cooldownSeconds) {
        if (!canAttack()) {
            return 0;
        }

        startAttackAnimation();
        attackCooldownTime = cooldownSeconds;
        int damage = role.attack(getWeaponType());
        target.takeDamage(damage);
        return damage;
    }

    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void startAttackAnimation() {
        attackAnimationTime = ATTACK_ANIMATION_DURATION;
    }

    public float centerX() {
        return bounds.x + bounds.width * 0.5f;
    }

    public float centerY() {
        return bounds.y + bounds.height * 0.5f;
    }

    public CharacterData getData() {
        return data;
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

    public float getMoveSpeed() {
        return role.getMoveSpeed();
    }

    public String getName() {
        return data.getName();
    }

    public String getRoleName() {
        return role.getRoleName();
    }

    public String getSkillName() {
        return role.getSkillName();
    }

    public WeaponType getWeaponType() {
        return data.getWeaponType();
    }

    public float getAttackDistance() {
        return data.getWeaponType().getAttackDistance();
    }

    public PaletteColor getOutfitColor() {
        return data.getOutfitColor();
    }

    public PaletteColor getHairColor() {
        return data.getHairColor();
    }

    public HairStyle getHairStyle() {
        return data.getHairStyle();
    }

    public PetType getPetType() {
        return data.getPetType();
    }

    public boolean isAttacking() {
        return attackAnimationTime > 0f;
    }

    public float getAttackAnimationProgress() {
        if (attackAnimationTime <= 0f) {
            return 0f;
        }
        return 1f - attackAnimationTime / ATTACK_ANIMATION_DURATION;
    }
}
