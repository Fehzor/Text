/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Enemy.Enemies.Graveyard.TutorialStuff;

import java.awt.Color;
import text.Combat.Enemy.Attacks.RandomCombatSystem;
import text.Combat.Enemy.Enemies.Enemy;
import text.Combat.Hero.HeroMapper;
import text.Combat.Hero.WordController;
import text.Images.TextImageComplex;
import text.Utility.ColorTuple;
import text.Utility.ImageLoader;

/**
 * @author FF6EB4
 */
public class Zombie_e extends Enemy{
    public Zombie_e(){
        super();
        this.name = "ZAM BRO";
        ImageLoader.switchMap("HUMAN");
        this.image = new TextImageComplex(ImageLoader.loadImage("zombie_stand.txt"));
        this.image.flip();
        
        this.turns = 30;
        
        RandomCombatSystem RCS = new RandomCombatSystem();
        WordController WCA = new WordController("Noggin",(float)0.1);
        
        WordController WCA2 = new WordController("Thinker",(float)0.1);
        
        HeroMapper WM = new HeroMapper();
        WM.addHero(1, 0, .5, 'I');
        
        WM.addHero(5, 0, .5, 'l');
        WM.addHero(5, 1, .5, 'o');
        WM.addHero(5, 2, .5, 'v');
        WM.addHero(5, 3, .5, 'e');
        
        WM.addHero(9, 0, .5, 'y');
        WM.addHero(9, 1, .5, 'o');
        WM.addHero(9, 2, .5, 'u');
        
        RCS.addCombatSystem(WCA,6);
        RCS.addCombatSystem(WCA2,6);
        RCS.addCombatSystem(WM,4);
        
        this.AH = RCS;
    }
    
    public String[] display(){
        String[] ret = new String[7];
        ret[0] = "";
        ret[1] = ".......he seems to want something....";
        ret[2] = "";
        ret[3] = "...is it you?? Is it your brain??";
        ret[4] = "";
        ret[5] = "...Or is he just in love???";
        ret[6] = "";
        return ret;
    }
    
    public String toString(){
        return "ZAM BRO";
    }
}
