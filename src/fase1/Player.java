/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fase1;

/**
 *
 * @author caleberios
 */
public class Player{
    private int score = 0;
    private int life = 3;
    private String name = "Calebe";
    
    public int getScore(){
        return this.score;
    }
    
    public int getLife(){
        return this.life;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void lossLife(){
        this.life--;
    }
    
    public void gainLife(){
        if(life < 3)
            this.life++;
    }
    
    public void gainScore(int bonus){
        this.score += bonus;
    }
}
