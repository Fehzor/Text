/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Stories.StoryPuzzles;

import text.WorldFrame.Puzzles.Stories.StoryPuzzles.*;
import java.awt.Color;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Instances.Chests.Chest;
import text.Actors.Instances.Food;
import text.Actors.Instances.VendingMachine;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalA;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalB;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalC;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalD;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalExtraA;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalExtraB;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalExtraC;
import text.Actors.NPC.NPCs.TutorialB.TutorialAnimalE;
import text.Actors.NPC.NPCs.TutorialB.TutorialAnimaExtraD;
import text.Actors.NPC.NPCs.TutorialB.TutorialAnimaExtraE;
import text.Actors.NPC.NPCs.TutorialB.TutorialAnimaExtraF;
import text.Actors.NPC.NPCs.TutorialB.TutorialAnimaExtraG;
import text.Actors.NPC.NPCs.TutorialB.TutorialAnimalF;
import text.Actors.NPC.NPCs.TutorialB.TutorialAnimalG;
import text.Actors.Player;
import text.Actors.PuzzleObjects.TalkRock;
import text.Frame.TextDisplay;
import text.Utility.ColorTuple;
import static text.Utility.SuperRandom.oRan;
import text.WorldFrame.Progress;
import text.WorldFrame.Puzzles.Puzzle;
import static text.WorldFrame.Templates.WorldTemplate.GRAVEYARD;
import text.WorldFrame.World;
import text.Actors.NPC.NPCs.WaveOne.*;
import text.Behaviour.BehaviourFeral;
import text.Environment.Animals.Animal;
import text.Inventory.Physical;
import text.WorldFrame.Puzzles.Puzzle;
import text.WorldFrame.Room;

/**
 *
 * @author FF6EB4
 */
public class DuckCityPuzzle extends Puzzle{
    
    public synchronized void initiate(World w){
        DuckA D = new DuckA(this);
        int rom = w.getRandomRoomNumber();
        w.storyRooms.add(rom);
        w.getRoom(rom).addActor(D);
    }
    
    public synchronized void update(World w){
       
    }
    
    public synchronized void solve(World w){
        Progress.removeStory("Duck City Story");
        Progress.addFlavor("Duck City Flavor", where);
        
        w.display(new String[]{"The faint quack of an overjoyed duck can be heard..."});
        
        Player.The.current.addActor(new Chest());
    }
}
