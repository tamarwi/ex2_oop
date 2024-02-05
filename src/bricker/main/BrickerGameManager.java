package bricker.main;

import bricker.Constants;
import bricker.Resources;
import bricker.gameobjects.Brick;
import bricker.brick_strategies.BasicCollisionStrategy;
import bricker.gameobjects.Ball;
import bricker.gameobjects.UserPaddle;
import danogl.GameManager;
import danogl.GameObject;
import danogl.gui.*;
import danogl.util.Vector2;

import java.util.Random;

public class BrickerGameManager extends GameManager {
    private int number_brick_rows;
    private int number_bricks_in_row;

    public BrickerGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
    }

    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader, UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        Resources.initializeResources(imageReader, soundReader);
        Vector2 windowDimensions = windowController.getWindowDimensions();
        windowController.setTargetFramerate(80);

        //create ball.
        createBall(imageReader, soundReader, windowDimensions);

        //create user paddle.
        createPaddle(imageReader, inputListener, windowDimensions);

        //create brick
        for(int i= 0; i<this.number_brick_rows;i++){
            for(int j= 0; j<this.number_bricks_in_row;j++){
                createBrick(imageReader,
                        windowDimensions,
                        new Vector2((((windowDimensions.x()- this.number_bricks_in_row*5)/number_bricks_in_row) * j), (i * 15) + (5* i)),
                        (int)(windowDimensions.x()/number_bricks_in_row)-5);
            }
        }
    }

    private void createBall(ImageReader imageReader, SoundReader soundReader,
                            Vector2 windowDimensions) {
        GameObject ball = new Ball(new Vector2(0, 0), new Vector2(Constants.BALL_RADIUS,
                Constants.BALL_RADIUS),
                Resources.ballImage, Resources.collisionSound);
        ball.setVelocity(new Vector2(0, Constants.BALL_SPEED));

        ball.setCenter(windowDimensions.mult(0.5F));
        this.gameObjects().addGameObject(ball);
        float ballVelX = Constants.BALL_SPEED;
        float ballVelY = Constants.BALL_SPEED;
        Random rand = new Random();
        if (rand.nextBoolean()) {
            ballVelX *= -1;
        }
        if (rand.nextBoolean()) {
            ballVelY *= -1;
        }
        ball.setVelocity(new Vector2(ballVelX, ballVelY));
    }

    public void addGameObject(GameObject gameObject){
        gameObjects().addGameObject(gameObject);
    }

    public void removeGameObject(GameObject gameObject){
        gameObjects().removeGameObject(gameObject);
    }


    private void createPaddle(ImageReader imageReader, UserInputListener inputListener,
                              Vector2 windowDimensions) {
        GameObject userPaddle = new UserPaddle(new Vector2(0, 0), new Vector2(Constants.PADDLE_WIDTH,
                Constants.PADDLE_HEIGHT),
                Resources.paddleImage, inputListener);
        userPaddle.setCenter(new Vector2(windowDimensions.x() / 2, (int) windowDimensions.y() - 30));
        gameObjects().addGameObject(userPaddle);
    }


    private void createBrick(ImageReader imageReader, Vector2 windowDimensions, Vector2 center, int length) {
        GameObject brick = new Brick(Vector2.ZERO, new Vector2(length, 15),
                Resources.brickImage, new BasicCollisionStrategy(this));
//        brick.setCenter(center);
        brick.setTopLeftCorner(center);
        gameObjects().addGameObject(brick);
    }

    public void getBrickRowsInfo(String[] args){
        if(args.length == 0){
            this.number_brick_rows = Constants.DEFAULT_NUMBER_BRICK_ROWS;
            this.number_bricks_in_row = Constants.DEFAULT_NUMBER_BRICKS_IN_ROW;
        }
        else{
            this.number_brick_rows = Integer.parseInt(args[0]);
            this.number_bricks_in_row = Integer.parseInt(args[1]);
        }
    }

    public static void main(String[] args) {
        BrickerGameManager brickerGameManager = new BrickerGameManager("Bouncing ball",
                new Vector2(700, 500));
        brickerGameManager.getBrickRowsInfo(args);
        brickerGameManager.run();
    }
}
