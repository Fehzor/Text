/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.Instances.Chests;

import text.Actors.Instances.Chests.Chest;
import java.util.ArrayList;
import text.Actors.*;
import text.Actors.Messages.Option;

/**
 *
 * @author FF6EB4
 */
public class LockedChest extends Chest{
    
    public boolean lock = true;
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> aList = super.pollOptions();
        
        if(lock == true){
            aList = new ArrayList<>();
            aList.add(Option.cancel(this));
            aList.add(Option.unlock(this));
        }
        
        return aList;
    }
}
