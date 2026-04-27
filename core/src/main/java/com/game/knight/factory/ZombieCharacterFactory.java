package com.game.knight.factory;

import com.game.knight.entity.Zombie;
import com.game.knight.model.CharacterClass;
import com.game.knight.model.CharacterData;
import com.game.knight.model.HairStyle;
import com.game.knight.model.PaletteColor;
import com.game.knight.model.PetType;
import com.game.knight.model.WeaponType;

/**
 * Defines the enemy character used in the game.
 */
public final class ZombieCharacterFactory {
    private static final float MOVE_SPEED_MULTIPLIER = 0.72f;
    private static final float REACTION_DISTANCE_BONUS = 20f;
    private static final float ATTACK_COOLDOWN_SECONDS = 0.9f;
    private static final float SWAY_DISTANCE = 24f;
    private static final float SWAY_SPEED = 2.4f;
    private static final float VERTICAL_ATTACK_RANGE = 60f;

    private ZombieCharacterFactory() {
    }

    public static Zombie createZombie(float x, float y) {
        CharacterData characterData = new CharacterData(
            "Zombie",
            CharacterClass.KNIGHT,
            WeaponType.SWORD,
            PaletteColor.CHARCOAL,
            PaletteColor.BROWN,
            HairStyle.SHORT,
            PetType.PEBBLE
        );

        return new Zombie(
            characterData,
            x,
            y,
            MOVE_SPEED_MULTIPLIER,
            REACTION_DISTANCE_BONUS,
            ATTACK_COOLDOWN_SECONDS,
            SWAY_DISTANCE,
            SWAY_SPEED,
            VERTICAL_ATTACK_RANGE
        );
    }
}
