package com.mygdx.game;

public class BodyParts {
    private int x;
    private int y;

    public BodyParts(int x, int y, int boardSize){
        this.x = x % boardSize; //make the snake can go through the edges
        if(this.x < 0) this.x += boardSize;
        this.y = y % boardSize;
        if(this.y < 0) this.y += boardSize;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
