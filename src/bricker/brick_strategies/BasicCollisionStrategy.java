package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;

public class BasicCollisionStrategy implements  CollisionStrategy{
    protected BrickerGameManager gameManager;
    public BasicCollisionStrategy(BrickerGameManager gameManager){
        this.gameManager = gameManager;
    }

    public void onCollision(GameObject thisObj, GameObject otherObj){
        gameManager.removeGameObject(thisObj);
        gameManager.setNumberOfBricks(gameManager.getNumberOfBricks()-1);
    }
}