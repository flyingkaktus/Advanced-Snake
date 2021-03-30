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
    GlobalScoreScreen newGlobalScreen;
    NetworkClient_w newClient;

    private int width = 1440;
    private int height = 2700;

    public HighscoreScreen(Snake game, NetworkClient_w newClient){
                this.game = game;
                this.newClient = newClient;
                newGlobalScreen = new GlobalScoreScreen(game, newClient);
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyDown(int keyCode){
                if(keyCode == Input.Keys.ENTER){
                    game.setScreen(game.newTitel);
                }
                if(keyCode == Input.Keys.A){
                    game.setScreen(newGlobalScreen);
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
        game.batch.draw(game.img, -1440/2, -2700/2);
        game.font.draw(game.batch, "Highscore Top 5 List: ", width*-0.45f, height*.45f);
        for (int i = 0; i < game.score_neu.listA.size(); i++){
            game.font.draw(game.batch, " " + game.score_neu.listA.get(i), width*-0.40f, height*(0.40f-0.05f*(i+1)));
            if (i >= 4) {
                break;
            }}
        for (int i = 0; i < 5; i++){
                game.font.draw(game.batch, (i+1)+".", width*-0.45f, height*(0.40f-0.05f*(i+1)));
            }
        game.font.draw(game.batch, "Press A to synch w/ Server.", width*-0.45f, height*-.05f);
        game.font.draw(game.batch, "Press Enter to play again.", width*-0.45f, height*-.10f);
        game.batch.end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }


}