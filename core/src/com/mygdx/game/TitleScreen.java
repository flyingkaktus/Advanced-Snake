package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

public class TitleScreen extends ScreenAdapter {
    Snake game;
    public TitleScreen(Snake game){
        this.game = game;
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyDown(int keyCode){
                if(keyCode == Input.Keys.SPACE){
                    game.setScreen(new GameScreen(game));
                }
                return true;
            }
        });
    }
    @Override
    public void render(float delta){
        //draw the title screen background
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //draw the line of the begining
        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Snake Game! ", Gdx.graphics.getWidth() * .4f, Gdx.graphics.getHeight() * .5f );
        game.font.draw(game.batch, "Press Space to play game ", Gdx.graphics.getWidth()* .4f, Gdx.graphics.getHeight()*.4f);
        game.batch.end();
    }

    @Override
    //Remove the input of the title screen
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
