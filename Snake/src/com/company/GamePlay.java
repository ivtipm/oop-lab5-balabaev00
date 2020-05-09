package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.Random;

public class GamePlay extends JPanel implements KeyListener,ActionListener {

    private int[] randomPositionX = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] randomPositionY = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    private int snakeLengthX[] = new int[750];
    private int snakeLengthY[] = new int[750];
    private int lengthForSnake = 3;
    private int delay=100;
    private int moves = 0;
    private int score = 0;
    private Random random = new Random();
    private int xRandom = random.nextInt(34);
    private int yRandom = random.nextInt(23);
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private boolean stop = false;
    private Timer timer;
    private ImageIcon snakeImage;
    private ImageIcon titleImage;
    private ImageIcon leftMouth;
    private ImageIcon rightMouth;
    private ImageIcon upMouth;
    private ImageIcon downMouth;
    private ImageIcon pointImage;

    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
    }

    public void paint(Graphics g) {
        if(moves == 0) {
            snakeLengthX[2]=50;
            snakeLengthX[1]=75;
            snakeLengthX[0]=100;
            snakeLengthY[2]=100;
            snakeLengthY[1]=100;
            snakeLengthY[0]=100;
        }
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);

        // Title
        titleImage = new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this,g,25,11);

        // Border
        g.setColor(Color.white);
        g.drawRect(24,74,851,577);

        g.setColor(Color.black);
        g.fillRect(25,75,850,575);

        // Scores
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.PLAIN,14));
        g.drawString("Scores: "+score,780,30);

        // Length
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.PLAIN,14));
        g.drawString("Length: "+lengthForSnake,780,50);

        rightMouth = new ImageIcon("rightmouth.png");
        rightMouth.paintIcon(this,g,snakeLengthX[0],snakeLengthY[0]);

        for (int i = 0; i < lengthForSnake; i++) {
            if(i==0 && right) {
                rightMouth = new ImageIcon("rightmouth.png");
                rightMouth.paintIcon(this,g,snakeLengthX[i],snakeLengthY[i]);
            }
            if(i==0 && left) {
                leftMouth = new ImageIcon("leftmouth.png");
                leftMouth.paintIcon(this,g,snakeLengthX[i],snakeLengthY[i]);
            }
            if(i==0 && up) {
                upMouth = new ImageIcon("upmouth.png");
                upMouth.paintIcon(this,g,snakeLengthX[i],snakeLengthY[i]);
            }
            if(i==0 && down) {
                downMouth = new ImageIcon("downmouth.png");
                downMouth.paintIcon(this,g,snakeLengthX[i],snakeLengthY[i]);
            }
            if(i!=0) {
                snakeImage = new ImageIcon("snakeimage.png");
                snakeImage.paintIcon(this,g,snakeLengthX[i],snakeLengthY[i]);
            }
        }
        pointImage = new ImageIcon("enemy.png");
        if((randomPositionX[xRandom] == snakeLengthX[0]) && (randomPositionY[yRandom] == snakeLengthY[0])) {
            score+=5;
            lengthForSnake++;
            xRandom=random.nextInt(34);
            yRandom=random.nextInt(23);
        }
        pointImage.paintIcon(this,g,randomPositionX[xRandom],randomPositionY[yRandom]);
        for (int i = 1; i < lengthForSnake; i++) {
            if(snakeLengthX[0] == snakeLengthX[i] && snakeLengthY[i]==snakeLengthY[0]) {
                right=false;
                left=false;
                down=false;
                up=false;

                g.setColor(Color.white);
                g.setFont(new Font("Ariak",Font.BOLD,50));
                g.drawString("Game Over",330,330);

                g.setFont(new Font("Ariak",Font.BOLD,20));
                g.drawString("Space to Restart",380,360);
                stop=true;
            }
        }
        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                moves = 0;
                score = 0;
                lengthForSnake = 3;
                repaint();
                stop=false;
            }
        if(stop==false) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                moves++;
                right = true;
                if (!left) {
                    right = true;
                } else {
                    right = false;
                    left = true;
                }
                up = false;
                down = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                moves++;
                left = true;
                if (!right) {
                    left = true;
                } else {
                    left = false;
                    right = true;
                }
                up = false;
                down = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                moves++;
                up = true;
                if (!down) {
                    up = true;
                } else {
                    up = false;
                    down = true;
                }
                left = false;
                right = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                moves++;
                down = true;
                if (!up) {
                    down = true;
                } else {
                    down = false;
                    up = true;
                }
                left = false;
                right = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(right) {
            for (int i = lengthForSnake-1; i >=0 ; i--) {
                snakeLengthY[i+1] = snakeLengthY[i];
            }
            for (int i = lengthForSnake; i >=0 ; i--) {
                if(i==0) {
                    snakeLengthX[i] = snakeLengthX[i]+25;
                }
                else {
                    snakeLengthX[i]=snakeLengthX[i-1];
                }
                if(snakeLengthX[i]>850) {
                    snakeLengthX[i]=25;
                }
            }
            repaint();
        }
        if(left) {
            for (int i = lengthForSnake-1; i >=0 ; i--) {
                snakeLengthY[i+1] = snakeLengthY[i];
            }
            for (int i = lengthForSnake; i >=0 ; i--) {
                if(i==0) {
                    snakeLengthX[i] = snakeLengthX[i]-25;
                }
                else {
                    snakeLengthX[i]=snakeLengthX[i-1];
                }
                if(snakeLengthX[i]<25) {
                    snakeLengthX[i]=850;
                }
            }
            repaint();
        }
        if(down) {
            for (int i = lengthForSnake-1; i >=0 ; i--) {
                snakeLengthX[i+1] = snakeLengthX[i];
            }
            for (int i = lengthForSnake; i >=0 ; i--) {
                if(i==0) {
                    snakeLengthY[i] = snakeLengthY[i]+25;
                }
                else {
                    snakeLengthY[i]=snakeLengthY[i-1];
                }
                if(snakeLengthY[i]>625) {
                    snakeLengthY[i]=75;
                }
            }
            repaint();
        }
        if(up) {
            for (int i = lengthForSnake-1; i >=0 ; i--) {
                snakeLengthX[i+1] = snakeLengthX[i];
            }
            for (int i = lengthForSnake; i >=0 ; i--) {
                if(i==0) {
                    snakeLengthY[i] = snakeLengthY[i]-25;
                }
                else {
                    snakeLengthY[i]=snakeLengthY[i-1];
                }
                if(snakeLengthY[i]<75) {
                    snakeLengthY[i]=625;
                }
            }
            repaint();
        }
    }
}
