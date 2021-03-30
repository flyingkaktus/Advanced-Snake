package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * This class is created to control movement of the snake.
 * @author Minh
 */
public class Controls {
    private int currentDirection = 0; //0-U, 1-R, 2-D,3-L
    private int nextDirection = 0;

    /**
     * Save the direction in currentDirection for nextDirection continue storing.
     * @return nextDirection
     */
    public int getDirection(){
        currentDirection = nextDirection;
        return nextDirection;
    }

    /**
     * This method prevents the snake from turn 180 his head (not logic in game).
     */
    public void update(){
        if(Gdx.input.isKeyPressed(Input.Keys.UP)&&currentDirection != 2) nextDirection= 0;
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)&&currentDirection != 3) nextDirection= 1;
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)&&currentDirection != 0) nextDirection= 2;
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)&&currentDirection != 1) nextDirection= 3;

    }
}
