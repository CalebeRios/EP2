/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fase1;

import java.util.Random;

/**
 *
 * @author caleberios
 */
public class Bonus extends Sprite{
    private int speed_y = 1;
    
    public Bonus(int x, int y){
        super(x,y);
        
        initBonus();
    }
    
    public void initBonus(){
        bonus();
    }
    
    public void bonus(){
        loadImage("/home/caleberios/Documents/UnB/4Sem/OO/JAVA/EP2/Assets/images/fase1/bonus.png");
    }
    
    public boolean move(){
        if(y + this.width <= Game.getWidth()){
            y += speed_y;
            return true;
        }
        
        return false;
    }
    
    public static Bonus insert(){
        Random randX = new Random();
        
        Bonus bonus = new Bonus(randX.nextInt(500), -300);
        
        return bonus;
    }
}