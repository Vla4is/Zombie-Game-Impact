package com.game.knight.role;

import com.game.knight.model.CharacterClass;

public final class RoleFactory {
    private RoleFactory() {
    }

    public static RoleBehavior create(CharacterClass characterClass) {
        return switch (characterClass) {
            case KNIGHT -> new KnightRole();
            case MAGE -> new MageRole();
            case ARCHER -> new ArcherRole();
        };
    }
}
