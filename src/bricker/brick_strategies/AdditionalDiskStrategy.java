package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;

/**
 * AdditionalDiskStrategy represents strategy of adding another paddle to
 * the game after collision with brick.
 */
public class AdditionalDiskStrategy extends BasicCollisionStrategy implements CollisionStrategy{

    /**
     * Constructor for AdditionalDiskStrategy.
     *
     * @param gameManager The BrickerGameManager instance managing the game.
     */
    public AdditionalDiskStrategy(BrickerGameManager gameManager){
        super(gameManager);
    }

    /**
     * Handles the collision event between two game objects.
     * Creates a second temporal paddle that the player can use.
     *
     * @param thisObj The current game object involved in the collision.
     * @param otherObj The other game object involved in the collision.
     */
    public void onCollision(GameObject thisObj, GameObject otherObj){
        gameManager.createTemporalPaddle();
        super.onCollision(thisObj, otherObj);
    }
}