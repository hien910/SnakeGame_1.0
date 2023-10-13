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
        setSize(750, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /// tat cua so ung dung se tat luon

        users = new ArrayList<>();
        readData();

        game = new GameScreen();
        add(game);
        this.addKeyListener(new handler());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                UpdateData();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        RanSanMoiJava f = new RanSanMoiJava();
    }

    private class handler implements KeyListener {
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
            fw = new FileWriter("D:\\sourcecode\\j22-basic java\\SnakeGame_1.0\\src\\data.txt");
            BufferedWriter bw = new BufferedWriter(fw);


            for (User u : users) {
                bw.write(u.getName()+" "+ u.getScore());
                bw.newLine();
            }


            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sortByScore(users);


    }

    public static void readData() {
        try {
            FileReader fr = new FileReader("D:\\sourcecode\\j22-basic java\\SnakeGame_1.0\\src\\data.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] str = line.split(" ");
                users.add(new User(str[0], Integer.parseInt(str[1])));
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sortByScore(users);

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