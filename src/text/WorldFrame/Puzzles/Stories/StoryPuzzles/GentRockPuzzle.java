/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Stories.StoryPuzzles;

import text.Actors.Player;
import text.Combat.Enemy.Enemies.Enemy;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.GentRock;
import text.Frame.TextDisplay;
import text.Combat.Hero.WhistleHead;
import text.Tools.ToolParts.Bumper;
import text.WorldFrame.Progress;
import text.WorldFrame.Puzzles.Puzzle;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class GentRockPuzzle extends Puzzle{
    GentRock GR;
    public synchronized void initiate(World w){
        GR = new GentRock();
        GR.x = TextDisplay.SCREEN_SIZE_X/2;
        GR.y = TextDisplay.SCREEN_SIZE_Y/2;
        
        int r = w.getRandomRoomNumber();
        //int r = 0;
        w.getRoom(r).addActor(GR);
        //System.out.println("GRP ACTIVE");
        
        int id = GR.current.id;
        w.storyRooms.add(id);
    }
    
    public synchronized void update(World w){
        if(GR.defeated){
            this.solved = true;
        }
    }
    
    public void solve(World w){
        super.solve(w);
        this.owner.advance();
        this.owner.implementStory();
        
    }
}
