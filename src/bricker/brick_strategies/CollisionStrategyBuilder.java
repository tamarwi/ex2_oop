package bricker.brick_strategies;

import java.util.Random;

public class CollisionStrategyBuilder {
    public static CollisionStrategy BuildCollisionStrategy(){
        Random r = new Random();
        double res = r.nextDouble();
        if(res <= 0.5){
            return new BasicCollisionStrategy();
        }else if(res < 0.6){
            return new AdditionalBallStrategy();
        }else if(res < 0.7){
            return new AdditionalDiskStrategy();
        }else if(res < 0.8){
            return new CameraChangeStrategy();
        }else if(res < 0.9){
            return new DoubleStrategy();
        }else{
            return new DropLifeStrategy();
        }
    }
    public static CollisionStrategy BuildCollisionStrategyForDouble(){
        Random r = new Random();
        double res = r.nextDouble();
        if(res <= 0.2){
            return new DoubleStrategy();
        }else if(res < 0.4){
            return new AdditionalBallStrategy();
        }else if(res < 0.6){
            return new AdditionalDiskStrategy();
        }else if(res < 0.8){
            return new CameraChangeStrategy();
        }else{
            return new DropLifeStrategy();
        }
    }
}
