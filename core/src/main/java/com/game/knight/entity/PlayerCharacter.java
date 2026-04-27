package com.game.knight.entity;

import com.badlogic.gdx.math.Rectangle;
import com.game.knight.model.CharacterData;
import com.game.knight.model.HairStyle;
import com.game.knight.model.PaletteColor;
import com.game.knight.model.PetType;
import com.game.knight.model.WeaponType;
import com.game.knight.role.RoleBehavior;
import com.game.knight.role.RoleFactory;

/** Represents the playable character during the arena scene. */
public class PlayerCharacter {
    private static final float ATTACK_ANIMATION_DURATION = 0.18f;

    private final CharacterData data;
    private final RoleBehavior role;
    private final Rectangle bounds;
    private int health;
    private float attackAnimationTime;

    public PlayerCharacter(CharacterData data, float x, float y) {
        this.data = data;
        this.role = RoleFactory.create(data.getCharacterClass());
        this.bounds = new Rectangle(x, y, 48f, 72f);
        this.health = role.getMaxHealth();
    }

    public void move(float deltaX, float deltaY) {
        bounds.x += deltaX;
        bounds.y += deltaY;
    }

    public int attack(TrainingDummy dummy) {
        startAttackAnimation();
        int damage = role.attack(this, dummy);
        dummy.takeDamage(damage);
        return damage;
    }

    public void startAttackAnimation() {
        attackAnimationTime = ATTACK_ANIMATION_DURATION;
    }

    public void update(float delta) {
        if (attackAnimationTime > 0f) {
            attackAnimationTime = Math.max(0f, attackAnimationTime - delta);
        }
    }

    public String getName() {
        return data.getName();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }

    public float getMoveSpeed() {
        return role.getMoveSpeed();
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

    public int getMaxHealth() {
        return role.getMaxHealth();
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

    public CharacterData getData() {
        return data;
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
