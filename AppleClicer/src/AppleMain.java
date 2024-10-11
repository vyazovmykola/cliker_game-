import javax.swing.*;
import java.awt.*;

public class AppleMain {
    public static void main(String[] args) {
        new AppleMain();
    }

    public AppleMain() {
        createUI();
    }

    public void createUI() {

        JFrame window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.green);
        window.setLayout(null);

        ImageIcon appleIcon = new ImageIcon("C:\\Users\\vazov\\IdeaProjects\\AppleClicer\\src\\apple.png");

        JButton appleButton = new JButton();
        appleButton.setBackground(Color.blue);
        appleButton.setFocusPainted(false);
        appleButton.setBorder(null);
        appleButton.setIcon(appleIcon);

        JPanel applePanel = new JPanel();
        applePanel.setBounds(100, 220, 200, 200);
        applePanel.setBackground(Color.lightGray);
        applePanel.setLayout(null);

        appleButton.setBounds(0, 0, 200, 200);
        applePanel.add(appleButton);

        window.add(applePanel);
        window.setVisible(true);
    }
}
