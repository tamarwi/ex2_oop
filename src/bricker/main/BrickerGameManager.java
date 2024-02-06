package bricker.main;

import bricker.Constants;
import bricker.Resources;
import bricker.brick_strategies.*;
import bricker.gameobjects.*;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.*;
import danogl.gui.rendering.Camera;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.util.Random;

public class BrickerGameManager extends GameManager {
    private int number_brick_rows;
    private int number_bricks_in_row;
    private Ball ball;
    private Lives lives;
    private Vector2 windowDimensions;
    private WindowController windowController;
    private int numberOfBricks;
    private UserInputListener inputListener;
    private TemporalPaddle temporalPaddle;
    private int lastBallNumCollisions;

    public BrickerGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
    }

    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader, UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        Resources.initializeResources(imageReader, soundReader);
        this.windowDimensions = windowController.getWindowDimensions();
        this.windowController = windowController;
        this.inputListener = inputListener;
        this.temporalPaddle = null;
        windowController.setTargetFramerate(80);

        //create background.
        createBackground();

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
        checkForCameraReset();
    }

    public Vector2 getWindowDimensions(){
        return this.windowDimensions;
    }

    private void checkForGameEnd() {
        float ballHeight = ball.getCenter().y();
        String prompt = "";
        GameObject heartToRemove = null;
        if (ballHeight > windowDimensions.y()) {
            if (null == (heartToRemove = this.lives.decreaseLife())) {
                prompt = "You lose! Play again?";
            } else {
                gameObjects().removeGameObject(heartToRemove, Layer.UI);
                resetBall();
            }
        }
        if(this.numberOfBricks == 0){
            prompt = "you win! play again?";
        }
        if(inputListener.isKeyPressed(KeyEvent.VK_W)) {
            prompt = "You win! Play again?";
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
        Ball ball = new Ball(new Vector2(0, 0), new Vector2(Constants.BALL_RADIUS,
                Constants.BALL_RADIUS),
                Resources.ballImage, Resources.collisionSound);
        this.ball = ball;
        ball.setTag(Constants.MAIN_BALL_TAG);
        resetBall();
        this.gameObjects().addGameObject(ball);

    }

    public void addGameObject(GameObject gameObject) {
        gameObjects().addGameObject(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        gameObjects().removeGameObject(gameObject);
    }

    public void focusCameraOnBall(){
        lastBallNumCollisions = ball.getCollisionCounter();
        this.setCamera(
                new Camera(
                        ball, //object to follow
                        Vector2.ZERO, //follow the center of the object
                        getWindowDimensions().mult(1.2f), //widen the frame a bit
                        getWindowDimensions() //share the window dimensions
                )
        );
    }

    private void checkForCameraReset(){
        if(ball.getCollisionCounter() >= lastBallNumCollisions +
                Constants.NUM_COLLISIONS_BEFORE_CAMERA_RESET){
            setCamera(null);
        }
    }

    private void createPaddle(ImageReader imageReader, UserInputListener inputListener) {
        GameObject userPaddle = new UserPaddle(new Vector2(0, 0), new Vector2(Constants.PADDLE_WIDTH,
                Constants.PADDLE_HEIGHT),
                Resources.paddleImage, inputListener);
        userPaddle.setCenter(new Vector2(windowDimensions.x() / 2, (int) windowDimensions.y() - 30));
        userPaddle.setTag(Constants.MAIN_PADDLE_TAG);
        gameObjects().addGameObject(userPaddle);
    }

    public void createTemporalPaddle() {
        if(this.temporalPaddle!=null){
            return;
        }
        TemporalPaddle temporalPaddle = new TemporalPaddle(new Vector2(0, 0), new Vector2(Constants.PADDLE_WIDTH,
                Constants.PADDLE_HEIGHT),
                Resources.paddleImage, inputListener, this);
        temporalPaddle.setCenter(new Vector2(windowDimensions.x() / 2, windowDimensions.y() / 2));
        gameObjects().addGameObject(temporalPaddle);
        this.temporalPaddle = temporalPaddle;
    }

    public void removeTemporalPaddle(){
        this.temporalPaddle = null;
    }

    private void createLives(ImageReader imageReader, UserInputListener inputListener) {
        Lives lives = new Lives(new Vector2(20, 150), Constants.HEART_DIMENSIONS, //TODO 20,150 random heart placement.
                Resources.heartImage);
        gameObjects().addGameObject(lives, Layer.UI);
        GameObject[] hearts = lives.getHearts();
        for (int i=0; i<lives.getNumberOfLivesLeft(); ++i) {
            gameObjects().addGameObject(hearts[i], Layer.UI);
        }
        this.lives = lives;
    }

    public void addLife(){
        this.lives.increaseLife();
        int num_lives = this.lives.getNumberOfLivesLeft();
        gameObjects().addGameObject(lives.getHearts()[num_lives-1], Layer.UI);
    }

    private void createBackground(){
        GameObject background = new GameObject(Vector2.ZERO, windowDimensions, Resources.backgroundImage);
        gameObjects().addGameObject(background, Layer.BACKGROUND);
    }


    private void createBrick(ImageReader imageReader, Vector2 windowDimensions, Vector2 center, int length) {
        CollisionStrategy strategy = CollisionStrategyBuilder.BuildCollisionStrategy(this);
        GameObject brick = new Brick(Vector2.ZERO, new Vector2(length, 15),
                Resources.brickImage, new DoubleStrategy(this));
        brick.setTopLeftCorner(center);
        gameObjects().addGameObject(brick);
    }

    private void createWalls(){
        Vector2 sideWallDimensions = new Vector2(5, this.windowDimensions.y());
        GameObject leftWall = new GameObject(Vector2.ZERO, sideWallDimensions, null);
        GameObject rightWall = new GameObject(new Vector2(this.windowDimensions.x() - 5, 0), sideWallDimensions, null);
        GameObject topWall = new GameObject(Vector2.ZERO, new Vector2(this.windowDimensions.x(), 5), null);
        leftWall.setTag(Constants.WALL_TAG);
        rightWall.setTag(Constants.WALL_TAG);
        topWall.setTag(Constants.WALL_TAG);
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
        this.numberOfBricks = this.number_bricks_in_row * this.number_brick_rows;
    }

    public void setNumberOfBricks(int numberOfBricks){
        this.numberOfBricks = numberOfBricks;
    }

    public int getNumberOfBricks(){
        return this.numberOfBricks;
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