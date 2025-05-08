import java.awt.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.applet.AudioClip;
import java.net.URL;

public class AppleMain {
    private int score = 0;
    private int totalscore = 0;
    private int pointsPerClick = 1;
    private int pointsUpgradeCost = 10;
    private int autoClickerCost = 50;
    private int autoClickers = 0;
    private JLabel scoreLabel;
    private JPanel mainpanel;
    private JLabel totalLabel;
    private AudioClip upgradeSound;
    private AudioClip upgradeSound2;
    private AudioClip lobbySound;
    private AudioClip clickSound;


    public AppleMain() {
        try {
            URL soundUrl = getClass().getResource("upgrade.wav");
            URL soundUrl2 = getClass().getResource("upgrade2.wav");
            URL lobby = getClass().getResource("lobby.wav");
            URL click = getClass().getResource("click.wav");
            if (soundUrl != null || soundUrl2 != null || lobby !=null || click !=null){
                upgradeSound = java.applet.Applet.newAudioClip(soundUrl);
                upgradeSound2 = java.applet.Applet.newAudioClip(soundUrl2);
                lobbySound = java.applet.Applet.newAudioClip(lobby);
                clickSound = java.applet.Applet.newAudioClip(click);
            } else {
                System.out.println("Could not find sound file");
            }
        } catch (Exception e) {
            System.out.println("Could not load sound file: " + e.getMessage());
        }


        createUI();
        startAutoClicker();
        if (lobbySound != null) {
            lobbySound.play();
        }
    }

    public void createUI() {
        mainpanel = new JPanel();
        mainpanel.setLayout(null);
        mainpanel.setBounds(0, 0, 800, 600);
        mainpanel.setBackground(Color.GREEN);

        ImageIcon appleIcon = new ImageIcon("C:\\Users\\vazov\\IdeaProjects\\AppleClicer\\src\\apple.png");

        JButton appleButton = new JButton();
        appleButton.setBackground(Color.BLUE);
        appleButton.setFocusPainted(false);
        appleButton.setBorder(null);
        appleButton.setIcon(appleIcon);

        JPanel applePanel = new JPanel();
        applePanel.setBounds(100, 220, 200, 200);
        applePanel.setBackground(Color.LIGHT_GRAY);
        applePanel.setLayout(null);
        appleButton.setBounds(0, 0, 200, 200);
        applePanel.add(appleButton);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(350, 50, 300, 50);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));

        totalLabel = new JLabel("Total score: 0");
        totalLabel.setBounds(350, 90, 300, 50); 
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainpanel.add(totalLabel);
        
        JButton upgradeButton = new JButton(
                "Upgrade (+" + pointsPerClick + " per click) - " + pointsUpgradeCost + " points");
        upgradeButton.setBounds(350, 150, 300, 50);

        JButton autoClickerButton = new JButton("Buy AutoClicker - " + autoClickerCost + " points");
        autoClickerButton.setBounds(350, 220, 300, 50);

        JTextField inputField = new JTextField();
        inputField.setBounds(350, 350, 150, 30);

        JButton addPointsButton = new JButton("Add Points");
        addPointsButton.setBounds(510, 350, 140, 30);

        mainpanel.add(applePanel);
        mainpanel.add(scoreLabel);
        mainpanel.add(upgradeButton);
        mainpanel.add(autoClickerButton);
        mainpanel.add(inputField);
        mainpanel.add(addPointsButton);

        appleButton.addActionListener(e -> {
            score += pointsPerClick;
            if (clickSound != null) {
                clickSound.play();
            }
            updateScoreLabel();
        });

        upgradeButton.addActionListener(e -> {
            if (score >= pointsUpgradeCost) {
                score -= pointsUpgradeCost;
                pointsPerClick++;
                pointsUpgradeCost *= 2;
                upgradeButton.setText("Upgrade (+" + pointsPerClick + " per click) - " + pointsUpgradeCost + " points");
                updateScoreLabel();
                
                if (upgradeSound != null) {
                    upgradeSound.play();
                }
            } else {
                JOptionPane.showMessageDialog(mainpanel, "Not enough points to upgrade!");
            }
        });

        autoClickerButton.addActionListener(e -> {
            if (score >= autoClickerCost) {
                score -= autoClickerCost;
                autoClickers++;
                autoClickerCost *= 2;
                autoClickerButton.setText("Buy AutoClicker - " + autoClickerCost + " points");
                updateScoreLabel();

                if (upgradeSound2 != null) {
                    upgradeSound2.play();
                }
            } else {
                JOptionPane.showMessageDialog(mainpanel, "Not enough points to buy AutoClicker!");
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
                    JOptionPane.showMessageDialog(mainpanel, "Please enter a positive number!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainpanel, "Invalid input! Please enter a number.");
            }
        });
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
        totalscore += pointsPerClick;
        totalLabel.setText("Total score: " + totalscore);
    }
    
    public JPanel getMainPanel() {
        return mainpanel;
    }
}