package bricker.brick_strategies;

import bricker.Constants;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Camera;
import danogl.util.Vector2;

public class CameraChangeStrategy extends BasicCollisionStrategy implements CollisionStrategy{
    public CameraChangeStrategy(BrickerGameManager gameManager){
        super(gameManager);
    }
    public void onCollision(GameObject thisObj, GameObject otherObj){
        if(otherObj.getTag().equals(Constants.MAIN_BALL_TAG) && gameManager.camera() == null){
            gameManager.focusCameraOnBall();
        }
        super.onCollision(thisObj, otherObj);
    }
}