/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Enemy.Enemies.Graveyard.TutorialStuff;

import java.awt.Color;
import text.Actors.Instances.Chests.Chest;
import text.Combat.Enemy.Enemies.Enemy;
import text.Combat.Hero.WordController;
import text.Frame.TextDisplay;
import text.Inventory.Physical;
import text.Combat.Hero.WhistleHead;
import text.Tools.ToolParts.Bumper;
import text.Utility.ColorTuple;
import text.Utility.ImageLoader;

/**
 * @author FF6EB4
 */
public class RockerRock extends Enemy{
    public RockerRock(){
        super();
        this.name = "Rock-er";
        ImageLoader.switchMap("GREYSCALE");
        this.image = ImageLoader.loadImage("rock_one.txt");
        
        this.turns = 2;
        
        this.messageA = "If you beat my jams I'll give you a whistle.";
        this.messageB = "Weapons speed battles up!";
        this.winString = "You won!";
        this.loseString = "Press the falling letters to dodge attacks!";
        
        this.AH = new WordController("JKL;",(float)0.1);
    }
    
    
    public boolean act(){
        return true;
    }
    
    public String[] display(){
        String[] ret = new String[7];
        ret[0] = "";
        ret[1] = "";
        ret[2] = "";
        ret[3] = "...He's rocking out! It's that mad tune, JKL;!!!!";
        ret[4] = "";
        ret[5] = "All you can do is wait him out!";
        ret[6] = "";
        return ret;
    }
    
    public String toString(){
        return "Rock-er";
    }
    
    public Chest loot(){
        Chest CH = new Chest();
        
        CH.generateRewardsBasic();
        
        WhistleHead WH = new WhistleHead();
        WH.x = TextDisplay.SCREEN_SIZE_X/2;
        WH.y = TextDisplay.SCREEN_SIZE_Y/2;
        
        Bumper B = new Bumper();
        B.x = TextDisplay.SCREEN_SIZE_X/2;
        B.y = TextDisplay.SCREEN_SIZE_Y/2;
        
        CH.inv.addStuff(new Physical(WH));
        CH.inv.addStuff(new Physical(B));
        
        return CH;
    }
}
