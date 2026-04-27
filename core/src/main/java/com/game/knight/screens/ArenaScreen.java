package com.game.knight.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.knight.Main;
import com.game.knight.entity.Combatant;
import com.game.knight.entity.Enemy;
import com.game.knight.entity.PlayerCharacter;
import com.game.knight.entity.Zombie;
import com.game.knight.factory.PlayerCharacterFactory;
import com.game.knight.factory.ZombieCharacterFactory;
import com.game.knight.model.PaletteColor;
import com.game.knight.render.CombatantRenderer;

public class ArenaScreen extends ScreenAdapter {
    private static final float ARENA_MIN_X = 30f;
    private static final float ARENA_MAX_X = 560f;
    private static final float ARENA_MIN_Y = 60f;
    private static final float ARENA_MAX_Y = 356f;
    private static final float ZOMBIE_SPAWN_X = 470f;
    private static final float ZOMBIE_SPAWN_Y = 190f;

    private final Main game;
    private final PlayerCharacter player;
    private final CombatantRenderer combatantRenderer;
    private Enemy enemy;

    public ArenaScreen(Main game) {
        this.game = game;
        this.player = PlayerCharacterFactory.createPlayer(80, 160);
        this.combatantRenderer = new CombatantRenderer();
        this.enemy = createEnemy();
    }

    @Override
    public void render(float delta) {
        handleInput(delta);
        player.update(delta);
        updateEnemy(delta);
        keepInsideArena(player);
        keepInsideArena(enemy);

        if (!player.isAlive()) {
            game.setScreen(new PlayGameScreen(
                game,
                "PLAY GAME",
                "YOU DIED",
                "Press ENTER or SPACE to play again",
                PlayGameScreen.getDeathStartDelay()
            ));
            return;
        }
        if (!enemy.isAlive()) {
            game.addKill();
            enemy = createEnemy();
            return;
        }

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

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (isPlayerInRange()) {
                player.attack(enemy);
            } else {
                player.startAttackAnimation();
            }
        }
    }

    private void updateEnemy(float delta) {
        enemy.updateAgainst(player, delta);
    }

    private Enemy createEnemy() {
        return ZombieCharacterFactory.createZombie(ZOMBIE_SPAWN_X, ZOMBIE_SPAWN_Y);
    }

    private void keepInsideArena(Combatant combatant) {
        combatant.getBounds().x = Math.max(ARENA_MIN_X, Math.min(combatant.getBounds().x, ARENA_MAX_X));
        combatant.getBounds().y = Math.max(ARENA_MIN_Y, Math.min(combatant.getBounds().y, ARENA_MAX_Y));
    }

    private void drawWorld() {
        ShapeRenderer shapeRenderer = game.getShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.18f, 0.30f, 0.18f, 1f);
        shapeRenderer.rect(20, 50, 600, 360);

        combatantRenderer.draw(shapeRenderer, player, PaletteColor.CREAM, player.getOutfitColor(), player.getHairColor(), false);
        drawPet(shapeRenderer);

        combatantRenderer.draw(shapeRenderer, enemy, PaletteColor.LIME, PaletteColor.CHARCOAL, enemy.getHairColor(), true);

        shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 1f);
        shapeRenderer.rect(430, 320, 120, 16);
        shapeRenderer.setColor(0.8f, 0.15f, 0.15f, 1f);
        shapeRenderer.rect(430, 320, 120f * enemy.getHealth() / enemy.getMaxHealth(), 16);
        shapeRenderer.end();
    }

    private boolean isPlayerInRange() {
        float horizontalDistance = Math.abs(enemy.centerX() - player.centerX());
        float verticalDistance = Math.abs(enemy.centerY() - player.centerY());
        return horizontalDistance <= player.getAttackDistance() && verticalDistance <= 60f;
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
        game.getFont().draw(game.getBatch(), "Kills: " + game.getKills(), 20, 440);
        game.getFont().draw(game.getBatch(), "Health: " + player.getHealth() + "/" + player.getMaxHealth(), 20, 350);
        game.getFont().draw(game.getBatch(), "Enemy HP: " + enemy.getHealth() + "/" + enemy.getMaxHealth(), 430, 360);
        game.getFont().draw(game.getBatch(), "Enemy: " + enemy.getName() + " with " + enemy.getWeaponType().getDisplayName(), 20, 90);
        game.getFont().draw(game.getBatch(), "WASD/Arrows move  SPACE attack", 300, 470);
        game.getBatch().end();
    }
}
