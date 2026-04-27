package com.game.knight.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.knight.Main;
import com.game.knight.entity.PlayerCharacter;
import com.game.knight.entity.TrainingDummy;
import com.game.knight.exercise.LessonChallenge;
import com.game.knight.exercise.StudentCharacterFactory;
import com.game.knight.model.CharacterData;
import com.game.knight.model.HairStyle;
import com.game.knight.model.PaletteColor;
import com.game.knight.model.WeaponType;

public class ArenaScreen extends ScreenAdapter {
    private final Main game;
    private final PlayerCharacter player;
    private final TrainingDummy dummy;
    private final CharacterData targetCharacter;
    private String message;

    public ArenaScreen(Main game, CharacterData characterData) {
        this.game = game;
        this.player = new PlayerCharacter(characterData, 80, 160);
        this.dummy = new TrainingDummy(450, 170, 60, 100, 60);
        this.targetCharacter = LessonChallenge.createTargetCharacter();
        this.message = player.getData().matches(targetCharacter)
            ? "Correct character. Walk to the dummy and press SPACE to attack."
            : "Edit StudentCharacterFactory.java until your code matches the target.";
    }

    @Override
    public void render(float delta) {
        handleInput(delta);
        player.update(delta);

        Gdx.gl.glClearColor(0.10f, 0.12f, 0.10f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        drawWorld();
        drawHud();
    }

    private void handleInput(float delta) {
        float moveAmount = player.getMoveSpeed() * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.move(-moveAmount, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.move(moveAmount, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.move(0, moveAmount);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.move(0, -moveAmount);
        }

        keepPlayerInsideArena();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player.startAttackAnimation();
            if (isDummyInRange()) {
                int damage = player.attack(dummy);
                message = player.getRoleName() + " used " + player.getSkillName() + " for " + damage + " damage.";
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            game.setScreen(new ArenaScreen(game, StudentCharacterFactory.createCharacter()));
        }
    }

    private void keepPlayerInsideArena() {
        player.getBounds().x = Math.max(30, Math.min(player.getBounds().x, 560));
        player.getBounds().y = Math.max(60, Math.min(player.getBounds().y, 356));
    }

    private void drawWorld() {
        ShapeRenderer shapeRenderer = game.getShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.18f, 0.30f, 0.18f, 1f);
        shapeRenderer.rect(20, 50, 600, 360);

        drawPlayer(shapeRenderer);
        drawPet(shapeRenderer);
        drawWeapon(shapeRenderer);

        shapeRenderer.setColor(0.65f, 0.30f, 0.25f, 1f);
        shapeRenderer.rect(dummy.getBounds().x, dummy.getBounds().y, dummy.getBounds().width, dummy.getBounds().height);

        shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 1f);
        shapeRenderer.rect(430, 320, 120, 16);
        shapeRenderer.setColor(0.8f, 0.15f, 0.15f, 1f);
        shapeRenderer.rect(430, 320, 120f * dummy.getHealth() / dummy.getMaxHealth(), 16);
        shapeRenderer.end();
    }

    private void drawPlayer(ShapeRenderer shapeRenderer) {
        float x = player.getBounds().x;
        float y = player.getBounds().y;

        shapeRenderer.setColor(PaletteColor.CREAM.toColor());
        shapeRenderer.circle(x + 24, y + 56, 14);

        shapeRenderer.setColor(player.getHairColor().toColor());
        drawHair(shapeRenderer, x, y);

        shapeRenderer.setColor(player.getOutfitColor().toColor());
        shapeRenderer.rect(x + 10, y + 20, 28, 30);

        shapeRenderer.setColor(PaletteColor.CREAM.toColor());
        shapeRenderer.rect(x + 2, y + 22, 8, 22);
        shapeRenderer.rect(x + 38, y + 22, 8, 22);

        shapeRenderer.setColor(PaletteColor.CHARCOAL.toColor());
        shapeRenderer.rect(x + 14, y, 8, 20);
        shapeRenderer.rect(x + 26, y, 8, 20);
    }

    private void drawHair(ShapeRenderer shapeRenderer, float x, float y) {
        HairStyle hairStyle = player.getHairStyle();
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

    private void drawWeapon(ShapeRenderer shapeRenderer) {
        float x = weaponX();
        float y = weaponY();
        WeaponType weaponType = player.getWeaponType();
        float swing = attackOffset();

        if (weaponType == WeaponType.SWORD) {
            shapeRenderer.setColor(PaletteColor.CHARCOAL.toColor());
            shapeRenderer.rect(x + swing, y, 6, 28);
            shapeRenderer.setColor(PaletteColor.CREAM.toColor());
            shapeRenderer.rect(x + 1 + swing, y + 28, 4, 18);
        } else if (weaponType == WeaponType.STAFF) {
            shapeRenderer.setColor(PaletteColor.BROWN.toColor());
            shapeRenderer.rect(x + swing, y - 4, 5, 52);
            shapeRenderer.setColor(PaletteColor.VIOLET.toColor());
            shapeRenderer.circle(x + 2 + swing, y + 52, 6);
        } else {
            shapeRenderer.setColor(PaletteColor.BROWN.toColor());
            shapeRenderer.rect(x + swing, y, 4, 38);
            shapeRenderer.arc(x - 8 + swing, y + 18, 14, -90, 180);
        }
    }

    private boolean isDummyInRange() {
        float horizontalDistance = dummy.getBounds().x - player.getBounds().x;
        float verticalDistance = Math.abs(dummyCenterY() - playerCenterY());
        return horizontalDistance >= 0f
            && horizontalDistance <= player.getAttackDistance()
            && verticalDistance <= 60f;
    }

    private float playerCenterY() {
        return player.getBounds().y + player.getBounds().height * 0.5f;
    }

    private float dummyCenterY() {
        return dummy.getBounds().y + dummy.getBounds().height * 0.5f;
    }

    private float weaponX() {
        return player.getBounds().x + player.getBounds().width + 4f + attackOffset();
    }

    private float weaponY() {
        return player.getBounds().y + 22f + attackHeightOffset();
    }

    private float attackOffset() {
        if (!player.isAttacking()) {
            return 0f;
        }

        float progress = player.getAttackAnimationProgress();
        if (progress < 0.5f) {
            return progress * 40f;
        }
        return (1f - progress) * 40f;
    }

    private float attackHeightOffset() {
        if (!player.isAttacking()) {
            return 0f;
        }

        float progress = player.getAttackAnimationProgress();
        if (progress < 0.5f) {
            return progress * 10f;
        }
        return (1f - progress) * 10f;
    }

    private void drawPet(ShapeRenderer shapeRenderer) {
        float x = player.getBounds().x - 28;
        float y = player.getBounds().y + 4;
        shapeRenderer.setColor(PaletteColor.GOLD.toColor());
        shapeRenderer.circle(x, y + 8, 8);
        shapeRenderer.circle(x - 6, y + 18, 4);
        shapeRenderer.circle(x + 6, y + 18, 4);
    }

    private void drawHud() {
        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), player.getName() + " the " + player.getRoleName(), 20, 470);
        game.getFont().draw(game.getBatch(), "Weapon: " + player.getWeaponType().getDisplayName() + "  Range: " + (int) player.getAttackDistance(), 20, 440);
        game.getFont().draw(game.getBatch(), "Hair: " + player.getHairStyle().getDisplayName() + " / " + player.getHairColor().getDisplayName(), 20, 410);
        game.getFont().draw(game.getBatch(), "Outfit: " + player.getOutfitColor().getDisplayName() + "  Pet: " + player.getPetType().getDisplayName(), 20, 380);
        game.getFont().draw(game.getBatch(), "Health: " + player.getHealth() + "/" + player.getMaxHealth(), 20, 350);
        game.getFont().draw(game.getBatch(), "Dummy HP: " + dummy.getHealth() + "/" + dummy.getMaxHealth(), 430, 360);
        game.getFont().draw(game.getBatch(), "Target: " + describe(targetCharacter), 20, 90, 600, 1, true);
        game.getFont().draw(
            game.getBatch(),
            player.getData().matches(targetCharacter) ? "Status: correct character" : "Status: not matched yet",
            20,
            60
        );
        game.getFont().draw(game.getBatch(), message, 20, 25);
        game.getFont().draw(game.getBatch(), "WASD/Arrows move  SPACE attack  R reload code character", 180, 470);
        game.getBatch().end();
    }

    private String describe(CharacterData character) {
        return character.getName()
            + ", "
            + character.getCharacterClass().getDisplayName()
            + ", "
            + character.getWeaponType().getDisplayName()
            + ", outfit "
            + character.getOutfitColor().getDisplayName()
            + ", hair "
            + character.getHairStyle().getDisplayName()
            + " "
            + character.getHairColor().getDisplayName()
            + ", pet "
            + character.getPetType().getDisplayName();
    }
}
