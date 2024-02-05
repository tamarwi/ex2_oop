package bricker.main;

import bricker.Resources;
import bricker.gameobjects.Ball;
import bricker.gameobjects.UserPaddle;
import danogl.GameManager;
import danogl.GameObject;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class BrickerGameManager extends GameManager {

    public BrickerGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
    }

    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader, UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);

        windowController.setTargetFramerate(80);
        //create ball.
        GameObject ball = new Ball(new Vector2(0,0), new Vector2( 20,20), Resources.ballImage,
                Resources.collisionSound);
        ball.setVelocity(new Vector2(0, 100));

        Vector2 windowDimensions = windowController.getWindowDimensions();
        ball.setCenter(windowDimensions.mult(0.5F));
        this.gameObjects().addGameObject(ball);

        //create user paddle.
        GameObject userPaddle = new UserPaddle(new Vector2(0,0), new Vector2( 100,15),
                Resources.paddleImage, inputListener);
        userPaddle.setCenter(new Vector2(windowDimensions.x() / 2, (int)windowDimensions.y() - 30));
        gameObjects().addGameObject(userPaddle);

        //create ai paddle
        GameObject aiPaddle = new GameObject(new Vector2(0,0), new Vector2( 100,15),
                Resources.paddleImage);
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
