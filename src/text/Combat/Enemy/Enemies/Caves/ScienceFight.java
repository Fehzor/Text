/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Enemy.Enemies.Caves;

import text.Combat.Enemy.Enemies.Wilds.*;
import java.awt.Color;
import java.util.ArrayList;
import text.Actors.Instances.Chests.Chest;
import text.Combat.CombatSystem;
import text.Combat.EightBall.EightBall;
import text.Combat.EightBall.EightBallSystem;
import text.Combat.EightBall.EightBallpart;
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
import static text.Utility.SuperRandom.oRan;
import text.WorldFrame.Progress;

/**
 * @author FF6EB4
 */
public class ScienceFight extends MultiEnemy{
    public ScienceFight(){
        super();
        this.name = "SCIENCE";
        ImageLoader.switchMap("HUMAN_TWO");
        this.image = new TextImageComplex(ImageLoader.loadImage("science_stand.txt"));

        this.turns = 666;
        
        
        EightBall EB = new EightBall(null,null);
        EB.players = 2;
        CombatSystem CS = new EightBallSystem(EB){
            public void guess(int i){
                guesses = new ArrayList<>();
                int guess = oRan.nextInt(round+1);
                switch(guess%11){
                    case 0:
                       guesses.add(5);
                       break;
                    case 1:
                        guesses.add(12);
                        guesses.add(25);
                        break;
                    case 2:
                        guesses.add(25);
                        guesses.add(50);
                        break;
                    case 3:
                        guesses.add(50);
                        guesses.add(65);
                        guesses.add(85);
                        guesses.add(90);
                        guesses.add(70);
                        break;
                    case 4:
                        guesses.add(25);
                        guesses.add(75);
                        guesses.add(63);
                        break;
                    case 5:
                        guesses.add(10);
                        guesses.add(90);
                        guesses.add(45);
                        break;
                    case 6:
                        guesses.add(40);
                        guesses.add(75);
                        break;
                    case 7:
                        guesses.add(5);
                        guesses.add(50);
                        guesses.add(25);
                        guesses.add(35);
                        break;
                    case 8:
                        guesses.add(50);
                        guesses.add(55);
                        break;
                    case 9:
                        guesses.add(33);
                        guesses.add(75);
                        break;
                    case 10:
                        guesses.add(23);
                }
                round++;
            }
        };
        
        this.AH = CS;
        

        
        messageAs = new String[]{
            "Thanks for helping me!",
            "You don't hate me?"
        };
    
        messageBs = new String[]{
            "I guess you really do care.",
            "You've surprised me."
        };
        
        messageWins = new String[]{
            "Why.",
            "..."
        };
        
        messageLosses = new String[]{
            "It was time for a crash course in SCIENCE.",
            "You stand for nothing."
        };
        
        setRandom();
    }
    
    public String[] display(){
        return AH.display();
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
        CH.generateRewardsBasic();
        CH.generateRewardsBasic();
        CH.generateRewardsBasic();
        CH.generateRewardsBasic();
        CH.generateRewardsBasic();
        CH.generateRewardsBasic();
        CH.generateRewardsBasic();
        CH.generateRewardsBasic();
        
        EightBallpart a = new EightBallpart(0,0,3);
        CH.inv.addStuff(new Physical(a));
        a = new EightBallpart(0,0,1);
        CH.inv.addStuff(new Physical(a));
        a = new EightBallpart(0,0,2);
        CH.inv.addStuff(new Physical(a));
        a = new EightBallpart(0,0,1);
        CH.inv.addStuff(new Physical(a));
        a = new EightBallpart(0,0,2);
        CH.inv.addStuff(new Physical(a));
        a = new EightBallpart(0,0,5);
        CH.inv.addStuff(new Physical(a));
        a = new EightBallpart(0,0,7);
        CH.inv.addStuff(new Physical(a));
        
        Progress.The.SCIENCE = 2;
        
        return CH;
    }
}
