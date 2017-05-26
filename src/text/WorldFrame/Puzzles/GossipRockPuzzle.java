/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Puzzles;

import java.awt.Color;
import text.Actors.Instances.Door;
import text.Actors.Player;
import text.Actors.PuzzleObjects.TalkRock;
import text.Environment.Inanimate.Rock;
import text.Frame.TextDisplay;
import text.Utility.ColorTuple;
import text.WorldFrame.Templates.WildTemplate;
import text.WorldFrame.Templates.WorldBuilder;
import static text.WorldFrame.Templates.WorldBuilder.oRan;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */

//TODO MAKE THIS

public class GossipRockPuzzle extends Puzzle{
    
    boolean ac = false;
    boolean bc = false;
    boolean abc = false;
    TalkRock a,b,c;
    public GossipRockPuzzle(){
    }
    
    public void initiate(World w){

        ColorTuple special = new ColorTuple(Color.GREEN,Color.GREEN,' ');
        a = new TalkRock(special,w.E,"Dwayne is totally that guy.");
        b = new TalkRock(special,w.E,"Ya I heard he eats too much cod.");
        c = new TalkRock(special,w.E,"...");
        
        a.adjective = "Gossipy";
        b.adjective = "Gossipy";
        c.adjective = "Dwayne the";

        int x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        int y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        a.x = x;
        a.y = y;
        
        x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        b.x = x;
        b.y = y;
        
        x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
        c.x = x;
        c.y = y;

        int r = w.getRandomRoomNumber();
        //r=0;
        w.getRoom(r).addActor(a);
        w.getRoom(r).addActor(b);
        r = w.getRandomRoomNumber();
        //r=1;
        w.getRoom(r).addActor(c);
    }
    
    public void update(World w){
        if(w.currentRoom.contains(a)&&w.currentRoom.contains(c)){
            c.message = "It hurts me when you say things like that.";
            a.message = "I never knew...";
            ac = true;
        }
        
        if(w.currentRoom.contains(b)&&w.currentRoom.contains(c)){
            c.message = "I just wanted to get more muscles.";
            b.message = "How admirable.";
            bc = true;
        }
        
        if(w.currentRoom.contains(a)&&w.currentRoom.contains(b)){
            if(ac&&bc){
                b.message = "We should really be nicer to Dwayne.";
                a.message = "Ya I might even eat cod too.";
                abc = true;
            } else {
                b.message = "Dwayne is totally that guy.";
                a.message = "Ya I heard he eats too much cod.";
                bc = false;
                ac = false;
            }
        }
        if(w.currentRoom.contains(a)&&w.currentRoom.contains(b)&&w.currentRoom.contains(c)){
            if(abc){
                b.message = "What the lovely day it is.";
                a.message = "Hello Dwayne, good fren yes yes.";
                c.message = "Wow... you guys rock!";
                solved = true;
            } else {
                b.message = "Dwayne is totally that guy.";
                a.message = "Ya I heard he eats too much cod.";
                c.message = "Oh god.. I can't. Also quit cod..";
                bc = false;
                ac = false;
            }
        }
    }
    
    public void solve(World w){
        super.solve(w);
    }
            
}
