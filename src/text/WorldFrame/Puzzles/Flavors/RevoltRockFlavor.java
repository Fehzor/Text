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
public class RevoltRockFlavor extends Flavor{

    public void initiate(World w){
        TalkRock TR;
        ColorTuple CT = new ColorTuple(Color.RED,Color.RED,' ');
        TR = new TalkRock(CT,w.E,"The era needed ending. There was no other way.");
        TR.adjective = "Comrade";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        int r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
        
        TR = new TalkRock(CT,w.E,"We were what made the world perfect.");
        TR.adjective = "Comrade";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
        
        TR = new TalkRock(CT,w.E,"To strive, is to become.");
        TR.adjective = "Comrade";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
        
        CT = new ColorTuple(Color.BLUE,Color.BLUE,' ');
        TR = new TalkRock(CT,w.E,"Perfection would never lead to this.");
        TR.adjective = "Loyalist";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
        
        TR = new TalkRock(CT,w.E,"Perfection was only ever an ideal.");
        TR.adjective = "Loyalist";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
        
        TR = new TalkRock(CT,w.E,"To strive is nothing.");
        TR.adjective = "Loyalist";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
    }
}
