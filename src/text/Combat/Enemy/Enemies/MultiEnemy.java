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
import text.Actors.Actor;
import text.Actors.Instances.Chests.Chest;
import text.Actors.Messages.Option;
import text.Actors.Player;
import text.Behaviour.Behaviour;
import text.Combat.CombatRunner;
import text.Combat.Enemy.Attacks.Genting;
import text.Combat.CombatSystem;
import text.Frame.TextDisplay;
import text.Utility.ColorTuple;
import text.Utility.SuperRandom;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public abstract class MultiEnemy extends Enemy{
    
    public String[] messageAs;
    public String[] messageWins;
    public String[] messageLosses;
    public String[] messageBs;
    
    public int num  = 0;
    
    public MultiEnemy(){
        super();
    }
    
    public MultiEnemy(int n){
        super();
        this.num = n;
    }
    
    public ArrayList<Actor> pollOptions(){
        this.messageA = messageAs[num];
        this.messageB = messageBs[num];
        this.winString = messageWins[num];
        this.loseString = messageLosses[num];
        return super.pollOptions();
    }
    
    //Chooses this as a random enemy
    public void setRandom(){
        try{
            this.num = oRan.nextInt(messageAs.length);
        } catch (Exception E){ num = 0; }    
    }
}
