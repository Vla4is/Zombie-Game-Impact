package com.game.knight.factory;

import com.game.knight.entity.PlayerCharacter;
import com.game.knight.model.CharacterClass;
import com.game.knight.model.CharacterData;
import com.game.knight.model.HairStyle;
import com.game.knight.model.PaletteColor;
import com.game.knight.model.WeaponType;

/**
 * Defines the player character used in the game.
 */
public final class PlayerCharacterFactory {
    private PlayerCharacterFactory() {
    }

    public static PlayerCharacter createPlayer(float x, float y) {
        CharacterData characterData = new CharacterData(
            "Vlad",
            CharacterClass.KNIGHT,
            WeaponType.SWORD,
            PaletteColor.SKY,
            PaletteColor.BROWN,
            HairStyle.SHORT
        );

        return new PlayerCharacter(characterData, x, y);
    }
}
