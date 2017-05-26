/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Enemy.Enemies.Graveyard.TutorialStuff;

import java.awt.Color;
import text.Combat.Enemy.Enemies.Enemy;
import text.Combat.Hero.HeroMapper;
import text.Combat.Hero.WordController;
import text.Images.TextImageComplex;
import text.Utility.ColorTuple;
import text.Utility.ImageLoader;

/**
 * @author FF6EB4
 */
public class Punkin_a extends Enemy{
    public Punkin_a(){
        super();
        this.name = "Jack";
        ImageLoader.switchMap("HUMAN");
        this.image = new TextImageComplex(ImageLoader.loadImage("jack_ol_lantern_stand.txt"));
        this.image.flip();
        
        this.turns = 10;
        
        messageA = "A b.. ... ...D?";
        messageA = "C.";
        
        this.AH = new HeroMapper();
        ((HeroMapper)AH).addHero(1,0,.5,'a');
        ((HeroMapper)AH).addHero(1,1,.5,'b');
        ((HeroMapper)AH).addHero(1,3,.5,'d');
        ((HeroMapper)AH).addHero(1,4,.5,'e');
        ((HeroMapper)AH).addHero(1,5,.5,'f');
        
        ((HeroMapper)AH).addHero(10,2,2,'c');
    }
    
    
    public String[] display(){
        String[] ret = new String[7];
        ret[0] = "";
        ret[1] = "...Could it be????!";
        ret[2] = "";
        ret[3] = "...He's... Forgotten the letter 'c'??!";
        ret[4] = "";
        ret[5] = "";
        ret[6] = "";
        return ret;
    }
    
    public String toString(){
        return "Jack";
    }
}
