package bricker.brick_strategies;

import danogl.GameObject;

/**
 * CollisionStrategy is a strategy interface for action after collision with brick
 * It has one function called onCollision(GameObject thisObj, GameObject otherObj)
 */
public interface CollisionStrategy {
    /**
     * onCollision is called after colliding a brick.
     * @param thisObj The current game object involved in the collision.
     * @param otherObj The other game object involved in the collision.
     */
    public void onCollision(GameObject thisObj, GameObject otherObj);
}