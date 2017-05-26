/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Stories.StoryPuzzles;

import java.awt.Color;
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
import static text.WorldFrame.Progress.putStory;
import text.WorldFrame.Puzzles.Puzzle;
import static text.WorldFrame.Templates.WorldTemplate.GRAVEYARD;
import static text.WorldFrame.Templates.WorldTemplate.WILDS;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class TutorialB extends Puzzle{
    
    Food cake = new Food();
    TutorialAnimalF F = new TutorialAnimalF(cake);
    
    public synchronized void initiate(World w){
        w.getRoom(0).addActor(new TutorialAnimalE());
        w.getRoom(oRan.nextInt(9)).addActor(new TutorialAnimaExtraD());
        w.getRoom(oRan.nextInt(9)).addActor(new TutorialAnimaExtraE());
        w.getRoom(oRan.nextInt(9)).addActor(new TutorialAnimaExtraF());
        w.getRoom(oRan.nextInt(9)).addActor(new TutorialAnimaExtraG());
        
        w.getRoom(5).addActor(F);
        w.storyRooms.add(5);
        
        VendingMachine VM = new VendingMachine();
        VM.x=TextDisplay.SCREEN_SIZE_X / 2;
        VM.y=TextDisplay.SCREEN_SIZE_Y / 2;
        
        VM.stock(cake, "all", 25);
        VM.stock(cake, "all", 25);
        VM.stock(cake, "all", 25);
        
        w.getRoom(6).addActor(VM);
        w.getRoom(6).addActor(new TutorialAnimalG());
    }
    
    public synchronized void update(World w){
        if(F.done){
            this.solved = true;
        }
    }
    
    public synchronized void solve(World w){
        //this.owner.advance();
        Progress.removeStory("Tutorial Story");
        Progress.addFlavor("Tutorial Pigs",WILDS);
        
        Progress.The.citySize = 1;
        
        Progress.putStory("Cat Story",WILDS);
        Progress.The.animalChoices.add("Cat");
        Progress.The.animalFriendliness.put("Cat",1.0);
        
        Progress.putStory("Duck City Story",WILDS);
        //this.owner.implementStory();
    }
}
