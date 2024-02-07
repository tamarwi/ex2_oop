package bricker.gameobjects;

import bricker.Constants;
import bricker.brick_strategies.CollisionStrategy;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Brick class represents a brick game object.
 * Extends GameObject.
 */
public class Brick extends GameObject {
    private static final int BRICK_HEIGHT  = 15;
    private boolean isDestroyed;
    private final CollisionStrategy collisionStrategy;

    /**
     * Constructor for Brick.
     * @param topLeftCorner The position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions The width and height in window coordinates.
     * @param renderable The renderable representing the object. Can be null, in which case
     *                   the GameObject will not be rendered.
     * @param collisionStrategy The CollisionStrategy object to handle collisions with the brick.
     */
    public Brick(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                 CollisionStrategy collisionStrategy) {
        super(topLeftCorner, dimensions, renderable);
        this.collisionStrategy = collisionStrategy;
        this.isDestroyed = false;
    }

    /**
     * Handles the event when a collision occurs with another game object.
     * @param other The other game object involved in the collision.
     * @param collision The Collision object representing the collision information.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
            if(!this.isDestroyed){
                super.onCollisionEnter(other, collision);
                this.collisionStrategy.onCollision(this, other);
            }
            this.isDestroyed = true;
    }

    /**
     * Determines if the brick should collide with another game object.
     * Bricks should collide with all objects except walls.
     * @param other The other game object.
     * @return True if the brick should collide with the other game object, false otherwise.
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        return true;
//        return !other.getTag().equals(Constants.WALL_TAG);
    }
}
