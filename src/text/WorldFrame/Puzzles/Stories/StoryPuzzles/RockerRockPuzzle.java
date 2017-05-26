/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Stories.StoryPuzzles;

import text.Actors.Instances.Chests.Chest;
import text.Actors.Player;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.GentRock;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.RockerRock;
import text.Frame.TextDisplay;
import text.Inventory.Physical;
import text.Combat.Hero.WhistleHead;
import text.Tools.ToolParts.Bumper;
import text.WorldFrame.Puzzles.Puzzle;
import text.WorldFrame.World;

public class RockerRockPuzzle extends Puzzle{
    RockerRock RR;
    public synchronized void initiate(World w){
        RR = new RockerRock();
        RR.x = TextDisplay.SCREEN_SIZE_X/2;
        RR.y = TextDisplay.SCREEN_SIZE_Y/2;
        
        int r = w.getRandomRoomNumber();
        //int r = 0;
        w.getRoom(r).addActor(RR);
        //System.out.println("GRP ACTIVE");
        
        int id = RR.current.id;
        w.storyRooms.add(id);
    }
    
    public synchronized void update(World w){
        
        if(RR.defeated){
            this.solved = true;
        }
    }
    
    public synchronized void solve(World w){
        if(done) return;
        done = true;
        
        
        
        String [] disp = new String[2];
        disp[0] = "To attack, you'll need an instrument.";
        disp[1] = "Combine these parts!";
        Player.The.current.owner.display(disp);
        
        
        this.owner.advance();
        this.owner.implementStory();
    }
    
    
    
}