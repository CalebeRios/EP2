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
    private int count = 1;
    private int countE = 1;
    private int countB = 1;
    private int countL = 1;
    
    private final Image background;
    private final Spaceship spaceship;
    private final ArrayList<Missile> missil;
    private final ArrayList<Enemie> enemies;
    private Bonus bonus;
    private Life life;
    
    public Map() {
        
        addKeyListener(new KeyListerner());
        
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon image = new ImageIcon("/home/caleberios/Documents/UnB/4Sem/OO/JAVA/EP2/Assets/images/fase1/space.jpg");
        
        this.background = image.getImage();
        
        missil = new ArrayList();
        enemies = new ArrayList(); 
        spaceship = new Spaceship(SPACESHIP_X, SPACESHIP_Y);
        bonus = Bonus.insert();
        life = Life.insert();
        
        timer_map = new Timer(Game.getDelay(), this);
        timer_map.start();
                            
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(this.background, 0, 0, null);
        drawSpaceship(g);
        drawEnemie(g);
        if(!missil.isEmpty())
            drawMissile(g);
        drawBonus(g);
        drawLife(g);
        
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawSpaceship(Graphics g) {
               
        // Draw spaceship
        g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(), this);
    }
    
    private void drawMissile(Graphics g){
        
        // Draw missiles
        for(Missile missile : missil){
            g.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);            
        }
    }
    
    private void drawEnemie(Graphics g){
    
        // Draw enemies
        for(Enemie enemie : enemies){
            g.drawImage(enemie.getImage(), enemie.getX(), enemie.getY(), this);            
        }
    }
    
    private void drawBonus(Graphics g){
        g.drawImage(bonus.getImage(), bonus.getX(), bonus.getY(), this);
    }
    
    private void drawLife(Graphics g){
        g.drawImage(life.getImage(), life.getX(), life.getY(), this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        updateSpaceship();
        updateEnemie();
        if(!missil.isEmpty())
            updateMissile();
        updateBonus();
        updateLife();

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
        
        for(Missile missile : missil){
            
            if(missile.move()){}
            else
                missil.remove(missile);
        }
    }
    
    private void updateEnemie(){
        int i = 0;
        Enemie ene = Enemie.insert();

        
//      if((countE) % 10 == 0){
            for(Enemie enemie : enemies){
                if(enemie.move()){}
            }
//      }

        if((count % 20) == 0)
            enemies.add(ene);
        
        countE++;
        count++;
    }

    private void updateBonus(){
        if(bonus.move()){}
        else if(bonus.move() == false && (countB % 200) == 0){
            bonus = Bonus.insert();
        }
        
        countB++;
    }
    
    private void updateLife(){
        if(life.move()){}
        else if(life.move() == false && (countL % 500) == 0){
            life = Life.insert();
        }
        
        countL++;
    }
    
    private class KeyListerner extends KeyAdapter {
        
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            
            if(key == KeyEvent.VK_SPACE){
                missil.add(new Missile(spaceship.getX()+5, spaceship.getY(), 1));
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
