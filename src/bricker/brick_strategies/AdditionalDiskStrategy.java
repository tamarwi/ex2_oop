package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;

public class AdditionalDiskStrategy extends CollisionStrategyManager implements CollisionStrategy{

    public AdditionalDiskStrategy(BrickerGameManager gameManager){
        super(gameManager);
    }
    public void onCollision(GameObject thisObj, GameObject otherObj){

    }
}