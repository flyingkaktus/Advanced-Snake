package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * This class is created to control the title screen of the game
 * @author Minh
 */
public class TitleScreen extends ScreenAdapter {
    Snake game;
    //Width and height of the game.
    private int width = 1080;
    private int height = 2280;
    //Camera used to adjust the screen when it is resized.
    public OrthographicCamera camera1 = new OrthographicCamera(width, height);

    public TitleScreen(Snake game){
        this.game = game;
    }

    @Override
    //Whenever we press ENTER the screen will move to Game Screen, where the game begins.
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyDown(int keyCode){ //press enter to play
                if(keyCode == Input.Keys.ENTER){
                    game.setScreen(new GameScreen(game));
                }
                return true;
            }
        });
    }

    /**
     * This method is used to draw the welcome screen for the player.
     * @param delta
     */
    @Override
    public void render(float delta){
        //draw the title screen background
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(camera1.combined);

        //draw the line of the beginning
        game.batch.begin();
        game.font.draw(game.batch, "Welcome to", Gdx.graphics.getWidth()*-0.3f, Gdx.graphics.getHeight()*.2f);
        game.font.draw(game.batch, "Advanced Snake!", Gdx.graphics.getWidth()*-0.3f, Gdx.graphics.getHeight()*.1f);
        game.font.draw(game.batch, "Press Enter to start ", Gdx.graphics.getWidth()*-0.3f, Gdx.graphics.getHeight()*.0f);
        game.batch.end();
    }

    @Override
    //Remove the input of the title screen
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
