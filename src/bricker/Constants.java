package bricker;

import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Constants {
    public static final String MAIN_PADDLE_TAG = "MainPaddle";
    public static final String SECONDARY_PADDLE_TAG = "SecondaryPaddle";
    public static final Vector2 HEART_SPEED = new Vector2(0, 100);
    public static final Vector2 HEART_DIMENSIONS = new Vector2( 20,20);
    public static final float BALL_SPEED = 100;
    public static final int PADDLE_WIDTH =100;
    public static final int PADDLE_HEIGHT = 15;
    public static final int BALL_RADIUS = 20;
    public static final String BALL_IMAGE_PATH = "assets/ball.png";
    public static final String PUCK_IMAGE_PATH = "assets/mockBall.png";
    public static final String PADDLE_IMAGE_PATH = "assets/paddle.png";
    public static final String HEART_IMAGE_PATH = "assets/heart.png";
    public static final String BRICK_IMAGE_PATH = "assets/brick.png";
    public static final String COLLISION_SOUND_PATH = "assets/blop_cut_silenced.wav";
    public static final int DEFAULT_NUMBER_BRICK_ROWS = 7;
    public static final int DEFAULT_NUMBER_BRICKS_IN_ROW = 8;
    public static final int NUMBER_OF_LIVES = 3;

}
