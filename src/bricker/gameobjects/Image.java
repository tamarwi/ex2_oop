package bricker.gameobjects;

import bricker.Constants;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Represents an image GameObject in the game.
 * The image does not participate in collisions with other GameObjects.
 * Extends the GameObject class.
 *
 * @author Your Name
 * @see GameObject
 */
public class Image extends GameObject {

    /**
     * Constructor for creating an Image object.
     *
     * @param topLeftCorner The top-left corner position of the image.
     * @param renderable The renderable object for rendering the image.
     */
    public Image(Vector2 topLeftCorner, Renderable renderable){
        // Call superclass constructor with default dimensions
        super(topLeftCorner, Constants.HEART_DIMENSIONS, renderable);
    }

    /**
     * Determines whether the image should collide with another GameObject.
     * Overrides the shouldCollideWith method from the superclass.
     * Images do not participate in collisions, so always returns false.
     *
     * @param other The GameObject with which collision is being checked.
     * @return Always returns false, indicating no collision should occur.
     */
    @Override
    public boolean shouldCollideWith(GameObject other){
        return false; // Images do not collide with any other GameObject
    }
}
