/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors;

import java.util.ArrayList;
import text.Actors.Messages.Option;
import text.Images.TextImage;
import text.Images.TextImageBasic;
import text.Tools.Tool;

/**
 *
 * @author FF6EB4
 */
public class Equipable extends Interactable{
        
    public int slot = -1;
    public static final int DIRECTIONAL = 0;
    public static final int TOOL = 1;
    public static final int MAP = 2;
    public static final int INSTRUMENT = 3;
            
    public boolean equipped = false;
    
    public static int SLOT_HELD = 0;//Slots in as a held object
    
    public Equipable(TextImage TIB, String name){
        super(TIB,name);
    }

    public Equipable clone(){
        return null;
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = super.pollOptions();
        
        if(held){
            if(!equipped){
                ret.add(Option.equip(this));
            } else {
                ret.add(Option.unequip(this));
            }
        } else {
            if(equipped){
                Player.The.unequip(this);
                equipped = false;
            }
        }
        
        return ret;
    }
    
    public static String slotName(int slot){
        switch(slot){
            case DIRECTIONAL:
                return "Directional (J,K,L,I)";
            case TOOL:
                return "Tool (;)";
            case MAP:
                return "Map (M)";
            case INSTRUMENT:
                return "Instrument (G,H)";
        }
                
        
        
        return "???";
    }
}
