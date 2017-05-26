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
public class LonelyRockPuzzle extends Puzzle{
    
    TalkRock loner;
    public LonelyRockPuzzle(){
    }
    
    public void initiate(World w){
        super.initiate(w);
        ColorTuple special = new ColorTuple(Color.BLUE,Color.BLUE,' ');
        loner = new TalkRock(special,w.E,":(");
        loner.adjective = "Lonely";
        loner.x = TextDisplay.SCREEN_SIZE_X/2;
        loner.y = TextDisplay.SCREEN_SIZE_Y/2;
        
        int r = w.getRandomRoomNumber();

        w.getRoom(r).addActor(loner);
    }
    
    public void update(World w){
        super.update(w);
        if(w.currentRoom.contains(loner)){
            if(w.currentRoom.countActors(loner) > 12){
                loner.adjective = "Popular";
                if(oRan.nextInt(100) > 15){
                    solved = true;
                    
                    String[] disp = new String[1];
                    disp[0] = "What a wonderful day outside.";
                    Player.The.current.owner.display(disp);
                } else {
                    done = true;
                    String[] disp = new String[1];
                    disp[0] = "You feel empty inside.";
                    Player.The.current.owner.display(disp);
                }
            }
            
            if(w.currentRoom.countActors(loner) == 1){
                loner.adjective = "Suicidal";
                if(oRan.nextInt(100) > 10){
                    solved = true;
                    String[] disp = new String[1];
                    disp[0] = "You feel empty inside.";
                    Player.The.current.owner.display(disp);
                } else {
                    done = true;
                    String[] disp = new String[1];
                    disp[0] = "You feel empty inside.";
                    Player.The.current.owner.display(disp);
                }
            }
        }
    }
    
    public void solve(World w){
        super.solve(w);
    }
            
}
