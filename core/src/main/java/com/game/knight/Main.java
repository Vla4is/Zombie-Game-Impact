package com.game.knight;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.knight.screens.PlayGameScreen;

/** Main game entry point shared by all platforms. */
public class Main extends Game {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private int kills;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        font.getData().setScale(1.2f);

        setScreen(new PlayGameScreen(this));
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public BitmapFont getFont() {
        return font;
    }

    public int getKills() {
        return kills;
    }

    public void resetKills() {
        kills = 0;
    }

    public void addKill() {
        kills++;
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
        shapeRenderer.dispose();
        batch.dispose();
    }
}
