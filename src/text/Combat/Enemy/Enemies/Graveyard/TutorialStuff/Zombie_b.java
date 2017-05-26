/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Enemy.Enemies.Graveyard.TutorialStuff;

import java.awt.Color;
import text.Combat.Enemy.Enemies.Enemy;
import text.Combat.Hero.WordController;
import text.Images.TextImageComplex;
import text.Utility.ColorTuple;
import text.Utility.ImageLoader;

/**
 * @author FF6EB4
 */
public class Zombie_b extends Enemy{
    public Zombie_b(){
        super();
        this.name = "ZAM BRO";
        ImageLoader.switchMap("HUMAN");
        this.image = new TextImageComplex(ImageLoader.loadImage("zombie_stand.txt"));
        this.image.flip();
        
        this.turns = 15;
        
        this.AH = new WordController("Braininiss",(float)0.1);
    }
    
    public String[] display(){
        String[] ret = new String[7];
        ret[0] = "";
        ret[1] = "...NO!";
        ret[2] = "";
        ret[3] = "...He's preparing to turn you into a Brainini!";
        ret[4] = "";
        ret[5] = "...That's like a panini?! I think?????";
        ret[6] = "";
        return ret;
    }
    
    public String toString(){
        return "ZAM BRO";
    }
}