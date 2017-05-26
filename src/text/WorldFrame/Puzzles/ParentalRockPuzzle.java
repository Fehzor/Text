/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles;

import java.awt.Color;
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
public class ParentalRockPuzzle extends Puzzle{
    
    TalkRock parent,child,childTwo,childThree;
    public ParentalRockPuzzle(){
    }
    
    public void initiate(World w){

        ColorTuple special = new ColorTuple(Color.PINK,Color.PINK,' ');
        parent = new TalkRock(special,w.E,"FFFFFFFFFFFfffff All 3 of them!");
        child = new TalkRock(special,w.E,"I'm lost!");

        parent.adjective = "Parental";
        child.adjective = "Childish";

        childTwo = new TalkRock(special,w.E,"Help!");;
        childThree = new TalkRock(special,w.E,"Ripperoni!");;

        int x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        int y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);

        parent.x = x;
        parent.y = y;

        x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);

        child.x = x;
        child.y = y;

        x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X); 
        y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);

        childTwo.x = x;
        childTwo.y = y;

        x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X); 
        y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);

        childThree.x = x;
        childThree.y = y;

        int r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(parent);

        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(child);

        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(childTwo);

        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(childThree);
    }
    
    public void update(World w){
        if(w.currentRoom.contains(parent)){
            if(w.currentRoom.contains(child)){
                if(w.currentRoom.contains(childTwo)){
                    if(w.currentRoom.contains(childThree)){
                        solved = true;
                        parent.message = "Thank you.";
                        child.message = ":)";
                        childTwo.message = ":)";
                        childThree.message = ";3";
                    }
                }
            }
        }
    }
    
    public void solve(World w){
        super.solve(w);
    }
            
}
