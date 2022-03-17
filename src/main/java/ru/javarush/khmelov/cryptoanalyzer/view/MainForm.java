package ru.javarush.khmelov.cryptoanalyzer.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainForm extends JFrame {
    private JPanel panel;
    private JPanel top;
    private JPanel left;
    private JPanel right;
    private JPanel bottom;
    private JPanel content;
    private JButton button1;
    private JTextArea textArea;
    private JButton setText;
    private JButton button4;
    private JTextField text;
    private JTextField encrypted;
    private JButton button2;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JTextField decypted;
    private JTextField textField4;
    private JTextField textField5;

    public MainForm() {
        this.add(panel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = screenSize.width;
        int y = screenSize.height;
        setBounds(x/4,y/4,x/2,y/2);
        setText.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showDialog(this,"OK");
            File selectedFile = jFileChooser.getSelectedFile();
            System.out.println(selectedFile.toString());
        });
    }

    public static void main(String[] args) {
        MainForm mainForm = new MainForm();

    }


}
