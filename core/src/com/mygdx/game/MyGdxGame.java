package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import static com.badlogic.gdx.scenes.scene2d.InputEvent.Type.keyTyped;

public class MyGdxGame extends ApplicationAdapter {
	enum Screen{
		TITLE,MAIN_GAME, GAME_OVER;
	}

	Screen currentScreen = Screen.TITLE;

	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	BitmapFont font;

	float circleX = 300;
	float circleY = 150;
	float circleRadius = 50;

	float xSpeed = 4;
	float ySpeed = 3;

	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();

		Gdx.input.setInputProcessor(new InputAdapter(){
			@Override
			public boolean keyDown(int keyCode){
				if(currentScreen == Screen.TITLE && keyCode == Input.Keys.SPACE){
					currentScreen = Screen.MAIN_GAME;
				} else if (currentScreen == Screen.GAME_OVER && keyCode == Input.Keys.ENTER) {
					currentScreen = Screen.TITLE;
				}
				return true;
			}

			@Override
			public boolean touchDown(int x, int y, int pointer, int button){
				if (currentScreen == Screen.MAIN_GAME) {
					int renderY = Gdx.graphics.getHeight() - y;
					if(Vector2.dst(circleX,circleY, x, renderY) < circleRadius){
						currentScreen = Screen.GAME_OVER;
					}

				}
			}
		});
	}
	@Override
	public void render () {

		if (currentScreen == Screen.TITLE) {

			 Gdx.gl.glClearColor();
		}
	}
	@Override
	public void dispose () {

	}
}
