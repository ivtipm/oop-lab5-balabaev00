package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame obj = new JFrame();
        obj.setBounds(0,0,905,700);
        obj.setBackground(Color.DARK_GRAY);
        obj.setTitle("Змейка");
        obj.setLocationRelativeTo(null);
        obj.setResizable(false);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setVisible(true);

        GamePlay gamePlay = new GamePlay();
        obj.add(gamePlay);
    }
}
