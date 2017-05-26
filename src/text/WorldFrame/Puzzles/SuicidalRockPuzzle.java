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
public class SuicidalRockPuzzle extends Puzzle{
    
    int stepstill = 50;
    
    TalkRock loner;
    public SuicidalRockPuzzle(){
    }
    
    public void initiate(World w){
        super.initiate(w);
        ColorTuple special = new ColorTuple(Color.BLUE,Color.BLUE,' ');
        loner = new TalkRock(special,w.E,"Please. Do something.");
        loner.adjective = "Suicidal";
        loner.x = TextDisplay.SCREEN_SIZE_X/2;
        loner.y = TextDisplay.SCREEN_SIZE_Y/2;
        
        int r = w.getRandomRoomNumber();
        //int r = 0;
        w.getRoom(r).addActor(loner);
    }
    
    public void update(World w){
        super.update(w);
        if(loner.dead == true && loner.held == false){
            String [] disp = new String[1];
            disp[0] = "...";
            Player.The.current.owner.display(disp);
            done = true;
        }
        if(loner.held == true){
            if(stepstill > 0){
                stepstill -= 1;
                System.out.println(stepstill);
            } else {
                String [] disp = new String[1];
                disp[0] = "...Thanks broski.";
                Player.The.current.owner.display(disp);
                loner.adjective = "Bro";
                loner.message = "Take me with you forever!!! ;D";
                solved = true;
            }
        }
    }
    
    public void solve(World w){
        super.solve(w);
    }
            
}
