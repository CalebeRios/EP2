/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author caleberios
 */
public class Player{
    private int score = 0;
    private int life = 3;
    private String name;
    private boolean winner = false;
    private boolean loss = false;
    public boolean chave = true;

    public Player(){}

    public Player(String name, int score){
        this.name = name;
        this.score = score;
    }

    public Player(String name){
        this.name = name;
    }

    public int getScore(){
        return this.score;
    }

    public boolean isWinner(){
        return this.winner;
    }

    public void winner(){
        this.winner = true;
    }

    public void Lost(){
        this.loss = true;
    }

    public boolean getLost(){
        return loss;
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
