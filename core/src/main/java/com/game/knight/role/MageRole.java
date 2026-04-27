package com.game.knight.role;

import com.game.knight.model.WeaponType;

public class MageRole implements RoleBehavior {
    @Override
    public String getRoleName() {
        return "Mage";
    }

    @Override
    public String getSkillName() {
        return "Arcane Burst";
    }

    @Override
    public int getMaxHealth() {
        return 90;
    }

    @Override
    public float getMoveSpeed() {
        return 190f;
    }

    @Override
    public int attack(WeaponType weaponType) {
        return weaponType.getBaseDamage() + 14;
    }
}
