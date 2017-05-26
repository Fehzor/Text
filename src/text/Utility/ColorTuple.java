/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Utility;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class ColorTuple implements Serializable{
    public static final Color TRANSPARENT = new Color(255,255,255,0);
    public static final int LETTER_OFFSET_X = -1;
    public static final int LETTER_OFFSET_Y = 15;
    
    
    public Color primary = Color.BLACK; //Background
    public Color secondary = Color.GREEN; //Letter color
    public char icon = '$';
    
    public ColorTuple(Color a, Color b, char c){
        primary = a;
        secondary = b;
        icon = c;
    }
    
    public ColorTuple(ColorTuple clone){
        this.primary = clone.primary;
        this.secondary = clone.secondary;
        this.icon = clone.icon;
    }
    
    public void drawBack(Graphics g, int x, int y, double xSize, double ySize){
        Graphics2D g2 = (Graphics2D)g;
        
        //System.out.println("Drawing!");
        
        g2.setColor(this.primary);
        g2.fillRect((int)Math.floor(x*xSize),(int)Math.floor(y*ySize), (int)Math.ceil(xSize), (int)Math.ceil(ySize));
    
    }
    public void drawFront(Graphics g, int x, int y, double xSize, double ySize){
        Graphics2D g2 = (Graphics2D)g;
        
        //System.out.println("Drawing!");
        
        g2.setColor(this.secondary);
        g2.drawString(icon+"",(int) (x*xSize+LETTER_OFFSET_X),(int)(y*ySize+LETTER_OFFSET_Y));
    }
    
    public boolean equals(Object o){
        ColorTuple other = (ColorTuple) o;
        
        boolean p = other.primary.getRed() == this.primary.getRed();
        p = p && other.primary.getBlue() == this.primary.getBlue();
        p = p && other.primary.getGreen() == this.primary.getGreen();
        
        
        boolean s = other.secondary.getRed() == this.secondary.getRed();
        s = s && other.secondary.getBlue() == this.secondary.getBlue();
        s = s && other.secondary.getGreen() == this.secondary.getGreen();
        
        boolean i = other.icon == this.icon;
        i = true;
        
        return p && s && i;
    }
    
    public boolean similar(ColorTuple other){
        if(this.icon == other.icon){
            return true;
        }
        
        int r = this.primary.getRed();
        int b = this.primary.getBlue();
        int g = this.primary.getGreen();
        
        float[] hsbMe = Color.RGBtoHSB(r, g, b, null);
        
        r = other.primary.getRed();
        b = other.primary.getBlue();
        g = other.primary.getGreen();
        
        float[] hsbOther = Color.RGBtoHSB(r,g,b,null);
        
        float myHue = hsbMe[0];
        float compare = hsbOther[0];
        
        float myBright = hsbMe[2];
        float compareBright = hsbOther[2];
        
        if(Math.abs(myHue - compare) < .2) return true;
        if(Math.abs(myBright - compareBright) < .05) return true;
        
        return false;
    }
    
    
    
    public ColorTuple clone(){
        ColorTuple ret = new ColorTuple(this.primary, this.secondary, this.icon);
        return ret;
    }
    
    public static ColorTuple getRandom(){
        int r = oRan.nextInt(255);
        int b = oRan.nextInt(255);
        int g = oRan.nextInt(255);
        int r2 = oRan.nextInt(255);
        int b2 = oRan.nextInt(255);
        int g2 = oRan.nextInt(255);
        
        Color one = new Color(r,g,b);
        Color two = new Color(r2,g2,b2);
        
        char c =(char) ('a' + oRan.nextInt(26));
        
        if(oRan.nextInt(100) < 20){
            c = (char) ('A' + oRan.nextInt(26));
        }
        
        if(oRan.nextInt(100) == 23){
            c = (char) (oRan.nextInt(255));
        }
        
        return new ColorTuple(one,two,c);
    }
    
    public static ColorTuple mergeColors(ColorTuple A, ColorTuple B){
        int r = (A.primary.getRed() + B.primary.getRed())/2;
        int b = (A.primary.getBlue() + B.primary.getBlue())/2;
        int g = (A.primary.getGreen() + B.primary.getGreen())/2;
        
        int r0 = (A.secondary.getRed() + B.secondary.getRed())/2;
        int b0 = (A.secondary.getBlue() + B.secondary.getBlue())/2;
        int g0 = (A.secondary.getGreen() + B.secondary.getGreen())/2;
        
        return new ColorTuple(new Color(r,g,b),new Color(r0,g0,b0),' ');
    }
}
