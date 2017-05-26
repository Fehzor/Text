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
public class Spider_PlayerReverseCounter extends MultiEnemy{
    public Spider_PlayerReverseCounter(){
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
                
                if(last == 1) return 0;
                if(last == 2) return 1;
                return 2;
            }
        };
        CombatSystem CS = RPS.RPS;
        
        this.AH = CS;
        
        
        messageAs = new String[]{
            "We tunnel through the ground eating worms.",
            "The mole people eat us if we go too deep.",
            "Don't be fooled by flashing colours and lights.",
            "As seen on TV."
        };
    
        messageBs = new String[]{
            "Delicious, delicious worms. Sadly there aren't any too far.",
            "I don't think they'd eat you, though.",
            "They are no god to me, nor should they be to you.",
            "A promise of reality."
        };
        
        messageWins = new String[]{
            "Don't eat me!",
            "That was unfortunate.",
            "I'm sorry.",
            "I'm not deep. You are."
        };
        
        messageLosses = new String[]{
            "I won't eat you. Don't worry.",
            "Hahaha I love being a spider!",
            "You must feel so sad.",
            "Can you ever really know how deep you are?"
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
