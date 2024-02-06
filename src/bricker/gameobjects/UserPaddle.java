package bricker.gameobjects;

import danogl.GameObject;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;

/**
 * UserPaddle class represents the paddle controlled by the user.
 * Extends GameObject.
 */
public class UserPaddle extends GameObject {

    private static final float MOVEMENT_SPEED = 300;
    private UserInputListener inputListener;

    /**
     * Constructor for UserPaddle.
     * @param topLeftCorner The position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions The width and height in window coordinates.
     * @param renderable The renderable representing the object. Can be null, in which case
     *                   the GameObject will not be rendered.
     * @param inputListener The UserInputListener object to handle user input.
     */
    public UserPaddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                      UserInputListener inputListener) {
        super(topLeftCorner, dimensions, renderable);
        this.inputListener = inputListener;
    }

    /**
     * Updates the paddle's position based on user input.
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Vector2 movementDirection = Vector2.ZERO;
        if((getTopLeftCorner().x() < 0)){ //100 is paddle size
            setTopLeftCorner(new Vector2(0, getTopLeftCorner().y()));
        }
        if(getTopLeftCorner().x() > 600){
            setTopLeftCorner(new Vector2(600, getTopLeftCorner().y()));
        }

        if(inputListener.isKeyPressed(KeyEvent.VK_LEFT)){
            movementDirection = movementDirection.add(Vector2.LEFT);
        }
        if(inputListener.isKeyPressed(KeyEvent.VK_RIGHT)){
            movementDirection = movementDirection.add(Vector2.RIGHT);
        }
        setVelocity(movementDirection.mult(MOVEMENT_SPEED));
    }
}
