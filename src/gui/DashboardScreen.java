package gui;

import models.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardScreen extends JFrame {
    
    public DashboardScreen(User user) {
        setTitle("SecureAuth Dashboard");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(15, 23, 42));
        
        add(ui(user));
    }

    private JPanel ui(User user) {
        JPanel main = new JPanel();
        main.setBackground(new Color(15, 23, 42));
        main.setLayout(new BorderLayout());
        main.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Top Header
        JLabel welcome = new JLabel("Welcome back, " + user.getUsername() + "!");
        welcome.setForeground(Color.WHITE);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 26));
        main.add(welcome, BorderLayout.NORTH);

        // Center Status Card
        JPanel statusCard = new JPanel();
        statusCard.setBackground(new Color(30, 41, 59));
        statusCard.setLayout(new GridLayout(3, 1, 10, 10));
        statusCard.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel s1 = createStatusLabel("✔ Password Verified", Color.GREEN);
        JLabel s2 = createStatusLabel("✔ Keystroke Biometrics Matched", Color.GREEN);
        JLabel s3 = createStatusLabel("✔ TOTP 2FA Secured", Color.GREEN);

        statusCard.add(s1);
        statusCard.add(s2);
        statusCard.add(s3);
        
        main.add(statusCard, BorderLayout.CENTER);

        // Logout Button
        JButton logout = new JButton("Logout and Lock");
        logout.setBackground(new Color(239, 68, 68)); // Red color
        logout.setForeground(Color.WHITE);
        logout.setFocusPainted(false);
        logout.addActionListener(e -> { new WelcomeScreen().setVisible(true); dispose(); });
        
        main.add(logout, BorderLayout.SOUTH);

        return main;
    }

    private JLabel createStatusLabel(String text, Color color) {
        JLabel l = new JLabel(text);
        l.setForeground(color);
        l.setFont(new Font("Segoe UI", Font.BOLD, 16));
        return l;
    }
}
