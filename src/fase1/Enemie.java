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
public class Enemie extends Sprite{
    
    private int speed_y;
    private final int difficulty;
    
    public Enemie(int x, int y, int difficulty){
        super(x,y);
        
        this.difficulty = difficulty;
        
        if(this.difficulty == 0)
            initEnemieEasy();
        else if(this.difficulty == 1)
            initEnemieMedium();
        else
            initEnemyHard();
    }
    
    public int getDifficulty(){
        return this.difficulty;
    }
    
    private void initEnemieEasy(){
        enemieEasy();
    }
    
    private void enemieEasy(){
        loadImage("/home/caleberios/Documents/UnB/4Sem/OO/JAVA/EP2/Assets/images/fase1/alien_EASY.png");
    }
    
    private void enemyMedium(){
        loadImage("/home/caleberios/Documents/UnB/4Sem/OO/JAVA/EP2/Assets/images/fase1/alien_MEDIUM.png");        
    }
    
    public void initEnemieMedium(){
        enemyMedium();
    }
    
    private void enemyHard(){
        loadImage("/home/caleberios/Documents/UnB/4Sem/OO/JAVA/EP2/Assets/images/fase1/alien_HARD.png");
    }
    
    public void initEnemyHard(){
        enemyHard();
    }
    
    public boolean move(){
        if(y + this.width >= Game.getWidth()){
            return false;
        }
        
        if(this.difficulty == 0)
            this.speed_y = 1;
        else if(this.difficulty == 1)
            this.speed_y = 2;
        else
            this.speed_y = 3;
            
        y += speed_y;
        
        return true;
    }
    
    public static Enemie insert(int difficulty){
        Random randX = new Random();    
        
        return new Enemie(randX.nextInt(480) + 10, -100, difficulty);
    }
}
