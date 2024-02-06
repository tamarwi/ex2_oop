package bricker.brick_strategies;

import bricker.Constants;
import bricker.Resources;
import bricker.gameobjects.UserPaddle;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.util.Vector2;

public class AdditionalDiskStrategy extends BasicCollisionStrategy implements CollisionStrategy{

    public AdditionalDiskStrategy(BrickerGameManager gameManager){
        super(gameManager);
    }
    public void onCollision(GameObject thisObj, GameObject otherObj){
        gameManager.createTemporalPaddle();
        super.onCollision(thisObj, otherObj);
    }
}