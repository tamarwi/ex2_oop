package bricker.brick_strategies;

import bricker.gameobjects.Heart;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.util.Vector2;

/**
 * DropLifeStrategy represents a strategy where a Heart object is dropped upon collision.
 */
public class DropLifeStrategy extends BasicCollisionStrategy implements CollisionStrategy{

    /**
     * Constructs a DropLifeStrategy.
     *
     * @param gameManager The BrickerGameManager instance managing the game.
     */
    public DropLifeStrategy(BrickerGameManager gameManager){
        super(gameManager);
    }

    /**
     * Handles the collision event between two game objects.
     * Creates a Heart object at the center of the collided object and adds it to the game.
     *
     * @param thisObj The current game object involved in the collision.
     * @param otherObj The other game object involved in the collision.
     */
    public void onCollision(GameObject thisObj, GameObject otherObj){
        Heart heart = new Heart(thisObj.getCenter(), gameManager);
        gameManager.addGameObject(heart);
        System.out.println(thisObj.toString() +"\n"+ otherObj.toString());
        super.onCollision(thisObj, otherObj);
    }
}