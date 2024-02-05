package bricker.gameobjects;

import bricker.Constants;
import bricker.Resources;
import danogl.GameObject;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.util.Arrays;

public class Lives extends GameObject{
    private GameObject[] hearts;
    private int numberOfLivesLeft;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     */
    public Lives(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);

        this.numberOfLivesLeft = Constants.NUMBER_OF_LIVES;
        this.hearts = new GameObject[Constants.NUMBER_OF_LIVES];
        Vector2 heartDiffVector = new Vector2( 25, 0);
        for(int i = 0;i < Constants.NUMBER_OF_LIVES;i++){
            this.hearts[i] = new Heart(topLeftCorner.add(heartDiffVector.mult(i)), Resources.heartImage);
        }
    }

    public GameObject[] getHearts(){
        return this.hearts;
    }


    public GameObject decreaseLife(){
        if((this.numberOfLivesLeft--) == 0){
            return null; //end of game
        }

        return this.hearts[this.numberOfLivesLeft]; //more lives left
    }

}
