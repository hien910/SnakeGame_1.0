package entity;

import data.Data;
import logic.RanSanMoiJava;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class GameScreen extends JPanel implements Runnable {
    static int[][] bg = new int[20][20];
    static int padding = 10;
    static int WIDTH = 400;
    static int HEIGHT = 400;
    public static boolean isPlaying = false;
    public static boolean isGameOver = false;
    static boolean enableTextStartGame = true;
    public ConRan ran;
    Thread thread;
    static int currentLevel= 1;
    static int score=0;

    public GameScreen() {
        ran = new ConRan();
        Data.loadImage();
        thread = new Thread(this);
        thread.start();                                       // run

        bg[15][16] = 2;
    }


    public void run() {
        long t1 = 0;
        while (true) {
            if (System.currentTimeMillis() - t1 > 500) {
                enableTextStartGame = !enableTextStartGame;
                t1 = System.currentTimeMillis();
            }

            if (isPlaying) {
                ran.update();
            }
            repaint();
            try {
                sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void paintBg(Graphics g) {
        g.setColor(Color.black);



        g.fillRect(0, 0, WIDTH + padding * 2+300, HEIGHT + padding * 2);

        int m= 30;
        int n = 30;

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {

//                g.fillRect(i * 20 + 1, j * 20 + 1, 18, 18);
                if (bg[i][j] == 2) {
                    g.drawImage(Data.imageWorm, i * 20 - 6 + padding, j * 20 - 6 + padding, null);
//                    g.fillRect(i * 20 + 1, j * 20 + 1, 18, 18);
//                    g.setColor(Color.GRAY);
                }

                if (bg[i][j]==3){
//                    g.setColor(Color.yellow);
//                    g.fillRect(i * 20 +padding, j * 20 +padding, 18, 18);
//                    g.drawImage(Data.imageWorm, i * 20 - 6 + padding, j * 20 - 6 + padding, null);
                    if (m>i){
                        m=i;
                    }
                    if (n>j){
                        n= j;
                    }
                }

            }
        }
        g.drawImage(Data.imageWormKing, m * 20 - 6 + padding, n * 20 - 6 + padding, null);

    }

    private void veKhung(Graphics g) {
        g.setColor(Color.ORANGE);

        g.drawRect(1, 1, WIDTH + padding * 2 - 2+300, HEIGHT + padding * 2 - 2);
        g.drawRect(1, 1, WIDTH + padding * 2 - 4+300, HEIGHT + padding * 2 - 4);
        g.drawRect(0, 0, WIDTH + padding * 2+300, HEIGHT + padding * 2);

        g.drawRect(1, 1, WIDTH + padding * 2 - 2, HEIGHT + padding * 2 - 2);
        g.drawRect(1, 1, WIDTH + padding * 2 - 4, HEIGHT + padding * 2 - 4);
        g.drawRect(0, 0, WIDTH + padding * 2, HEIGHT + padding * 2);
    }

    public void paint(Graphics g) {
        paintBg(g);
        ran.veRan(g);
        veKhung(g);

        if (!isPlaying) {
            if (enableTextStartGame) {
                g.setColor(Color.WHITE);
                g.setFont(g.getFont().deriveFont(18.0f));
                g.drawString("PRESS SPACE TO PLAY GAME!", 80, 200);
            }
        }
        if (isGameOver ){
            g.setColor(Color.WHITE);
            g.setFont(g.getFont().deriveFont(23.0f));
            g.drawString("GAME OVER!", 150, 60);

        }
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(23.0f));
        g.drawString("LEVEL: "+currentLevel, 466, 100);

        g.setFont(g.getFont().deriveFont(20.0f));
        g.drawString("Score: "+score, 466, 150);

        g.setFont(g.getFont().deriveFont(20.0f));
        g.drawString("High score: ", 466, 200);

        for (int i = 0; i < RanSanMoiJava.users.size(); i++) {
            if (i<5){
                g.drawString((i+1)+". "+ RanSanMoiJava.users.get(i).toString(),480,i*30+230);
            }

        }
//        System.out.println(logic.RanSanMoiJava.users);


//        Image image = null;
//        try {
//            image = ImageIO.read(new File("D:/sourcecode/j22-basic java/SnakeGame_1.0/src/image/head.png"));
//            g.drawImage(image,0,0,null);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
