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
import text.Utility.ColorTuple;
import text.WorldFrame.World;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class SurprisePlantFlavor extends Flavor{

    public void initiate(World w){
        for(int i = 0; i<10; ++i){
            ColorTuple CT = new ColorTuple(Color.GRAY,Color.GRAY,' ');
            TalkRock surprise = new TalkRock(CT,w.E,"WASSUUUPPPP");
            SurprisePlant SP = new SurprisePlant(w.E,surprise);
            SP.adjective = "Surprise";
            SP.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
            SP.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
            int r = w.getRandomRoomNumber();
            w.getRoom(r).addActor(SP);
        }
    }
}
