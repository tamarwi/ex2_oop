package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;

public class BasicCollisionStrategy extends CollisionStrategyManager implements  CollisionStrategy{
    public BasicCollisionStrategy(BrickerGameManager gameManager){
        super(gameManager);
    }
    public void onCollision(GameObject thisObj, GameObject otherObj){
        System.out.println("collision with brick detected");
    }
}
