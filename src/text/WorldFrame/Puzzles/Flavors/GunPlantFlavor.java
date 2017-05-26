/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Flavors;

import java.awt.Color;
import text.Actors.PuzzleObjects.SurprisePlant;
import text.Actors.PuzzleObjects.TalkRock;
import text.Frame.TextDisplay;
import text.Tools.ToolParts.Revolver;
import text.Utility.ColorTuple;
import text.WorldFrame.World;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class GunPlantFlavor extends Flavor{

    public void initiate(World w){
        Revolver surprise = new Revolver();
        SurprisePlant SP = new SurprisePlant(w.E,surprise);
        SP.adjective = "Suspicious";
        SP.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        SP.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        int r = 0;
        //int r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(SP);
        
        TalkRock TR;
        ColorTuple CT = new ColorTuple(Color.RED,Color.RED,' ');
        TR = new TalkRock(CT,w.E,"I... I... Ugh...");
        TR.adjective = "Degenerate";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        w.getRoom(r).addActor(TR);
    }
}
