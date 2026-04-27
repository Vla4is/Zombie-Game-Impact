package com.game.knight.role;

import com.game.knight.model.WeaponType;

public interface RoleBehavior {
    String getRoleName();

    String getSkillName();

    int getMaxHealth();

    float getMoveSpeed();

    int attack(WeaponType weaponType);
}
