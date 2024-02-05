package bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.Collision;

public class DoubleStrategy implements CollisionStrategy{
    private CollisionStrategy strategy1;
    private CollisionStrategy strategy2;
    public DoubleStrategy(){
        this.strategy1 = CollisionStrategyBuilder.BuildCollisionStrategyForDouble();
        this.strategy2 = CollisionStrategyBuilder.BuildCollisionStrategyForDouble();
    }
    public void OnCollision(GameObject other, Collision collision){
        this.strategy1.OnCollision(other, collision);
        this.strategy2.OnCollision(other, collision);
    }
}
