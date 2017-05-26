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
public class Spider_PaperScissors extends MultiEnemy{
    public Spider_PaperScissors(){
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
            int set = 1;
            public int getRPS(){
                if(set==1){
                    set = 2;
                } else {
                    set = 1;
                }
                
                return set;
            }
        };
        CombatSystem CS = RPS.RPS;
        
        this.AH = CS;
        
        
        messageAs = new String[]{
            "Your hand feels like a dead fish.",
            "We say the same things every time you know.",
            "The colors of the earth are synthetic here.",
            "Dirt, gravel, trash, more trash, a lot more trash."
        };
    
        messageBs = new String[]{
            "I'd rather like a dead fish right about now.",
            "I'll always alternate paper-scissors. It's just what works for me.",
            "The garbage of a milenia",
            "Some begin to worship it."
        };
        
        messageWins = new String[]{
            "Good for you.",
            "Have you any fresh bones?",
            "I rather like these colors.",
            "Don't become tainted down here."
        };
        
        messageLosses = new String[]{
            "Good for me.",
            "ROFL I am a spider",
            "If you stay long enough you too will become colourful.",
            "If you stay long enough you too will become colourful."
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
