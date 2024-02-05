package bricker.main;

import bricker.gameobjects.Ball;
import bricker.gameobjects.UserPaddle;
import danogl.GameManager;
import danogl.GameObject;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.util.Random;

public class BrickerGameManager extends GameManager {
    private static final float BALL_SPEED = 100;
    private static final int PADDLE_WIDTH =100;
    private static final int PADDLE_HEIGHT = 15;
    private static final int BALL_RADIUS = 20;


    public BrickerGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
    }

    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader, UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);

        windowController.setTargetFramerate(80);
        //create ball.
        Renderable ballImage = imageReader.readImage("assets/ball.png", true);
        Sound collisionSound = soundReader.readSound("assets/blop_cut_silenced.wav");
        GameObject ball = new Ball(new Vector2(0, 0), new Vector2(BALL_RADIUS, BALL_RADIUS),
                ballImage, collisionSound);
        ball.setVelocity(new Vector2(0, BALL_SPEED));

        Vector2 windowDimensions = windowController.getWindowDimensions();
        ball.setCenter(windowDimensions.mult(0.5F));
        this.gameObjects().addGameObject(ball);
        float ballVelX = BALL_SPEED;
        float ballVelY = BALL_SPEED;
        Random rand = new Random();
        if (rand.nextBoolean()) {
            ballVelX *= -1;
        }
        if (rand.nextBoolean()) {
            ballVelY *= -1;
        }
        ball.setVelocity(new Vector2(ballVelX, ballVelY));
        Renderable paddleImage = imageReader.readImage("assets/paddle.png", true);

        //create user paddle.
        GameObject userPaddle = new UserPaddle(new Vector2(0, 0), new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT),
                paddleImage, inputListener);
        userPaddle.setCenter(new Vector2(windowDimensions.x() / 2, (int) windowDimensions.y() - 30));
        gameObjects().addGameObject(userPaddle);

        //create ai paddle
        GameObject aiPaddle = new GameObject(new Vector2(0, 0), new Vector2(100, 15), paddleImage);
        aiPaddle.setCenter(new Vector2(windowDimensions.x() / 2, 30));
        gameObjects().addGameObject(aiPaddle);

    }


    public static void main(String[] args) {
        BrickerGameManager brickerGameManager = new BrickerGameManager("Bouncing ball",
                new Vector2(700, 500));
        brickerGameManager.run();

        System.out.printf("hello");
    }
}
