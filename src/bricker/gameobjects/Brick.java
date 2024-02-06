package bricker.gameobjects;

import bricker.Constants;
import bricker.brick_strategies.CollisionStrategy;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Brick extends GameObject {
    private static final int BRICK_HEIGHT  = 15;
    private boolean isDestroyed;
    private CollisionStrategy collisionStrategy;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     */
    public Brick(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                 CollisionStrategy collisionStrategy) {
        super(topLeftCorner, dimensions, renderable);
        this.collisionStrategy = collisionStrategy;
        this.isDestroyed = false;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
            if(!this.isDestroyed){
                super.onCollisionEnter(other, collision);
                this.collisionStrategy.onCollision(this, other);
            }
            this.isDestroyed = true;
    }

    @Override
    public boolean shouldCollideWith(GameObject other){
        return !other.getTag().equals(Constants.WALL_TAG);
    }

}
