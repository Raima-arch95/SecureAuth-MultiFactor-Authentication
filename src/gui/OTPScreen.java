package gui;

import models.User;
import auth.TOTPGenerator;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OTPScreen extends JFrame {
    public OTPScreen(User user) {
        setTitle("Two-Factor Auth");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(15, 23, 42));

        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(new EmptyBorder(50, 40, 50, 40));

        JLabel l = new JLabel("Verification Code");
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Segoe UI", Font.BOLD, 18));
        l.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField otpField = new JTextField();
        otpField.setHorizontalAlignment(JTextField.CENTER);
        otpField.setFont(new Font("Monospaced", Font.BOLD, 28));
        otpField.setBackground(new Color(30, 41, 59));
        otpField.setForeground(new Color(96, 165, 250));
        otpField.setCaretColor(Color.WHITE);
        otpField.setMaximumSize(new Dimension(200, 60));

        JButton verifyBtn = new JButton("Verify & Log In");
        verifyBtn.setBackground(new Color(59, 130, 246));
        
        // Dark text for better visibility on blue background
        verifyBtn.setForeground(new Color(15, 23, 42)); 
        
        verifyBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        verifyBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        verifyBtn.setMaximumSize(new Dimension(200, 45));
        verifyBtn.setFocusPainted(false);
        verifyBtn.setBorderPainted(false);
        verifyBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        verifyBtn.addActionListener(e -> {
            TOTPGenerator totp = new TOTPGenerator();
            
            if (totp.verifyCode(user.getSecretKey(), otpField.getText())) {
                JOptionPane.showMessageDialog(this, "Success! Access Granted.");
                
                // REDIRECT TO DASHBOARD
                new DashboardScreen(user).setVisible(true);
                
                // Close the OTP window
                dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Invalid OTP. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                otpField.setText(""); // Clear field on failure
                otpField.requestFocus();
            }
        });

        p.add(l);
        p.add(Box.createVerticalStrut(25));
        p.add(otpField);
        p.add(Box.createVerticalStrut(30));
        p.add(verifyBtn);

        add(p);
    }
}
