package bricker.brick_strategies;

import bricker.main.BrickerGameManager;

import java.util.Random;

/**
 * A builder class for creating different types of collision strategies based on random thresholds.
 * Strategies include basic collision, additional ball collision, additional disk collision,
 * camera change, double strategy, and drop life strategy.
 *
 * @author Your Name
 * @see CollisionStrategy
 * @see BasicCollisionStrategy
 * @see AdditionalBallStrategy
 * @see AdditionalDiskStrategy
 * @see CameraChangeStrategy
 * @see DoubleStrategy
 * @see DropLifeStrategy
 */
public class CollisionStrategyBuilder {

    // Thresholds for determining which collision strategy to build
    private final static double BASIC_COLLISION_STRATEGY_THRESHOLD = 0.5;
    private final static double ADDITIONAL_BALL_COLLISION_STRATEGY_THRESHOLD = 0.6;
    private final static double ADDITIONAL_DISK_COLLISION_STRATEGY_THRESHOLD = 0.7;
    private final static double CAMERA_CHANGE_STRATEGY = 0.8;
    private final static double DOUBLE_STRATEGY_THRESHOLD = 0.9;
    private final static double DOUBLE_DOUBLE_STRATEGY_THRESHOLD = 0.2;
    private final static double DOUBLE_ADDITIONAL_BALL_COLLISION_STRATEGY_THRESHOLD = 0.4;
    private final static double DOUBLE_ADDITIONAL_DISK_COLLISION_STRATEGY_THRESHOLD = 0.6;
    private final static double DOUBLE_CAMERA_CHANGE_STRATEGY = 0.8;

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
