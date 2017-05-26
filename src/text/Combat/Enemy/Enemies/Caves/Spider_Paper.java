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
import text.Images.TextImageAnimated;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import text.Utility.ColorTuple;
import text.Utility.ImageLoader;

/**
 * @author FF6EB4
 */
public class Spider_Paper extends MultiEnemy{
    public Spider_Paper(){
        super();
        this.name = "Cave Spider";
        ImageLoader.switchMap("GREYSCALE");
        this.image = new TextImageComplex(ImageLoader.loadImage("spider_stand.txt"));
        
        ColorTuple purple = new ColorTuple(Color.MAGENTA,Color.WHITE,' ');
        ColorTuple green = new ColorTuple(Color.GREEN,Color.YELLOW,' ');
        this.recolor(purple,green,purple,green);
        
        this.image.flip();
        
        this.turns = 20;
        
        RPSObject RPS = new RPSObject(null,"null");
        CombatSystem CS = RPS.RPS;
        
        this.AH = CS;
        
        messageAs = new String[]{
            "If you want to dig down any further you have to beat me.",
            "I don't believe there's anything down there.",
            "I am a paper spider rofl",
            "I am going to choose rock.",
            "I dare not leave the confines of dirt and rock."
        };
    
        messageBs = new String[]{
            "...It's just the way it is I guess.",
            "Not that I'm stopping you. Any more, at least.",
            "Obvious spider is obvious???",
            "We have fun here.",
            "Except for the occasion."
        };
        
        messageWins = new String[]{
            "...But paper is the best????",
            "Go ahead I suppose.",
            "The power of paper.... it should never have failed!",
            "Hhahahaahaha it was a lie!",
            "You did it. Good job."
        };
        
        messageLosses = new String[]{
            "You'll never beat the likes of paper!",
            "It's better you stay with me.",
            "I mean I told you..",
            "GOTCHA",
            "You failed. Bad job."
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
