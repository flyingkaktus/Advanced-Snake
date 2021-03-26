package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

public class EndScreen extends ScreenAdapter {
    Snake game;
    public EndScreen(Snake game){
        this.game = game;
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyDown(int keyCode){
                if(keyCode == Input.Keys.ENTER){
                    game.setScreen(new Highscore(game));
                }
                return true;
            }
        });
    }

    @Override

    public void render(float delta){
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.font.draw(game.batch, "Game", Gdx.graphics.getWidth()*-0.3f, Gdx.graphics.getHeight()*.2f);
        game.font.draw(game.batch, "Over!", Gdx.graphics.getWidth()*-0.3f, Gdx.graphics.getHeight()*.1f);
        game.font.draw(game.batch, "Score: " + GameState.score, Gdx.graphics.getWidth()*-0.3f, Gdx.graphics.getHeight()*.0f);
        //  Gdx.input.getTextInput(listener, "New", "Input Your Name: ", "Your Name", Input.OnscreenKeyboardType 1);
        game.batch.end();

    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}