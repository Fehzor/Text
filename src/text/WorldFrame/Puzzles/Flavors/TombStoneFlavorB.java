/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Flavors;

import java.awt.Color;
import text.Actors.PuzzleObjects.TalkRock;
import static text.Environment.Inanimate.Rock.TYPE_TOMB;
import text.Frame.TextDisplay;
import text.Utility.ColorTuple;
import text.WorldFrame.World;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class TombStoneFlavorB extends Flavor{

    public void initiate(World w){
        TalkRock TR;
        ColorTuple CT = new ColorTuple(Color.PINK,Color.PINK,' ');
        TR = new TalkRock(CT,"Tombstone",TYPE_TOMB,w.E);
        TR.message = "Love is not a victory march.";
        TR.adjective = "Joyous";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        TR.depth = TR.y;
        int r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);

        TR = new TalkRock(CT,"Tombstone",TYPE_TOMB,w.E);
        TR.message = "There is nothing for you here.";
        TR.adjective = "Lovely";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
        
        TR = new TalkRock(CT,"Tombstone",TYPE_TOMB,w.E);
        TR.message = "Corporations are gods too.";
        TR.adjective = "Delightful";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
    }
}
