/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.Messages;

import text.WorldFrame.Room;
import java.awt.event.KeyEvent;
import text.Utility.*;
import text.Images.*;
import text.Frame.*;
import java.util.*;
import text.Actors.Actor;
import text.Actors.Player;
/**
 *
 * @author FF6EB4
 */
public class InspectMenu extends Menu {
    public ArrayList<Actor> aList;
    boolean subList;
    public InspectMenu(ArrayList<Actor> aList, Room W){
        super(parseList(aList),W);
        blockable = false;
        this.aList = aList;
        subList = false;
    }
    
    public static String[] parseList(ArrayList<Actor> aList){
        String[] ret = new String[aList.size()];
        for(int i = 0; i<aList.size(); ++i){
            ret[i] = aList.get(i).toString();
        }
        return ret;
    }
    
    public boolean act(){
        super.act();
        if(TextListener.firstPress(KeyEvent.VK_ESCAPE)){
            this.dead = true;
            this.done = true;
            Player.The.paused = false;
            return true;
        }
        
        if(done){
            if(aList.get(selection).pollOptions().size() == 0){
               aList.get(selection).act();
               for(Actor A : aList){
                   A.paused = false;
               }
               return true; 
            } else {
                for(Actor A : aList){
                    if(A != aList.get(selection)){
                        A.paused = false;
                    }
                }
                ArrayList<Actor> nextList = aList.get(selection).pollOptions();
                if(nextList == null) {
                    return true;
                }
                InspectMenu next = new InspectMenu(nextList,W);
                next.subList = true;
                done = false;
                
            }
        }
        
        return true;
    }
}
