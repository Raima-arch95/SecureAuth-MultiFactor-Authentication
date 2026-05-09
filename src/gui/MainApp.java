package gui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MainApp {
    public static void main(String[] args) {
        // Set System Look & Feel but override tooltips/dialogs for dark mode
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } 
        catch (Exception e) { e.printStackTrace(); }

        SwingUtilities.invokeLater(() -> {
            WelcomeScreen ws = new WelcomeScreen();
            ws.setVisible(true);
        });
    }
}
