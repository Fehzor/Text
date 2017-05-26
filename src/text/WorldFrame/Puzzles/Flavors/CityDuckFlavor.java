/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Flavors;

import java.awt.Color;
import text.Actors.NPC.NPCs.WaveOne.DuckExtraA;
import text.Actors.PuzzleObjects.TalkRock;
import text.Frame.TextDisplay;
import text.Utility.ColorTuple;
import text.WorldFrame.World;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class CityDuckFlavor extends Flavor{
    
    String name = "City Duck Flavor";
    
    public void initiate(World w){
        int rom = w.getRandomRoomNumber();

        w.getRoom(rom).addActor(new DuckExtraA());
    }
}
