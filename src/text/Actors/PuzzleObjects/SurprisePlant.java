/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.PuzzleObjects;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Environment.Environment;
import text.Environment.Plants.SmallPlant;
import text.Inventory.Inventory;
import text.Inventory.Physical;

/**
 *
 * @author FF6EB4
 */
public class SurprisePlant extends SmallPlant{
        Inventory inv;
        Actor A;
        
        public SurprisePlant (Environment E, Actor A){
            super(makeSmallPlant(E));
            this.inv = new Inventory(1);
            inv.addStuff(new Physical(A));
            this.name = "Plant";
            this.A = A;
        }
    
        public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> aList = super.pollOptions();
        
        if(inv.full() != true){
            return aList;
        }
        
        Option E = Option.take(A,inv);
        aList.add(E);

        return aList;
    }
}
