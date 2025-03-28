import javax.swing.*;
import java.awt.*;

public class StartPanel {
    private JPanel mainPanel;
    private JFrame window;

    public StartPanel() {
        window = new JFrame("Apple Clicker Game");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 800, 600);
        mainPanel.setBackground(Color.WHITE);

        JLabel welcomeText = new JLabel("Welcome to Apple Clicker Game!");
        welcomeText.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeText.setBounds(100, 50, 600, 50);
        mainPanel.add(welcomeText);

        JButton startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 25));
        startButton.setBounds(300, 250, 200, 50);
        startButton.addActionListener(e -> {
            window.getContentPane().removeAll();
            AppleMain game = new AppleMain();
            window.getContentPane().add(game.getMainPanel());
            window.revalidate();
            window.repaint();
        });
        mainPanel.add(startButton);

        window.add(mainPanel);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartPanel());
    }
}