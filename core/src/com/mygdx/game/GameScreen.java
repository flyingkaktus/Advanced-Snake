package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * This class is used to draw the screen for the main game.
 * @author Minh
 */
public class GameScreen extends ScreenAdapter {
    public Snake game;
    private GameState gameState;

    private int width = Gdx.graphics.getWidth();
    private int height = Gdx.graphics.getHeight();

    public OrthographicCamera camera = new OrthographicCamera(width, height);
    private Viewport viewport;

    public GameScreen(Snake game){
        this.game = game;
        camera.setToOrtho(false, width, height);
        viewport = new FitViewport(width, height, camera);
        viewport.apply();
    }

    public void setGameState(){
        gameState = new GameState(this);
    }

    @Override
    public void render(float delta){
        gameState.update(delta);
        camera.update();
        viewport.apply();

    //draw the background color
        Gdx.gl.glClearColor(0,0,0,1 );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameState.draw(width,height,camera);

    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height);
    }

}

