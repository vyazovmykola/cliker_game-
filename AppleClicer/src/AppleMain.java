import java.awt.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class AppleMain {

    private int score = 0;
    private int pointsPerClick = 1;
    private int pointsUpgradeCost = 10;
    private int autoClickerCost = 50;
    private int autoClickers = 0;

    private JLabel scoreLabel;

    public static void main(String[] args) {
        new AppleMain();
    }

    public AppleMain() {
        createUI();
        startAutoClicker();
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

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(350, 50, 300, 50);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton upgradeButton = new JButton(
                "Upgrade (+" + pointsPerClick + " per click) - " + pointsUpgradeCost + " points");
        upgradeButton.setBounds(350, 150, 300, 50);

        JButton autoClickerButton = new JButton("Buy AutoClicker - " + autoClickerCost + " points");
        autoClickerButton.setBounds(350, 220, 300, 50);

        JTextField inputField = new JTextField();
        inputField.setBounds(350, 350, 150, 30);

        JButton addPointsButton = new JButton("Add Points");
        addPointsButton.setBounds(510, 350, 140, 30);

        window.add(applePanel);
        window.add(scoreLabel);
        window.add(upgradeButton);
        window.add(autoClickerButton);
        window.add(inputField);
        window.add(addPointsButton);

        appleButton.addActionListener(e -> {
            score += pointsPerClick;
            updateScoreLabel();
        });

        upgradeButton.addActionListener(e -> {
            if (score >= pointsUpgradeCost) {
                score -= pointsUpgradeCost;
                pointsPerClick++;
                pointsUpgradeCost *= 2;
                upgradeButton.setText("Upgrade (+" + pointsPerClick + " per click) - " + pointsUpgradeCost + " points");
                updateScoreLabel();
            } else {
                JOptionPane.showMessageDialog(window, "Not enough points to upgrade!");
            }
        });

        autoClickerButton.addActionListener(e -> {
            if (score >= autoClickerCost) {
                score -= autoClickerCost;
                autoClickers++;
                autoClickerCost *= 2;
                autoClickerButton.setText("Buy AutoClicker - " + autoClickerCost + " points");
                updateScoreLabel();
            } else {
                JOptionPane.showMessageDialog(window, "Not enough points to buy AutoClicker!");
            }
        });

        addPointsButton.addActionListener(e -> {
            try {
                int pointsToAdd = Integer.parseInt(inputField.getText());
                if (pointsToAdd > 0) {
                    score += pointsToAdd;
                    inputField.setText(""); 
                    updateScoreLabel();
                } else {
                    JOptionPane.showMessageDialog(window, "Please enter a positive number!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(window, "Invalid input! Please enter a number.");
            }
        });

        window.setVisible(true);
    }

    public void startAutoClicker() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (autoClickers > 0) {
                    score += autoClickers;
                    updateScoreLabel();
                }
            }
        }, 0, 1000);
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + score);
    }
}
