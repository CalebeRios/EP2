package fase1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;

public class Map extends JPanel implements ActionListener {

    private final int SPACESHIP_X = 220;
    private final int SPACESHIP_Y = 430;
    private final Timer timer_map;
    
    private final Image background;
    private final Spaceship spaceship;
    private Missile missile;
    
    private boolean shooting = false;

    public Map() {
        
        addKeyListener(new KeyListerner());
        
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon image = new ImageIcon("/home/caleberios/Documentos/EP2_base/images/space.jpg");
        
        this.background = image.getImage();

        spaceship = new Spaceship(SPACESHIP_X, SPACESHIP_Y);
        
        timer_map = new Timer(Game.getDelay(), this);
        timer_map.start();
                            
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(this.background, 0, 0, null);
       
        drawSpaceship(g);
        if(shooting){
            drawMissile(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawSpaceship(Graphics g) {
               
        // Draw spaceship
        g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(), this);
    }
    
    private void drawMissile(Graphics g){
        
        // Draw missile
       // if(!missil.isEmpty()){
            //for(Missile missile : missil){
                g.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
            //}            
        //}
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
        updateSpaceship();
        //if(shooting){
        if(shooting){
            updateMissile();           
        }
            
        //}
        
        repaint();
    }
    
    private void drawMissionAccomplished(Graphics g) {

        String message = "MISSION ACCOMPLISHED";
        Font font = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metric = getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, (Game.getWidth() - metric.stringWidth(message)) / 2, Game.getHeight() / 2);
    }
    
    private void drawGameOver(Graphics g) {

        String message = "Game Over";
        Font font = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metric = getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, (Game.getWidth() - metric.stringWidth(message)) / 2, Game.getHeight() / 2);
    }
    
    private void updateSpaceship() {
        spaceship.move();
    }
    
    private void updateMissile(){
        missile.move();
         //for(Missile missile : missil){
         //   if(!missile.move()){
         //       missil.remove(missile);
         //  }
         //}
    }
  

    private class KeyListerner extends KeyAdapter {
        
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            
            if(key == KeyEvent.VK_SPACE){
                missile = new Missile(spaceship.getX(), spaceship.getY(), 1);
                
                //missil.add(missile);
                
                shooting = true;
            }
            else{
                spaceship.keyPressed(e); 
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            spaceship.keyReleased(e);
        }
    }
}
