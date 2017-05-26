/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Images;
import java.awt.Graphics;
import java.io.Serializable;
import text.Utility.*;

import java.util.*;

/**
 *
 * @author FF6EB4
 */
public abstract class TextImage implements Serializable{
    public int type = 0;
    String name;
    String colorScheme;
    public int xOrigin,yOrigin;
    private boolean flipped;
    
    public TextImage(){
        this.xOrigin = 0;
        this.yOrigin = 0;
        flipped = false;
    }
    
    public TextImage(int xOrigin, int yOrigin){
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        flipped = false;
    }
    
    public abstract TextImage clone();
    
    public String save(){
        return colorScheme+" "+name;
    }
    
    public boolean flipped(){
        return flipped;
    }
    
    public void flip(){
        flipped = !flipped;
    }
    
    public void flip(boolean flip){
        this.flipped = flip;
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
    public abstract void drawBack(Graphics g, int x, int y, double xSize, double ySize);
    public abstract void drawFront(Graphics g, int x, int y, double xSize, double ySize);
    
    //Get the image as a TextImageBasic
    public abstract TextImageBasic getBasic();
}
