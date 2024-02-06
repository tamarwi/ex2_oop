package bricker.gameobjects;

import bricker.Constants;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Image extends GameObject {

    public Image(Vector2 topLeftCorner, Renderable renderable){
        super(topLeftCorner, Constants.HEART_DIMENSIONS, renderable);
    }

    @Override
    public boolean shouldCollideWith(GameObject other){
        return false;
    }
}
