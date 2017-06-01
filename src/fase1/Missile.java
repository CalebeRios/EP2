/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fase1;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author caleberios
 */
public class Missile extends Sprite{
   
    private int speed_y;
    
    public Missile(int x, int y, int speed){
        super(x, y);
        speed_y = speed;
        initMissile();
    }
    
    private void initMissile(){
        missile();
    }
    
    private void missile(){
        loadImage("/home/caleberios/Documents/UnB/4Sem/OO/JAVA/EP2/Assets/images/fase1/missile.png");
    }
    
    public void explosion(){
        explosionMissile();
    }
            
    private void explosionMissile(){
        loadImage("/home/caleberios/Documents/UnB/4Sem/OO/JAVA/EP2/Assets/images/fase1/explosion.png");        
    }
      
    public boolean move(){
        if(y + this.width >= Game.getWidth()){
            y = 0;
            return false;
        }
                    
        y -= speed_y;
        
        return true;
    }
}