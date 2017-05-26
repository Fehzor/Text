/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors;

import text.Images.TextImage;
import java.awt.Graphics;
import java.io.Serializable;
import text.Frame.TextDisplay;

/**
 * This class represents an image of any sort that cannot do anything; 
 * i.e. that rock in the background... or a tree, or whatnot
 * @author FF6EB4
 */
public class Drawable implements Serializable{
    
    public TextImage image;
    public int x;
    public int y;
    public int height=0;
    public int depth = 0;
    
    public Drawable(){
        x = 0;
        y = 0;
        image = null;
    }
    
    public Drawable(TextImage TI){
        this.x = 0;
        this.y = 0;
        this.image = TI;
    }
    
    public Drawable(int x, int y, TextImage TI){
        this.x = x;
        this.y = y;
        this.image = TI;
    }

    //Draws itself. Returns if it isn't Drawable.
    public boolean drawBack(Graphics g, double xSize, double ySize){ 
        if(image == null){
            TextDisplay.drawables.remove(this);
            //System.out.println();
            return true;
        }
        image.drawBack(g,x,y,xSize,ySize);
        return false;
    }
    
    //Draws itself.
    public void drawFront(Graphics g, double xSize, double ySize){ 
        if(image == null){
            TextDisplay.drawables.remove(this);
            //System.out.println();
            return;
        }
        image.drawFront(g,x,y,xSize,ySize);
    }
}
