/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Enemy.Enemies.Graveyard.TutorialStuff;

import java.awt.Color;
import text.Combat.Enemy.Enemies.Enemy;
import text.Utility.ColorTuple;
import text.Utility.ImageLoader;

/**
 * @author FF6EB4
 */
public class GentRock extends Enemy{
    public GentRock(){
        super();
        this.name = "Gent Rock";
        ImageLoader.switchMap("GREYSCALE");
        this.image = ImageLoader.loadImage("rock_one.txt");
        
        messageA = "Wanna win a fight?";
        messageB = "That's how it's done dog.";
        winString = "You waited long enough! Congrats!";
        this.turns = 2;
    }
    
    public boolean act(){
        return true;
    }
    
    public String[] display(){
        String[] ret = new String[7];
        ret[0] = "";
        ret[1] = "...He's genting! He's letting you win!";
        ret[2] = "";
        ret[3] = "";
        ret[4] = "...I guess this fight is just waiting???";
        ret[5] = "";
        ret[6] = "";
        return ret;
    }
    
    
    public String toString(){
        return "Gent Rock";
    }
}
