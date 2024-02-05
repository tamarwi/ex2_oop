package bricker.brick_strategies;

import bricker.Constants;
import bricker.Resources;
import bricker.gameobjects.Ball;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.util.Vector2;

import java.util.Random;

public class AdditionalBallStrategy extends CollisionStrategyManager implements CollisionStrategy{
    public AdditionalBallStrategy(BrickerGameManager gameManager){
        super(gameManager);
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj){
        Random random = new Random();
        GameObject ball1 = new Ball(thisObj.getCenter(), new Vector2( (float)Constants.BALL_RADIUS*3/4,
                (float)Constants.BALL_RADIUS*3/4), Resources.puckImage,
                Resources.collisionSound);
        GameObject ball2 = new Ball(thisObj.getCenter(), new Vector2( (float)Constants.BALL_RADIUS*3/4,
                (float)Constants.BALL_RADIUS*3/4), Resources.puckImage,
                Resources.collisionSound);

        double angle1 = random.nextDouble() * Math.PI;
        float velocityX1 = (float)Math.cos(angle1) * Constants.BALL_SPEED;
        float velocityY1 = (float)Math.sin(angle1) * Constants.BALL_SPEED;
        ball1.setVelocity(new Vector2(velocityX1, velocityY1));

        double angle2 = random.nextDouble() * Math.PI;
        float velocityX2 = (float)Math.cos(angle2) * Constants.BALL_SPEED;
        float velocityY2 = (float)Math.sin(angle2) * Constants.BALL_SPEED;
        ball2.setVelocity(new Vector2(velocityX2, velocityY2));

        gameManager.addGameObject(ball1);
        gameManager.addGameObject(ball2);
    }
}