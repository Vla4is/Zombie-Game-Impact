package com.game.knight.role;

import com.game.knight.model.WeaponType;

public class ArcherRole implements RoleBehavior {
    @Override
    public String getRoleName() {
        return "Archer";
    }

    @Override
    public String getSkillName() {
        return "Quick Shot";
    }

    @Override
    public int getMaxHealth() {
        return 105;
    }

    @Override
    public float getMoveSpeed() {
        return 220f;
    }

    @Override
    public int attack(WeaponType weaponType) {
        return weaponType.getBaseDamage() + 10;
    }
}
