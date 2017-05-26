/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles;

import java.awt.Color;
import text.Actors.Actor;
import text.Actors.Instances.Door;
import text.Actors.Player;
import text.Actors.PuzzleObjects.TalkRock;
import text.Environment.Plants.SmallPlant;
import text.Environment.Inanimate.Rock;
import text.Frame.TextDisplay;
import text.Utility.ColorTuple;
import text.WorldFrame.Templates.WildTemplate;
import text.WorldFrame.Templates.WorldBuilder;
import static text.WorldFrame.Templates.WorldBuilder.oRan;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class HeldRockPuzzle extends Puzzle{
    
    boolean room = false;
    boolean held = false;
    boolean down = false;
    
    TalkRock heldRock;
    public HeldRockPuzzle(){
    }
    
    public void initiate(World w){
        super.initiate(w);
        ColorTuple special = new ColorTuple(Color.YELLOW,Color.YELLOW,' ');
        heldRock = new TalkRock(special,w.E,"I'll be staying right here thank you.");
        heldRock.adjective = "Stubborn";
        heldRock.x = TextDisplay.SCREEN_SIZE_X/2;
        heldRock.y = TextDisplay.SCREEN_SIZE_Y/2;
        
        int r = w.getRandomRoomNumber();
        //int r = 0;
        w.getRoom(r).addActor(heldRock);
    }
    
    public void update(World w){
        super.update(w);
        
        if(heldRock.held == true && room && !held){
            String [] disp = new String[1];
            disp[0] = "NUUU PUT ME DOWN";
            Player.The.current.owner.display(disp);
            heldRock.message = "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
            held = true;
        }
        
        if(held && heldRock.held == false){
            String [] disp = new String[1];
            disp[0] = "NEVER. AGAIN.";
            Player.The.current.owner.display(disp);
            heldRock.message = "NO.";
            solved = true;
        }
        
        if(Player.The.current.contains(heldRock) && !held){
            room = true;
        } else {
            room = false;
        }

        
    }
    
    public void solve(World w){
        super.solve(w);
    }
            
}
