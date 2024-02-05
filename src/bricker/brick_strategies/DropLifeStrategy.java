package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;

public class DropLifeStrategy extends CollisionStrategyManager implements  CollisionStrategy{

    public DropLifeStrategy(BrickerGameManager gameManager){
        super(gameManager);
    }
    public void onCollision(GameObject thisObj, GameObject otherObj){

    }
}