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
public class PostFactRockFlavor extends Flavor{

    public void initiate(World w){
        TalkRock TR;
        ColorTuple CT = new ColorTuple(Color.ORANGE,Color.RED,' ');
        TR = new TalkRock(CT,w.E,"The world you live in is post fact too.");
        TR.adjective = "Ancient";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        int r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
        
        TR = new TalkRock(CT,w.E,"The world you're exploring.. it's post fact.");
        TR.adjective = "Observant";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
        
        TR = new TalkRock(CT,w.E,"Nothing is ever real.");
        TR.adjective = "Mystic";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
        
        CT = new ColorTuple(Color.CYAN,Color.CYAN,' ');
        TR = new TalkRock(CT,w.E,"Wisdom is knowing you know nothing.");
        TR.adjective = "Wise";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
        
        TR = new TalkRock(CT,w.E,"Swag is knowing it doesn't matter.");
        TR.adjective = "Swag";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
        
        TR = new TalkRock(CT,w.E,"If nothing matters, why are you here?");
        TR.adjective = "Timid";
        TR.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        TR.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        r = w.getRandomRoomNumber();
        w.getRoom(r).addActor(TR);
    }
}
