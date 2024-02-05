package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;

public class DoubleStrategy extends CollisionStrategyManager implements CollisionStrategy{
    private CollisionStrategy strategy1;
    private CollisionStrategy strategy2;
    public DoubleStrategy(BrickerGameManager gameManager){
        super(gameManager);
        this.strategy1 = CollisionStrategyBuilder.BuildCollisionStrategyForDouble(gameManager);
        this.strategy2 = CollisionStrategyBuilder.BuildCollisionStrategyForDouble(gameManager);
    }

    public void onCollision(GameObject thisObj, GameObject otherObj){
        this.strategy1.onCollision(thisObj, otherObj);
        this.strategy2.onCollision(thisObj, otherObj);
    }
}
