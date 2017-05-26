/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Enemy.Enemies;

import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Zombie_b;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Punkin_b;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Zombie_a;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Zombie_c;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Punkin_a;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Zombie_d;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import text.Actors.Actor;
import text.Actors.Instances.Chests.Chest;
import text.Actors.Messages.Option;
import text.Actors.Player;
import text.Behaviour.Behaviour;
import text.Combat.CombatRunner;
import text.Combat.Enemy.Attacks.Genting;
import text.Combat.CombatSystem;
import text.Frame.TextDisplay;
import text.Images.TextImageAnimated;
import text.Images.TextImageComplex;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import text.Utility.SuperRandom;

/**
 *
 * @author FF6EB4
 */
public abstract class Enemy extends Actor{
    
    public static ArrayList<Enemy> graveyardEnemies = loadGYE();
    
    Behaviour behav = new Behaviour();
    
    public CombatSystem AH = new CombatSystem();

    public boolean done = false;
    
    public boolean defeated = false;
    
    public int turns = 20;
    
    public String messageA = "Wanna fight??";
    public String messageB = "You sure showed me!";
    public String winString = "You won!";
    public String loseString = "You lost! Try again next time!";
      
    public Enemy(){
        this.dead = false;
        this.x = SuperRandom.nextIntGaussian(TextDisplay.SCREEN_SIZE_X);
        this.y = SuperRandom.nextIntGaussian(TextDisplay.SCREEN_SIZE_Y);
    }
    
    public boolean act(){
        if(paused){
            return true;
        }
        behav.act(this);
        this.depth = this.y;
        return true;
    }
    
    public boolean go(){
        AH.act();
        if(AH.done()){
            this.done = true;
        }
        return true;
    }
    
    public boolean done(){
        if(this.done){
            done = false;
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Returns true when this enemy is dead.
     * @param turn The current turn of the battle
     * @return 
     */
    public boolean dead(int turn){
        if(turn > turns){
            this.defeated = true;
            return true;
        }
        
        return false;
    }
    
    public String[] display(){
        String[] ret = new String[7];
        ret[0] = "";
        ret[1] = "";
        ret[2] = "";
        ret[3] = "...The enemy growls! GRRRRR...";
        ret[4] = "";
        ret[5] = "";
        ret[6] = "";
        return ret;
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = new ArrayList<>();
        
        ret.add(Option.cancel(this));
        
        
        
        if(defeated){
            ret.add(Option.display(this, "Talk", messageB));
        } else {
            ret.add(Option.display(this, "Talk", messageA));
        
        
            ret.add(new Option(this){
                public boolean act(){
                    CombatRunner CR = new CombatRunner((Enemy)owner);
                    return true;
                }

                public String toString(){
                    return "Challenge";
                }
            });
        }
        return ret;
    }
    
    public static ArrayList<Enemy> loadGYE(){
        ArrayList<Enemy> ret = new ArrayList<>();
        
        ret.add(new Zombie_a());
        ret.add(new Zombie_b());
        ret.add(new Zombie_c());
        ret.add(new Zombie_d());
        ret.add(new Punkin_a());
        ret.add(new Punkin_b());
        
        return ret;
    }
    
    public Chest loot(){
        Chest CH = new Chest();
        
        CH.generateRewardsBasic();
        CH.generateRewardsBasic();
        
        return CH;
    }
    
    public void recolor(ColorTuple A, ColorTuple B, ColorTuple C, ColorTuple D){
        
        this.image = image.clone();
        
        //0,1,2,3
        //0 = fur
        //1 = coat
        //2 = bone
        HashMap<Character,ColorTuple> map = ImageBuilder.buildColorMap(C, A, B, D);
        
        //for(int i = 0; i< 100; ++i)System.out.println(oRan.nextInt(animalNames.size()));
        
        ImageBuilder.swapColorScheme((TextImageComplex)image, map);
    }
}
