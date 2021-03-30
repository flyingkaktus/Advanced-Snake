package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

/**
 * This class is created to initialize food.
 * Implements the Food as an Object for more simple review and test.
 * @author Minh
 */
public class Food {
    private int x;
    private int y;

    /**
     * Spawn the food randomly on the screen.
     * @param boardSizeX
     * @param boardSizeY
     */
    public void randomisePos(int boardSizeX,int boardSizeY){
        x = MathUtils.random(boardSizeX-1);
        y = MathUtils.random(boardSizeY -1);
}
    public Food(int boardSizeX,int boardSizeY){
        randomisePos(boardSizeX,boardSizeY);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
