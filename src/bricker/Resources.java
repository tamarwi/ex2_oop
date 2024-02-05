package bricker;

import danogl.gui.ImageReader;
import danogl.gui.Sound;
import danogl.gui.SoundReader;
import danogl.gui.rendering.Renderable;

public class Resources {
    public static Renderable ballImage;
    public static Sound collisionSound;
    public static Renderable paddleImage;
    public static Renderable heartImage;
    public static Renderable puckImage;
    public static Renderable brickImage;

    public static void initializeResources(ImageReader imageReader, SoundReader soundReader){
        ballImage = imageReader.readImage(Constants.BALL_IMAGE_PATH, true);
        paddleImage = imageReader.readImage(Constants.PADDLE_IMAGE_PATH, true);
        heartImage = imageReader.readImage(Constants.HEART_IMAGE_PATH, true);
        puckImage = imageReader.readImage(Constants.PUCK_IMAGE_PATH, true);
        brickImage = imageReader.readImage(Constants.BRICK_IMAGE_PATH, false);
        collisionSound = soundReader.readSound(Constants.COLLISION_SOUND_PATH);
    }
}
