package bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.Collision;

public class BasicCollisionStrategy implements  CollisionStrategy{
    public BasicCollisionStrategy() {
    }

    public void onCollision(GameObject thisObj, GameObject otherObj){
        System.out.println("collision with brick detected");
    }
}
