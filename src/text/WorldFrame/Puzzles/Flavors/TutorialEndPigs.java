/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Flavors;

import java.awt.Color;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalExtraA;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalExtraB;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalExtraC;
import text.Actors.PuzzleObjects.TalkRock;
import text.Frame.TextDisplay;
import text.Utility.ColorTuple;
import static text.Utility.SuperRandom.oRan;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class TutorialEndPigs extends Flavor{

    public void initiate(World w){
        w.getRoom(oRan.nextInt(w.roomCount())).addActor(new TutorialAnimalExtraA());
        w.getRoom(oRan.nextInt(w.roomCount())).addActor(new TutorialAnimalExtraB());
        w.getRoom(oRan.nextInt(w.roomCount())).addActor(new TutorialAnimalExtraC());
    }
}
