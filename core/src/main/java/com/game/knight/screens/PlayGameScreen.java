package com.game.knight.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.game.knight.Main;

public class PlayGameScreen extends ScreenAdapter {
    private static final float DEATH_START_DELAY = 3f;

    private final Main game;
    private final String heading;
    private final String status;
    private final String subheading;
    private final float startDelay;
    private boolean readyForStart;
    private float delayRemaining;

    public PlayGameScreen(Main game) {
        this(game, "PLAY GAME", "", "Press ENTER or SPACE to start", 0f);
    }

    public PlayGameScreen(Main game, String heading, String status, String subheading, float startDelay) {
        this.game = game;
        this.heading = heading;
        this.status = status;
        this.subheading = subheading;
        this.startDelay = startDelay;
        this.delayRemaining = startDelay;
    }

    @Override
    public void render(float delta) {
        if (delayRemaining > 0f) {
            delayRemaining = Math.max(0f, delayRemaining - delta);
        }

        if (!Gdx.input.isKeyPressed(Input.Keys.ENTER) && !Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            readyForStart = true;
        }

        if (readyForStart
            && delayRemaining <= 0f
            && (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE))) {
            game.resetKills();
            game.setScreen(new ArenaScreen(game));
            return;
        }

        Gdx.gl.glClearColor(0.08f, 0.10f, 0.08f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), heading, 240, 300);
        if (!status.isEmpty()) {
            game.getFont().draw(game.getBatch(), status, 270, 270);
        }
        if (delayRemaining > 0f) {
            game.getFont().draw(game.getBatch(), "Wait " + (int) Math.ceil(delayRemaining) + " seconds", 245, 250);
        } else {
            game.getFont().draw(game.getBatch(), subheading, 180, 250);
        }
        game.getFont().draw(game.getBatch(), "Kills: " + game.getKills(), 270, 225);
        game.getFont().draw(game.getBatch(), "Move with WASD or arrows. Press SPACE to attack.", 120, 200);
        game.getBatch().end();
    }

    public static float getDeathStartDelay() {
        return DEATH_START_DELAY;
    }
}
