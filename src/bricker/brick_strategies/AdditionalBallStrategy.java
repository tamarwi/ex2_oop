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
    public void onCollision(GameObject thisObj, GameObject otherObj){
        Random random = new Random();
        double angle = random.nextDouble() * Math.PI;
        float velocityX = (float)Math.cos(angle) * Constants.BALL_SPEED;
        float velocityY = (float)Math.sin(angle) * Constants.BALL_SPEED;
        //TODO: fix size of ball
        GameObject ball = new Ball(thisObj.getCenter(), new Vector2( (float)Constants.BALL_RADIUS*3/4,
                (float)Constants.BALL_RADIUS*3/4), Resources.puckImage,
                Resources.collisionSound);
        ball.setVelocity(new Vector2(velocityX, velocityY));
    }
}
