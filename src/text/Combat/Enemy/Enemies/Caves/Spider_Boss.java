/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Enemy.Enemies.Caves;

import text.Combat.Enemy.Enemies.Wilds.*;
import java.awt.Color;
import text.Actors.Instances.Chests.Chest;
import text.Combat.CombatSystem;
import text.Combat.Enemy.Attacks.RandomCombatSystem;
import text.Combat.Enemy.Enemies.Enemy;
import text.Combat.Enemy.Enemies.MultiEnemy;
import text.Combat.Hero.HeroMapper;
import text.Combat.Hero.WordController;
import text.Combat.RPS.RPSObject;
import text.Combat.RPS.RPSPart;
import text.Combat.RPS.RPSSystem;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import text.Inventory.Physical;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import text.Utility.ImageLoader;

/**
 * @author FF6EB4
 */
public class Spider_Boss extends MultiEnemy{
    public Spider_Boss(){
        super();
        this.name = "Arachnophobia";
        ImageLoader.switchMap("GREYSCALE");
        this.image = ImageBuilder.resizeImage(ImageLoader.loadImage("spider_stand.txt"), 48, 72);
        this.image = new TextImageComplex((TextImageBasic)this.image);
        this.image.xOrigin = 24;
        this.image.yOrigin = 72;
        this.image.flip();
        
        ColorTuple a = new ColorTuple(Color.BLACK,Color.PINK,' ');
        ColorTuple b = new ColorTuple(Color.WHITE,Color.GREEN,' ');
        this.recolor(a,b,a,b);
        
        
        this.turns = 200;
        
        RPSObject RPS = new RPSObject(null,"null"){
            long base = System.currentTimeMillis();
            long secs = 1000*10;
            public int getRPS(){
                long current = System.currentTimeMillis();
                
                //System.out.println("SECONDS: "+((current-base) / 1000));
                
                if(current - base > secs){
                    
                    base = System.currentTimeMillis();
                    int answer = RPS.answer;
                    return (answer+1)%2;
                } else {
                    base = System.currentTimeMillis();
                    return RPS.lastAnswer;
                }
            }
        };
        CombatSystem CS = RPS.RPS;
        
        this.AH = CS;
        

        
        messageAs = new String[]{
            "I can read minds, you know. Your thoughts are garbage.",
            "I've been here for a millenia. I've watched these walls crumble."
        };
    
        messageBs = new String[]{
            "Most don't think except through a stream of emotion.",
            "You're not the first human I've seen."
        };
        
        messageWins = new String[]{
            "Thank you for having tea with me.",
            "Thank you for your ignorance."
        };
        
        messageLosses = new String[]{
            "You can do better. I believe in you.",
            "I am impartial to this."
        };
        
        setRandom();
    }
    
    public String[] display(){
        RPSSystem RPS = (RPSSystem)this.AH;
        
        String[] ret = new String[7];
        if(!RPS.done){
            ret[0] = "Rock";
            ret[1] = "Paper";
            ret[2] = "Scissors";
            ret[3] = "";
            ret[4] = "Except I can read minds. How can you win?";
            ret[5] = "It's really not fair, is it?";
            ret[6] = "Take your time during your turn..................................";
        } else {
            ret[0] = "........................";
            ret[1] = "....";
            
            
            String ans = "scissors";
            if(RPS.answer == 0) ans = "rock";
            if(RPS.answer == 1) ans = "paper";
            
            ret[2] = "The opposing result was " + ans +".";
            ret[3] = "....";
            ret[4] = "You "+ (RPS.hits>0 ? "won!" : "lost..");
            ret[5] = "...........";
            ret[6] = "....";
        }
        return ret;
    }
    
    public String toString(){
        return this.name;
    }
    
    public Chest loot(){
        Chest CH = new Chest();
        
        CH.generateRewardsBasic();
        CH.generateRewardsBasic();
        CH.generateRewardsBasic();
        CH.generateRewardsBasic();
        CH.generateRewardsBasic();
        CH.generateRewardsBasic();
        CH.generateRewardsBasic();
        CH.generateRewardsBasic();
        
        RPSPart RPS = new RPSPart();
        CH.inv.addStuff(new Physical(RPS));
        
        
        return CH;
    }
}
