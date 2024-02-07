package bricker.brick_strategies;

import bricker.main.BrickerGameManager;

import java.util.Random;

public class CollisionStrategyBuilder {
    public static CollisionStrategy BuildCollisionStrategy(BrickerGameManager gameManager){
        Random r = new Random();
        double res = r.nextDouble();
        if(res <= 0.5){
            return new BasicCollisionStrategy(gameManager);
        }else if(res < 0.6){
            return new AdditionalBallStrategy(gameManager);
        }else if(res < 0.7){
            return new AdditionalDiskStrategy(gameManager);
        }else if(res < 0.8){
            return new CameraChangeStrategy(gameManager);
        }else if(res < 0.9){
            return new DoubleStrategy(gameManager);
        }else{
            return new DropLifeStrategy(gameManager);
        }
    }
    public static CollisionStrategy BuildCollisionStrategyForDouble(BrickerGameManager gameManager){
        Random r = new Random();
        double res = r.nextDouble();
        if(res < 0.2){
            return new DoubleStrategy(gameManager);
        }else if(res < 0.4){
            return new AdditionalBallStrategy(gameManager);
        }else if(res < 0.6){
            return new AdditionalDiskStrategy(gameManager);
        }else if(res < 0.8){
            return new CameraChangeStrategy(gameManager);
        }else{
            return new DropLifeStrategy(gameManager);
        }
    }
}