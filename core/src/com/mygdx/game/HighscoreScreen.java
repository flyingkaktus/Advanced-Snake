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
    private Score score;
    Snake game;

    public HighscoreScreen(Snake game, Score score){

        this.game = game;
        this.score = score;

    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyDown(int keyCode){
                if(keyCode == Input.Keys.ENTER){
                    game.setScreen(new TitleScreen(game));
                }
                return true;
            }
        });

        score.listA.add(score.getScore_highest());
    }

    @Override

    public void render(float delta){
        Gdx.gl.glClearColor(1,2,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.font.draw(game.batch, "Highscore List: ", Gdx.graphics.getWidth()*-0.35f, Gdx.graphics.getHeight()*.4f);

        for (int i = 0; i < score.listA.size(); i++){
            game.font.draw(game.batch, (i+1)+". "+ score.listA.get(i), Gdx.graphics.getWidth()*-0.35f, Gdx.graphics.getHeight()*0.3f);
        }
        game.batch.end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }

}