package com.game.knight.role;

import com.game.knight.model.WeaponType;

public class KnightRole implements RoleBehavior {
    @Override
    public String getRoleName() {
        return "Knight";
    }

    @Override
    public String getSkillName() {
        return "Shield Bash";
    }

    @Override
    public int getMaxHealth() {
        return 140;
    }

    @Override
    public float getMoveSpeed() {
        return 170f;
    }

    @Override
    public int attack(WeaponType weaponType) {
        return weaponType.getBaseDamage() + 8;
    }
}
