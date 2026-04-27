package com.game.knight.model;

/** Stores the character description that students build in code. */
public class CharacterData {
    private final String name;
    private final CharacterClass characterClass;
    private final WeaponType weaponType;
    private final PaletteColor outfitColor;
    private final PaletteColor hairColor;
    private final HairStyle hairStyle;

    public CharacterData(
        String name,
        CharacterClass characterClass,
        WeaponType weaponType,
        PaletteColor outfitColor,
        PaletteColor hairColor,
        HairStyle hairStyle
    ) {
        this.name = name;
        this.characterClass = characterClass;
        this.weaponType = weaponType;
        this.outfitColor = outfitColor;
        this.hairColor = hairColor;
        this.hairStyle = hairStyle;
    }

    public boolean matches(CharacterData other) {
        return name.equals(other.name)
            && characterClass == other.characterClass
            && weaponType == other.weaponType
            && outfitColor == other.outfitColor
            && hairColor == other.hairColor
            && hairStyle == other.hairStyle;
    }

    public String getName() {
        return name;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public PaletteColor getOutfitColor() {
        return outfitColor;
    }

    public PaletteColor getHairColor() {
        return hairColor;
    }

    public HairStyle getHairStyle() {
        return hairStyle;
    }
}
