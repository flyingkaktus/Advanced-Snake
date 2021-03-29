package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * This class controls the background screens of the game.
 * @author Minh, Maciej
 */
public class Snake extends Game { //Extends game to use multiple screens
    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    BitmapFont font;
    Score score_neu;
    Texture img;
    TitleScreen newTitel;

    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;


    @Override
    public void create(){
        //create batch, shape, font to use in other screen
        score_neu = new Score();
        batch = new SpriteBatch();
        font = new BitmapFont();
        newTitel = new TitleScreen(this);
        img = new Texture("background_grass_full.jpg");


        shapeRenderer = new ShapeRenderer();
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Traveler Note.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 140;
        fontParameter.borderWidth = 6;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.color = Color.WHITE;
        font = fontGenerator.generateFont(fontParameter);
        setScreen(newTitel);
    }

    @Override
    public void dispose(){
        //close these when loop game is done
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }
}
