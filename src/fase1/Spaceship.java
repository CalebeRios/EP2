package fase1;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Spaceship extends Sprite {
    
    private static final int MAX_SPEED_X = 2;
    private static final int MAX_SPEED_Y = 1;
    
    private int speed_x;
    private int speed_y;
    
   // public static ArrayList<Missile> missil;

    public Spaceship(int x, int y) {
        super(x, y);
        
        initSpaceShip();
    }

    private void initSpaceShip() {
        noThrust();
        
    }
    
    private void noThrust(){
        loadImage("/home/caleberios/Documents/UnB/4Sem/OO/JAVA/EP2/Assets/images/fase1/spaceship.png");
    }
    
    private void thrust(){
        loadImage("/home/caleberios/Documents/UnB/4Sem/OO/JAVA/EP2/Assets/images/fase1/spaceship_thrust.png");
    }

    public void move() {
        
        // Limits the movement of the spaceship to the side edges.
        if((speed_x < 0 && x <= 0) || (speed_x > 0 && x + width >= Game.getWidth())){
            speed_x = 0;
        }
        
        // Moves the spaceship on the horizontal axis
        x += speed_x;
        
        // Limits the movement of the spaceship to the vertical edges.
        if((speed_y < 0 && y <= 0) || (speed_y > 0 && y + height >= Game.getHeight())){
            speed_y = 0;
        }

        // Moves the spaceship on the vertical axis
        y += speed_y;
        
    }
    
    public static Spaceship insert(){
        return (new Spaceship(250, -400));
    }
    
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        // Set speed to move to the left
        if (key == KeyEvent.VK_LEFT) { 
            speed_x = -1 * MAX_SPEED_X;
        }

        // Set speed to move to the right
        if (key == KeyEvent.VK_RIGHT) {
            speed_x = MAX_SPEED_X;
        }
        
        // Set speed to move to up and set thrust effect
        if (key == KeyEvent.VK_UP) {
            speed_y = -1 * MAX_SPEED_Y;
            thrust();
        }
        
        // Set speed to move to down
        if (key == KeyEvent.VK_DOWN) {
            speed_y = MAX_SPEED_Y;
        }
    }
    
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            speed_x = 0;
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            speed_y = 0;
            noThrust();
        }
    }
}
