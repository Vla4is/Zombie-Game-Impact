package com.game.knight.factory;

import com.game.knight.entity.PlayerCharacter;
import com.game.knight.model.CharacterClass;
import com.game.knight.model.CharacterData;
import com.game.knight.model.HairStyle;
import com.game.knight.model.PaletteColor;
import com.game.knight.model.PetType;
import com.game.knight.model.WeaponType;

/**
 * Defines the player character used in the game.
 */
public final class PlayerCharacterFactory {
    private static final float MOVE_SPEED_MULTIPLIER = 3.0f;
    private static final float ATTACK_COOLDOWN_SECONDS = 0.32f;

    private PlayerCharacterFactory() {
    }

    public static PlayerCharacter createPlayer(float x, float y) {
        CharacterData characterData = new CharacterData(
            "Vlad",
            CharacterClass.ARCHER,
            WeaponType.BOW,
            PaletteColor.SKY,
            PaletteColor.BROWN,
            HairStyle.SHORT,
            PetType.SPARK
        );

        return new PlayerCharacter(characterData, x, y, MOVE_SPEED_MULTIPLIER, ATTACK_COOLDOWN_SECONDS);
    }
}
