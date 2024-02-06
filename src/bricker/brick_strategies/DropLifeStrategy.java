package bricker.brick_strategies;

import bricker.gameobjects.Heart;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.util.Vector2;

public class DropLifeStrategy extends BasicCollisionStrategy implements  CollisionStrategy{

    public DropLifeStrategy(BrickerGameManager gameManager){
        super(gameManager);
    }
    public void onCollision(GameObject thisObj, GameObject otherObj){
        Heart heart = new Heart(thisObj.getCenter(), gameManager);
        gameManager.addGameObject(heart);
        System.out.println(thisObj.toString() +"\n"+ otherObj.toString());
        super.onCollision(thisObj, otherObj);
    }
}