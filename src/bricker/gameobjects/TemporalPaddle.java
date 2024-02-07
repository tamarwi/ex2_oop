package bricker.gameobjects;

import bricker.Constants;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class TemporalPaddle extends Paddle {
    private int numberOfHitsLeft;
    private BrickerGameManager gameManager;
    public TemporalPaddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                          UserInputListener inputListener, BrickerGameManager gameManager) {
        super(topLeftCorner, dimensions, renderable, inputListener);
        this.numberOfHitsLeft = Constants.TEMPORAL_PADDLE_NUM_HITS;
        this.gameManager = gameManager;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if(--this.numberOfHitsLeft <= 0){
            this.gameManager.removeGameObject(this);
            this.gameManager.removeTemporalPaddle();
        }
    }

    @Override
    public boolean shouldCollideWith(GameObject other){
        return !other.getTag().equals(Constants.WALL_TAG);
    }
}
