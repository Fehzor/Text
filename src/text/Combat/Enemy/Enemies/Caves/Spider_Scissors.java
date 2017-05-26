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
public class Spider_Scissors extends MultiEnemy{
    public Spider_Scissors(){
        super();
        this.name = "Cave Spider";
        ImageLoader.switchMap("GREYSCALE");
        this.image = new TextImageComplex(ImageLoader.loadImage("spider_stand.txt"));
        this.image.flip();
        
        ColorTuple purple = new ColorTuple(Color.MAGENTA,Color.WHITE,' ');
        ColorTuple green = new ColorTuple(Color.GREEN,Color.YELLOW,' ');
        this.recolor(purple,green,purple,green);
        
        this.turns = 20;
        
        RPSObject RPS = new RPSObject(null,"null"){
            public int getRPS(){
                return 2;
            }
        };
        CombatSystem CS = RPS.RPS;
        
        this.AH = CS;
        
        messageAs = new String[]{
            "It gets lonely down here, waiting for prey.",
            "What is it like, to walk on two legs?",
            "What has two legs and cannot fly?",
            "Would you care for some donuts and cider?",
            "Spinning clockwise, deeper and deeper."
        };
    
        messageBs = new String[]{
            "The rocks beneath the earth never talk.",
            "A pathetic loss.",
            "Why you, of course.",
            "Yes, that game was wonderful. Then people went a bit far with it.",
            "It's no way to live."
        };
        
        messageWins = new String[]{
            "...How did you beat my cutting edge tech???",
            "Scissors have forbade me.",
            "I let you win.",
            "Rock is so lame...",
            "My favorite colour is yellow. But I am not."
        };
        
        messageLosses = new String[]{
            "Thank god you don't know about rock.",
            "If this was another world I would eat you.",
            "I mean I tried to let you win...",
            "You're losing on purpose, aren't you?",
            "Well then."
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
