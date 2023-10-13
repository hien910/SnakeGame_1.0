package data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Data {
    public static Image imageHead;
    public static Image imageBody;
    public static Image imageWorm;

    public static void loadImage() {
        try {
            imageHead = ImageIO.read(new File("D:/sourcecode/j22-basic java/SnakeGame_1.0/src/image/head.png"));
            imageBody = ImageIO.read(new File("D:/sourcecode/j22-basic java/SnakeGame_1.0/src/image/body.png"));
            imageWorm = ImageIO.read(new File("D:/sourcecode/j22-basic java/SnakeGame_1.0/src/image/worm.png"));
        } catch (Exception e) {
        }
    }
}
