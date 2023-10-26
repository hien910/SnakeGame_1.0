package data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Data {
    public static Image imageHead;
    public static Image imageBody;
    public static Image imageWorm;
    public static Image imageBg;
    public static Image imageWormKing;

    public static void loadImage() {
        try {
            imageHead = ImageIO.read(new File("src/image/head.png"));
            imageBody = ImageIO.read(new File("src/image/body.png"));
            imageWorm = ImageIO.read(new File("src/image/worm.png"));
            imageBg = ImageIO.read(new File("src/image/sanGame.jpg"));
            imageWormKing = ImageIO.read(new File("src/image/wormKing.png"));
        } catch (Exception e) {
        }
    }
}
