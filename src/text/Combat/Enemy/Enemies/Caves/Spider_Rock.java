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
public class Spider_Rock extends MultiEnemy{
    public Spider_Rock(){
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
                return 0;
            }
        };
        CombatSystem CS = RPS.RPS;
        
        this.AH = CS;
        
        messageAs = new String[]{
            "They say there is no bottom.",
            "The animals above have many gods..",
            "Don't be evil.",
            "We'll be here for quite some time, you and I.",
            "You didn't have to talk to me. But you did. How curious.",
            "I choose rock. You choose paper. I lose. I'm purple. You're red."
        };
    
        messageBs = new String[]{
            "Most of us have never ventured beyond a point.",
            "But we have none. Without forced order, there are none.",
            "Except they always will.",
            "I suppose our time has come to an end.",
            "I suppose we do have things to say.",
            "This isn't fair, but then nothing ever was."
        };
        
        messageWins = new String[]{
            "But my strategy was flawless..",
            "Ok.",
            "Yes.",
            "I'm alright. You?",
            "Dig away, you delicious mound of flesh",
            "Time and time again."
        };
        
        messageLosses = new String[]{
            "I don't hate you.",
            "Thank you for losing. Winning feels good to me.",
            "Unless.",
            "Not me. You.",
            "I choose rock every time. It's not hard.",
            "...I was wrong about you, you know that?"
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
