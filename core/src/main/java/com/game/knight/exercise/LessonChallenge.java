package com.game.knight.exercise;

import com.game.knight.model.CharacterClass;
import com.game.knight.model.CharacterData;
import com.game.knight.model.HairStyle;
import com.game.knight.model.PaletteColor;
import com.game.knight.model.PetType;
import com.game.knight.model.WeaponType;

public final class LessonChallenge {
    private LessonChallenge() {
    }

    public static CharacterData createTargetCharacter() {
        return new CharacterData(
            "Zara",
            CharacterClass.ARCHER,
            WeaponType.BOW,
            PaletteColor.CORAL,
            PaletteColor.GOLD,
            HairStyle.PONYTAIL,
            PetType.MOCHI
        );
    }
}
