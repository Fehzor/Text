/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Tools;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Interactable;
import text.Actors.Messages.Option;
import text.Actors.Player;
import text.Images.TextImageBasic;
import text.Inventory.Physical;

/**
 *
 * @author FF6EB4
 */
public class ToolPart extends Interactable{
    
    public Tool owner;
    public ArrayList<ToolPart> connections;
    public int type;
    public String info = "A portion of a tool...";
    
    public static final int TYPE_HANDLE = 0;
    public static final int TYPE_MIDDLE = 1;
    public static final int TYPE_END = 2;
    public static final int TYPE_ACCESSORY = 3;
    
    public ToolPart(){}
    
    public ToolPart(TextImageBasic myImage){
        super(myImage);
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = super.pollOptions();
        
        if((this.type == TYPE_HANDLE || this.type == TYPE_ACCESSORY) && this.held == true){
            ret.add(Option.attachToolPart(this));

        }
        
        ret.add(Option.display(this, "Info", info));
        
        return ret;
    }
    
    /**
     * Connect a tool part to this one. All tool parts are connected to
     * one of type TYPE_HANDLE
     * 
     * @param another
     * The other tool part to add on.
     * 
     * @return
     * Whether it was added or not.
     */
    public boolean connect(ToolPart another){
        if(this.type != TYPE_HANDLE && this.type != TYPE_ACCESSORY){
            return false;
        }
        
        if(this.owner != null){
            return false;
        }
        
        if(another.type == TYPE_HANDLE){
            return false;
        }
        
        if(another.type == TYPE_MIDDLE || another.type == TYPE_ACCESSORY){
            if(this.connections == null){
                this.connections = new ArrayList<>();
            }
            
            this.connections.add(another);
            this.name += "+";
            return true;
        }
        
        if (another.type == TYPE_END){
            if(this.connections == null){
                this.connections = new ArrayList<>();
            }
            
            this.connections.add(another);
            this.name += "+";
            
            Tool T = this.compile(null);
            T.held = true;
            Physical<Tool> p = new Physical<Tool>(T);
            
            Player.The.inv.removeStuff(this);
            Player.The.inv.addStuff(p);
            
            return true;
        }
        
        
        
        return true;
    }
    
    /**
     * Add an effect to Tool T. If you are a handle, call super.compile(). 
     * 
     * @Tool T
     * The tool getting returned/worked on
    */
    public Tool compile(Tool T){
        if(T == null){
            return null;
        }
        if(this.connections != null){
            for(ToolPart TP : connections){
                TP.compile(T);
            }
        }
        
        return T;
    }
    
    public String toString(){
        return this.name;
    }
    
    
    //Applies the effect of this tool part.
}
