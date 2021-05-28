package com.example.hrms.swingDemo;

import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//@SpringBootApplication
public class SwingApp extends JFrame {

    public SwingApp() {
        initUI();

    }

    private void initUI() {

        var quitButton = new JButton("Quit");

        quitButton.addActionListener((ActionEvent event) -> {
            JOptionPane.showMessageDialog(null, "Game Over");
            System.exit(0);
        });

        createLayout(quitButton);

        setTitle("Quit button");
        setSize(30, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    class GameOverActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Game Over");
            System.exit(0);
        }
    }


    private void createLayout(JComponent... arg) {

        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
    }

    public static void main(String[] args) {

        var ctx = new SpringApplicationBuilder(SwingApp.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {

            var ex = ctx.getBean(SwingApp.class);
            ex.setVisible(true);
        });
    }
}