package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class WelcomeScreen extends JFrame {
    private final Color BG = new Color(15, 23, 42);
    private final Color CARD = new Color(30, 41, 59);

    public WelcomeScreen() {
        setTitle("SecureAuth");
        setSize(450, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(ui());
    }

    private JPanel ui() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(BG);

        // Custom Rounded Card
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(CARD);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30));
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setPreferredSize(new Dimension(320, 380));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(40, 20, 40, 20));

        JLabel title = new JLabel("SecureAuth");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sub = new JLabel("Advanced 2FA Protection");
        sub.setForeground(new Color(148, 163, 184));
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton loginBtn = createModernButton("Login", new Color(59, 130, 246));
        JButton regBtn = createModernButton("Create Account", new Color(71, 85, 105));

        loginBtn.addActionListener(e -> { new LoginScreen().setVisible(true); dispose(); });
        regBtn.addActionListener(e -> { new RegisterScreen().setVisible(true); dispose(); });

        card.add(title);
        card.add(Box.createVerticalStrut(5));
        card.add(sub);
        card.add(Box.createVerticalGlue());
        card.add(loginBtn);
        card.add(Box.createVerticalStrut(15));
        card.add(regBtn);
        card.add(Box.createVerticalGlue());

        mainPanel.add(card);
        return mainPanel;
    }

    private JButton createModernButton(String text, Color color) {
        JButton b = new JButton(text);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setMaximumSize(new Dimension(240, 45));
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setBackground(color);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }
}
