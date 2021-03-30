package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Queue;

/**
 * This class is created to control the game logics.
 * @author Minh, Maciej
 */
public class GameState {
    private GameScreen gameScreen;
    private int boardSizeY = 45;
    private int boardSizeX = 24;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Controls controls = new Controls();
    private float mTimer = 0;
    private Food mFood = new Food(boardSizeX,boardSizeY );
    private Queue<BodyPart> mBody = new Queue();
    private int snakeLength = 3; //==point -3 !!1
    public int that_score;
    private EndScreen newEndscreen;

    public GameState(GameScreen gameScreen){
        this.gameScreen = gameScreen;
        newEndscreen = new EndScreen(gameScreen.game);
        mBody.addLast(new BodyPart(15,15, boardSizeX,boardSizeY));//first
        mBody.addLast(new BodyPart(14,15, boardSizeX,boardSizeY));
        mBody.addLast(new BodyPart(13,15, boardSizeX,boardSizeY));//last
       // mBody.addLast(new BodyPart(12,15, boardSize));
    }
    private float acc = .13f; //acceleration
    public void update(float delta){
        mTimer += delta;
        controls.update();
        //preset time period and advance the snake
        if(mTimer > acc){
            mTimer = 0;
            advance();
            if(that_score % 4 == 0 && that_score > 0){
                acc -= .01f;
            }

        }

    }
    public void draw(int width, int height, OrthographicCamera camera){ // draw snake and board
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //draw the game screen border
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rect(0,0, width,height);

        //drawover to see clearly the border
        shapeRenderer.setColor(.0059f,.0322f,.0729f,1);
        shapeRenderer.rect(5,5,width-10,height-10);


        shapeRenderer.setColor(1,1,1,1);
        float scaleSnakeX = width/boardSizeX;
        float scaleSnakeY = height/boardSizeY;
        //draw food
        shapeRenderer.rect(mFood.getX() * scaleSnakeX, mFood.getY() * scaleSnakeY, scaleSnakeX, scaleSnakeY);
        //draw snake
        for(BodyPart bp: mBody){
            shapeRenderer.rect(bp.getX()*scaleSnakeX, bp.getY()*scaleSnakeY, scaleSnakeX, scaleSnakeY);
        }

        shapeRenderer.end();
    }

    public void advance(){

        int headX = mBody.first().getX();
        int headY = mBody.first().getY();

        switch(controls.getDirection()){
            case 0: //UP
                mBody.addFirst(new BodyPart(headX, headY+1, boardSizeX,boardSizeY));
                break;
            case 1: //RIGHT
                mBody.addFirst(new BodyPart(headX+1, headY, boardSizeX,boardSizeY));
                break;
            case 2: //DOWN
                mBody.addFirst(new BodyPart(headX, headY+-1, boardSizeX,boardSizeY));
                break;
            case 3: //LEFT
                mBody.addFirst(new BodyPart(headX-1, headY, boardSizeX,boardSizeY));
                break;
            default:
                mBody.addFirst(new BodyPart(headX, headY+1, boardSizeX,boardSizeY));
                break;
        }
        //snake eat food
        if(mBody.first().getX() == mFood.getX() && mBody.first().getY() == mFood.getY()){
            snakeLength ++;
            mFood.randomisePos(boardSizeX,boardSizeY);
        }
        //death
        for(int i = 1; i < mBody.size; i++){
            if (mBody.get(i).getX() == mBody.first().getX()
                    && mBody.get(i).getY() == mBody.first().getY()) {
                    that_score = snakeLength-3;
                    newEndscreen.setThatscore(that_score);
                    gameScreen.game.setScreen(newEndscreen);
            }

        }
        //Ex: snakeLength = 4 while mBodysize = 3 --> doesn't remove --> +1 part to snake
        while(mBody.size -1 >= snakeLength){
            mBody.removeLast();
        }
    }
}
