package bricker;

import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Constants class containing various game-related constants.
 */
public class Constants {

    /**
     * Tag for the main paddle.
     */
    public static final String MAIN_PADDLE_TAG = "MainPaddle";

    /**
     * Tag for the secondary paddle.
     */
    public static final String SECONDARY_PADDLE_TAG = "SecondaryPaddle";

    /**
     * Speed vector for hearts.
     */
    public static final Vector2 HEART_SPEED = new Vector2(0, 100);

    /**
     * Dimensions vector for hearts.
     */
    public static final Vector2 HEART_DIMENSIONS = new Vector2(20, 20);

    /**
     * Speed of the ball.
     */
    public static final float BALL_SPEED = 100;

    /**
     * Width of the paddle.
     */
    public static final int PADDLE_WIDTH = 100;

    /**
     * Height of the paddle.
     */
    public static final int PADDLE_HEIGHT = 15;

    /**
     * Radius of the ball.
     */
    public static final int BALL_RADIUS = 20;

    /**
     * Image path for the ball.
     */
    public static final String BALL_IMAGE_PATH = "assets/ball.png";

    /**
     * Image path for the puck.
     */
    public static final String PUCK_IMAGE_PATH = "assets/mockBall.png";

    /**
     * Image path for the paddle.
     */
    public static final String PADDLE_IMAGE_PATH = "assets/paddle.png";

    /**
     * Image path for the heart.
     */
    public static final String HEART_IMAGE_PATH = "assets/heart.png";

    /**
     * Image path for the brick.
     */
    public static final String BRICK_IMAGE_PATH = "assets/brick.png";

    /**
     * Image path for the background.
     */
    public static final String BACKGROUND_IMAGE_PATH = "assets/DARK_BG2_small.jpeg";

    /**
     * Sound path for collision.
     */
    public static final String COLLISION_SOUND_PATH = "assets/blop_cut_silenced.wav";

    /**
     * Default number of brick rows.
     */
    public static final int DEFAULT_NUMBER_BRICK_ROWS = 7;

    /**
     * Default number of bricks in each row.
     */
    public static final int DEFAULT_NUMBER_BRICKS_IN_ROW = 8;

    /**
     * Number of lives for the player.
     */
    public static final int NUMBER_OF_LIVES = 3;

}
