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

@SuppressWarnings("unused")
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
    private JButton setEncrypted;
    private JTextField text;
    private JTextField encrypted;
    private JButton setDecrypted;
    private JButton setBruteforce;
    private JButton setAnalized;
    private JTextField decrypted;
    private JTextField bruteforce;
    private JTextField analyzed;
    private JButton runEncode;
    private JButton runDecode;
    private JButton runBruteforce;
    private JButton analyze;
    private JButton loadText;
    private JButton loadEncrytpted;
    private JButton loadDecrypted;
    private JButton loadBruteforce;
    private JButton loadAnaliyzed;
    private JSlider keySlider;
    private JButton swap;
    private JSpinner key;
    private JButton loadDict;
    private JTextField dict;
    private JButton setDict;
    private JTextField ch1;
    private JTextField ch2;

    public MainForm(MainController controller) {
        this.controller = controller;
        this.add(panel);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setStartPosition();
        setDefaultValues(text, encrypted, decrypted, bruteforce, dict, analyzed);

        initOpenFileDialogs();
        initLoadButtons();
        initCommandListeners();
        initKeyListeners();
        initCharacterSwapListeners();

    }

    private void initOpenFileDialogs() {
        addDialog(setText, text);
        addDialog(setEncrypted, encrypted);
        addDialog(setDecrypted, decrypted);
        addDialog(setBruteforce, bruteforce);
        addDialog(setDict, dict);
        addDialog(setAnalized, analyzed);
    }

    private void initLoadButtons() {
        setLoad(loadText, text);
        setLoad(loadEncrytpted, encrypted);
        setLoad(loadDecrypted, decrypted);
        setLoad(loadBruteforce, bruteforce);
        setLoad(loadDict, dict);
        setLoad(loadAnaliyzed, analyzed);
    }

    private void initCommandListeners() {
        runEncode.addActionListener(e -> run("encode",
                text.getText(), encrypted.getText(), key.getValue().toString()));
        runDecode.addActionListener(e -> run("decode",
                encrypted.getText(), decrypted.getText(), key.getValue().toString()));
        runBruteforce.addActionListener(e -> run("bruteforce",
                encrypted.getText(), bruteforce.getText()));
        analyze.addActionListener(e -> run("analyze",
                encrypted.getText(), dict.getText(), analyzed.getText()));
    }

    private void initCharacterSwapListeners() {
        swap.addActionListener(e -> {
            char ch1 = this.ch1.getText().charAt(0);
            char ch2 = this.ch2.getText().charAt(0);

            String text = textArea.getText();
            text = text.replace(ch1, '~');
            text = text.replace(ch2, ch1);
            text = text.replace('~', ch2);
            textArea.setText(text);
            textArea.setCaretPosition(0);
        });
    }

    private void initKeyListeners() {
        keySlider.addChangeListener(e -> {
            int value = keySlider.getValue();
            key.setValue(value);
        });

        key.addChangeListener(e -> {
            int value = Integer.parseInt(key.getValue().toString());
            keySlider.setValue(value);
        });
    }

    private void setLoad(JButton loadButton, JTextField textField) {
        loadButton.addActionListener(e -> {
            loadText(textField.getText());
        });
    }

    private void run(String command, String... parameters) {
        Result result = controller.doAction(command, parameters);
        if (result.resultCode == ResultCode.OK) {
            loadText(result.resultFile);
        } else {
            System.out.println(result);
        }
    }

    private void loadText(String filename) {
        try {
            String string = Files.readString(PathBuilder.get(filename));
            textArea.setText(string);
            textArea.setCaretPosition(0);
        } catch (IOException ex) {
            throw new AppException("Don't load ", ex);
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
            JFileChooser jFileChooser = new JFileChooser(file.getParent());
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

    @SuppressWarnings("SameParameterValue")
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
