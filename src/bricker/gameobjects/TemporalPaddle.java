package bricker.gameobjects;

import bricker.Constants;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Represents a temporal paddle GameObject in the game.
 * The temporal paddle has a limited number of hits before it disappears from the game.
 * Extends UserPaddle.
 *
 * <p>
 * The TemporalPaddle class extends the functionality of the UserPaddle class and adds the feature
 * of disappearing after a certain number of collisions with other GameObjects.
 * </p>
 *
 * @author Your Name
 * @see Paddle
 */
public class TemporalPaddle extends Paddle {

    private int numberOfHitsLeft; // Number of hits left before the paddle disappears
    private BrickerGameManager gameManager; // Reference to the game manager

    /**
     * Constructor for creating a TemporalPaddle object.
     *
     * @param topLeftCorner The top-left corner position of the paddle.
     * @param dimensions The dimensions (size) of the paddle.
     * @param renderable The renderable object for rendering the paddle.
     * @param inputListener The input listener for handling user input.
     * @param gameManager The game manager instance.
     */
    public TemporalPaddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                          UserInputListener inputListener, BrickerGameManager gameManager,
                          Vector2 windowDimensions) {
        // Call superclass constructor
        super(topLeftCorner, dimensions, renderable, inputListener, windowDimensions);
        this.numberOfHitsLeft = Constants.TEMPORAL_PADDLE_NUM_HITS; // Initialize number of hits left
        this.gameManager = gameManager; // Set game manager reference
    }

    /**
     * Handles behavior when the paddle collides with another GameObject.
     * Overrides the onCollisionEnter method from the superclass.
     * Decrements the number of hits left, and if it reaches zero, removes the paddle from the game.
     *
     * @param other The GameObject with which the paddle collided.
     * @param collision Information about the collision.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision); // Call superclass method
        if (--this.numberOfHitsLeft <= 0) { // Decrement hits left and check if zero
            this.gameManager.removeGameObject(this); // Remove paddle from game
            this.gameManager.removeTemporalPaddle(); // Remove temporal paddle from game manager
        }
    }

    /**
     * Determines whether the paddle should collide with another GameObject.
     * Overrides the shouldCollideWith method from the superclass.
     * Paddle should collide with GameObjects other than walls.
     *
     * @param other The GameObject with which collision is being checked.
     * @return True if the paddle should collide with the other GameObject, false otherwise.
     */
    @Override
    public boolean shouldCollideWith(GameObject other){

        return other.getTag().equals(Constants.MAIN_BALL_TAG) ||
                other.getTag().equals(Constants.PUCK_TAG); //paddle should only collide with balls
    }
}
