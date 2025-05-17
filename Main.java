package Main;

import javax.swing.JFrame;

/**
 * Main class for the 2D Adventure game.
 * This class initializes the game window and sets up the game panel.
 * It serves as the entry point for the application, creating the main window
 * and starting the game thread.
 */
public class Main {

    public static void main(String[] args) {
        
        // Create a new window for the game
        JFrame window = new JFrame();
        // Set the operation that will happen when closing the window (terminate the program)
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Disable window resizing to maintain consistent game dimensions
        window.setResizable(false);
        // Set the title of the game window
        window.setTitle("2D Adventure");
        
        // Create a new GamePanel instance which will contain the game content
        GamePanel gamePanel = new GamePanel();
        // Add the game panel to the window
        window.add(gamePanel);
        
        // Pack the window, which sizes the frame appropriately for its subcomponents (GamePanel)
        // This ensures the window is sized according to the preferred size of the GamePanel
        window.pack();
        
        // Center the window on the screen
        window.setLocationRelativeTo(null);
        // Make the window visible to the user
        window.setVisible(true);
        
        // Start the game loop by initiating the game thread in the GamePanel
        gamePanel.startGameThread();
    }
}
