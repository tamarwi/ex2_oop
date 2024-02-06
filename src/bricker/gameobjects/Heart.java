package bricker.gameobjects;

import bricker.Constants;
import bricker.Resources;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Heart extends GameObject {
    private BrickerGameManager gameManager;
    public Heart(Vector2 topLeftCorner, BrickerGameManager gameManager){
        super(topLeftCorner, Constants.HEART_DIMENSIONS, Resources.heartImage);
        this.setVelocity(Constants.HEART_SPEED);
        this.gameManager = gameManager;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        this.gameManager.addLife();
        this.gameManager.removeGameObject(this);
    }

    @Override
    public boolean shouldCollideWith(GameObject other){
        return other.getTag().equals(Constants.MAIN_PADDLE_TAG);
    }
}
