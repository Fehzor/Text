/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors;

import java.util.ArrayList;
import text.Actors.Interactable;
import text.Actors.Messages.Option;
import text.Images.TextImageBasic;

/**
 *
 * @author FF6EB4
 */
public class Searchable extends Interactable{
    private Actor heldActor;
    
    public Searchable(String name){
        super(name);
        this.name = name;
    }
    
    public Searchable(){
        super();
        this.dead = false;
    }
    
    public Searchable(TextImageBasic img, String name){
        super(img, name);
        this.dead = false;
        this.name = (name.charAt(0)+"").toUpperCase()+name.substring(1);
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = super.pollOptions();
        
        ret.add(Option.search(this));
        
        return ret;
    }
    
    public void give(Actor add){
       heldActor = add.clone();
    }
    
    public boolean hasActor(){
        return heldActor != null;
    }
    
    public Actor take(){
        Actor ret = heldActor;
        heldActor = null;
        return ret;
    }
}
