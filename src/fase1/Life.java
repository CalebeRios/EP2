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
public class Life extends Sprite{
    
    private int speed_y = 1;
    private int difficulty = 0;
    
    public Life(int x, int y){
        super(x, y);
        
        initLife();
    }
    
    private void initLife(){
        life();
    }
    
    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
    
    public void life(){
        loadImage("/home/caleberios/Documents/UnB/4Sem/OO/JAVA/EP2/Assets/images/fase1/life.png");
    }
    
    public void heart(){
        loadImage("/home/caleberios/Documents/UnB/4Sem/OO/JAVA/EP2/Assets/images/fase1/heart.png");
    }
    
    public boolean move(){
        if(y + this.width <= Game.getWidth() + this.width){
            if(this.difficulty == 0)
                this.speed_y = 1;
            else if(this.difficulty == 1)
                this.speed_y = 2;
            else
                this.speed_y = 3;         
            y += speed_y;
            return true;
        }
        
        return false;
    }
    
    public static Life insert(){
        Random randX = new Random();
        
        Life life = new Life(randX.nextInt(490) + 5, -300);
        
        return life;
    }
}
