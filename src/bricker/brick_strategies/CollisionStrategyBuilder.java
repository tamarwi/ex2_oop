package bricker.brick_strategies;

import bricker.main.BrickerGameManager;

import java.util.Random;

/**
 * A builder class for creating different types of collision strategies based on random thresholds.
 * Strategies include basic collision, additional ball collision, additional disk collision,
 * camera change, double strategy, and drop life strategy.
 *
 * @author tamarwi, roei.nathanzon
 */
public class CollisionStrategyBuilder {
    /**
     * Threshold for when to use basic collision strategy.
     */
    private final static double BASIC_COLLISION_STRATEGY_THRESHOLD = 0.5;
    /**
     * Threshold for when to use additional ball collision strategy.
     */
    private final static double ADDITIONAL_BALL_COLLISION_STRATEGY_THRESHOLD = 0.6;
    /**
     * Threshold for when to use additional disk collision strategy.
     */
    private final static double ADDITIONAL_DISK_COLLISION_STRATEGY_THRESHOLD = 0.7;
    /**
     * Threshold for when to use camera change strategy.
     */
    private final static double CAMERA_CHANGE_STRATEGY = 0.8;
    /**
     * Threshold for when to use double strategy.
     */
    private final static double DOUBLE_STRATEGY_THRESHOLD = 0.9;
    /**
     * Threshold for when to use double strategy after double strategy was picked.
     */
    private final static double DOUBLE_DOUBLE_STRATEGY_THRESHOLD = 0.2;
    /**
     * Threshold for when to use additional ball collision strategy after double strategy was picked.
     */
    private final static double DOUBLE_ADDITIONAL_BALL_COLLISION_STRATEGY_THRESHOLD = 0.4;
    /**
     * Threshold for when to use additional disk collision strategy after double strategy was picked.
     */
    private final static double DOUBLE_ADDITIONAL_DISK_COLLISION_STRATEGY_THRESHOLD = 0.6;
    /**
     * Threshold for when to use camera change strategy after double strategy was picked.
     */
    private final static double DOUBLE_CAMERA_CHANGE_STRATEGY = 0.8;

    /**
     * Default Constructor for CollisionStrategyBuilder.
     */
    CollisionStrategyBuilder(){}
    /**
     * Builds a collision strategy based on random thresholds for various strategies.
     *
     * @param gameManager The game manager to associate with the created strategy.
     * @return A collision strategy instance based on the generated random threshold.
     */
    public static CollisionStrategy BuildCollisionStrategy(BrickerGameManager gameManager) {
        Random r = new Random();
        double res = r.nextDouble();
        if (res <= CollisionStrategyBuilder.BASIC_COLLISION_STRATEGY_THRESHOLD) {
            return new BasicCollisionStrategy(gameManager);
        } else if (res < CollisionStrategyBuilder.ADDITIONAL_BALL_COLLISION_STRATEGY_THRESHOLD) {
            return new AdditionalBallStrategy(gameManager);
        } else if (res < CollisionStrategyBuilder.ADDITIONAL_DISK_COLLISION_STRATEGY_THRESHOLD) {
            return new AdditionalDiskStrategy(gameManager);
        } else if (res < CollisionStrategyBuilder.CAMERA_CHANGE_STRATEGY) {
            return new CameraChangeStrategy(gameManager);
        } else if (res < CollisionStrategyBuilder.DOUBLE_STRATEGY_THRESHOLD) {
            return new DoubleStrategy(gameManager);
        } else {
            return new DropLifeStrategy(gameManager);
        }
    }

    /**
     * Builds a collision strategy for a double event based on random thresholds for various strategies.
     *
     * @param gameManager The game manager to associate with the created strategy.
     * @return A collision strategy instance for a double event based on the generated random threshold.
     */
    public static CollisionStrategy BuildCollisionStrategyForDouble(BrickerGameManager gameManager) {
        Random r = new Random();
        double res = r.nextDouble();
        if (res < CollisionStrategyBuilder.DOUBLE_DOUBLE_STRATEGY_THRESHOLD) {
            return new DoubleStrategy(gameManager);
        } else if (res < CollisionStrategyBuilder.DOUBLE_ADDITIONAL_BALL_COLLISION_STRATEGY_THRESHOLD) {
            return new AdditionalBallStrategy(gameManager);
        } else if (res < CollisionStrategyBuilder.DOUBLE_ADDITIONAL_DISK_COLLISION_STRATEGY_THRESHOLD) {
            return new AdditionalDiskStrategy(gameManager);
        } else if (res < CollisionStrategyBuilder.DOUBLE_CAMERA_CHANGE_STRATEGY) {
            return new CameraChangeStrategy(gameManager);
        } else {
            return new DropLifeStrategy(gameManager);
        }
    }
}
