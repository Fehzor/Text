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
import static text.Utility.SuperRandom.oRan;

/**
 * @author FF6EB4
 */
public class Spider_PlayerCounter extends MultiEnemy{
    public Spider_PlayerCounter(){
        super();
        this.name = "Cave Spider";
        ImageLoader.switchMap("GREYSCALE");
        this.image = new TextImageComplex(ImageLoader.loadImage("spider_stand.txt"));
        this.image.flip();
        
        ColorTuple pink = new ColorTuple(Color.PINK,Color.WHITE,' ');
        ColorTuple yellow = new ColorTuple(Color.YELLOW,Color.RED,' ');
        this.recolor(yellow,pink,yellow,pink);
        
        this.turns = 70;
        
        RPSObject RPS = new RPSObject(null,"null"){
            
            public int getRPS(){
                int last = RPS.lastAnswer;
                
                if(last == 1) return 2;
                if(last == 2) return 0;
                return 1;
            }
        };
        CombatSystem CS = RPS.RPS;
        
        this.AH = CS;
        
        
        messageAs = new String[]{
            "I can read your mind, you know.",
            "This is my home!",
            "The trash here is blinding to some.",
            "A descent into madness."
        };
    
        messageBs = new String[]{
            "...I can't actually do that. But it was worth a shot.",
            "You're always welcome here.",
            "...just as it once was to many.",
            "A descent through decomposition."
        };
        
        messageWins = new String[]{
            "Oh no!",
            "That was unfortunate.",
            "...",
            "Both of you, and of me."
        };
        
        messageLosses = new String[]{
            "Try again next time.",
            "Hahaha I love being a spider!",
            "Heh.",
            "How poetic."
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
