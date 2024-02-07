package bricker.main;

import bricker.Constants;
import bricker.Resources;
import bricker.brick_strategies.*;
import bricker.gameobjects.*;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.*;
import danogl.gui.rendering.Camera;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Manages the game logic for the Bricker game.
 * Extends GameManager.
 *
 * <p>
 * The BrickerGameManager class handles the initialization, update, and management of game objects,
 * including the ball, paddle, bricks, lives, and camera. It also manages game-ending conditions
 * and user input.
 * </p>
 *
 * @author Your Name
 * @see GameManager
 */
public class BrickerGameManager extends GameManager {

    private int number_brick_rows; // Number of rows of bricks
    private int number_bricks_in_row; // Number of bricks in each row
    private Ball ball; // The ball GameObject
    private Lives lives; // The Lives GameObject
    private Vector2 windowDimensions; // Dimensions of the game window
    private WindowController windowController; // Controller for the game window
    private int numberOfBricks; // Total number of bricks in the game
    private UserInputListener inputListener; // Listener for user input
    private TemporalPaddle temporalPaddle; // Temporal paddle GameObject
    private int lastBallNumCollisions; // Number of collisions the ball had in the last frame

    /**
     * Constructor for BrickerGameManager.
     *
     * @param windowTitle The title of the game window.
     * @param windowDimensions The dimensions of the game window.
     */
    public BrickerGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
    }

    /**
     * Initializes the game resources and objects.
     *
     * @param imageReader      The ImageReader object to read image resources.
     * @param soundReader      The SoundReader object to read sound resources.
     * @param inputListener    The UserInputListener object to handle user input.
     * @param windowController The WindowController object to control the game window.
     */
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
                               UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        Resources.initializeResources(imageReader, soundReader);
        this.windowDimensions = windowController.getWindowDimensions();
        this.windowController = windowController;
        this.inputListener = inputListener;
        this.temporalPaddle = null;
        windowController.setTargetFramerate(Constants.GAME_FRAMERATE);

        // Create background.
        createBackground();

        // Create ball.
        createBall();

        // Create user paddle.
        createPaddle(imageReader, inputListener);

        // Create bricks.
        createBricks();

        // Create lives.
        createLives(imageReader, inputListener);

        // Create walls.
        createWalls();
    }

    /**
     * Updates the game logic.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        checkForGameEnd();
        checkForCameraReset();
    }

    /**
     * Gets the dimensions of the game window.
     *
     * @return The Vector2 object representing the window dimensions.
     */
    public Vector2 getWindowDimensions() {
        return this.windowDimensions;
    }

    /**
     * Checks for the end of the game.
     */
    private void checkForGameEnd() {
        float ballHeight = ball.getCenter().y();
        String prompt = "";
        GameObject heartToRemove = null;
        if (ballHeight > windowDimensions.y()) {
            if (null == (heartToRemove = this.lives.decreaseLife())) {
                prompt = Constants.LOSE_PROMPT;
            } else {
                gameObjects().removeGameObject(heartToRemove, Layer.UI);
                resetBall();
            }
        }
        if (this.numberOfBricks == 0) {
            prompt = Constants.WIN_PROMPT;
        }
        if (inputListener.isKeyPressed(KeyEvent.VK_W)) {
            prompt = Constants.WIN_PROMPT;
        }

        if (!prompt.isEmpty()) {
            if (windowController.openYesNoDialog(prompt)) {
                windowController.resetGame();
            } else {
                windowController.closeWindow();
            }
        }
    }

    /**
     * Resets the ball's position and velocity.
     */
    private void resetBall() {
        this.ball.setCenter(windowDimensions.mult(Constants.MIDDLE));

        float ballVelocityX = Constants.BALL_SPEED;
        float ballVelocityY = Constants.BALL_SPEED;
        Random rand = new Random();
        if (rand.nextBoolean()) {
            ballVelocityX *= -1;
        }
        if (rand.nextBoolean()) {
            ballVelocityY *= -1;
        }
        ball.setVelocity(new Vector2(ballVelocityX, ballVelocityY));
    }

    /**
     * Creates the ball game object.
     */
    private void createBall() {
        Ball ball = new Ball(new Vector2(0, 0), new Vector2(Constants.BALL_RADIUS,
                Constants.BALL_RADIUS),
                Resources.ballImage, Resources.collisionSound);
        this.ball = ball;
        ball.setTag(Constants.MAIN_BALL_TAG);
        resetBall();
        this.gameObjects().addGameObject(ball);
    }

    /**
     * Adds a game object to the game.
     *
     * @param gameObject The game object to add.
     */
    public void addGameObject(GameObject gameObject) {
        gameObjects().addGameObject(gameObject);
    }

    /**
     * Removes a game object from the game.
     *
     * @param gameObject The game object to remove.
     */
    public void removeGameObject(GameObject gameObject) {
        gameObjects().removeGameObject(gameObject);
    }

    /**
     * Focuses the camera on the ball.
     */
    public void focusCameraOnBall() {
        lastBallNumCollisions = ball.getCollisionCounter();
        this.setCamera(
                new Camera(
                        ball, // Object to follow
                        Vector2.ZERO, // Follow the center of the object
                        getWindowDimensions().mult(Constants.WIDEN_FRAME_FACTOR), // Widen the frame a bit
                        getWindowDimensions() // Share the window dimensions
                )
        );
    }

    /**
     * Checks for camera reset condition.
     */
    private void checkForCameraReset() {
        if (ball.getCollisionCounter() >= lastBallNumCollisions +
                Constants.NUM_COLLISIONS_BEFORE_CAMERA_RESET) {
            setCamera(null);
        }
    }

    /**
     * Creates the user paddle game object.
     *
     * @param imageReader   The ImageReader object to read paddle image.
     * @param inputListener The UserInputListener object to handle user input.
     */
    private void createPaddle(ImageReader imageReader, UserInputListener inputListener) {
        GameObject userPaddle = new Paddle(new Vector2(0, 0), new Vector2(Constants.PADDLE_WIDTH,
                Constants.PADDLE_HEIGHT),
                Resources.paddleImage, inputListener);
        userPaddle.setCenter(new Vector2((int) (windowDimensions.x() * Constants.MIDDLE),
                (int) windowDimensions.y() - 30));
        userPaddle.setTag(Constants.MAIN_PADDLE_TAG);
        gameObjects().addGameObject(userPaddle);
    }

    /**
     * Creates a temporal paddle game object.
     */
    public void createTemporalPaddle() {
        if (this.temporalPaddle != null) {
            return;
        }
        TemporalPaddle temporalPaddle = new TemporalPaddle(new Vector2(0, 0), new Vector2(
                Constants.PADDLE_WIDTH,
                Constants.PADDLE_HEIGHT),
                Resources.paddleImage, inputListener, this);
        temporalPaddle.setCenter(new Vector2(windowDimensions.mult(Constants.MIDDLE)));
        gameObjects().addGameObject(temporalPaddle);
        this.temporalPaddle = temporalPaddle;
    }

    /**
     * Removes the temporal paddle game object.
     */
    public void removeTemporalPaddle() {
        this.temporalPaddle = null;
    }

    /**
     * Creates the lives game object.
     *
     * @param imageReader   The ImageReader object to read heart image.
     * @param inputListener The UserInputListener object to handle user input.
     */
    private void createLives(ImageReader imageReader, UserInputListener inputListener) {
        // Initialize lives GameObject
        Lives lives = new Lives(new Vector2(40, windowDimensions.y() - 30), Constants.HEART_DIMENSIONS,
                Resources.heartImage);
        lives.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        gameObjects().addGameObject(lives, Layer.UI);
        GameObject[] livesGameObjects = lives.getHearts();
        for (int i = 0; i < lives.getNumberOfLivesLeft(); ++i) {
            livesGameObjects[i].setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
            gameObjects().addGameObject(livesGameObjects[i], Layer.UI);
        }
        GameObject numericDisplayGameObject = lives.getNumericDisplay();
        numericDisplayGameObject.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        gameObjects().addGameObject(numericDisplayGameObject);
        this.lives = lives;
    }

    /**
     * Adds a life to the player's lives.
     */
    public void addLife() {
        this.lives.increaseLife();
        int num_lives = this.lives.getNumberOfLivesLeft();
        gameObjects().addGameObject(lives.getHearts()[num_lives - 1], Layer.UI);
    }

    /**
     * Creates the background game object.
     */
    private void createBackground() {
        GameObject background = new GameObject(Vector2.ZERO, windowDimensions, Resources.backgroundImage);
        background.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        gameObjects().addGameObject(background, Layer.BACKGROUND);
    }

    /**
     * Creates a brick game object.
     *
     * @param topLeftCorner The top left corner position of the brick.
     * @param length        The length of the brick.
     */
    private void createBrick(Vector2 topLeftCorner, int length) {
        CollisionStrategy strategy = CollisionStrategyBuilder.BuildCollisionStrategy(this);
        GameObject brick = new Brick(topLeftCorner, new Vector2(length, Constants.BRICK_HEIGHT),
                Resources.brickImage, strategy);
        gameObjects().addGameObject(brick);
    }

    /**
     * Creates the bricks for the game.
     *
     */
    private void createBricks() {
        int brick_length = (int) (windowDimensions.x() - (Constants.SAFETY_LENGTH_FROM_WALL * 2)
                - (this.number_bricks_in_row - 1)) / number_bricks_in_row;
        for (int i = 0; i < this.number_brick_rows; i++) {
            for (int j = 0; j < this.number_bricks_in_row; j++) {
                createBrick(new Vector2((Constants.SAFETY_LENGTH_FROM_WALL + j * brick_length),
                        Constants.SAFETY_LENGTH_FROM_WALL +
                                (i * Constants.SAFETY_HEIGHT_BETWEEN_BRICKS)), brick_length);
            }
        }
        this.numberOfBricks = this.number_bricks_in_row * this.number_brick_rows;
    }


    /**
     * Creates the wall game objects.
     */
    private void createWalls() {
        Vector2 sideWallDimensions = new Vector2(Constants.WALL_WIDTH, this.windowDimensions.y());
        GameObject leftWall = new GameObject(Vector2.ZERO, sideWallDimensions, null);
        GameObject rightWall = new GameObject(new Vector2(this.windowDimensions.x() - Constants.WALL_WIDTH,
                0), sideWallDimensions, null);
        GameObject topWall = new GameObject(Vector2.ZERO, new Vector2(this.windowDimensions.x(),
                Constants.WALL_WIDTH), null);
        leftWall.setTag(Constants.WALL_TAG);
        rightWall.setTag(Constants.WALL_TAG);
        topWall.setTag(Constants.WALL_TAG);
        gameObjects().addGameObject(leftWall);
        gameObjects().addGameObject(rightWall);
        gameObjects().addGameObject(topWall);
    }


    /**
     * Sets the number of bricks in the game.
     *
     * @param numberOfBricks The number of bricks to set.
     */
    public void setNumberOfBricks(int numberOfBricks) {
        this.numberOfBricks = numberOfBricks;
    }

    /**
     * Gets the number of bricks in the game.
     *
     * @return The number of bricks.
     */
    public int getNumberOfBricks() {
        return this.numberOfBricks;
    }

    /**
     * Parses command line arguments to get the number of brick rows and bricks in each row.
     *
     * @param args The command line arguments.
     */
    public void getBrickRowsInfo(String[] args) {
        if (args.length == 0) {
            this.number_brick_rows = Constants.DEFAULT_NUMBER_BRICK_ROWS;
            this.number_bricks_in_row = Constants.DEFAULT_NUMBER_BRICKS_IN_ROW;
        } else {
            this.number_brick_rows = Integer.parseInt(args[0]);
            this.number_bricks_in_row = Integer.parseInt(args[1]);
        }
    }

    /**
     * Entry point of the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        BrickerGameManager brickerGameManager = new BrickerGameManager("Bouncing ball",
                new Vector2(700, 500));
        brickerGameManager.getBrickRowsInfo(args);
        brickerGameManager.run();
    }
}
