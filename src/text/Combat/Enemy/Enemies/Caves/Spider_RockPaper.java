/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Enemy.Enemies.Caves;

import text.Combat.Enemy.Enemies.Wilds.*;
import java.awt.Color;
import text.Combat.CombatSystem;
import text.Combat.Enemy.Attacks.RandomCombatSystem;
import text.Combat.Enemy.Enemies.Enemy;
import text.Combat.Enemy.Enemies.MultiEnemy;
import text.Combat.Hero.HeroMapper;
import text.Combat.Hero.WordController;
import text.Combat.RPS.RPSObject;
import text.Images.TextImageComplex;
import text.Utility.ColorTuple;
import text.Utility.ImageLoader;

/**
 * @author FF6EB4
 */
public class Spider_RockPaper extends MultiEnemy{
    public Spider_RockPaper(){
        super();
        this.name = "Cave Spider";
        ImageLoader.switchMap("GREYSCALE");
        this.image = new TextImageComplex(ImageLoader.loadImage("spider_stand.txt"));
        this.image.flip();
        
        ColorTuple black = new ColorTuple(Color.BLACK,Color.WHITE,' ');
        ColorTuple yellow = new ColorTuple(Color.YELLOW,Color.RED,' ');
        this.recolor(black,yellow,black,yellow);
        
        this.turns = 50;
        
        RPSObject RPS = new RPSObject(null,"null"){
            int set = 0;
            public int getRPS(){
                if(set==0){
                    set = 1;
                } else {
                    set = 0;
                }
                
                return set;
            }
        };
        CombatSystem CS = RPS.RPS;
        
        this.AH = CS;
        

        
        messageAs = new String[]{
            "We used to live on the surface.",
            "I've heard of humans."
        };
    
        messageBs = new String[]{
            "Then the fire nation attacked.",
            "You're the first I've seen though."
        };
        
        messageWins = new String[]{
            "Shorts are comfy and easy to wear.",
            "That's unfortunate."
        };
        
        messageLosses = new String[]{
            "Shorts are comfy and easy to wear.",
            "Hooray!"
        };
        
        setRandom();
    }
    
    public String[] display(){
        return this.AH.display();
    }
    
    public String toString(){
        return "Cave Spider";
    }
}
