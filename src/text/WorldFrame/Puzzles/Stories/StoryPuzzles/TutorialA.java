/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Stories.StoryPuzzles;

import java.awt.Color;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalA;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalB;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalC;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalD;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalExtraA;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalExtraB;
import text.Actors.NPC.NPCs.TutorialA.TutorialAnimalExtraC;
import text.Actors.Player;
import text.Actors.PuzzleObjects.TalkRock;
import text.Frame.TextDisplay;
import text.Utility.ColorTuple;
import static text.Utility.SuperRandom.oRan;
import text.WorldFrame.Progress;
import text.WorldFrame.Puzzles.Puzzle;
import static text.WorldFrame.Templates.WorldTemplate.GRAVEYARD;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class TutorialA extends Puzzle{
    
    TutorialAnimalD D = new TutorialAnimalD();
    TutorialAnimalC C = new TutorialAnimalC();
    TutorialAnimalB B = new TutorialAnimalB();
    TutorialAnimalA A = new TutorialAnimalA();
    
    boolean a,b,c,spawned;
    
    public synchronized void initiate(World w){
            a = false;
            b = false;
            c = false;
            spawned = false;

            w.getRoom(0).addActor(A);
            w.getRoom(1).addActor(B);
            w.getRoom(2).addActor(C);
            
            w.getRoom(oRan.nextInt(4)).addActor(new TutorialAnimalExtraA());
            w.getRoom(oRan.nextInt(4)).addActor(new TutorialAnimalExtraB());
            w.getRoom(oRan.nextInt(4)).addActor(new TutorialAnimalExtraC());
    }
    
    public synchronized void update(World w){
        if(Player.The.current.contains(D)){
            this.solved= true;
        }
        if(Player.The.current.contains(C)){
            c = true;
        }
        if(Player.The.current.contains(B)){
            b = true;
        }
        if(Player.The.current.contains(A)){
            a = true;
        }
        
        
        if(a&&b&&c&&!spawned){
            w.getRoom(3).addActor(D);
            spawned = true;
        }
    }
    
    public synchronized void solve(World w){
        this.owner.advance();
        //this.owner.implementStory();
        Progress.The.animalChoices.add("Duck");
        Progress.The.animalFriendliness.put("Duck",1.0);
    }
}
