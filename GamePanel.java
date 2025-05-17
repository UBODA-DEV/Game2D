package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * GamePanel class that serves as the main game canvas.
 * This class handles the game loop, rendering, and player movement.
 * It extends JPanel to provide a drawable surface and implements Runnable
 * to enable the game loop to run on a separate thread.
 */
public class GamePanel extends JPanel implements Runnable{
    
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile - the default size of characters, tiles, etc.
    final int scale = 3; // Scale factor to increase visibility on modern screens
    
    final int tileSize = originalTileSize * scale; // 48x48 tile - the actual size used in the game
    final int maxScreenCol = 16; // Number of tiles horizontally
    final int maxScreenRow = 12; // Number of tiles vertically
    final int screenWidth = tileSize * maxScreenCol; // 768 pixel - total width of the game window
    final int screenHeight = tileSize * maxScreenRow; // 576 pixel - total height of the game window
    
    // FPS (Frames Per Second)
    int FPS = 60; // Controls how many times the screen updates per second
    
    // Create key handler to process keyboard input
    KeyHandler keyH = new KeyHandler();
    // Game thread that will run the game loop
    Thread gameThread;
    
    // Set player's default position and movement speed
    int playerX = 100; // Initial X position of the player
    int playerY = 100; // Initial Y position of the player
    int playerSpeed = 4; // How many pixels the player moves per update
    
    /**
     * Constructor for the GamePanel.
     * Sets up the panel properties and initializes input handling.
     */
    public GamePanel() {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set the panel size
        this.setBackground(Color.black); // Set background color to black
        this.setDoubleBuffered(true); // Enables double buffering to reduce flickering and improve rendering performance
        this.addKeyListener(keyH); // Register the KeyHandler to receive keyboard events
        this.setFocusable(true); // Make the panel focusable to receive key inputs

    }
    
    /**
     * Starts the game thread, initiating the game loop.
     */
    public void startGameThread() {
        
        gameThread = new Thread(this); // Create a new thread with this class as the Runnable
        gameThread.start(); // Start the thread, which calls the run() method
    }
    
    /**
     * The main game loop that runs on a separate thread.
     * This method is called when the thread starts and controls the timing of updates and rendering.
     */
    @Override
    public void run() {
        
        double drawInterval = 1000000000/FPS; // 0.01666... seconds in nanoseconds (time between frames)
        double nextDrawTime = System.nanoTime() + drawInterval; // Time when the next frame should be drawn
        
        while (gameThread != null) { // As long as gameThread exists, continue the game loop
            
            // 1: Update information such as character positions
            update();
            
            // 2: Draw the screen with the updated information
            repaint(); // Calls the paintComponent method
            
            // 3: Pause the game loop to maintain consistent FPS
            try {
                double remainingTime = nextDrawTime - System.nanoTime(); // Calculate time left until next frame
                remainingTime = remainingTime/1000000; // Convert nanoseconds to milliseconds for Thread.sleep()
                
                if (remainingTime < 0) {
                    remainingTime = 0; // If we're behind schedule, don't wait
                }
                
                Thread.sleep((long) remainingTime); // Pause the thread for the remaining time
                
                nextDrawTime += drawInterval; // Set the time for the next frame
                
            } catch (InterruptedException e) {
                // Handle thread interruption
                e.printStackTrace();
            }
        }
    }
        
    /**
     * Updates the game state, including player position based on key inputs.
     * Called once per frame before rendering.
     */
    public void update() {
        
        // Move the player based on which keys are pressed
        if(keyH.upPressed == true) {
            playerY -= playerSpeed; // Move player up
        }
        else if(keyH.downPressed == true) {
            playerY += playerSpeed; // Move player down
        }
        else if(keyH.leftPressed == true) {
            playerX -= playerSpeed; // Move player left
        }
        else if(keyH.rightPressed == true) {
            playerX += playerSpeed; // Move player right
        }
        
    }
    
    /**
     * Draws the game elements on the screen.
     * This method is called automatically when repaint() is invoked.
     * @param g The Graphics context used for drawing
     */
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g); // Call the parent class's paintComponent to ensure proper rendering
        
        Graphics2D g2 = (Graphics2D)g; // Cast to Graphics2D for enhanced drawing capabilities
        
        g2.setColor(Color.white); // Set the drawing color to white
        
        g2.fillRect(playerX, playerY, tileSize, tileSize); // Draw the player as a white rectangle
        
        g2.dispose(); // Dispose of the graphics context to free up system resources
    }
}