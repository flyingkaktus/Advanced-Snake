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
import static com.mygdx.game.GameState.*;

public class EndScreen extends ScreenAdapter {
    Snake game;
    public Score score_neu;
    private HighscoreScreen highscoreScreen;

    public EndScreen(Snake game){
        this.game = game;
        this.score_neu = new Score();
        highscoreScreen = new HighscoreScreen(game, score_neu);
        }

    @Override
    public void show(){

        score_neu.setScore_latest(GameState.that_score);
        score_neu.manage();

        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyDown(int keyCode){
                if(keyCode == Input.Keys.ENTER){
                    game.setScreen(highscoreScreen);
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
        game.font.draw(game.batch, "Game Over!", Gdx.graphics.getWidth()*-0.3f, Gdx.graphics.getHeight()*.6f);
        game.font.draw(game.batch, "Score: " + score_neu.getScore_latest(), Gdx.graphics.getWidth()*-0.3f, Gdx.graphics.getHeight()*.4f);
        game.font.draw(game.batch, "Highscore: " + score_neu.getScore_highest(), Gdx.graphics.getWidth()*-0.3f, Gdx.graphics.getHeight()*.2f);
      if (score_neu.new_score_achieved == true) {
        game.font.draw(game.batch, "Congratulations!", Gdx.graphics.getWidth()*-0.3f, Gdx.graphics.getHeight()*-.25f);
        game.font.draw(game.batch, "New personal record!", Gdx.graphics.getWidth()*-0.3f, Gdx.graphics.getHeight()*-.5f);
      }
        //  Gdx.input.getTextInput(listener, "New", "Input Your Name: ", "Your Name", Input.OnscreenKeyboardType 1);
        game.batch.end();

    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}