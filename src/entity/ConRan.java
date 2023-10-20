package entity;

import data.Data;
import logic.RanSanMoiJava;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class ConRan {


    int doDai = 3;
    int[] x;
    int[] y;
    public static int GO_UP = 1;
    public static int GO_DOWN = -1;
    public static int GO_LEFT = 2;
    public static int GO_RIGHT = -2;
    int vector = ConRan.GO_RIGHT;
    long t1 = 0;
    long t2 ;
    double speed = 500;
    int maxLength = 7;
    boolean updateChangeVector = false;
    int soMoi = 0;                  //
    int xuatMoiTo = 5;


    public ConRan() {
        x = new int[400];
        y = new int[400];

        x[0] = 8;
        y[0] = 6;

        x[1] = 8;
        y[1] = 7;

        x[2] = 8;
        y[2] = 8;

    }

    public void resetGame() {
        doDai = 3;
        x = new int[400];
        y = new int[400];

        x[0] = 8;
        y[0] = 6;

        x[1] = 8;
        y[1] = 7;

        x[2] = 8;
        y[2] = 8;

        vector = ConRan.GO_RIGHT;
    }

    public void setVector(int v) {
        if (vector != -v && updateChangeVector) {
            vector = v;
            updateChangeVector = false;
        }
    }

    public Point layToaDoMoi() {
        Random r = new Random();
        int x;
        int y;
        do {
            x = r.nextInt(19);
            y = r.nextInt(19);
        } while (toaTrungVoiRan(x, y));

        return new Point(x, y);

    }

    public boolean checkPoint(Point p) {
        if (p.x < 19 && p.y < 19) {
            return !toaTrungVoiRan(p.x, p.y) &&
                    !toaTrungVoiRan(p.x + 1, p.y) &&
                    !toaTrungVoiRan(p.x, p.y + 1) &&
                    !toaTrungVoiRan(p.x + 1, p.y + 1);
        }
        return false;
    }

    public boolean toaTrungVoiRan(int a, int b) {
        for (int i = 0; i < doDai; i++) {
            if (x[i] == a && y[i] == b) {
                return true;
            }
        }
        return false;
    }

    public double getCurrentSpeed() {
        double speed = 500;
        for (int i = 0; i < GameScreen.currentLevel; i++) {
            speed = speed * (0.8);
        }
        return speed;
    }

    public void update() {

        if (doDai == maxLength) {
            GameScreen.isPlaying = false;
            resetGame();

            GameScreen.currentLevel++;
            maxLength += 3;
            speed = getCurrentSpeed();
            System.out.println(speed);
        }

        for (int i = 1; i < doDai; i++) {
            if (x[0] == x[i] && y[0] == y[i]) {

                String name = JOptionPane.showInputDialog("Tên người chơi: ");
                if (name == null) {
                    name = "Player" + RanSanMoiJava.users.size();
                }
                RanSanMoiJava.users.add((new User(name, GameScreen.score)));

                RanSanMoiJava.UpdateData();
//                logic.RanSanMoiJava.readData();


                GameScreen.isPlaying = false;
                GameScreen.isGameOver = true;
                System.out.println("GAME OVER");


                GameScreen.score = 0;
                GameScreen.currentLevel = 1;
                speed = 500;
                maxLength = 7;
                soMoi = 0;                  //
                xuatMoiTo = 5;
            }
        }


        if (System.currentTimeMillis() - t1 > speed) {
            updateChangeVector = true;
            Point point = layToaDoMoi();

            if (GameScreen.bg[x[0]][y[0]] == 2) {
                doDai++;
                soMoi++;

                if (soMoi % xuatMoiTo == 0) {
                    xuatMoiTo += 3;
                    soMoi = 0;

                    while (!checkPoint(point)) {
                        point = layToaDoMoi();
                    }
                    GameScreen.bg[point.x][point.y] = 3;
                    GameScreen.bg[point.x + 1][point.y] = 3;
                    GameScreen.bg[point.x][point.y + 1] = 3;
                    GameScreen.bg[point.x + 1][point.y + 1] = 3;
                    t2 = 0;
                }

                GameScreen.bg[x[0]][y[0]] = 0;
                GameScreen.bg[layToaDoMoi().x][layToaDoMoi().y] = 2;
                GameScreen.score = GameScreen.score + (100 * GameScreen.currentLevel);

            } else if (GameScreen.bg[x[0]][y[0]] == 3) {
                for (int i = x[0] - 1; i < x[0] + 2; i++) {
                    for (int j = y[0] - 1; j < y[0] + 2; j++) {
                        if (i >= 0 && i <= 19 && j >= 0 && j <= 19 && GameScreen.bg[i][j] == 3) {
                            GameScreen.bg[i][j] = 0;
                        }
                    }
                }
                GameScreen.bg[x[0]][y[0]] = 0;
                GameScreen.bg[point.x][point.y] = 0;
                GameScreen.bg[point.x + 1][point.y] = 0;
                GameScreen.bg[point.x][point.y + 1] = 0;
                GameScreen.bg[point.x + 1][point.y + 1] = 0;

                GameScreen.score = GameScreen.score + (1000 * GameScreen.currentLevel);
            }



            if (t2==20) {
                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 20; j++) {
                        if (GameScreen.bg[i][j]==3){
                            GameScreen.bg[i][j]=0;
                        }
                    }
                }
                t2=0;
            }



            for (int i = doDai - 1; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }
            if (vector == ConRan.GO_UP) y[0]--;
            if (vector == ConRan.GO_DOWN) y[0]++;
            if (vector == ConRan.GO_LEFT) x[0]--;
            if (vector == ConRan.GO_RIGHT) x[0]++;

            if (x[0] < 0) x[0] = 19;
            if (x[0] > 19) x[0] = 0;
            if (y[0] < 0) y[0] = 19;
            if (y[0] > 19) y[0] = 0;


            t1 = System.currentTimeMillis();
            t2++;

        }

    }

    public void veRan(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < doDai; i++) {
            g.fillRect(x[i] * 20 + 1 + GameScreen.padding, y[i] * 20 + 1 + GameScreen.padding, 18, 18);

            g.drawImage(Data.imageBody, x[i] * 20 - 3 + GameScreen.padding, y[i] * 20 - 3 + GameScreen.padding, null);
            g.drawImage(Data.imageHead, x[0] * 20 - 8 + GameScreen.padding, y[0] * 20 - 6 + GameScreen.padding, null);
        }
    }
}
