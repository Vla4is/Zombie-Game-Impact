package com.game.knight.render;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.knight.entity.Combatant;
import com.game.knight.model.HairStyle;
import com.game.knight.model.PaletteColor;
import com.game.knight.model.WeaponType;

public class CombatantRenderer {
    public void draw(
        ShapeRenderer shapeRenderer,
        Combatant combatant,
        PaletteColor skinColor,
        PaletteColor outfitColor,
        PaletteColor hairColor,
        boolean facesLeft
    ) {
        float x = combatant.getBounds().x;
        float y = combatant.getBounds().y;

        shapeRenderer.setColor(skinColor.toColor());
        shapeRenderer.circle(x + 24, y + 56, 14);

        shapeRenderer.setColor(hairColor.toColor());
        drawHair(shapeRenderer, combatant, x, y);

        shapeRenderer.setColor(outfitColor.toColor());
        shapeRenderer.rect(x + 10, y + 20, 28, 30);

        shapeRenderer.setColor(skinColor.toColor());
        shapeRenderer.rect(x + 2, y + 22, 8, 22);
        shapeRenderer.rect(x + 38, y + 22, 8, 22);

        shapeRenderer.setColor(PaletteColor.CHARCOAL.toColor());
        shapeRenderer.rect(x + 14, y, 8, 20);
        shapeRenderer.rect(x + 26, y, 8, 20);

        drawWeapon(shapeRenderer, combatant, facesLeft);
    }

    private void drawHair(ShapeRenderer shapeRenderer, Combatant combatant, float x, float y) {
        HairStyle hairStyle = combatant.getHairStyle();
        if (hairStyle == HairStyle.SHORT) {
            shapeRenderer.rect(x + 12, y + 62, 24, 8);
        } else if (hairStyle == HairStyle.SPIKY) {
            shapeRenderer.triangle(x + 10, y + 64, x + 18, y + 78, x + 26, y + 64);
            shapeRenderer.triangle(x + 22, y + 64, x + 30, y + 80, x + 38, y + 64);
        } else {
            shapeRenderer.rect(x + 12, y + 62, 24, 8);
            shapeRenderer.rect(x + 30, y + 38, 8, 28);
        }
    }

    private void drawWeapon(ShapeRenderer shapeRenderer, Combatant combatant, boolean facesLeft) {
        float swing = attackOffset(combatant);
        float x = weaponX(combatant, facesLeft, swing);
        float y = weaponY(combatant);
        WeaponType weaponType = combatant.getWeaponType();

        if (weaponType == WeaponType.SWORD) {
            shapeRenderer.setColor(PaletteColor.CHARCOAL.toColor());
            shapeRenderer.rect(x, y, 6, 28);
            shapeRenderer.setColor(PaletteColor.CREAM.toColor());
            shapeRenderer.rect(x + 1, y + 28, 4, 18);
        } else if (weaponType == WeaponType.STAFF) {
            shapeRenderer.setColor(PaletteColor.BROWN.toColor());
            shapeRenderer.rect(x, y - 4, 5, 52);
            shapeRenderer.setColor(PaletteColor.VIOLET.toColor());
            shapeRenderer.circle(x + 2, y + 52, 6);
        } else {
            // Bow
            shapeRenderer.setColor(PaletteColor.BROWN.toColor());
            shapeRenderer.rect(x, y, 4, 38);
            if (facesLeft) {
                shapeRenderer.arc(x - 2, y + 18, 14, 90, 180);
            } else {
                shapeRenderer.arc(x - 8, y + 18, 14, -90, 180);
            }
        }
    }

    private float weaponX(Combatant combatant, boolean facesLeft, float swing) {
        if (facesLeft) {
            return combatant.getBounds().x - 10f - swing;
        }
        return combatant.getBounds().x + combatant.getBounds().width + 4f + swing;
    }

    private float weaponY(Combatant combatant) {
        return combatant.getBounds().y + 22f + attackHeightOffset(combatant);
    }

    private float attackOffset(Combatant combatant) {
        if (!combatant.isAttacking()) {
            return 0f;
        }

        float progress = combatant.getAttackAnimationProgress();
        if (progress < 0.5f) {
            return progress * 40f;
        }
        return (1f - progress) * 40f;
    }

    private float attackHeightOffset(Combatant combatant) {
        if (!combatant.isAttacking()) {
            return 0f;
        }

        float progress = combatant.getAttackAnimationProgress();
        if (progress < 0.5f) {
            return progress * 10f;
        }
        return (1f - progress) * 10f;
    }
}
