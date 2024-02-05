package bricker.main;

import bricker.gameobjects.Ball;
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
        Renderable ballImage = imageReader.readImage("assets/ball.png", true);
        Sound collisionSound = soundReader.readSound("assets/blop_cut_silenced.wav");
        GameObject ball = new Ball(new Vector2(0,0), new Vector2( 20,20), ballImage, collisionSound);
        ball.setVelocity(new Vector2(0, 100));

        Vector2 windowDimensions = windowController.getWindowDimensions();
        ball.setCenter(windowDimensions.mult(0.5F));
        this.gameObjects().addGameObject(ball);

        //create paddles.
        int[] paddleHeights = {(int)windowDimensions.y() - 30, 30};
        Renderable paddleImage = imageReader.readImage("assets/paddle.png", true);

        for(int i =0 ; i< paddleHeights.length; i++){
            GameObject paddle = new GameObject(new Vector2(0,0), new Vector2( 100,15), paddleImage);
            paddle.setCenter(new Vector2(windowDimensions.x() / 2, paddleHeights[i]));
            gameObjects().addGameObject(paddle);
        }
    }

    public static void main(String[] args) {
        BrickerGameManager brickerGameManager = new BrickerGameManager("Bouncing ball",
                new Vector2(700, 500));
        brickerGameManager.run();

        System.out.printf("hello");
    }
}
