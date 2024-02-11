package bricker.brick_strategies;

import bricker.main.Constants;
import bricker.main.Resources;
import bricker.gameobjects.Ball;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.util.Vector2;

import java.util.Random;

/**
 * AdditionalBallStrategy represents strategy of adding 2 pucks to
 * the game after collision with brick.
 *
 * @author tamarwi, roei.nathanzon
 * @see BasicCollisionStrategy
 * @see CollisionStrategy
 */
public class AdditionalBallStrategy extends BasicCollisionStrategy implements CollisionStrategy {
    private final static float PUCK_BALL_RATIO = 0.75f;

    /**
     * Constructor for AdditionalBallStrategy
     *
     * @param gameManager The BrickerGameManager instance managing the game.
     */
    public AdditionalBallStrategy(BrickerGameManager gameManager) {
        super(gameManager);
    }

    /**
     * Handles the collision event between two game objects.
     * Creates 2 pucks with random velocity directions with positive y value.
     *
     * @param thisObj  The current game object involved in the collision.
     * @param otherObj The other game object involved in the collision.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        Random random = new Random();
        Ball ball1 = new Ball(thisObj.getCenter(), new Vector2(
                (float) Constants.BALL_RADIUS * AdditionalBallStrategy.PUCK_BALL_RATIO,
                (float) Constants.BALL_RADIUS * AdditionalBallStrategy.PUCK_BALL_RATIO), Resources.puckImage,
                Resources.collisionSound);
        Ball ball2 = new Ball(thisObj.getCenter(), new Vector2(
                (float) Constants.BALL_RADIUS * AdditionalBallStrategy.PUCK_BALL_RATIO,
                (float) Constants.BALL_RADIUS * AdditionalBallStrategy.PUCK_BALL_RATIO), Resources.puckImage,
                Resources.collisionSound);

        double angle1 = random.nextDouble() * Math.PI;
        float velocityX1 = (float) Math.cos(angle1) * Constants.BALL_SPEED;
        float velocityY1 = (float) Math.sin(angle1) * Constants.BALL_SPEED;
        ball1.setVelocity(new Vector2(velocityX1, velocityY1));
        ball1.setTag(Constants.PUCK_TAG);

        double angle2 = random.nextDouble() * Math.PI;
        float velocityX2 = (float) Math.cos(angle2) * Constants.BALL_SPEED;
        float velocityY2 = (float) Math.sin(angle2) * Constants.BALL_SPEED;
        ball2.setVelocity(new Vector2(velocityX2, velocityY2));
        ball2.setTag(Constants.PUCK_TAG);

        gameManager.addPuck(ball1);
        gameManager.addPuck(ball2);

        super.onCollision(thisObj, otherObj);
    }
}