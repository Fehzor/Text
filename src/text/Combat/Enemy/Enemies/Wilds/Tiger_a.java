/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Enemy.Enemies.Wilds;

import java.awt.Color;
import text.Combat.CombatSystem;
import text.Combat.Enemy.Attacks.RandomCombatSystem;
import text.Combat.Enemy.Enemies.Enemy;
import text.Combat.Hero.HeroMapper;
import text.Combat.Hero.WordController;
import text.Combat.RPS.RPSObject;
import text.Images.TextImageComplex;
import text.Utility.ColorTuple;
import text.Utility.ImageLoader;

/**
 * @author FF6EB4
 */
public class Tiger_a extends Enemy{
    public Tiger_a(){
        super();
        this.name = "Tiger";
        ImageLoader.switchMap("ANIMAL_SCHEME_C");
        this.image = new TextImageComplex(ImageLoader.loadImage("tiger_stand.txt"));
        this.image.flip();
        
        this.turns = 30;
        
        RPSObject RPS = new RPSObject(null,"null");
        CombatSystem CS = RPS.RPS;
        
        this.AH = CS;
    }
    
    public String[] display(){
        return this.AH.display();
    }
    
    public String toString(){
        return "Tiger";
    }
}
