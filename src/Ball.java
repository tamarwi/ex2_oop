import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Ball extends GameObject {
    public Ball(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);
    }


    void onCollisionEnter(GameObject other, Collision collision){

    }

    int getCollisionCounter(){
        return 0;
    }

}
