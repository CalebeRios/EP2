/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.Random;

/**
 *
 * @author caleberios
 */
public class Bonus extends Sprite{
    private int speed_y;
    private int difficulty = 0;

    public Bonus(int x, int y){
        super(x,y);

        initBonus();
    }

    public void initBonus(){
        bonus();
    }

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }

    public boolean isBonus(){
        return (this != null);
    }

    public void bonus(){
        String path = getClass().getResource("").getPath().toString();
        if(path.contains("jar!")){
            loadImage(path.replace("file:", "").replace("/dist/EP2.jar!/game/", "/assets/images/bonus.png"));
        }
        else{
            loadImage(path.replace("/build/classes/game/", "/assets/images/bonus.png"));
        }
    }

    public boolean move(){
        if(y + this.width <= Game.getWidth()){
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

    public static Bonus insert(){
        Random randX = new Random();

        Bonus bonus = new Bonus(randX.nextInt(500), -300);

        return bonus;
    }
}
