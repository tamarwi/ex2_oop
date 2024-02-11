package bricker;

import danogl.gui.ImageReader;
import danogl.gui.Sound;
import danogl.gui.SoundReader;
import danogl.gui.rendering.Renderable;

/**
 * Resources class manages the game's graphical and audio resources.
 *
 * @author tamarwi, roei.nathanzon
 */
public class Resources {
    /**
     * Renderable object representing the ball image.
     */
    public static Renderable ballImage;

    /**
     * Sound object representing the collision sound.
     */
    public static Sound collisionSound;

    /**
     * Renderable object representing the paddle image.
     */
    public static Renderable paddleImage;

    /**
     * Renderable object representing the heart image.
     */
    public static Renderable heartImage;

    /**
     * Renderable object representing the puck image.
     */
    public static Renderable puckImage;

    /**
     * Renderable object representing the brick image.
     */
    public static Renderable brickImage;

    /**
     * Renderable object representing the background image.
     */
    public static Renderable backgroundImage;

    /**
     * Initializes the game resources.
     *
     * @param imageReader ImageReader object to read image resources.
     * @param soundReader SoundReader object to read sound resources.
     */

    /**
     * Default Constructor for Resource.
     */
    Resources(){}
    public static void initializeResources(ImageReader imageReader, SoundReader soundReader) {
        ballImage = imageReader.readImage(Constants.BALL_IMAGE_PATH, true);
        paddleImage = imageReader.readImage(Constants.PADDLE_IMAGE_PATH, true);
        heartImage = imageReader.readImage(Constants.HEART_IMAGE_PATH, true);
        puckImage = imageReader.readImage(Constants.PUCK_IMAGE_PATH, true);
        brickImage = imageReader.readImage(Constants.BRICK_IMAGE_PATH, false);
        backgroundImage = imageReader.readImage(Constants.BACKGROUND_IMAGE_PATH, false);
        collisionSound = soundReader.readSound(Constants.COLLISION_SOUND_PATH);
    }
}
