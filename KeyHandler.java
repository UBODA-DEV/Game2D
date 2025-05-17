package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KeyHandler class that manages keyboard input for the game.
 * This class implements KeyListener to detect key presses and releases,
 * which are then used to control player movement in the game.
 * It tracks the state of the WASD keys for directional movement.
 */
public class KeyHandler implements KeyListener {
    
    // Boolean flags to track which direction keys are currently pressed
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    /**
     * This method is called whenever a key is pressed down.
     * It sets the corresponding direction flag to true based on which key was pressed.
     * 
     * @param e The KeyEvent object containing information about the key press
     */
    @Override
    public void keyPressed(KeyEvent e) {
        
        // Get the code of the key that was pressed
        int code = e.getKeyCode();
        
        // Check which key was pressed and set the appropriate flag
        if(code == KeyEvent.VK_W) {
            upPressed = true; // W key controls upward movement
        }
        
        if(code == KeyEvent.VK_S) {
            downPressed = true; // S key controls downward movement
        }
        
        if(code == KeyEvent.VK_A) {
            leftPressed = true; // A key controls leftward movement
        }
        
        if(code == KeyEvent.VK_D) {
            rightPressed = true; // D key controls rightward movement
        }
        
    }

    /**
     * This method is called whenever a key is released.
     * It sets the corresponding direction flag to false based on which key was released.
     * 
     * @param e The KeyEvent object containing information about the key release
     */
    @Override
    public void keyReleased(KeyEvent e) {
        
        // Get the code of the key that was released
        int code = e.getKeyCode();
        
        // Check which key was released and reset the appropriate flag
        if(code == KeyEvent.VK_W) {
            upPressed = false; // Stop upward movement when W is released
        }
        
        if(code == KeyEvent.VK_S) {
            downPressed = false; // Stop downward movement when S is released
        }
        
        if(code == KeyEvent.VK_A) {
            leftPressed = false; // Stop leftward movement when A is released
        }
        
        if(code == KeyEvent.VK_D) {
            rightPressed = false; // Stop rightward movement when D is released
        }
                
    }

    /**
     * This method is called whenever a key is typed (pressed and released).
     * Not used in this implementation but required by the KeyListener interface.
     * 
     * @param e The KeyEvent object containing information about the key typed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this implementation
        // This method is typically used for character input rather than game controls
    }
}