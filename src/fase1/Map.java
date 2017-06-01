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
import java.util.Iterator;

public class Map extends JPanel implements ActionListener {

    private final int SPACESHIP_X = 220;
    private final int SPACESHIP_Y = 430;
    private final Timer timer_map;
    private int count = 1;
    private int countE = 1;
    private int countB = 1;
    private int countL = 1;
    
    private final Player player;
    private final Image background;
    private final Spaceship spaceship;
    private final ArrayList<Missile> missiles;
    private final ArrayList<Enemie> enemies;
    private Bonus bonus;
    private Life life;
    
    public Map() {
        
        addKeyListener(new KeyListerner());
        
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon image = new ImageIcon("/home/caleberios/Documents/UnB/4Sem/OO/JAVA/EP2/Assets/images/fase1/space.jpg");
        
        this.background = image.getImage();
        
        player = new Player();
        missiles = new ArrayList();
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
        if(!missiles.isEmpty())
            drawMissile(g);
        if(bonus.isVisible())
            drawBonus(g);
        if(life.isVisible())
            drawLife(g);
        drawLifeMessage(g);
        drawBonusMessage(g);
        drawName(g);
                
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawSpaceship(Graphics g) {
               
        // Draw spaceship
        g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(), this);
    }
    
    private void drawMissile(Graphics g){
        
        // Draw missiles
        for(Missile missile : missiles){
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
        if(!missiles.isEmpty())
            updateMissile();
        updateBonus();
        updateLife();
        Collision();
        updatePlayer();
        
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
    
    private void drawLifeMessage(Graphics g){
        String message = "Life:";
        Font font = new Font("Helvetiva", Font.BOLD, 14);
        FontMetrics metric = getFontMetrics(font);
        
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, 0, 14);
        
        int j = 0;
        life.heart();
        int k = life.getWidth();
        while(j < player.getLife()){
            g.drawImage(life.getImage(), (metric.stringWidth(message) - 10) + k, 0, this);
            j++;
            k += life.getWidth();
        }
        life.life();
    }
    
    private void drawName(Graphics g){
        String message = player.getName();
        Font font = new Font("Helvetiva", Font.BOLD, 14);
        FontMetrics metric = getFontMetrics(font);
        
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, (Game.getWidth() - metric.stringWidth(message)) / 2, 14);
    }
    
    private void drawBonusMessage(Graphics g){
        String message = "Score: " + player.getScore();
        Font font = new Font("Helvetiva", Font.BOLD, 14);
        FontMetrics metric = getFontMetrics(font);
        
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, (Game.getWidth() - metric.stringWidth(message)), 14);
    }
    
    private void updateSpaceship() {
        spaceship.move();
    }
    
    private void updatePlayer(){
        if(player.getLife() == 0)
            player.Lost(true);
    }
    
    private void updateMissile(){
        
        for(Iterator<Missile> missil = missiles.iterator(); missil.hasNext();){
            Missile next = missil.next();
            
            if(next.move()){}
            else{
                missil.remove();
            }
        }
    }
    
    private void Collision(){
        if((spaceship.getX() > life.getX() && spaceship.getX() <= (life.getX() + life.getWidth()) 
                || ((spaceship.getX() + spaceship.getWidth()) >= life.getX() 
                && (spaceship.getX() + spaceship.getWidth()) <= life.getX() + life.getWidth()))){
            if((spaceship.getY() > life.getY() && spaceship.getY() <= (life.getY() + life.getHeight()) 
                    || ((spaceship.getY() + spaceship.getHeight()) >= life.getY() 
                    && (spaceship.getY() + spaceship.getHeight()) <= life.getY() + life.getHeight()))){
                player.gainLife();
                life.setVisible(false);
            }
        }
        
        if((spaceship.getX() > bonus.getX() && spaceship.getX() <= (bonus.getX() + bonus.getWidth()) 
                || ((spaceship.getX() + spaceship.getWidth()) >= bonus.getX() 
                && (spaceship.getX() + spaceship.getWidth()) <= bonus.getX() + bonus.getWidth()))){
            if((spaceship.getY() > bonus.getY() && spaceship.getY() <= (bonus.getY() + bonus.getHeight()) 
                    || ((spaceship.getY() + spaceship.getHeight()) >= bonus.getY() 
                    && (spaceship.getY() + spaceship.getHeight()) <= bonus.getY() + bonus.getHeight()))){
                player.gainScore(10);
                bonus.setVisible(Boolean.FALSE);
            }
        }
        
        for(Iterator<Enemie> enemie = enemies.iterator(); enemie.hasNext();){
            Enemie next = enemie.next();

            if((spaceship.getX() > next.getX() && spaceship.getX() <= (next.getX() + next.getWidth()) 
                    || ((spaceship.getX() + spaceship.getWidth()) >= next.getX() 
                    && (spaceship.getX() + spaceship.getWidth()) <= next.getX() + next.getWidth()))){
                if((spaceship.getY() > next.getY() && spaceship.getY() <= (next.getY() + next.getHeight()) 
                        || ((spaceship.getY() + spaceship.getHeight()) >= next.getY() 
                        && (spaceship.getY() + spaceship.getHeight()) <= next.getY() + next.getHeight()))){
                    enemie.remove();
                    player.lossLife();
                }
            }
        }
        
        for(Iterator<Missile> missil = missiles.iterator(); missil.hasNext();){
            Missile next = missil.next();
            
            for(Iterator<Enemie> enemie = enemies.iterator(); enemie.hasNext();){
                Enemie nexte = enemie.next();
                if((next.getX() > nexte.getX() && next.getX() <= (nexte.getX() + nexte.getWidth()) 
                        || ((next.getX() + next.getWidth()) >= nexte.getX() 
                        && (next.getX() + next.getWidth()) <= nexte.getX() + nexte.getWidth()))){
                    if((next.getY() > nexte.getY() && next.getY() <= (nexte.getY() + nexte.getHeight()) 
                            || ((next.getY() + next.getHeight()) >= nexte.getY() 
                            && (next.getY() + next.getHeight()) <= nexte.getY() + nexte.getHeight()))){
                        missil.remove();
                        enemie.remove();
                        spaceship.missil.explosion();
                        player.gainScore(50);
                    }
                }
            }
        }
    }
    
    private void updateEnemie(){
        Enemie ene = Enemie.insert();
        
        for(Iterator<Enemie> enemie = enemies.iterator(); enemie.hasNext();){
            Enemie next = enemie.next();
            
            if(next.move()){}
            else{
                enemie.remove();
            }
        }

        if((count % 20) == 0)
            enemies.add(ene);
        
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
                missiles.add(new Missile(spaceship.getX()+5, spaceship.getY(), 1));
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
