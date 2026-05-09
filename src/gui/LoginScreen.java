package gui;

import db.UserDAO;
import db.KeystrokeProfileDAO;
import models.User;
import models.KeystrokeProfile;
import auth.PasswordHasher;
import keystroke.KeystrokeRecorder;
import keystroke.KeystrokeAnalyzer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class LoginScreen extends JFrame {

    private JTextField userField;
    private JPasswordField passField;
    private final Color ACCENT = new Color(59, 130, 246);
    private KeystrokeRecorder recorder = new KeystrokeRecorder();

    public LoginScreen() {
        setTitle("SecureAuth - Login");
        setSize(450, 580);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(ui());
    }

    private JPanel ui() {
        JPanel bg = new JPanel(new GridBagLayout());
        bg.setBackground(new Color(15, 23, 42));

        JPanel card = new JPanel();
        card.setBackground(new Color(30, 41, 59));
        card.setPreferredSize(new Dimension(340, 450));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(30, 25, 30, 25));

        JLabel head = new JLabel("Identity Verification");
        head.setForeground(Color.WHITE);
        head.setFont(new Font("Segoe UI", Font.BOLD, 22));

        userField = createStyledInput();
        passField = createStyledPass();

        // Capture typing dynamics on the password field
        passField.addKeyListener(recorder);

        JButton login = new JButton("Verify & Sign In");
        styleFullBtn(login, ACCENT);
        login.addActionListener(e -> handleLogin());

        card.add(head);
        card.add(Box.createVerticalStrut(30));
        card.add(createLabel("USERNAME"));
        card.add(userField);
        card.add(Box.createVerticalStrut(20));
        card.add(createLabel("PASSWORD"));
        card.add(passField);
        card.add(Box.createVerticalStrut(35));
        card.add(login);

        bg.add(card);
        return bg;
    }

    private void handleLogin() {
        String uText = userField.getText();
        String pText = new String(passField.getPassword());

        UserDAO dao = new UserDAO();
        PasswordHasher hasher = new PasswordHasher();
        User user = dao.getUserByUsername(uText);

        // 1. Password Verification
        if (user == null || !hasher.verifyPassword(pText, user.getPasswordHash())) {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Biometric Verification
        List<Double> currentVector = recorder.getTimingVector();
        KeystrokeProfileDAO kpDao = new KeystrokeProfileDAO();
        KeystrokeProfile profile = kpDao.getProfileByUserId(user.getId());

        // If no biometric profile exists (first login), skip to OTP
        if (profile == null || currentVector.isEmpty()) {
            proceedToOTP(user);
            return;
        }

        List<Double> storedVector = parseVector(profile.getTimingVector());
        KeystrokeAnalyzer analyzer = new KeystrokeAnalyzer();

        // FIX: Ensure vectors are the same length before comparing
        // This prevents crashes if the user pressed 'Enter' or 'Backspace'
        if (currentVector.size() > storedVector.size()) {
            currentVector = currentVector.subList(0, storedVector.size());
        }

        if (analyzer.isUserLegit(storedVector, currentVector)) {
            proceedToOTP(user);
        } else {
            // Log details to console for debugging
            System.out.println("DEBUG: Biometric mismatch. Distance too high.");
            JOptionPane.showMessageDialog(this, "Suspicious typing behavior detected.", "Biometric Mismatch", JOptionPane.WARNING_MESSAGE);
            recorder.getTimingVector().clear(); // Reset recorder for next attempt
        }
    }

    private void proceedToOTP(User user) {
        new OTPScreen(user).setVisible(true);
        dispose();
    }

    private List<Double> parseVector(String data) {
        List<Double> list = new ArrayList<>();
        String clean = data.replace("[", "").replace("]", "");
        String[] parts = clean.split(",");
        for (String p : parts) {
            if (!p.trim().isEmpty()) list.add(Double.parseDouble(p.trim()));
        }
        return list;
    }

    // UI STYLING METHODS
    private JTextField createStyledInput() {
        JTextField f = new JTextField();
        f.setBackground(new Color(51, 65, 85));
        f.setForeground(Color.WHITE);
        f.setCaretColor(Color.WHITE);
        f.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(71, 85, 105)), new EmptyBorder(5, 10, 5, 10)));
        f.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        return f;
    }

    private JPasswordField createStyledPass() {
        JPasswordField f = new JPasswordField();
        f.setBackground(new Color(51, 65, 85));
        f.setForeground(Color.WHITE);
        f.setCaretColor(Color.WHITE);
        f.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(71, 85, 105)), new EmptyBorder(5, 10, 5, 10)));
        f.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        return f;
    }

    private JLabel createLabel(String t) {
        JLabel l = new JLabel(t);
        l.setForeground(new Color(148, 163, 184));
        l.setFont(new Font("Segoe UI", Font.BOLD, 10));
        return l;
    }

    private void styleFullBtn(JButton b, Color c) {
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
