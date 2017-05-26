/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles;

import java.awt.Color;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Instances.VendingMachine;
import text.Actors.Player;
import text.Environment.Inanimate.Rock;
import text.Frame.TextDisplay;
import text.Inventory.Physical;
import text.Utility.ColorTuple;
import text.WorldFrame.World;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class VendingPuzzle extends Puzzle{
    VendingMachine VM;
    Rock R;
    
    public void initiate(World w){
        super.initiate(w);
        
        ColorTuple special = new ColorTuple(Color.GRAY,Color.GRAY,' ');
        R = new Rock(special,w.E);
        
        int adjChoice = oRan.nextInt(10);
        switch(adjChoice){
            case 0:
                R.adjective = "Smelly";
                break;
            case 1:
                R.adjective = "Useless";
                break;
            case 2:
                R.adjective = "Rotten";
                break;
            case 3:
                R.adjective = "Ugly";
                break;
            case 4:
                R.adjective = "Depressing";
                break;    
            case 5:
                R.adjective = "Forsaken";
                break;
            case 6:
                R.adjective = "Unwanted";
                break;
            case 7:
                R.adjective = "Diswanted";
                break;
            case 8:
                R.adjective = "Banal";
                break;
            case 9:
                R.adjective = "Aging";
                break;
            
        }
        
        VendingMachine VM = new VendingMachine(VendingMachine.WILD);
        VM.name = "Sad Vending Machine";
        
        VM.stock(R, "dim", 20);
        
        VM.x = TextDisplay.SCREEN_SIZE_X/2;
        VM.y = TextDisplay.SCREEN_SIZE_Y/2;
        
        int r = w.getRandomRoomNumber();

        w.getRoom(r).addActor(VM);
    }
        
    
    public void update(World w){
        ArrayList<Physical> get = Player.The.inv.queryStuff("rock");
        //System.out.println(get.size());
        for(Physical P : get){
            if(((Rock)P.getData()).adjective.equals(R.adjective)){
                solved = true;
            }
        }
    }
    
    public void solve(World w){
        super.solve(w);
    }
}
