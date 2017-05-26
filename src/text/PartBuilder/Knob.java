/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.PartBuilder;

import java.util.*;
import java.awt.Point;
import java.io.Serializable;
import text.Images.*;

/**
 *
 * @author FF6EB4
 */
public class Knob implements Serializable{
    public Point p; //The point relative to the part where this goes.
    
    private boolean connected; //If the knob is connected, how so?
    private Part connection;
    
    public int type; //What kind of knob is this?
    public int subType; //In its system, what kind of of knob is it?
    public int direction; //If this is direction, what direction?
    
    public static final int NONE = 0; //Works for either.
    
    public static final int TYPE_NONE = 0;
    public static final int TYPE_A = -1;
    public static final int TYPE_B = -2;
    public static final int TYPE_C = -3;
    public static final int TYPE_D = -4;
    public static final int TYPE_E = -5;
    public static final int TYPE_PLANT = 1;
    public static final int TYPE_BEHAVIOUR = 2;
    public static final int TYPE_TOOL = 3;
    
    public static final int DIR_NONE = 0;
    public static final int DIR_VERTICAL = 1;
    public static final int DIR_HORIZONTAL = 2;
    public static final int DIR_DIAGONAL_UP = 3;
    public static final int DIR_DIAGONAL_DOWN = 4;
    
    public Knob(Point p, int type, int subType, int direction){
        this.connected = false;
        this.p = p;
        this.type = type;
        this.subType = subType;
        this.direction = direction;
    }
    
    public Knob(Point p, int type, int subType){
        this(p,type,subType,NONE);
    }
    
    public Knob(Point p, int type){
        this(p,type,NONE,NONE);
    }
    
    public Knob(Point p){
        this(p,NONE,NONE,NONE);
    }
    
    public Knob(){
        this(new Point(0,0),NONE,NONE,NONE);
    }
    
    //Adds this Knob's image to a base image
    public TextImageComplex buildImage(TextImageComplex base){
        if(connected){
            base.addKnob(p.x, p.y, connection.buildImage());
            return base;
        }
        return base;
    }
    
    //Is this knob compatible with a specific Knob?
    public boolean compatible(Knob K){
        //System.out.println(this.type+" "+K.type);
        //System.out.println(this.subType+" "+K.subType);
        boolean ret = (K.type == this.type || K.type == NONE || this.type == NONE);
        ret = ret && (K.subType == this.subType || K.subType == NONE || this.subType == NONE);
        ret = ret && (K.direction == this.direction || K.direction == NONE || this.direction == NONE);
        
        //All types must match or not matter for knobs to function.
        
        return ret;
    }
    
    //It works on parts as well, since those are effectively their heads.
    public boolean compatible(Part P){
        return compatible(P.head);
    }
    
    public boolean connect(Part P){
        if(connected){ //If already connected return false because can't connect
            return false;
        } else if(compatible(P)){ //If a connection is possible go for it.
            //System.out.println("Connected!");
            this.connected = true;
            this.connection = P;
            return true;
        }
        return false; //If no connection is made return false
    }
    
    //Attempt to insert this part between this knob and the connection it has.
    public boolean insert(Part P){
        boolean ret = false;
        if(connected){
            if(compatible(P)){
                if(P.compatible(connection)){
                    Part old = connection;
                    this.connection = P;
                    
                    P.addPart(old);
                    return true;
                }
            }
        } else { //If not connected just connected lol
            ret = this.connect(P);
        }
        
        return ret;
    }
    
    public Knob clone(){
        return new Knob(new Point(p.x,p.y),this.type,this.subType,this.direction);
    }
    
    //Get the connected part. 
    public boolean connected(){
        return connected;
    }
    
    public Part connection(){
        if(connected()){
            return connection;
        } else {
            return null;
        }
    }
}
