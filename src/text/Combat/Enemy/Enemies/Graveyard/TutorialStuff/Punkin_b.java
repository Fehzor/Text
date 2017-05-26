/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Enemy.Enemies.Graveyard.TutorialStuff;

import java.awt.Color;
import text.Combat.CombatSystem;
import text.Combat.Enemy.Attacks.RandomCombatSystem;
import text.Combat.Enemy.Enemies.Enemy;
import text.Combat.Hero.HeroMapper;
import text.Combat.Hero.WordController;
import text.Images.TextImageComplex;
import text.Utility.ColorTuple;
import text.Utility.ImageLoader;

/**
 * @author FF6EB4
 */
public class Punkin_b extends Enemy{
    public Punkin_b(){
        super();
        this.name = "Jack";
        ImageLoader.switchMap("HUMAN");
        this.image = new TextImageComplex(ImageLoader.loadImage("jack_ol_lantern_stand.txt"));
        this.image.flip();
        
        this.turns = 10;
        
        messageA = "Grr!";
        messageB = "I'm in charge of cleaning the graves.";
        
        this.AH = generateAH();
        
    }
    
    private CombatSystem generateAH(){
        RandomCombatSystem RCS = new RandomCombatSystem();
        
        for(int i = 0; i < 5; ++i){
            HeroMapper temp = new HeroMapper();
            for(int j = 0; j < 5; ++j){
                if(j == i){
                    temp.addHero(10,j,2,(char)(j+(int)'a'));
                } else {
                    temp.addHero(1,j,.5,(char)(j+(int)'a'));
                }
            }
            RCS.addCombatSystem(temp,3);
        }
        
        
        return RCS;
    }
    
    
    public String[] display(){
        String[] ret = new String[7];
        ret[0] = "";
        ret[1] = "...OH MY!";
        ret[2] = "";
        ret[3] = "...She... she left out a letter!?";
        ret[4] = "";
        ret[5] = "...OR PERHAPS!";
        ret[6] = "";
        return ret;
    }
    
    public String toString(){
        return "Jack";
    }
}
