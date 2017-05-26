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
public class CatMousePuzzle extends Puzzle{
    
    Animal mouse;
    int catLocal = 0;
    CatA C;
    
    public synchronized void initiate(World w){
        
        int roomA = w.getRandomRoomNumber();
        int roomB = w.getRandomRoomNumber();
        
        Room m = w.getRoom(roomA);
        this.mouse = new Animal("Mouse",w,m);
        this.mouse.behav = new BehaviourFeral();
        this.mouse.adjective = "Impoverished";
        m.addActor(this.mouse);
        //Player.The.inv.addStuff(new Physical(this.mouse));
        
        C = new CatA(this.mouse);
        Room c = w.getRoom(roomB);
        c.addActor(C);
        catLocal = roomB;
        
        c.owner.storyRooms.add(roomA);
        m.owner.storyRooms.add(roomB);
        System.out.println("FATCAT- "+roomA + " " + m.id);
    }
    
    public synchronized void update(World w){
        
        
        try{
            w.storyRooms = new ArrayList<>();
            w.storyRooms.add(catLocal);
            int get = mouse.current.id;
            w.storyRooms.add(get);
        } catch (Exception E){}//If an exception... the mouse is probably held!
        
        if(C.done){
            this.solved = true;
        }
        
        if(C.dead){
            this.solved = true;
            C.done = false;
        }
    }
    
    public synchronized void solve(World w){
        Player.The.current.addActor(new Chest());
        owner.advance();
        
        if(C.done){
            Progress.The.animalChoices.add("Mouse");
            Progress.The.animalChoices.add("Cat");
            Progress.The.animalFriendliness.put("Mouse",0.0);
            Progress.The.animalFriendliness.put("Cat",1.0);
            Progress.The.animalFriendliness.put("Fatcat",1.0);
        } else {
            Progress.The.animalChoices.add("Mouse");
            Progress.The.animalChoices.add("Cat");
            Progress.The.animalFriendliness.put("Mouse",1.0);
            Progress.The.animalFriendliness.put("Cat",.5);
            Progress.The.animalFriendliness.put("Fatcat",0.0);
        }
    }
}
