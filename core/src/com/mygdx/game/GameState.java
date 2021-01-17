package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Queue;

public class GameState {
    private int boardSize = 30;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Controls controls = new Controls();
    private Queue<BodyParts> mBody = new Queue<BodyParts>();
    private Food mFood = new Food(boardSize);
    private float mTimer = 0;
    private int snakeLength = 3;

    public GameState(){
        mBody.addLast(new BodyParts(15,15,boardSize)); //head
        mBody.addLast(new BodyParts(15,14,boardSize));
        mBody.addLast(new BodyParts(15,13,boardSize)); //tail
    }

    public void update(float delta){ //update some logic ?
        mTimer += delta;
        if(mTimer > 0.13f){
            mTimer = 0;
            advance();
        }
        controls.update();
    }
    public void draw(int width, int height, OrthographicCamera camera){ //draw the snake and board
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rect(0,0,width,width);

        shapeRenderer.setColor(0,0,0,1);
        shapeRenderer.rect(0+5,0+5,width-5*2, width - 5*2);
        float scaleSnake = width/boardSize;
        //draw the food
        shapeRenderer.setColor(.5f,1,0,1);
        shapeRenderer.rect(mFood.getX()*scaleSnake,mFood.getY()*scaleSnake, scaleSnake,scaleSnake);

        //draw the snake
        shapeRenderer.setColor(1,1,1,1);
        for(BodyParts bp: mBody){
            shapeRenderer.rect( bp.getX()*scaleSnake, bp.getY()*scaleSnake, scaleSnake, scaleSnake);
        }

        shapeRenderer.end();
    }

    private void advance(){
        int headX = mBody.first().getX();
        int headY = mBody.first().getY();

        switch(controls.getDirection()){
            case 0: //UP
                mBody.addFirst(new BodyParts(headX, headY+1, boardSize));
                break;
                case 1: //RIGHT
                    mBody.addFirst(new BodyParts(headX+1, headY, boardSize));
                break;
                case 2: //DOWN
                    mBody.addFirst(new BodyParts(headX, headY-1, boardSize));
                break;
                case 3: //LEFT
                    mBody.addFirst(new BodyParts(headX-1, headY, boardSize));
                break;
            default:
                mBody.addFirst(new BodyParts(headX, headY -1 , boardSize));
                break;
        }
        if(mBody.first().getX() == mFood.getX() && mBody.first().getY() == mFood.getY()){
            snakeLength++;
            mFood.randomisePos(boardSize);
        }
        //dead
        for(int i = 1; i<mBody.size; i++){
            if(mBody.get(i).getX() == mBody.first().getX()
                    && mBody.get(i).getY() == mBody.first().getY()){
                        snakeLength = 3;
            }
        }
        if(mBody.size - 1 == snakeLength){
            mBody.removeLast();
        }
    }
}
