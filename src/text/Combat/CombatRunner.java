/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat;

import java.util.ArrayList;
import text.Combat.PlayerCombatSystem.Body;
import text.Actors.Actor;
import text.Actors.Drawable;
import text.Actors.Instances.Chests.Chest;
import text.Actors.Player;
import text.Combat.Enemy.Enemies.Enemy;
import text.Frame.TextDisplay;
import text.Inventory.Resource;
import text.Utility.ColorTuple;
import text.Utility.ImageLoader;
import text.Utility.MenuBuilder;

/**
 *
 * @author FF6EB4
 */
public class CombatRunner extends Actor{
    Body TheBody = new Body();
    CombatSystem CSPlayer;
    CombatSystem CSEnemy;
    public static Enemy E;
    
    boolean playerTurn = true;
    int turn = 0;
    
    String[] disp;
    
    public CombatRunner(Enemy E){
        name = "Combat Runner";
        
        TextDisplay.darken = 990;
        Player.The.depth = 991;
        E.depth = 991;
        
        this.x = 0;
        this.y = 0;
        this.depth = 1000;
        this.blockable = false;
        dead = false;
        
        this.E = E;
        
        E.x = TextDisplay.SCREEN_SIZE_X - 20;
        E.y = 40;
        E.image.flip(true);
        
        CSPlayer = Player.The.combat;
        CSEnemy = E.AH;
        Player.The.paused = true;
        Player.The.x = 20;
        Player.The.y = 40;
        Player.The.image.flip(false);
        
        this.image = ImageLoader.loadImage("invisible.txt");
        
        //display();
        
        Player.The.current.addActor(this);
        Player.The.current.addActor(CSPlayer);
        
        CSPlayer.held = true;
        CSEnemy.held = true;
    }
    
    public boolean act(){
        Player.The.depth = 991;
        E.depth = 991;
        this.display();
        Player.The.paused = true;
        if(playerTurn){
            
            
            CSPlayer.go();
            if(CSPlayer.done()){
                this.turn += CSPlayer.hits - CSPlayer.misses * 2;
                CombatText.addPhrase(4,CSPlayer.hits+"DMG");
                CSPlayer.hits = 0;
                CSPlayer.misses = 0;
                playerTurn = false;
                Player.The.paused = true;
                Player.The.current.addActor(CSEnemy);
                //System.out.println("SWITCHING TO BOT");
            }
            //System.out.println("END PLAYER ACTION");
        } else {
            CSEnemy.dead = false;
            CSEnemy.go();
            if(CSEnemy.done()){
                
                while(CSEnemy.misses > 0){
                    CSEnemy.misses -= 1;
                    TheBody.damage(1);
                }
                
                playerTurn = true;
                this.turn += 1;
                if(E.dead(turn) != true){
                    CSPlayer.dead = false;
                    Player.The.current.addActor(CSPlayer);
                }
            }
            //System.out.println("END ENEMY ACTION");
        }
        
        
        if(E.dead(turn)){
            this.dead = true;
            Player.The.paused = false;
            E.paused = false;
            
            String[] disp = new String[1];
            disp[0] = E.winString;
            Player.The.current.owner.display(disp);
            Chest CH = E.loot();
            Player.The.current.addActor(CH);
            CSEnemy.reset();
            CSPlayer.reset();
            
            TextDisplay.darken = -1;
        }
        if(TheBody.dead()){
            //END THE FIGHT! THE PLAYER LOST!
            this.dead = true;
            Player.The.paused = false;
            E.paused = false;
            
            String[] disp = new String[1];
            disp[0] = E.loseString;
            Player.The.current.owner.display(disp);
            //System.out.println("PLAYER DEAD");
            CSEnemy.reset();
            CSPlayer.reset();
            
            TextDisplay.darken = -1;
        }
        
        //System.out.println("END TURN: "+turn);
        
        return true;
    }
    
    public void display(){
        String[] disp = TheBody.Display();
        
        String[] bigMessage;
        if(playerTurn){
            bigMessage = CSPlayer.display();
        } else {
            bigMessage = E.display();
        }
        
        for(int i = 0; i < 7; ++i){
            disp[i] += bigMessage[i];
        }
        //System.out.println(disp);
        
        Drawable D = MenuBuilder.buildDisplayFull(disp);
        this.image = D.image;
    }
    
    public String toString(){
        return "Combat Runner";
    }
}
