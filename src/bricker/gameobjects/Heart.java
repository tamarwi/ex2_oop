package bricker.gameobjects;

import bricker.Constants;
import bricker.Resources;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Heart class represents a heart game object.
 * Extends GameObject.
 */
public class Heart extends GameObject {
    private BrickerGameManager gameManager;

    /**
     * Constructor for Heart.
     * @param topLeftCorner The top left corner position of the heart.
     * @param gameManager The BrickerGameManager instance managing the game.
     */
    public Heart(Vector2 topLeftCorner, BrickerGameManager gameManager){
        super(topLeftCorner, Constants.HEART_DIMENSIONS, Resources.heartImage);
        this.setVelocity(Constants.HEART_SPEED);
        this.gameManager = gameManager;
    }

    /**
     * Handles the event when a collision occurs with another game object.
     * @param other The other game object involved in the collision.
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
     * @param other The other game object.
     * @return True if the heart should collide with the other game object, false otherwise.
     */
    @Override
    public boolean shouldCollideWith(GameObject other){
        return other.getTag().equals(Constants.MAIN_PADDLE_TAG);
    }
}
