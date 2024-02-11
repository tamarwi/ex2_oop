package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Represents a ball GameObject in the game.
 * The ball can collide with other GameObjects and change its direction upon collision.
 * Keeps track of the number of collisions it has encountered.
 * Extends the GameObject class.
 *
 * @author tamarwi, roei.nathanzon
 * @see GameObject
 */
public class Ball extends GameObject {

    private Sound collisionSound; // Sound played upon collision
    private int collisionCounter; // Number of collisions encountered

    /**
     * Constructor for creating a Ball object.
     *
     * @param topLeftCorner  The top-left corner position of the ball.
     * @param dimensions     The dimensions (size) of the ball.
     * @param renderable     The renderable object for rendering the ball.
     * @param collisionSound The sound to be played upon collision.
     */
    public Ball(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                Sound collisionSound) {
        super(topLeftCorner, dimensions, renderable); // Call superclass constructor
        this.collisionCounter = 0; // Initialize collision counter
        this.collisionSound = collisionSound; // Set collision sound
    }

    /**
     * Handles behavior when the ball collides with another GameObject.
     * Overrides the onCollisionEnter method from the superclass.
     * Changes the ball's velocity based on the collision normal.
     * Increments the collision counter.
     * Plays the collision sound.
     *
     * @param other     The GameObject with which the ball collided.
     * @param collision Information about the collision.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision); // Call superclass method
        Vector2 newVel = getVelocity().flipped(collision.getNormal()); // Reflect velocity
        setVelocity(newVel); // Set new velocity
        this.collisionCounter++; // Increment collision counter
        this.collisionSound.play(); // Play collision sound
    }

    /**
     * Retrieves the number of collisions encountered by the ball.
     *
     * @return The number of collisions encountered.
     */
    public int getCollisionCounter() {
        return this.collisionCounter; // Return collision counter
    }
}
