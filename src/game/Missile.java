/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

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
        String path = getClass().getResource("").getPath().toString();
        if(path.contains("jar!")){
            loadImage(path.replace("file:", "").replace("/dist/EP2.jar!/game/", "/assets/images/missile.png"));
        }
        else{
            loadImage(path.replace("/build/classes/game/", "/assets/images/missile.png"));
        }
    }

    public boolean move(){
        if((y + this.width) >= Game.getWidth() || (y + this.width) < 0){
            y = 0;
            return false;
        }

        y -= speed_y;

        return true;
    }
}
