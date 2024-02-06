package bricker.brick_strategies;

import bricker.Constants;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;

public class DoubleStrategy extends BasicCollisionStrategy implements CollisionStrategy{
    private CollisionStrategy strategy1;
    private CollisionStrategy strategy2;
    private int numDoubleStrategy;
    public DoubleStrategy(BrickerGameManager gameManager){
        super(gameManager);
        do{
            this.strategy1 = CollisionStrategyBuilder.BuildCollisionStrategyForDouble(gameManager);
            this.strategy2 = CollisionStrategyBuilder.BuildCollisionStrategyForDouble(gameManager);
            this.numDoubleStrategy = 0;
            if(this.strategy1 instanceof DoubleStrategy){
                this.numDoubleStrategy += ((DoubleStrategy) this.strategy1).getNumDoubleStrategy() + 1;
            }
            if(this.strategy2 instanceof DoubleStrategy){
                this.numDoubleStrategy += ((DoubleStrategy) this.strategy2).getNumDoubleStrategy() + 1;
            }
        }while(this.numDoubleStrategy > Constants.MAX_STRATEGIES_PER_BRICK);
    }

    public int getNumDoubleStrategy(){
        return this.numDoubleStrategy;
    }

    public void onCollision(GameObject thisObj, GameObject otherObj){
        gameManager.setNumberOfBricks(gameManager.getNumberOfBricks()+1);
        this.strategy1.onCollision(thisObj, otherObj);
        this.strategy2.onCollision(thisObj, otherObj);
    }
}