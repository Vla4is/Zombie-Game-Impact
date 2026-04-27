package com.game.knight.factory;

import com.game.knight.entity.Zombie;
import com.game.knight.model.CharacterClass;
import com.game.knight.model.CharacterData;
import com.game.knight.model.HairStyle;
import com.game.knight.model.PaletteColor;
import com.game.knight.model.WeaponType;

/**
 * Defines the enemy character used in the game.
 */
public final class ZombieCharacterFactory {
    private ZombieCharacterFactory() {
    }

    public static Zombie createZombie(float x, float y) {
        CharacterData characterData = new CharacterData(
            "Zombie",
            CharacterClass.ZOMBIE,
            WeaponType.SWORD,
            PaletteColor.CHARCOAL,
            PaletteColor.BROWN,
            HairStyle.SHORT
        );

        return new Zombie(characterData, x, y);
    }
}
