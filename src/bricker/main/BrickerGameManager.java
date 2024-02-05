package bricker.main;

import bricker.Constants;
import bricker.Resources;
import bricker.gameobjects.Brick;
import bricker.brick_strategies.BasicCollisionStrategy;
import bricker.gameobjects.Ball;
import bricker.gameobjects.Lives;
import bricker.gameobjects.UserPaddle;
import danogl.GameManager;
import danogl.GameObject;
import danogl.gui.*;
import danogl.util.Vector2;

import java.util.Random;

public class BrickerGameManager extends GameManager {
    private int number_brick_rows;
    private int number_bricks_in_row;
    private GameObject ball;
    private Lives lives;
    private Vector2 windowDimensions;
    private WindowController windowController;
    private int numberOfLivesLeft;

    public BrickerGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
    }

    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader, UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        Resources.initializeResources(imageReader, soundReader);
        this.windowDimensions = windowController.getWindowDimensions();
        this.windowController = windowController;
        windowController.setTargetFramerate(80);

        //initialize lives.
        this.numberOfLivesLeft = Constants.NUMBER_OF_LIVES;

        //create ball.
        createBall(imageReader, soundReader);

        //create user paddle.
        createPaddle(imageReader, inputListener);

        //create bricks.
        createBricks(imageReader);

        //create lives.
        createLives(imageReader, inputListener);

        //create walls.
        createWalls();
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        checkForGameEnd();
    }

    private void checkForGameEnd() {
        float ballHeight = ball.getCenter().y();
        String prompt = "";
        GameObject heartToRemove = null;
        if (ballHeight < 0 || ballHeight > windowDimensions.y()) {
            if (null == (heartToRemove = this.lives.decreaseLife())) {
                prompt = "You lose! Play again?";
            } else {
                gameObjects().removeGameObject(heartToRemove);
                resetBall();
            }
        }

        if (!prompt.isEmpty()) {
            if (windowController.openYesNoDialog(prompt)) {
                windowController.resetGame();
            } else {
                windowController.closeWindow();
            }
        }
    }

    private void resetBall() {
        this.ball.setCenter(windowDimensions.mult(0.5F));

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

    private void createBall(ImageReader imageReader, SoundReader soundReader) {
        GameObject ball = new Ball(new Vector2(0, 0), new Vector2(Constants.BALL_RADIUS,
                Constants.BALL_RADIUS),
                Resources.ballImage, Resources.collisionSound);
        this.ball = ball;
        resetBall();
        this.gameObjects().addGameObject(ball);

    }

    public void addGameObject(GameObject gameObject) {
        gameObjects().addGameObject(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        gameObjects().removeGameObject(gameObject);
    }


    private void createPaddle(ImageReader imageReader, UserInputListener inputListener) {
        GameObject userPaddle = new UserPaddle(new Vector2(0, 0), new Vector2(Constants.PADDLE_WIDTH,
                Constants.PADDLE_HEIGHT),
                Resources.paddleImage, inputListener);
        userPaddle.setCenter(new Vector2(windowDimensions.x() / 2, (int) windowDimensions.y() - 30));
        gameObjects().addGameObject(userPaddle);
    }

    private void createLives(ImageReader imageReader, UserInputListener inputListener) {
        Lives lives = new Lives(new Vector2(20, 150), Constants.HEART_DIMENSIONS, //TODO 20,150 random heart placement.
                Resources.heartImage);
        gameObjects().addGameObject(lives);
        for (GameObject heart : lives.getHearts()) {
            gameObjects().addGameObject(heart);
        }
        this.lives = lives;
    }


    private void createBrick(ImageReader imageReader, Vector2 windowDimensions, Vector2 center, int length) {
        GameObject brick = new Brick(Vector2.ZERO, new Vector2(length, 15),
                Resources.brickImage, new BasicCollisionStrategy(this));
        brick.setTopLeftCorner(center);
        gameObjects().addGameObject(brick);
    }

    private void createWalls(){
        Vector2 sideWallDimensions = new Vector2(5, this.windowDimensions.y());
        GameObject leftWall = new GameObject(Vector2.ZERO, sideWallDimensions, null);
        GameObject rightWall = new GameObject(new Vector2(this.windowDimensions.x() - 5, 0), sideWallDimensions, null);
        GameObject topWall = new GameObject(Vector2.ZERO, new Vector2(this.windowDimensions.x(), 5), null);
        gameObjects().addGameObject(leftWall);
        gameObjects().addGameObject(rightWall);
        gameObjects().addGameObject(topWall);
    }

    private void createBricks(ImageReader imageReader){
        for (int i = 0; i < this.number_brick_rows; i++) {
            for (int j = 0; j < this.number_bricks_in_row; j++) {
                createBrick(imageReader,
                        windowDimensions,
                        new Vector2((((windowDimensions.x() - (this.number_bricks_in_row - 1) * 5) / number_bricks_in_row) * j), (i * 15) + (5 * i)),
                        (int) (windowDimensions.x() / number_bricks_in_row) - 5);
            }
        }
    }

    public void getBrickRowsInfo(String[] args) {
        if (args.length == 0) {
            this.number_brick_rows = Constants.DEFAULT_NUMBER_BRICK_ROWS;
            this.number_bricks_in_row = Constants.DEFAULT_NUMBER_BRICKS_IN_ROW;
        } else {
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
