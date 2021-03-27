package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

/**
 * This class is created to store high score.
 * @author Maciej
 */
public class HighscoreScreen extends ScreenAdapter {
    Snake game;

    public HighscoreScreen(Snake game){
        this.game = game;
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyDown(int keyCode){
                if(keyCode == Input.Keys.ENTER){
                    game.setScreen(game.newTitel);
                }
                return true;
            }
        });
    }

    @Override

    public void render(float delta){
        Gdx.gl.glClearColor(1,2,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.font.draw(game.batch, "Top 5 Highscore List: ", Gdx.graphics.getWidth()*-0.45f, Gdx.graphics.getHeight()*.45f);
        for (int i = 0; i < game.score_neu.listA.size(); i++){
            game.font.draw(game.batch, (i+1)+". "+ game.score_neu.listA.get(i), Gdx.graphics.getWidth()*-0.45f, Gdx.graphics.getHeight()*(0.40f-0.05f*(i+1)));
            if (i >= 4) {
                break;
            }
        }
        game.batch.end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }


}