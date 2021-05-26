package com.example.hrms.swingDemo2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private JButton btnGet;
    private JTextField tbxInput;
    private JTextArea tbxShow;
    private JPanel panelMain;

    public App() {
        btnGet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tbxShow.setText(tbxInput.getText());
            }
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("AppDemo");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //programı çarpı ile kapatınca running dursun diye
        frame.pack();
        frame.setVisible(true);
    }
}
