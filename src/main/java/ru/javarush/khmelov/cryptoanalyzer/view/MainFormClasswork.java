package ru.javarush.khmelov.cryptoanalyzer.view;

import javax.swing.*;
import java.awt.*;

public class MainFormClasswork extends JFrame{
    private JPanel mainPanel;
    private JPanel top;
    private JPanel left;
    private JPanel bottom;
    private JPanel right;
    private JPanel center;
    private JButton button2;
    private JButton button1;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JTextField login;
    private JPasswordField password;
    private JButton okButton;
    private JLabel message;

    public MainFormClasswork() throws HeadlessException {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        this.setBounds(width/4,height/4,width/2,height/2);
        this.add(mainPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        okButton.addActionListener(e -> {
            String p = new String(this.password.getPassword());
            String loginText = login.getText();
            if (p.equals("qwerty")){
                message.setText(loginText+" ok");
            } else {
                JOptionPane.showMessageDialog(mainPanel,"incorrect","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
