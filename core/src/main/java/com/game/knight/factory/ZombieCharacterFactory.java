package com.game.knight.factory;

import java.util.Random;

import com.game.knight.model.CharacterClass;
import com.game.knight.model.CharacterData;
import com.game.knight.model.HairStyle;
import com.game.knight.model.PaletteColor;
import com.game.knight.model.PetType;
import com.game.knight.model.WeaponType;

public final class ZombieCharacterFactory {
    private static final Random RANDOM = new Random();

    private ZombieCharacterFactory() {
    }

    public static CharacterData createCharacter() {
        CharacterClass characterClass = pick(CharacterClass.values());
        return new CharacterData(
            "Zombie",
            characterClass,
            weaponFor(characterClass),
            pick(new PaletteColor[]{PaletteColor.LIME, PaletteColor.CHARCOAL, PaletteColor.VIOLET}),
            pick(new PaletteColor[]{PaletteColor.LIME, PaletteColor.BROWN, PaletteColor.CHARCOAL}),
            pick(HairStyle.values()),
            pick(PetType.values())
        );
    }

    private static WeaponType weaponFor(CharacterClass characterClass) {
        return switch (characterClass) {
            case KNIGHT -> WeaponType.SWORD;
            case MAGE -> WeaponType.STAFF;
            case ARCHER -> WeaponType.BOW;
        };
    }

    private static <T> T pick(T[] values) {
        return values[RANDOM.nextInt(values.length)];
    }
}
