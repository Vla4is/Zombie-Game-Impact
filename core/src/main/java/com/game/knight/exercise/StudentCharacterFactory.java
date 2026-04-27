package com.game.knight.exercise;

import com.game.knight.model.CharacterClass;
import com.game.knight.model.CharacterData;
import com.game.knight.model.HairStyle;
import com.game.knight.model.PaletteColor;
import com.game.knight.model.PetType;
import com.game.knight.model.WeaponType;

/**
 * Students should edit this file.
 * Change the values below until the character matches the target shown in the game.
 */
public final class StudentCharacterFactory {
    private StudentCharacterFactory() {
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
