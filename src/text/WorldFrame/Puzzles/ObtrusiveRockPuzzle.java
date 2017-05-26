/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles;

import java.awt.Color;
import java.util.ArrayList;
import text.Actors.Instances.Door;
import text.Actors.Player;
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
public class ObtrusiveRockPuzzle extends Puzzle{
    
    Rock r;
    ArrayList<Rock> rList = new ArrayList<>();
    
    public ObtrusiveRockPuzzle(){
    }
    
    public void initiate(World w){

        ColorTuple special = new ColorTuple(Color.BLACK,Color.WHITE,' ');
        r = new Rock(special,w.E);
        r.adjective = "No Good Rotten";
        
        for(int i = 0; i<3; ++i){
            rList.add(r.clone());
            
            int x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
            int y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);

            int r = w.getRandomRoomNumber();
            
            rList.get(i).x = x;
            rList.get(i).y = y;
            w.getRoom(r).addActor(rList.get(i));
        }



    }
    
    public void update(World w){
        
        boolean flag = true;
        for(int i = 0; i<3; ++i){
            if(!w.currentRoom.contains(rList.get(i))){
                flag = false;
            }
        }
        if(flag){
            for(int i = 0; i<3; ++i){
                rList.get(i).adjective = "Gang-Member";
            }
            solved = true;
        }
        
        flag = true;
        for(int i = 0; i<3; ++i){
            if(rList.get(i).dead == false){
                flag = false;
            }
        }
        if(flag){
            solved = true;
        }
    }
    
    public void solve(World w){
        super.solve(w);
    }
            
}
