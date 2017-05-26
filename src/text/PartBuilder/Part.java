/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.PartBuilder;

import text.Actors.Interactable;
import java.util.*;
import java.awt.Point;
import java.io.Serializable;
import text.Images.*;
import text.Actors.*;

/**
 * 
 * @author FF6EB4
 */
public class Part extends Interactable implements Serializable{
    
    public Knob head;
    public ArrayList<Knob> knobs;
    
    public Part(TextImageComplex TIC){
        super(TIC);
        this.head = new Knob();
        knobs = new ArrayList<>();
    }
    
    public Part(){
        this.head = new Knob();
        knobs = new ArrayList<>();
    }
    
    //Does it have ANY knob for add?
    public boolean compatible(Part add){
        for(Knob K : knobs){
            boolean attempt = K.compatible(add);
            if(attempt){
                return true;
            }
        }
        return false;
    }
    
    //adds a part. always addes to the first knob first etc.
    //Returns true if successful.
    public boolean addPart(Part add){
        for(Knob K : knobs){
            //System.out.println("ADDING");
            boolean attempt = K.connect(add);
            if(attempt){
                return true;
            }
        }
        return false;
    }
    
    //adds a part to the first knob that works.
    public boolean insertPart(Part add){
        for(Knob K : knobs){
            
            boolean attempt = K.insert(add);
            if(attempt){
                return true;
            }
        }
        return false;
    }
    
    public Part clone(){
        Part ret = new Part();
        
        ret.head = this.head.clone();
        for(Knob K : knobs){
            ret.knobs.add(K.clone());
        }
        
        return ret;
    }
    
    //REPLACE THIS IF THE PART CORRESPONDS TO AN IMAGE.
    public TextImageComplex buildImage(){
        return null;
    }
}
