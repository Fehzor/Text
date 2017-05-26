/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles.Stories.StoryPuzzles;

import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Zombie_b;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Punkin_b;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Zombie_a;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Zombie_c;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Punkin_a;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.Zombie_d;
import java.util.ArrayList;
import text.Actors.Player;
import text.Combat.Enemy.Enemies.Enemy;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.GentRock;
import text.Combat.Enemy.Enemies.Graveyard.TutorialStuff.RockerRock;
import text.Combat.Enemy.Enemies.*;
import text.Frame.TextDisplay;
import text.Combat.Hero.WhistleHead;
import text.Tools.ToolParts.Bumper;
import text.Utility.SuperRandom;
import static text.Utility.SuperRandom.oRan;
import text.WorldFrame.Puzzles.Flavors.Flavor;
import text.WorldFrame.Puzzles.Puzzle;
import text.WorldFrame.World;

public class ZombieFlavor extends Flavor{
    public ArrayList<Enemy> enemyList = loadZambies();
    
    public synchronized void initiate(World w){
        Enemy temp = null;
        for(int i = 0; i<Math.sqrt(w.roomCount()); ++i){
            temp = enemyList.get(oRan.nextInt(enemyList.size()));
            
            temp.x = SuperRandom.nextIntGaussian(TextDisplay.SCREEN_SIZE_X);
            temp.y = SuperRandom.nextIntGaussian(TextDisplay.SCREEN_SIZE_Y);

            int r = w.getRandomRoomNumber();
            w.getRoom(r).addActor(temp);
        }
    
    }
    
    public synchronized void update(World w){}
    
    public static ArrayList<Enemy> loadZambies(){
        ArrayList<Enemy> ret = new ArrayList<>();
        ret.add(new Zombie_a());
        ret.add(new Zombie_b());
        ret.add(new Zombie_c());
        ret.add(new Zombie_d());
        ret.add(new Punkin_a());
        ret.add(new Punkin_b());
        return ret;
    }
    
}