package logic;

import entity.ConRan;
import entity.GameScreen;
import entity.User;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RanSanMoiJava extends JFrame {
    GameScreen game;
    public static ArrayList<User> users;


    public RanSanMoiJava() {
        setSize(735, 460);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /// tat cua so chuong trinh se tat luon

        users = new ArrayList<>();
        readData();

        game = new GameScreen();
        add(game);
        this.addKeyListener(new Handler());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                UpdateData();
            }
        });

        setVisible(true);
    }

//    public static void main(String[] args) {
//        RanSanMoiJava f = new RanSanMoiJava();
//    }

    private class Handler implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                GameScreen.isPlaying = !GameScreen.isPlaying;
                if (GameScreen.isGameOver) {
                    GameScreen.isGameOver = false;
                    game.ran.resetGame();
                }

            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                game.ran.setVector(ConRan.GO_UP);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                game.ran.setVector(ConRan.GO_DOWN);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                game.ran.setVector(ConRan.GO_LEFT);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                game.ran.setVector(ConRan.GO_RIGHT);
            }


        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    public static void UpdateData() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("data.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < users.size(); i++) {
                User u = new User(users.get(i).getName(),users.get(i).getScore());
                if (users.get(i).getName()== null ){
                    bw.write("Player"+users.size()+" "+ users.get(i).getScore());
                }else {
                    bw.write(u.getName() + " " + u.getScore());
                }
                bw.newLine();
            }
            sortByScore(users);
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void readData() {
        try {
            FileReader fr = new FileReader("data.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] str = line.split(" ");
                users.add(new User(str[0], Integer.parseInt(str[1])));
            }
            br.close();
            sortByScore(users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void sortByScore(ArrayList<User> list){

        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o2.getScore() - o1.getScore() ;
            }
        });
    }
}