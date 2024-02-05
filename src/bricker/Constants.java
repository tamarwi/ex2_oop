package bricker;

import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Constants {
    public static String MAIN_PADDLE_TAG = "MainPaddle";
    public static String SECONDARY_PADDLE_TAG = "SecondaryPaddle";
    public static Vector2 HEART_SPEED = new Vector2(0, 100);
    public static Vector2 HEART_DIMENSIONS = new Vector2( 20,20);
    public static String BALL_IMAGE_PATH = "assets/ball.png";
    public static String PADDLE_IMAGE_PATH = "assets/paddle.png";
    public static String HEART_IMAGE_PATH = "assets/heart.png";
    public static String COLLISION_SOUND_PATH = "assets/blop_cut_silenced.wav";
}
