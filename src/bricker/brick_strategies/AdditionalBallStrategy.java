package bricker.brick_strategies;

import bricker.Resources;
import bricker.gameobjects.Ball;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.util.Vector2;

public class AdditionalBallStrategy implements CollisionStrategy{
    public void OnCollision(GameObject other, Collision collision){
        GameObject ball = new Ball(new Vector2(0,0), new Vector2( 20,20), Resources.ballImage,
                Resources.collisionSound);
        ball.setVelocity(new Vector2(0, 100));
    }
}
