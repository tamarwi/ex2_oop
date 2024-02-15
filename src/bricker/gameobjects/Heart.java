package bricker.gameobjects;

import bricker.main.Constants;
import bricker.main.Resources;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.util.Vector2;

/**
 * Heart class represents a falling heart game object.
 *
 * @author tamarwi, roei.nathanzon
 * @see GameObject
 */
public class Heart extends GameObject {
    private BrickerGameManager gameManager;

    /**
     * Speed vector for the heart power-up.
     */
    private static final Vector2 HEART_SPEED = new Vector2(0, 100);

    /**
     * Constructor for Heart.
     *
     * @param topLeftCorner The top left corner position of the heart.
     * @param gameManager   The BrickerGameManager instance managing the game.
     */
    public Heart(Vector2 topLeftCorner, BrickerGameManager gameManager) {
        super(topLeftCorner, Constants.HEART_DIMENSIONS, Resources.heartImage);
        this.setVelocity(HEART_SPEED);
        this.gameManager = gameManager;
        this.setTag(Constants.HEART_TAG);
    }

    /**
     * Handles the event when a collision occurs with another game object.
     *
     * @param other     The other game object involved in the collision.
     * @param collision The Collision object representing the collision information.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        this.gameManager.addLife();
        this.gameManager.removeGameObject(this);
    }

    /**
     * Determines if the heart should collide with another game object.
     * Hearts should only collide with the main paddle.
     *
     * @param other The other game object.
     * @return True if the heart should collide with the other game object, false otherwise.
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        return other.getTag().equals(Constants.MAIN_PADDLE_TAG);
    }

    /**
     * Checks whether the heart is out of bound and removes it from the game if it is
     */
    public void checkOutOfBounds(){
        if(this.getCenter().y() > this.gameManager.getWindowDimensions().y()) {
            this.gameManager.removeGameObject(this);
        }
    }
}
