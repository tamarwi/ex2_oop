package bricker.brick_strategies;

import bricker.Constants;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Camera;
import danogl.util.Vector2;

/**
 * CameraChangeStrategy represents strategy of changing camera to follow
 * the main ball after collision with brick.
 */
public class CameraChangeStrategy extends BasicCollisionStrategy implements CollisionStrategy{
    /**
     * Constructor for CameraChangeStrategy.
     *
     * @param gameManager The BrickerGameManager instance managing the game.
     */
    public CameraChangeStrategy(BrickerGameManager gameManager){
        super(gameManager);
    }

    /**
     * Handles the collision event between two game objects.
     * Change camera to follow the main ball.
     *
     * @param thisObj The current game object involved in the collision.
     * @param otherObj The other game object involved in the collision.
     */
    public void onCollision(GameObject thisObj, GameObject otherObj){
        if(otherObj.getTag().equals(Constants.MAIN_BALL_TAG) && gameManager.camera() == null){
            gameManager.focusCameraOnBall();
        }
        super.onCollision(thisObj, otherObj);
    }
}