package ru.lessons.lesson13.gui;


import ru.lessons.lesson13.logics.Easy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Класс реализующий запуск Игры
 */
public class Main {
    private static final JPanel controlPanel = new JPanel();
    private static final GuiBoard board = new GuiBoard();

    public static void main(String[] arg){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final JFrame frame = new JFrame();
                frame.setTitle("Sapper");
                frame.setLayout(new BorderLayout());
                frame.setSize(508,600);
                frame.add(board, BorderLayout.CENTER);
                board.setBorder(new EmptyBorder(10,10,10,10));
                frame.add(controlPanel, BorderLayout.PAGE_END);
                controlPanel.setLayout(new FlowLayout());
                final GuiAction action = new GuiAction(
                        new GuiGeneratorBoard(),
                        board,
                        new Easy()
                );
                final JButton generate = new JButton("start");
                generate.addActionListener(action);
                controlPanel.add(generate);
                centre(frame);
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        closePerform(frame);
                    }
                });
                frame.setVisible(true);
            }

        });
    }

    private static void closePerform(JFrame frame) {
        frame.setVisible(false);
        frame.dispose();
        System.exit(0);
    }

    private static void centre(Window w) {
        Dimension us = w.getSize();
        Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
        int newX = (them.width - us.width)/2;
        int newY = (them.height - us.height)/2;
        w.setLocation(newX, newY);

    }

}
