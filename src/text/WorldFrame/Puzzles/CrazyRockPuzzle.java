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
public class CrazyRockPuzzle extends Puzzle{
    
    boolean grass = false;
    int stepstill = 25;
    
    TalkRock loner;
    public CrazyRockPuzzle(){
    }
    
    public void initiate(World w){
        super.initiate(w);
        ColorTuple special = new ColorTuple(Color.YELLOW,Color.YELLOW,' ');
        loner = new TalkRock(special,w.E,"THE GRASS. NO MORE GRASS.");
        loner.adjective = "Insane";
        loner.x = TextDisplay.SCREEN_SIZE_X/2;
        loner.y = TextDisplay.SCREEN_SIZE_Y/2;
        
        int r = w.getRandomRoomNumber();
        //int r = 0;
        w.getRoom(r).addActor(loner);
    }
    
    public void update(World w){
        super.update(w);
        if(Player.The.current.contains(loner)){
            //System.out.println("YES");
            if(Player.The.current.countActors(new SmallPlant()) == 0){
                grass = true;
                loner.message = "OH yEs GooddDd HOLD ME";
            }
        }
        if(loner.held == true && grass){
            if(stepstill > 0){
                stepstill -= 1;
                System.out.println(stepstill);
            } else {
                //String [] disp = new String[1];
                //disp[0] = "YES";
                //Player.The.current.owner.display(disp);
                loner.message = "SD:JVKSA:EJ :D :D :D :D";
                solved = true;
            }
        }
    }
    
    public void solve(World w){
        super.solve(w);
    }
            
}
