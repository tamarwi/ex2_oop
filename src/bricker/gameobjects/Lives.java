package bricker.gameobjects;

import bricker.Constants;
import bricker.Resources;
import danogl.GameObject;
import danogl.gui.rendering.Renderable;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Vector2;
import java.awt.*;

/**
 * Represents the player's lives in the game.
 * Extends GameObject.
 *
 * <p>
 * The Lives class manages the player's life count, displays it visually with heart GameObjects,
 * and updates the display color based on the remaining lives.
 * </p>
 *
 * @author Your Name
 * @see GameObject
 */
public class Lives extends GameObject {
    private GameObject[] hearts; // Array of heart GameObjects
    private GameObject numericLivesGameObject; // GameObject for displaying numeric lives
    private TextRenderable numericLivesDisplay; // TextRenderable for displaying numeric lives
    private int numberOfLivesLeft; // Number of lives left
    private final static int GREEN_NUMBER_OF_LIVES = 3; // Number of lives for green display
    private final static int YELLOW_NUMBER_OF_LIVES = 2; // Number of lives for yellow display
    private final static int RED_NUMBER_OF_LIVES = 1; // Number of lives for red display
    private final static int X_NUMERIC_DISPLAY = 10; //X coordinate for numeric lives display.
    // Vector difference between heart positions
    private final static Vector2 HEART_DIFF_VECTOR = new Vector2(25, 0);


    /**
     * Constructor for Lives.
     *
     * @param topLeftCorner The top-left corner position of the object, in window coordinates (pixels).
     * @param dimensions    The width and height of the object in window coordinates.
     * @param renderable    The Renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     */
    public Lives(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable); // Call superclass constructor

        // Initialize instance variables
        this.numberOfLivesLeft = Constants.NUMBER_OF_LIVES;
        this.hearts = new GameObject[Constants.MAX_NUMBER_OF_LIVES];

        // Create heart GameObjects
        for (int i = 0; i < Constants.MAX_NUMBER_OF_LIVES; i++) {
            this.hearts[i] = new Image(topLeftCorner.add(Lives.HEART_DIFF_VECTOR.mult(i)),
                    Resources.heartImage);
        }

        // Create and initialize numeric lives display
        this.numericLivesDisplay = new TextRenderable(String.valueOf(this.numberOfLivesLeft));
        updateNumericLivesDisplay();
        this.numericLivesGameObject = new GameObject((new Vector2(Lives.X_NUMERIC_DISPLAY,
                (int) topLeftCorner.y())),
                Constants.HEART_DIMENSIONS,
                numericLivesDisplay);
    }

    // Method to update the color of numeric lives display based on remaining lives
    private void updateNumericLivesDisplay() {
        int numberOfLives = this.numberOfLivesLeft;
        if (numberOfLives >= Lives.GREEN_NUMBER_OF_LIVES) {
            this.numericLivesDisplay.setColor(Color.GREEN);
        } else if (numberOfLives == Lives.YELLOW_NUMBER_OF_LIVES) {
            this.numericLivesDisplay.setColor(Color.YELLOW);
        } else if (numberOfLives == Lives.RED_NUMBER_OF_LIVES) {
            this.numericLivesDisplay.setColor(Color.RED);
        }
        this.numericLivesDisplay.setString(String.valueOf(this.numberOfLivesLeft));
    }

    /**
     * Gets the array of heart GameObjects.
     *
     * @return The array of heart GameObjects.
     */
    public GameObject[] getHearts() {
        return this.hearts;
    }

    /**
     * Gets the GameObject for displaying numeric lives.
     *
     * @return The GameObject for displaying numeric lives.
     */
    public GameObject getNumericDisplay() {
        return this.numericLivesGameObject;
    }

    /**
     * Gets the number of lives left.
     *
     * @return The number of lives left.
     */
    public int getNumberOfLivesLeft() {
        return this.numberOfLivesLeft;
    }

    /**
     * Decreases the player's life count and returns the heart GameObject to remove.
     *
     * @return The heart GameObject to remove, or null if the game ends.
     */
    public GameObject decreaseLife() {
        if ((this.numberOfLivesLeft--) == 1) {
            return null; // End of game
        }
        updateNumericLivesDisplay();
        return this.hearts[this.numberOfLivesLeft]; // More lives left
    }

    /**
     * Increases the player's life count if it's less than the maximum number of lives.
     */
    public void increaseLife() {
        if (this.numberOfLivesLeft < Constants.MAX_NUMBER_OF_LIVES) {
            this.numberOfLivesLeft++;
        }
        updateNumericLivesDisplay();
    }
}
