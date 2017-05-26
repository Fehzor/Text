/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Stories.StoryPuzzles;

import text.Actors.Player;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.GentRock;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.RockerRock;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Zombie_a;
import text.Frame.TextDisplay;
import text.Combat.Hero.WhistleHead;
import text.Tools.ToolParts.Bumper;
import text.WorldFrame.Progress;
import text.WorldFrame.Puzzles.Puzzle;
import static text.WorldFrame.Templates.WorldTemplate.GRAVEYARD;
import text.WorldFrame.World;

public class Zombie_aPuzzle extends Puzzle{
    Zombie_a ZA;
    public synchronized void initiate(World w){
        ZA = new Zombie_a();
        ZA.x = TextDisplay.SCREEN_SIZE_X/2;
        ZA.y = TextDisplay.SCREEN_SIZE_Y/2;
        
        int r = w.getRandomRoomNumber();
        //int r = 0;
        w.getRoom(r).addActor(ZA);
        //System.out.println("GRP ACTIVE");
        
        int id = ZA.current.id;
        w.storyRooms.add(id);
    }
    
    public synchronized void update(World w){
        if(ZA.defeated){
            this.solved = true;
        }
    }
    
    public synchronized void solve(World w){
        this.owner.advance();
        this.owner.implementStory();
        Progress.addFlavor("ZombieFlavor", GRAVEYARD);
        Progress.removeStory(owner.name);
    }
    
}