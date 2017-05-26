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
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import text.Utility.ImageLoader;
import static text.Utility.SuperRandom.oRan;

/**
 * @author FF6EB4
 */
public class Spider_BaitNSwitch extends MultiEnemy{
    public Spider_BaitNSwitch(){
        super();
        this.name = "Chaos Spider";
        ImageLoader.switchMap("GREYSCALE");
        this.image = ImageBuilder.resizeImage(ImageLoader.loadImage("spider_stand.txt"), 36, 12);
        this.image = new TextImageComplex((TextImageBasic)this.image);
        this.image.xOrigin = 18;
        this.image.yOrigin = 12;
        this.image.flip();
        
        this.turns = 100;
        
        RPSObject RPS = new RPSObject(null,"null"){
            int num = 0;
            public int getRPS(){
                if(num < 100){
                    num += oRan.nextInt(80);
                    return 0;
                } else if(num == -1){
                    num = 0;
                    return 1;
                } else {
                    num = -1;
                    return 2;
                }
            }
        };
        CombatSystem CS = RPS.RPS;
        
        this.AH = CS;
        
        
        messageAs = new String[]{
            "Challenge me. I dare you.",
            "Hello friend! Welcome!",
            "It can see with its eyes closed.",
            "Even if you don't believe."
        };
    
        messageBs = new String[]{
            "...this blows.",
            "Have a nice day!",
            "It fears nothing.",
            "The sky isn't real."
        };
        
        messageWins = new String[]{
            "Well damn.",
            "What fun for you!",
            "You shouldn't be here.",
            "You've dug yourself into a hole."
        };
        
        messageLosses = new String[]{
            "Try again next time.",
            "Hahaha I love being a spider!",
            "I didn't believe either.",
            "We're friends now."
        };
        
        setRandom();
    }
    
    public String[] display(){
        return this.AH.display();
    }
    
    public String toString(){
        return "Chaos Spider";
    }
}
