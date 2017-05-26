/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.PartBuilder;

import java.util.Collections;
import java.io.*;
import text.Images.TextImageComplex;


/**
 *
 * @author FF6EB4
 */
public class OrganicPart extends Part implements Serializable{
    //public TextImageComplex image;
    public OrganicPart(){}
    
    public OrganicPart(TextImageComplex image){
        super(image);
    }
    
    public TextImageComplex buildImage(){
        if(this.image == null){
            //System.out.println("NO IMAGE DETECTED");
            return new TextImageComplex();
        }
        
        TextImageComplex base = ((TextImageComplex)image).clone();
        for(Knob K : knobs){
            //System.out.println("PARSING KNOBS");
            K.buildImage(base);
        }
        return base;
    }
    
    //adds a part randomly to this object.
    public boolean insertPart(Part add){
        Collections.shuffle(knobs); //Otherwise it would always add to the first knob.
        
        for(Knob K : knobs){
            boolean attempt = K.insert(add);
            if(attempt){
                return true;
            }
        }
        return false;
    }
    
    //adds a part randomly to this object. Never inserts it between 2.
    public boolean addPart(Part add){
        Collections.shuffle(knobs); //Otherwise it would always add to the first knob.
        
        for(Knob K : knobs){
            boolean attempt = K.connect(add);
            if(attempt){
                return true;
            }
        }
        return false;
    }
}
