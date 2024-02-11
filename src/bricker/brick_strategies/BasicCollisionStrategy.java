package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;

/**
 * BasicCollisionStrategy class implements a basic collision strategy for bricks.
 * Implements CollisionStrategy interface.
 *
 * @author tamarwi, roei.nathanzon
 * @see CollisionStrategy
 */
public class BasicCollisionStrategy implements CollisionStrategy {
    /**
     * An instance of the game manager to activate strategy on.
     */
    protected BrickerGameManager gameManager;

    /**
     * Constructor for BasicCollisionStrategy.
     *
     * @param gameManager The BrickerGameManager instance managing the game.
     */
    public BasicCollisionStrategy(BrickerGameManager gameManager) {

        this.gameManager = gameManager;
    }

    /**
     * Handles the collision event between two game objects.
     * Removes the current object and decreases the number of bricks in the game.
     *
     * @param thisObj  The current game object involved in the collision.
     * @param otherObj The other game object involved in the collision.
     */
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        gameManager.removeGameObject(thisObj);
        gameManager.decreaseNumberOfBricks();
        System.out.println("one down");
    }
}
