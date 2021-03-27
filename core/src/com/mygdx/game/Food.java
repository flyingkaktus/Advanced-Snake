package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

/**
 * This class is created to initialize food.
 * @author Minh
 */
public class Food {
    private int x;
    private int y;

    public void randomisePos(int boardSize){
        x = MathUtils.random(boardSize-1);
        y = MathUtils.random(boardSize -1);
}
    public Food(int boardSize){
        randomisePos(boardSize);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
