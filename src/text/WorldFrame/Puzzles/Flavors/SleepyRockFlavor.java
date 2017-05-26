/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Flavors;

import java.awt.Color;
import text.Actors.PuzzleObjects.TalkRock;
import text.Frame.TextDisplay;
import text.Utility.ColorTuple;
import text.WorldFrame.World;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class SleepyRockFlavor extends Flavor{

    public void initiate(World w){
        TalkRock TR;
        ColorTuple CT = new ColorTuple(Color.PINK,Color.PINK,' ');
        TR = new TalkRock(CT,w.E,".........what???");
        TR.adjective = "Sleepy";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        int r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
        
        TR = new TalkRock(CT,w.E,"ZZZZZzzzzzzzzzzzzzzzzz");
        TR.adjective = "Sleepy";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
        
        TR = new TalkRock(CT,w.E,"idc dude");
        TR.adjective = "Sleepy";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
        
        CT = new ColorTuple(Color.CYAN,Color.CYAN,' ');
        TR = new TalkRock(CT,w.E,"Nooooo");
        TR.adjective = "Sleepy";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
        
        TR = new TalkRock(CT,w.E,"Swag.");
        TR.adjective = "Cool";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
        
        TR = new TalkRock(CT,w.E,"hey");
        TR.adjective = "Timid";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
    }
}
