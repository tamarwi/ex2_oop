package bricker.gameobjects;

import bricker.Constants;
import bricker.Resources;
import danogl.GameObject;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Lives class represents the player's lives in the game.
 * Extends GameObject.
 */
public class Lives extends GameObject {
    private GameObject[] hearts;
    private int numberOfLivesLeft;
    private final static int GREEN_NUMBER_OF_LIVES = 3;

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
        this.hearts = new GameObject[Constants.MAX_NUMBER_OF_LIVES];
        Vector2 heartDiffVector = new Vector2(25, 0);
        for (int i = 0; i < Constants.MAX_NUMBER_OF_LIVES; i++) {
            this.hearts[i] = new Image(topLeftCorner.add(heartDiffVector.mult(i)), Resources.heartImage);
        }

    }

    /**
     * Gets the array of heart GameObjects.
     * @return The array of heart GameObjects.
     */
    public GameObject[] getHearts() {
        return this.hearts;
    }

    /**
     * Gets the number of lives left.
     * @return The number of lives left.
     */
    public int getNumberOfLivesLeft() {
        return this.numberOfLivesLeft;
    }

    /**
     * Decreases the player's life count and returns the heart GameObject to remove.
     * @return The heart GameObject to remove, or null if the game ends.
     */
    public GameObject decreaseLife() {
        if ((this.numberOfLivesLeft--) == 1) {
            return null; // End of game
        }

        return this.hearts[this.numberOfLivesLeft]; // More lives left
    }

    /**
     * Increases the player's life count if it's less than the maximum number of lives.
     */
    public void increaseLife() {
        if (this.numberOfLivesLeft < Constants.MAX_NUMBER_OF_LIVES) {
            this.numberOfLivesLeft++;
        }
    }
}
