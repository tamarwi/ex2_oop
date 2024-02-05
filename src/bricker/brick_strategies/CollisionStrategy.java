package bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.Collision;

public interface CollisionStrategy {
    public void OnCollision(GameObject thisObj, GameObject otherObj);
}
