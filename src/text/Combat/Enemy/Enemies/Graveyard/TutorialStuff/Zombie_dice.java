/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Enemy.Enemies.Graveyard.TutorialStuff;

import java.awt.Color;
import text.Combat.Dice.DiceRunner;
import text.Combat.Dice.DiceSet;
import text.Combat.Enemy.Attacks.RandomCombatSystem;
import text.Combat.Hero.HeroMapper;
import text.Combat.Hero.WordController;
import text.Images.TextImageComplex;
import text.Combat.Dice.Die;
import text.Combat.Enemy.Enemies.Enemy;
import text.Utility.ColorTuple;
import text.Utility.ImageLoader;
import static text.Utility.SuperRandom.oRan;

/**
 * @author FF6EB4
 */
public class Zombie_dice extends Enemy{
    public Zombie_dice(){
        super();
        this.name = "ZAM BRO";
        ImageLoader.switchMap("HUMAN");
        this.image = new TextImageComplex(ImageLoader.loadImage("zombie_stand.txt"));
        this.image.flip();
        
        this.turns = 30;
        
        DiceSet DS = new DiceSet();
        DiceRunner DR = new DiceRunner(DS);
        
        for(int i = 0; i < 3; ++i){
            DS.dice.add(Die.presets.get(oRan.nextInt(Die.presets.size())));
        }
        
        DS.startHits = 10;
        DS.missDivisor = 2000;
        
        this.AH = DR;
    }
    
    
    public String[] display(){
        return this.AH.display();
    }
    
    public String toString(){
        return "ZAM BRO";
    }
}
