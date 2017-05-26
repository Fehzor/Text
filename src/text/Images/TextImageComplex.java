/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Images;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.*;
import text.Actors.Player;
import text.Utility.ColorTuple;

/**
 * @author FF6EB4
 */
public class TextImageComplex extends TextImageBasic implements Serializable{
     //public ArrayList<ArrayList<ColorTuple>> image;
     
     ArrayList<Point> knobs;
     HashMap<Point,TextImage> connections;
    
    public TextImageComplex(){
        super.type = 2;
        image = new ArrayList<>();
        knobs = new ArrayList<>();
    }
    
    public TextImageComplex(ArrayList<ArrayList<ColorTuple>> block){
        super.type = 2;
        image = block;
        knobs = new ArrayList<>();
        connections = new HashMap<>();
    }
    
    public TextImageComplex(TextImageBasic base){
        super(base.xOrigin,base.yOrigin);
        super.type = 2;
        image = base.image;
        knobs = new ArrayList<>();
        connections = new HashMap<>();
    }
    
    public TextImageComplex(ArrayList<ArrayList<ColorTuple>> block, int xOrigin, int yOrigin){
        super(xOrigin, yOrigin);
        super.type = 2;
        image = block;
        knobs = new ArrayList<>();
        connections = new HashMap<>();

    }
    
    public TextImageComplex clone(){
        ArrayList<ArrayList<ColorTuple>> clon = new ArrayList<>();
        
        for(int i = 0; i<image.size(); ++i)
        {
            clon.add(new ArrayList<>());
            for(ColorTuple c : image.get(i)){
                clon.get(i).add(c.clone());
            }
        }
        
        TextImageComplex ret =  new TextImageComplex(clon,this.xOrigin, this.yOrigin);
        
        ret.flip(this.flipped());
        
        return ret;
    }
    
    /**
     * Draws this image at a position, x, y.
     * @param g
     * 
     * The Graphics object responsible for drawing these things.
     * 
     * @param x
     * @param y
     * 
     * The x and y coordinates of where it is. In squares, not pixels.
     * 
     * @param xSize
     * @param ySize 
     * 
     * The size of a block.
     */
    public void drawBack(Graphics g, int x, int y, double xSize, double ySize){ 
        ColorTuple bigPixel;
        for(int i = 0; i<image.size(); ++i){
            for(int j = 0; j<image.get(i).size();++j){
               //System.out.println(i+", "+j);
                if(flipped()){
                    bigPixel = image.get(i).get(j);
                    bigPixel.drawBack(g,x+xOrigin-j,i+y-yOrigin,xSize,ySize);
                } else {
                    bigPixel = image.get(i).get(j);
                    bigPixel.drawBack(g,j+x-xOrigin,i+y-yOrigin,xSize,ySize);
                }
                
                
            }
        }
        
        for(int i = 0; i<knobs.size(); ++i){
            int xOff = knobs.get(i).x;
            int yOff = knobs.get(i).y;
            TextImage A = connections.get(knobs.get(i));
            
            A.flip(this.flipped());
            if(this.flipped()){
                A.drawBack(g, x-xOff, y+yOff, xSize, ySize);
            } else {
                A.drawBack(g, x+xOff, y+yOff, xSize, ySize);
            }
        }
    }
    
    public void drawFront(Graphics g, int x, int y, double xSize, double ySize){ 
        ColorTuple bigPixel;
        for(int i = 0; i<image.size(); ++i){
            for(int j = 0; j<image.get(i).size();++j){
               //System.out.println(i+", "+j);
                if(flipped()){
                    bigPixel = image.get(i).get(j);
                    bigPixel.drawFront(g,x+xOrigin-j,i+y-yOrigin,xSize,ySize);
                } else {
                    bigPixel = image.get(i).get(j);
                    bigPixel.drawFront(g,j+x-xOrigin,i+y-yOrigin,xSize,ySize);
                }
                
                
            }
        }
        
        for(int i = 0; i<knobs.size(); ++i){
            int xOff = knobs.get(i).x;
            int yOff = knobs.get(i).y;
            TextImage A = connections.get(knobs.get(i));
            
            A.flip(this.flipped());
            if(this.flipped()){
                A.drawFront(g, x-xOff, y+yOff, xSize, ySize);
            } else {
                A.drawFront(g, x+xOff, y+yOff, xSize, ySize);
            }
        }
    }
    
    //Add a knob.
    public void addKnob(int x, int y, TextImage con){
        knobs.add(new Point(x,y));
        connections.put(new Point(x,y),con);
    }
    
    //Returns knob at x,y
    public TextImage removeKnob(int x, int y){
        Point P = new Point(x,y);
        knobs.remove(P);
        TextImage ret = connections.remove(P);
        return ret;
    }
    
    public int dumpKnob(){
        int ret = 0;
        //Player.The.current.pause();
        while(knobs.size() > 0){
            Point P = knobs.get(0);
            removeKnob(P.x,P.y);
            //System.out.println(ret);
            ret++;
        }
        return ret;
    }
    
    public TextImage swapKnob(int x, int y, TextImage con){
        Point P = new Point(x,y);
        //knobs.remove(P);
        TextImage ret = connections.get(P);
        connections.remove(P);
        connections.put(P,con);
        return ret;
    }
    
    public TextImageBasic getBasic(){
        return new TextImageBasic(this.image);
    }
    
    
    
    /*
    public TextImage swapKnob(int x, int y, TextImage con){
        TextImage ret = removeKnob(x,y);
        addKnob(x,y,con);
        return ret;
    }
    */
}
