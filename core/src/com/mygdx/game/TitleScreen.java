package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

/**
 * This class is created to control the title screen of the game
 * @author Minh
 */
public class TitleScreen extends ScreenAdapter {
    Snake game;

    //Width and height of the game.
    private int width = 1440;
    private int height = 2700;

    //Camera used to adjust the screen when it is resized.
    public OrthographicCamera camera1 = new OrthographicCamera(width, height);
    public GameScreen gameScreen;

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
                    gameScreen = new GameScreen(game);
                    gameScreen.setGameState();
                    game.setScreen(gameScreen);
                }
                return true;
            }
        });
    }

    /**
     * This method is used to draw the welcome screen for the player.
     */
    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);       // set a specific color to clean the frame;
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);                   // cleans the frame;

        game.batch.setProjectionMatrix(camera1.combined);

        // the different objects for TitleScreen are drawn here;
        game.batch.begin();
        game.batch.draw(game.img, -1440/2, -2700/2);
        game.font.draw(game.batch, "Welcome to", width*-0.45f, height*.35f);
        game.font.draw(game.batch, "Advanced Snake!", width*-0.45f, height*.25f);
        game.font.draw(game.batch, "Press Enter to start ", width*-0.45f, height*.0f);
        game.batch.end();
    }

    @Override
    //Remove the input of the title screen
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
