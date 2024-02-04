import danogl.GameManager;
import danogl.util.Vector2;

import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        new GameManager("Bouncing ball",
                new Vector2(700, 500));
        System.out.printf("hello");
    }
}