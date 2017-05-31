/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fase1;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author caleberios
 */
public class Enemie extends Sprite{
    
    private int speed_y = 1;
    
    public Enemie(int x, int y){
        super(x,y);
        
        initEnemie();
    }
    
    private void initEnemie(){
        enemie();
    }
    
    private void enemie(){
        loadImage("/home/caleberios/Documents/UnB/4Sem/OO/JAVA/EP2/Assets/images/fase1/alien_EASY.png");
    }
    
    public boolean move(){
        if(y + this.width >= Game.getWidth()){
            return false;
        }
        
        y += speed_y;
        
        return true;
    }
    
    public static ArrayList<Enemie> insert(int amount){
        int i = 0;
        ArrayList<Enemie> enemie = new ArrayList();
        Random randX = new Random();    
        Random randY = new Random();
        
        while(i < amount){
            int ranX = randX.nextInt(490)+5;
            //int ranY = randY.nextInt(100);
            enemie.add(new Enemie(ranX, -200));
            i++;
        }
        
        return enemie;
    }
    
    public static Enemie insert(){
        Random randX = new Random();    
        Random randY = new Random();
        
        return new Enemie(randX.nextInt(490) + 5, -100);
    }
}
