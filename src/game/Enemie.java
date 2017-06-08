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
        String path = getClass().getResource("").getPath().toString();
        if(path.contains("jar!")){
            loadImage(path.replace("file:", "").replace("/dist/EP2.jar!/game/", "/assets/images/alien_EASY.png"));
        }
        else{
            loadImage(path.replace("/build/classes/game/", "/assets/images/alien_EASY.png"));
        }
    }

    private void enemyMedium(){
        String path = getClass().getResource("").getPath().toString();
        if(path.contains("jar!")){
            loadImage(path.replace("file:", "").replace("/dist/EP2.jar!/game/", "/assets/images/alien_MEDIUM.png"));
        }
        else{
            loadImage(path.replace("/build/classes/game/", "/assets/images/alien_MEDIUM.png"));
        }
    }

    public void initEnemieMedium(){
        enemyMedium();
    }

    private void enemyHard(){
        String path = getClass().getResource("").getPath().toString();
        if(path.contains("jar!")){
            loadImage(path.replace("file:", "").replace("/dist/EP2.jar!/game/", "/assets/images/alien_HARD.png"));
        }
        else{
            loadImage(path.replace("/build/classes/game/", "/assets/images/alien_HARD.png"));
        }
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
