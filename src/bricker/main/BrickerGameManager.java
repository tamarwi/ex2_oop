package bricker.main;

import bricker.Constants;
import bricker.Resources;
import bricker.gameobjects.Brick;
import bricker.brick_strategies.BasicCollisionStrategy;
import bricker.brick_strategies.CollisionStrategy;
import bricker.gameobjects.Ball;
import bricker.gameobjects.UserPaddle;
import danogl.GameManager;
import danogl.GameObject;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.util.Random;

public class BrickerGameManager extends GameManager {

    public BrickerGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
    }

    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader, UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);

        Vector2 windowDimensions = windowController.getWindowDimensions();

        windowController.setTargetFramerate(80);
        //create ball.
        createBall(imageReader, soundReader, windowDimensions);


        Renderable paddleImage = imageReader.readImage("assets/paddle.png", true);

        //create user paddle.
        createPaddle(imageReader, inputListener, windowDimensions);

        //create ai paddle
//        GameObject aiPaddle = new GameObject(new Vector2(0, 0), new Vector2(100, 15), paddleImage);
//        aiPaddle.setCenter(new Vector2(windowDimensions.x() / 2, 30));
//        gameObjects().addGameObject(aiPaddle);

        //create brick
        createBrick(imageReader, windowDimensions);

    }

    private void createBall(ImageReader imageReader, SoundReader soundReader,
                            Vector2 windowDimensions) {
        Renderable ballImage = imageReader.readImage("assets/ball.png", true);
        Sound collisionSound = soundReader.readSound("assets/blop_cut_silenced.wav");
        GameObject ball = new Ball(new Vector2(0, 0), new Vector2(Constants.BALL_RADIUS, Constants.BALL_RADIUS),
                ballImage, collisionSound);
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


    private void createPaddle(ImageReader imageReader, UserInputListener inputListener,
                              Vector2 windowDimensions) {
        GameObject userPaddle = new UserPaddle(new Vector2(0, 0), new Vector2(Constants.PADDLE_WIDTH,
                Constants.PADDLE_HEIGHT),
                Resources.paddleImage, inputListener);
        userPaddle.setCenter(new Vector2(windowDimensions.x() / 2, (int) windowDimensions.y() - 30));
        gameObjects().addGameObject(userPaddle);
    }


    private void createBrick(ImageReader imageReader, Vector2 windowDimensions) {
        Renderable brickImage = imageReader.readImage("assets/brick.png", false);
        GameObject brick = new Brick(Vector2.ZERO, new Vector2(windowDimensions.x(), 15),
                brickImage, new BasicCollisionStrategy());
        brick.setCenter(new Vector2(windowDimensions.x() / 2, (int) windowDimensions.y()/2));
        gameObjects().addGameObject(brick);
    }


    public static void main(String[] args) {
        BrickerGameManager brickerGameManager = new BrickerGameManager("Bouncing ball",
                new Vector2(700, 500));
        brickerGameManager.run();

        System.out.printf("hello");
    }
}
