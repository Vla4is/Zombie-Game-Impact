package com.game.knight.factory;

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
    private PlayerCharacterFactory() {
    }

    public static CharacterData createCharacter() {
        return new CharacterData(
            "Vlad",
            CharacterClass.ARCHER,
            WeaponType.BOW,
            PaletteColor.SKY,
            PaletteColor.BROWN,
            HairStyle.SHORT,
            PetType.SPARK
        );
    }
}
