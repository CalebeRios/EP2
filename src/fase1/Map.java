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
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Iterator;

public class Map extends JPanel implements ActionListener {

    private final int SPACESHIP_X = 220;
    private final int SPACESHIP_Y = 430;
    private final Timer timer_map;
    private int countE = 1;
    private int countB = 1;
    private int countL = 1;
    private int countS = 100;
    private int count = 0;
    private int countGame = 0;
    
    private final Player player;
    private final Image background;
    private Spaceship spaceship;
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
        if(countS > 10 || (countS% 3) != 0){
            drawSpaceship(g);
            countS += 2;
        }else if(player.getLost()){
            if(count == 0){
                try{Ranking.insert(player);} catch(IOException ex){}
                try{Ranking.list();} catch(FileNotFoundException ex){} catch (IOException ex) {}
                count++;
            }
            drawGameOver(g);
            countGame = 0;
        }else
            countS++;
        drawEnemie(g);
        if(!missiles.isEmpty())
            drawMissile(g);
        drawBonus(g);
        drawLife(g);
        drawLifeMessage(g);
        drawBonusMessage(g);
        drawName(g);
        if(player.isWinner())
            drawMissionAccomplished(g);
        
        
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
        
        if(!player.getLost())
            updateSpaceship();
        else
            spaceship = Spaceship.insert();
        updateEnemie();
        if(!missiles.isEmpty())
            updateMissile();
        updateBonus();
        updateLife();
        if(!player.isWinner())
            Collision();
        updatePlayer();
        
        countGame++;
        
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
    
    private void Collision(){
        if((spaceship.getX() > life.getX() && spaceship.getX() <= (life.getX() + life.getWidth()) 
                || ((spaceship.getX() + spaceship.getWidth()) >= life.getX() 
                && (spaceship.getX() + spaceship.getWidth()) <= life.getX() + life.getWidth()))){
            if((spaceship.getY() > life.getY() && spaceship.getY() <= (life.getY() + life.getHeight()) 
                    || ((spaceship.getY() + spaceship.getHeight()) >= life.getY() 
                    && (spaceship.getY() + spaceship.getHeight()) <= life.getY() + life.getHeight()))){
                if(!player.isWinner() && !player.getLost())
                    player.gainLife();
                life = Life.insert();
            }
        }
        
        if((spaceship.getX() > bonus.getX() && spaceship.getX() <= (bonus.getX() + bonus.getWidth()) 
                || ((spaceship.getX() + spaceship.getWidth()) >= bonus.getX() 
                && (spaceship.getX() + spaceship.getWidth()) <= bonus.getX() + bonus.getWidth()))){
            if((spaceship.getY() > bonus.getY() && spaceship.getY() <= (bonus.getY() + bonus.getHeight()) 
                    || ((spaceship.getY() + spaceship.getHeight()) >= bonus.getY() 
                    && (spaceship.getY() + spaceship.getHeight()) <= bonus.getY() + bonus.getHeight()))){
                if(!player.getLost() && !player.isWinner())
                    player.gainScore(10);
                bonus = Bonus.insert();
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
                    countS = 0;
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
                        if(nexte.getDifficulty() == 0 && !player.getLost())
                            player.gainScore(1);
                        else if(nexte.getDifficulty() == 1 && !player.getLost())
                            player.gainScore(2);
                        else if(nexte.getDifficulty() == 2 && !player.getLost())
                            player.gainScore(3);
                        missil.remove();
                        enemie.remove();
                    }
                }
            }
        }
    }

    private void updateSpaceship() {
        spaceship.move();
    }
    
    private void updatePlayer(){
        if(player.getLife() == 0)
            player.Lost();
        if(countGame == 1000000000)
            player.winner();
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
    
    private void updateEnemie(){
        Enemie ene = null;
        
        if(!player.isWinner()){
            if(player.getScore() > 50 && player.getScore() < 100){
                ene = Enemie.insert(1);
                bonus.setDifficulty(1);
                life.setDifficulty(1);
            }
            else if(player.getScore() >= 100){
                ene = Enemie.insert(2);
                bonus.setDifficulty(2);
                life.setDifficulty(2);
            }
            else
                ene = Enemie.insert(0);
        }
        for(Iterator<Enemie> enemie = enemies.iterator(); enemie.hasNext();){
            Enemie next = enemie.next();
            
            if(next.move()){}
            else{
                enemie.remove();
            }
        }

        if((countE % 20) == 0 && !player.isWinner())
            enemies.add(ene);
        
        countE++;
    }

    private void updateBonus(){
        if(bonus.move()){}
        else if(!bonus.move() && (countB % 200) == 0 && !player.isWinner()){
            bonus = Bonus.insert();
        }
        
        countB++;
    }
    
    private void updateLife(){
        if(life.move()){}
        else if(life.move() == false && (countL % 500) == 0 && !player.isWinner()){
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
            else if(key == KeyEvent.VK_ENTER){
                while(true){
                    
                }
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
