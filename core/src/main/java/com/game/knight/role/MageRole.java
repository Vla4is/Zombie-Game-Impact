package com.game.knight.role;

import com.game.knight.entity.PlayerCharacter;
import com.game.knight.entity.TrainingDummy;

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
    public int attack(PlayerCharacter player, TrainingDummy dummy) {
        return player.getWeaponType().getBaseDamage() + 14;
    }
}
