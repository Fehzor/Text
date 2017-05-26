/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Images;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import text.Utility.ColorTuple;

/**
 *
 * @author FF6EB4
 */
public class TextImageBasic extends TextImage implements Serializable{
    //TextImageBasic is very, very, transparent with its implementation.
    //In this way it's almost like a colorTuple...
    public ArrayList<ArrayList<ColorTuple>> image;
    
    public TextImageBasic(){
        super.type = 1;
        image = new ArrayList<ArrayList<ColorTuple>>();
    }
    
    public TextImageBasic( int xOrigin, int yOrigin){
        super(xOrigin, yOrigin);
        super.type = 1;
    }
    
    public TextImageBasic(ArrayList<ArrayList<ColorTuple>> block){
        super.type = 1;
        image = block;
    }
    
    public TextImageBasic(ColorTuple block, boolean sides){
        super.type = 1;
        image = new ArrayList<>();
        image.add(new ArrayList<>());
        
        if(sides){
            ColorTuple left = block.clone();
            left.icon = '(';
            ColorTuple right = block.clone();
            right.icon = ')';

            image.get(0).add(left);
            image.get(0).add(block);
            image.get(0).add(right);
        } else {
            image.get(0).add(block);
        }
    }
    
    public TextImageBasic(ArrayList<ArrayList<ColorTuple>> block,int xOrigin,int yOrigin){
        super(xOrigin, yOrigin);
        super.type = 1;
        image = block;
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
                   bigPixel = image.get(image.size()-i-1).get(j);
                } else {
                    bigPixel = image.get(i).get(j);
                    
                }
                bigPixel.drawBack(g,j+x-xOrigin,i+y-yOrigin,xSize,ySize);
            }
        }
    }
    public void drawFront(Graphics g, int x, int y, double xSize, double ySize){ 
        
        ColorTuple bigPixel;
        for(int i = 0; i<image.size(); ++i){
            for(int j = 0; j<image.get(i).size();++j){
               //System.out.println(i+", "+j);
                if(flipped()){
                   bigPixel = image.get(image.size()-i-1).get(j);
                } else {
                    bigPixel = image.get(i).get(j);
                    
                }
                bigPixel.drawFront(g,j+x-xOrigin,i+y-yOrigin,xSize,ySize);
            }
        }
    }
    
    public TextImageBasic clone(){
        ArrayList<ArrayList<ColorTuple>> clon = new ArrayList<>();
        
        for(int i = 0; i<image.size(); ++i)
        {
            clon.add(new ArrayList<>());
            for(ColorTuple c : image.get(i)){
                clon.get(i).add(c.clone());
            }
        }
        
        return new TextImageBasic(clon,this.xOrigin, this.yOrigin);
    }
    
    /*
    * Flips the image; i.e. actual letters around.
    */
    public void flipImage(){
        ArrayList<ArrayList<ColorTuple>> ret = new ArrayList<>();
        for(int i = 0; i < image.size(); ++i){
            ret.add(new ArrayList<>());
            for(int j = 0; j < image.get(0).size(); ++j){
                ret.get(i).add(image.get(i).get(image.get(0).size() - j - 1));
            }
        }
        this.image = ret;
    }
    
    public TextImageBasic getBasic(){
        return this;
    }
}
