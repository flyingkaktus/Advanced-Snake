package com.mygdx.game;

/**
 * This class is created to create the body parts randomly.
 * Implements the Bodypart as an Object.
 * @author Minh
 *
 */
public class BodyPart {
    private int x;
    private int y;
    //Spawn the body part of the snake, which also go through the screen without borders.
    public BodyPart(int x, int y , int boardSizeX,int boardSizeY){
        this.x = x %boardSizeX;
        if(this.x < 0) this.x += boardSizeX;

        this.y = y%boardSizeY;
        if(this.y < 0) this.y += boardSizeY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
