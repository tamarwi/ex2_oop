package bricker.gameobjects;

import bricker.Constants;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Heart extends GameObject {
    private Sound collisionSound;

    public Heart(Vector2 topLeftCorner, Renderable renderable){

        super(topLeftCorner, Constants.HEART_DIMENSIONS, renderable);
//        this.setVelocity(Constants.HEART_SPEED);
//        this.collisionSound = collisionSound;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
//        this.collisionSound.play();
        // ADD LIFE
    }

    @Override
    public boolean shouldCollideWith(GameObject other){
        return other.getTag().equals(Constants.MAIN_PADDLE_TAG);
    }
}
