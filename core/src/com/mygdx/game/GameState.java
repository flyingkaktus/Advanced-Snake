package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Queue;

/**
 * This class is created to control the game logics.
 * @author Minh, Maciej
 */
public class GameState {
    private GameScreen gameScreen;
    private Snake game;
    private int boardSizeY = 45;
    private int boardSizeX = 24;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Controls controls = new Controls();
    private float mTimer = 0;
    private Food mFood = new Food(boardSizeX,boardSizeY );
    private Queue<BodyPart> mBody = new Queue(); //Use Queue for FIFO,
    private int snakeLength = 3; //==point -3 !!1
    public int that_score;
    private EndScreen newEndscreen;
    private float colourCounter = 0;


    public GameState(GameScreen gameScreen, Snake game){
        this.gameScreen = gameScreen;
        this.game = game;
        newEndscreen = new EndScreen(gameScreen.game);

        //initialize the first 3 parts of the snake in Queue.
        mBody.addLast(new BodyPart(15,15, boardSizeX,boardSizeY));//first
        mBody.addLast(new BodyPart(14,15, boardSizeX,boardSizeY));
        mBody.addLast(new BodyPart(13,15, boardSizeX,boardSizeY));//last
       // mBody.addLast(new BodyPart(12,15, boardSize));
    }
    private float acc = .13f; //Acceleration

    /**
     * This method updates the logics of the advance() method, see what to do everytime gameScreen
     * renders.
     * @param delta
     */
    public void update(float delta){
        mTimer += delta;
        colourCounter += delta;
        controls.update();

        //Reset time period (control the snake movement's speed) and advance the snake.
        if(mTimer > acc){
            mTimer = 0;
            advance();
            //Use for making the snake move faster every 4 apples it eats.
            if(that_score % 4 == 0 && that_score > 0){
                acc -= .01f;
            }

        }

    }
    public void draw(int width, int height, OrthographicCamera camera){ // draw snake and board
        //Set ProjectionMatrix of ShapeRenderer
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        /**
         * This scales the X partion of the snake in the matrix, makes its shape to be square!
         */
        float scaleSnakeX = width/boardSizeX;
        /**
         * This scales the Y partion of the snake in the matrix, makes its shape to be square!
         */
        float scaleSnakeY = height/boardSizeY;

        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rect(0,0,width,height);

        shapeRenderer.setColor(0,0,0,1);
        shapeRenderer.rect(0+5,0+5,width-10, height-10);

        shapeRenderer.setColor(MathUtils.sin(colourCounter), 1, -MathUtils.sin(colourCounter), 1 );
        shapeRenderer.rect(mFood.getX()*scaleSnakeX,
                mFood.getY()*scaleSnakeY,scaleSnakeX, scaleSnakeY);
        for(BodyPart bp: mBody){
            shapeRenderer.rect(bp.getX()*scaleSnakeX,
                    bp.getY()*scaleSnakeY, scaleSnakeX, scaleSnakeY);
        }

        shapeRenderer.end();
    }

    public void advance(){

        int headX = mBody.first().getX();
        int headY = mBody.first().getY();

        //everytime advance() called, the snake move with these instructions.
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
            game.sound.play();
            mFood.randomisePos(boardSizeX,boardSizeY);
        }
        //death
        for(int i = 1; i < mBody.size; i++){
            if (mBody.get(i).getX() == mBody.first().getX()
                    && mBody.get(i).getY() == mBody.first().getY()) {
                    that_score = snakeLength-3;
                    game.score_neu.setScore_latest(that_score);
                    gameScreen.game.setScreen(newEndscreen);
            }

        }
        //Ex: snakeLength = 4 while mBodysize = 3 --> doesn't remove --> +1 part to snake
        while(mBody.size -1 >= snakeLength){
            mBody.removeLast();
        }
    }

}
