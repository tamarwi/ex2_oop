package bricker;

import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Constants class contains constant values used throughout the game.
 *
 * @author tamarwi, roei.nathanzon
 */
public class Constants {
    /**
     * Tag for the main paddle object.
     */
    public static final String MAIN_PADDLE_TAG = "MainPaddle";

    /**
     * Tag for wall objects.
     */
    public static final String WALL_TAG = "Wall";

    /**
     * Tag for the main ball object.
     */
    public static final String MAIN_BALL_TAG = "MainBall";

    /**
     * Tag for the puck object.
     */
    public static final String PUCK_TAG = "Puck";

    /**
     * Speed vector for the heart power-up.
     */
    public static final Vector2 HEART_SPEED = new Vector2(0, 100);

    /**
     * Dimensions of the heart power-up.
     */
    public static final Vector2 HEART_DIMENSIONS = new Vector2(20, 20);

    /**
     * Speed of the ball object.
     */
    public static final float BALL_SPEED = 100;

    /**
     * Width of the paddle object.
     */
    public static final int PADDLE_WIDTH = 100;

    /**
     * Height of the paddle object.
     */
    public static final int PADDLE_HEIGHT = 15;

    /**
     * Radius of the ball object.
     */
    public static final int BALL_RADIUS = 20;

    /**
     * File path for the ball image.
     */
    public static final String BALL_IMAGE_PATH = "assets/ball.png";

    /**
     * File path for the puck image.
     */
    public static final String PUCK_IMAGE_PATH = "assets/mockBall.png";

    /**
     * File path for the paddle image.
     */
    public static final String PADDLE_IMAGE_PATH = "assets/paddle.png";

    /**
     * File path for the heart image.
     */
    public static final String HEART_IMAGE_PATH = "assets/heart.png";

    /**
     * File path for the brick image.
     */
    public static final String BRICK_IMAGE_PATH = "assets/brick.png";

    /**
     * File path for the background image.
     */
    public static final String BACKGROUND_IMAGE_PATH = "assets/DARK_BG2_small.jpeg";

    /**
     * File path for the collision sound.
     */
    public static final String COLLISION_SOUND_PATH = "assets/blop_cut_silenced.wav";

    /**
     * Default number of brick rows in the game.
     */
    public static final int DEFAULT_NUMBER_BRICK_ROWS = 7;

    /**
     * Default number of bricks in each row.
     */
    public static final int DEFAULT_NUMBER_BRICKS_IN_ROW = 8;

    /**
     * Number of lives a player starts with.
     */
    public static final int NUMBER_OF_LIVES = 3;

    /**
     * Maximum number of lives a player can have.
     */
    public static final int MAX_NUMBER_OF_LIVES = 4;

    /**
     * Maximum number of collision strategies per brick.
     */
    public static final int MAX_STRATEGIES_PER_BRICK = 3;

    /**
     * Number of hits required for the temporal paddle power-up.
     */
    public static final int TEMPORAL_PADDLE_NUM_HITS = 4;

    /**
     * Number of collisions before resetting the camera focus on the ball.
     */
    public static final int NUM_COLLISIONS_BEFORE_CAMERA_RESET = 4;

    /**
     * Number of lives at which the display color changes to green.
     */
    public static final int GREEN_NUM_OF_LIVES = 3;

    /**
     * Number of lives at which the display color changes to yellow.
     */
    public static final int YELLOW_NUM_OF_LIVES = 2;

    /**
     * Framerate of the game.
     */
    public static final int GAME_FRAMERATE = 80;

    /**
     * Prompt message displayed when the player wins.
     */
    public static final String WIN_PROMPT = "You win! Play again?";

    /**
     * Prompt message displayed when the player loses.
     */
    public static final String LOSE_PROMPT = "You lose! Play again?";

    /**
     * Value representing the middle position.
     */
    public static final float MIDDLE = 0.5F;

    /**
     * Factor by which to widen the camera frame.
     */
    public static final float WIDEN_FRAME_FACTOR = 1.2F;

    /**
     * Height of a brick.
     */
    public static final int BRICK_HEIGHT = 15;

    /**
     * Width of the wall objects.
     */
    public static final int WALL_WIDTH = 5;

    /**
     * Safety distance from the walls for placing bricks.
     */
    public static final int SAFETY_LENGTH_FROM_WALL = Constants.WALL_WIDTH + 1;

    /**
     * Safety distance between bricks.
     */
    public static final int SAFETY_HEIGHT_BETWEEN_BRICKS = Constants.BRICK_HEIGHT + 3;

    /**
     * Default Constructor for Constants.
     */
    Constants(){}
}
