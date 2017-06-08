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
        String path = getClass().getResource("").getPath().toString();
        if(path.contains("jar!")){
            loadImage(path.replace("file:", "").replace("/dist/EP2.jar!/game/", "/assets/images/life.png"));
        }
        else{
            loadImage(path.replace("/build/classes/game/", "/assets/images/life.png"));
        }
    }

    public void heart(){
        String path = getClass().getResource("").getPath().toString();
        if(path.contains("jar!")){
            loadImage(path.replace("file:", "").replace("/dist/EP2.jar!/game/", "/assets/images/heart.png"));
        }
        else{
            loadImage(path.replace("/build/classes/game/", "/assets/images/heart.png"));
        }
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
