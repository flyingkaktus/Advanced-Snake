package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

/**
 * Drawed when condition is met in gameScreen class.
 * @author Minh, Maciej
 */
import java.util.Collections;

import static com.mygdx.game.GameState.*;

public class EndScreen extends ScreenAdapter {
    Snake game;
    HighscoreScreen newHigh;
    private int thatscore;

    public EndScreen(Snake game){
        this.game = game;
        this.newHigh = new HighscoreScreen(game);
        }

    public int getThatscore() {
        return thatscore;
    }

    public void setThatscore(int thatscore) {
        this.thatscore = thatscore;
    }

    @Override
    public void show(){

        game.score_neu.setScore_latest(thatscore);
        game.score_neu.manage();
        game.score_neu.listA.add(game.score_neu.getScore_latest());
        game.score_neu.sortthatlist();

        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyDown(int keyCode){
                if(keyCode == Input.Keys.ENTER){
                    game.setScreen(newHigh);
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.font.draw(game.batch, "Game Over!", Gdx.graphics.getWidth()*-0.45f, Gdx.graphics.getHeight()*0.45f);
        game.font.draw(game.batch, "Score: " + game.score_neu.getScore_latest(), Gdx.graphics.getWidth()*-0.45f, Gdx.graphics.getHeight()*.25f);
        game.font.draw(game.batch, "Highscore: " + game.score_neu.getScore_highest(), Gdx.graphics.getWidth()*-0.45f, Gdx.graphics.getHeight()*.2f);
      if (game.score_neu.new_score_achieved == true) {
        game.font.draw(game.batch, "Congratulations!", Gdx.graphics.getWidth()*-0.45f, Gdx.graphics.getHeight()*-.25f);
        game.font.draw(game.batch, "New personal record!", Gdx.graphics.getWidth()*-0.45f, Gdx.graphics.getHeight()*-.30f);
      }
        //  Gdx.input.getTextInput(listener, "New", "Input Your Name: ", "Your Name", Input.OnscreenKeyboardType 1);
        game.batch.end();

    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}