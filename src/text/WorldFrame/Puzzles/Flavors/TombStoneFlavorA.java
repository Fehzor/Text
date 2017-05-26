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
public class TombStoneFlavorA extends Flavor{

    public void initiate(World w){
        TalkRock TR;
        ColorTuple CT = new ColorTuple(Color.PINK,Color.PINK,' ');
        TR = new TalkRock(CT,"Tombstone",TYPE_TOMB,w.E);
        TR.message = "There's a dead guy under me....";
        TR.adjective = "Worried";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        int r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);

        TR = new TalkRock(CT,"Tombstone",TYPE_TOMB,w.E);
        TR.message = "What if someone robs me?? It'd be horrible!";
        TR.adjective = "Scared";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
        
        TR = new TalkRock(CT,"Tombstone",TYPE_TOMB,w.E);
        TR.message = "Technically the ground holds the body. I, am empty.";
        TR.adjective = "Philosophical";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
    }
}
