package bricker.gameobjects;

import bricker.Constants;
import bricker.Resources;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.rendering.Renderable;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Vector2;

import java.awt.*;

/**
 * Lives class represents the player's lives in the game.
 * Extends GameObject.
 */
public class Lives extends GameObject {
    private GameObject[] livesGameObjects;
    private TextRenderable numericLivesDisplay;
    private int numberOfLivesLeft;
    private final static int GREEN_NUMBER_OF_LIVES = 3;
    private final static int YELLOW_NUMBER_OF_LIVES = 2;
    private final static int RED_NUMBER_OF_LIVES  = 1;
    private final static int NUMERIC_LIVES_INDEX = 0;

    /**
     * Constructor for Lives.
     * @param topLeftCorner The position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions The width and height in window coordinates.
     * @param renderable The Renderable representing the object. Can be null, in which case
     *                   the GameObject will not be rendered.
     */
    public Lives(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);

        this.numberOfLivesLeft = Constants.NUMBER_OF_LIVES;
        this.livesGameObjects = new GameObject[Constants.MAX_NUMBER_OF_LIVES + 1];
        Vector2 heartDiffVector = new Vector2(25, 0);
        for (int i = 1; i < Constants.MAX_NUMBER_OF_LIVES+1; i++) {
            this.livesGameObjects[i] = new Image(topLeftCorner.add(heartDiffVector.mult(i)), Resources.heartImage);
        }

        this.numericLivesDisplay = new TextRenderable(String.valueOf(this.numberOfLivesLeft));
        setColorOfLivesString();
        this.livesGameObjects[Lives.NUMERIC_LIVES_INDEX] = new GameObject((new Vector2(10, (int)topLeftCorner.y())),
                Constants.HEART_DIMENSIONS,
                numericLivesDisplay);
    }

    private void setColorOfLivesString(){
        int numberOfLives = this.numberOfLivesLeft;
        if(numberOfLives >= Lives.GREEN_NUMBER_OF_LIVES)
        {
            this.numericLivesDisplay.setColor(Color.GREEN);
        }
        else if(numberOfLives == Lives.YELLOW_NUMBER_OF_LIVES)
        {
            this.numericLivesDisplay.setColor(Color.YELLOW);
        }
        else if(numberOfLives == Lives.RED_NUMBER_OF_LIVES)
        {
            this.numericLivesDisplay.setColor(Color.RED);
        }

    }
    /**
     * Gets the array of heart GameObjects.
     * @return The array of heart GameObjects.
     */
    public GameObject[] getLivesGameObjects() {
        return this.livesGameObjects;
    }

    /**
     * Gets the number of lives left.
     * @return The number of lives left.
     */
    public int getNumberOfLivesLeft() {
        return this.numberOfLivesLeft;
    }

    public int getNumberOfGameObjects(){
        return this.numberOfLivesLeft + 1;
    }

    /**
     * Decreases the player's life count and returns the heart GameObject to remove.
     * @return The heart GameObject to remove, or null if the game ends.
     */
    public GameObject decreaseLife() {
        if ((this.numberOfLivesLeft--) == 1) {
            return null; // End of game
        }
        this.numericLivesDisplay.setString(String.valueOf(this.numberOfLivesLeft));
        this.setColorOfLivesString();
        return this.livesGameObjects[this.numberOfLivesLeft]; // More lives left
    }

    /**
     * Increases the player's life count if it's less than the maximum number of lives.
     */
    public void increaseLife() {
        if (this.numberOfLivesLeft < Constants.MAX_NUMBER_OF_LIVES) {
            this.numberOfLivesLeft++;
        }
        this.numericLivesDisplay.setString(String.valueOf(this.numberOfLivesLeft));
        setColorOfLivesString();
    }
}
