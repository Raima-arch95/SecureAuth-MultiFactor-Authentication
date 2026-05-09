package gui;

import db.UserDAO;
import models.User;
import auth.PasswordHasher;
import auth.TOTPGenerator;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterScreen extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JProgressBar strength;

    public RegisterScreen() {
        setTitle("Register");
        setSize(450, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(ui());
    }

    private JPanel ui() {
        JPanel bg = new JPanel(new GridBagLayout());
        bg.setBackground(new Color(15, 23, 42));

        JPanel card = new JPanel();
        card.setBackground(new Color(30, 41, 59));
        card.setPreferredSize(new Dimension(340, 520));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(30, 25, 30, 25));

        JLabel mainHeader = new JLabel("Create Account");
        mainHeader.setForeground(Color.WHITE);
        mainHeader.setFont(new Font("Segoe UI", Font.BOLD, 22));
        mainHeader.setAlignmentX(Component.LEFT_ALIGNMENT);

        userField = createStyledInput();
        passField = createStyledPass();

        strength = new JProgressBar(0, 100);
        strength.setMaximumSize(new Dimension(Integer.MAX_VALUE, 12));
        strength.setBackground(new Color(51, 65, 85));

        passField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { updateStrength(); }
        });

        JButton reg = new JButton("Register Now");
        styleBtn(reg, new Color(59, 130, 246));
        reg.addActionListener(e -> handleRegister());

        // Assembly with Headers
        card.add(mainHeader);
        card.add(Box.createVerticalStrut(25));
        
        card.add(createLabel("USERNAME"));
        card.add(userField);
        card.add(Box.createVerticalStrut(15));
        
        card.add(createLabel("PASSWORD"));
        card.add(passField);
        card.add(Box.createVerticalStrut(10));
        
        card.add(createLabel("PASSWORD STRENGTH")); // New strength header
        card.add(strength);
        
        card.add(Box.createVerticalStrut(35));
        card.add(reg);

        bg.add(card);
        return bg;
    }

    private void updateStrength() {
        String p = new String(passField.getPassword());
        int s = 0;
        if (p.length() >= 8) s += 25;
        if (p.matches(".*[A-Z].*")) s += 25;
        if (p.matches(".*\\d.*")) s += 25;
        if (p.matches(".*[!@#$%^&*()].*")) s += 25;
        strength.setValue(s);
        strength.setForeground(s < 50 ? Color.RED : (s < 100 ? Color.ORANGE : Color.GREEN));
    }

    private void handleRegister() {
        UserDAO dao = new UserDAO();
        TOTPGenerator totp = new TOTPGenerator();
        PasswordHasher hasher = new PasswordHasher();
        String u = userField.getText();
        String p = new String(passField.getPassword());

        if(u.isEmpty() || p.length() < 6) {
            JOptionPane.showMessageDialog(this, "Check inputs!"); return;
        }

        String secret = totp.generateSecretKey();
        User user = new User(u, hasher.hashPassword(p), secret);
        dao.saveUser(user);
        totp.generateQRCode(u, secret);

        // QR Code Popup
        JOptionPane.showMessageDialog(this, new JLabel(new ImageIcon("qrcode.png")), "Scan for 2FA", JOptionPane.PLAIN_MESSAGE);

        new OTPScreen(user).setVisible(true);
        dispose();
    }

    private JTextField createStyledInput() {
        JTextField f = new JTextField();
        f.setBackground(new Color(51, 65, 85));
        f.setForeground(Color.WHITE);
        f.setCaretColor(Color.WHITE);
        f.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        f.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(71, 85, 105)), new EmptyBorder(5, 10, 5, 10)));
        return f;
    }

    private JPasswordField createStyledPass() {
        JPasswordField f = new JPasswordField();
        f.setBackground(new Color(51, 65, 85));
        f.setForeground(Color.WHITE);
        f.setCaretColor(Color.WHITE);
        f.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        f.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(71, 85, 105)), new EmptyBorder(5, 10, 5, 10)));
        return f;
    }

    private JLabel createLabel(String t) {
        JLabel l = new JLabel(t);
        l.setForeground(new Color(148, 163, 184));
        l.setFont(new Font("Segoe UI", Font.BOLD, 11));
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private void styleBtn(JButton b, Color c) {
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
