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
public class MouseMousePuzzle extends Puzzle{
    
    MouseFall MF = new MouseFall();
    MouseMother MM = new MouseMother("The mouse children! They've gone from us!","Oh thank goodness!",MF);
    
    MouseFall MF1 = new MouseFall();
    MouseMother MM1= new MouseMother("The pigs tried to take them.. I hope they slipped away.","I was so worried...",MF1);
    
    MouseFall MF2 = new MouseFall();
    MouseMother MM2 = new MouseMother("Please help us.","..I was worried sick.",MF2);
    
    public synchronized void initiate(World w){
        
        int roomA = w.getRandomRoomNumber();
        
        int roomB = w.getRandomRoomNumber();
        while(roomB == roomA){
            roomB = w.getRandomRoomNumber();
        }
        int roomC = w.getRandomRoomNumber();
        while(roomC == roomA){
            roomC = w.getRandomRoomNumber();
        }
        int roomD = w.getRandomRoomNumber();
        while(roomD == roomA){
            roomD = w.getRandomRoomNumber();
        }
        
        w.storyRooms.add(roomA);
        w.storyRooms.add(roomB);
        w.storyRooms.add(roomC);
        w.storyRooms.add(roomD);
        
        MM.name += " Linda";
        MM1.name += " Tasha";
        MM2.name += " Dave";
        
        w.getRoom(roomA).addActor(MM);
        w.getRoom(roomA).addActor(MM1);
        w.getRoom(roomA).addActor(MM2);
        
        w.getRoom(roomB).addActor(MF);
        w.getRoom(roomC).addActor(MF1);
        w.getRoom(roomD).addActor(MF2);
        
        
    }
    
    public synchronized void update(World w){
        if(oRan.nextInt(100) < 20){
            //System.out.println("DDDRROPPP THE MOUSE");
            if(Player.The.inv.inspectStuff().contains(MF)){
                Player.The.inv.removeStuff(MF);
                
                MF.held = false;
                MF.dead = false;
                
                MF.x = Player.The.x;
                MF.y = Player.The.y;
                
                Player.The.current.addActor(MF);
                
            }
        }
        if(oRan.nextInt(100) < 20){
            //System.out.println("DDDRROPPP THE MOUSE");
            if(Player.The.inv.inspectStuff().contains(MF1)){
                Player.The.inv.removeStuff(MF1);
                
                MF1.held = false;
                MF1.dead = false;
                
                MF1.x = Player.The.x;
                MF1.y = Player.The.y;
                
                Player.The.current.addActor(MF1);
                
            }
        }
        if(oRan.nextInt(100) < 20){
            //System.out.println("DDDRROPPP THE MOUSE");
            if(Player.The.inv.inspectStuff().contains(MF2)){
                Player.The.inv.removeStuff(MF2);
                
                MF2.held = false;
                MF2.dead = false;
                
                MF2.x = Player.The.x;
                MF2.y = Player.The.y;
                
                Player.The.current.addActor(MF2);
                
            }
        }
        
        try{
            w.storyRooms = new ArrayList<>();
            w.storyRooms.add(MM.current.id);
        }catch (Exception E){}
            
        try{
            w.storyRooms.add(MF.current.id);
        }catch (Exception E){}
        
        try{
            w.storyRooms.add(MF1.current.id);
        }catch (Exception E){}
        
        try{
            w.storyRooms.add(MF2.current.id);
        }catch (Exception E){}
        
            

        if(MM.done && MM1.done && MM2.done){
            this.solved = true;
        }
    }
    
    public synchronized void solve(World w){
        Player.The.current.addActor(new Chest());
        
    }
}
