package bricker.brick_strategies;

import bricker.Constants;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;

/**
 * DoubleStrategy represents a strategy where two collision strategies are applied simultaneously.
 *
 * @author tamarwi, roei.nathanzon
 * @see BasicCollisionStrategy
 * @see CollisionStrategy
 */
public class DoubleStrategy extends BasicCollisionStrategy implements CollisionStrategy {
    private CollisionStrategy strategy1;
    private CollisionStrategy strategy2;

    /**
     * Constructs a DoubleStrategy.
     * Generates 2 random strategies to be used on collision.
     * This strategy will not hold more than 3 total collision strategies
     * (For example: double strategy with another double strategy used internally).
     *
     * @param gameManager The BrickerGameManager instance managing the game.
     */
    public DoubleStrategy(BrickerGameManager gameManager) {
        super(gameManager);
        do {
            this.strategy1 = CollisionStrategyBuilder.BuildCollisionStrategyForDouble(gameManager);
            this.strategy2 = CollisionStrategyBuilder.BuildCollisionStrategyForDouble(gameManager);
        } while (getNumStrategies() > Constants.MAX_STRATEGIES_PER_BRICK);
    }

    /**
     * Retrieves the total number of strategies that will be activated on collision.
     *
     * @return The total number of strategies that will be activated on collision.
     */
    public int getNumStrategies() {
        int numDoubleStrategy = 0;
        if (this.strategy1 instanceof DoubleStrategy) {
            numDoubleStrategy += ((DoubleStrategy) this.strategy1).getNumStrategies();
        } else {
            ++numDoubleStrategy;
        }
        if (this.strategy2 instanceof DoubleStrategy) {
            numDoubleStrategy += ((DoubleStrategy) this.strategy2).getNumStrategies();
        } else {
            ++numDoubleStrategy;
        }
        return numDoubleStrategy;
    }

    /**
     * Handles the collision event between two game objects.
     * Increments the number of bricks in the game and triggers collision handling
     * for both nested strategies.
     *
     * @param thisObj  The current game object involved in the collision.
     * @param otherObj The other game object involved in the collision.
     */
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        gameManager.decreaseNumberOfBricks();
        this.strategy1.onCollision(thisObj, otherObj);
        this.strategy2.onCollision(thisObj, otherObj);
    }
}