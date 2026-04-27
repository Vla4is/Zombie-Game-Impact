package com.game.knight.role;

import com.game.knight.entity.PlayerCharacter;
import com.game.knight.entity.TrainingDummy;

public interface RoleBehavior {
    String getRoleName();

    String getSkillName();

    int getMaxHealth();

    float getMoveSpeed();

    int attack(PlayerCharacter player, TrainingDummy dummy);
}
