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
import static text.Utility.LootGenerator.oRan;

/**
 * @author FF6EB4
 */
public class Spider_Random extends MultiEnemy{
    public Spider_Random(){
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
            int a = oRan.nextInt(100);
            int b = a+oRan.nextInt(101 - a);
            public int getRPS(){
                int which = oRan.nextInt(100);
                
                if(which < a) return 0;
                if(which < b) return 1;
                return 2;
            }
        };
        CombatSystem CS = RPS.RPS;
        
        this.AH = CS;
        
        messageAs = new String[]{
            "I've seen the light of the sun once.",
            "It lives at the bottom..",
            "There's no such thing as random.",
            "You of all people should know that."
        };
    
        messageBs = new String[]{
            "It draws many in, but it is no god to me.",
            "I can feel it.",
            "Yet determinism is no god of mine either.",
            "It just seems right."
        };
        
        messageWins = new String[]{
            "The light burned my eyes.",
            "Proceed.",
            "We have no god to pray to. Yes, we all die young.",
            "The clock doesn't always tick."
        };
        
        messageLosses = new String[]{
            "I'm not entirely random, you know.",
            "It wouldn't surprise me if you ended here, like I did.",
            "There exists a stable state.",
            "I would like to die now."
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
