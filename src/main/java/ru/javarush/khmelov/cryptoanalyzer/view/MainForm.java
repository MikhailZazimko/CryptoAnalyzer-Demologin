package ru.javarush.khmelov.cryptoanalyzer.view;

import ru.javarush.khmelov.cryptoanalyzer.controllers.MainController;
import ru.javarush.khmelov.cryptoanalyzer.entity.Result;
import ru.javarush.khmelov.cryptoanalyzer.entity.ResultCode;
import ru.javarush.khmelov.cryptoanalyzer.exceptions.AppException;
import ru.javarush.khmelov.cryptoanalyzer.util.PathBuilder;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class MainForm extends JFrame {

    private final MainController controller;

    private JPanel panel;
    private JPanel top;
    private JPanel left;
    private JPanel right;
    private JPanel bottom;
    private JPanel content;
    private JTextArea textArea;
    private JButton setText;
    private JButton changeButton;
    private JTextField text;
    private JTextField encrypted;
    private JButton changeButton2;
    private JButton changeButton1;
    private JButton changeButton3;
    private JTextField decypted;
    private JTextField bruteforced;
    private JTextField analyzed;
    private JButton encode;
    private JButton decode;
    private JButton bruteforce;
    private JButton analyze;
    private JButton loadButton4;
    private JButton loadButton3;
    private JButton loadButton1;
    private JButton loadButton2;
    private JButton loadButton;
    private JSlider slider1;
    private JButton button1;
    private JSpinner key;

    public MainForm(MainController controller) {
        this.controller = controller;
        //
        this.add(panel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setStartPosition();
        setDefaultValues(text, encrypted, decypted, bruteforced, analyzed);
        addDialog(setText, text);
        encode.addActionListener(e -> run("encode",
                text.getText(), encrypted.getText(), key.getValue().toString()));
        decode.addActionListener(e -> run("decode",
                encrypted.getText(), decypted.getText(), key.getValue().toString()));
        bruteforce.addActionListener(e -> run("bruteforce",
                encrypted.getText(), bruteforce.getText()));

    }

    private void run(String command, String... parameters) {
        Result result = controller.doAction(command, parameters);
        if (result.resultCode == ResultCode.OK) {
            try {
                String string = Files.readString(PathBuilder.get(result.resultFile));
                textArea.setText(string);
                textArea.setCaretPosition(0);
            } catch (IOException ex) {
                throw new AppException("Don't load ", ex);
            }
        }
    }

    private void setDefaultValues(JTextField... filenames) {
        for (JTextField filename : filenames) {
            String name = filename.getText();
            filename.setText(PathBuilder.get(name).toString());
        }
    }

    private void setStartPosition() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = screenSize.width;
        int y = screenSize.height;
        setBounds(x / 4, y / 4, x / 2, y / 2);
    }

    private void addDialog(JButton changeButton, JTextField jTextField) {
        changeButton.addActionListener(e -> {
            File file = new File(jTextField.getText());
            JFileChooser jFileChooser = new JFileChooser(file);
            jFileChooser.showDialog(this, "OK");
            File selectedFile = jFileChooser.getSelectedFile();
            if (Objects.nonNull(selectedFile)) {
                jTextField.setText(selectedFile.toString());
            }
        });

    }

    public static void main(String[] args) {
        setLookAndFeel("Nimbus");
        MainController controller = new MainController();
        MainForm mainForm = new MainForm(controller);
    }

    private static void setLookAndFeel(String nameLookAndFeel) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (nameLookAndFeel.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
            System.out.println(lookAndFeel);
        }
    }


}
