/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Editor;

import java.awt.MouseInfo;
import text.Images.TextImage;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import static text.Actors.Player.generateSprite;
import static text.Frame.TextDisplay.JP;
import static text.Frame.TextDisplay.SCREEN_SIZE_X;
import static text.Frame.TextDisplay.SCREEN_SIZE_Y;
import text.Images.TextImageComplex;
import text.Utility.ColorTuple;
import java.awt.datatransfer.*;
import java.io.*;
import text.Actors.Actor;
import text.Utility.ImageLoader;

/**
 *
 * @author FF6EB4
 */


// |ABCD

public class TextEditor extends Actor implements MouseListener{
    
    //GREYSCALE
    //GREYSCALE_LIGHT
    //ANIMAL_DEMO
    //LIGHTBRIGHT
    //TREESCALE
    public static final String COLOR_KEY = "CITYSCALE";
    public static final int SIZE_X = 15; //HEIGHT
    public static final int SIZE_Y = 15; //WIDTH Yes I do realize these are backwards but w/e this tool is something only I use so..
    public static final int X_ORIGIN = 0;
    public static final int Y_ORIGIN = 0;
    
    ArrayList<ColorTuple> colorKey;
    int colorPos = 0;
    
    public boolean leftMouse;
    public boolean rightMouse;
    MouseEvent last;    
    
    String letterSet=" -|/\\";
    int letterPos = 0;
    
    ArrayList<ArrayList<ColorTuple>> editing;
    
    ColorTuple marker;
       
    public TextEditor(){
        
        super(30,30,new TextImageComplex(generateSprite(),X_ORIGIN,Y_ORIGIN));
        
        colorKey = ImageLoader.loadMap(COLOR_KEY);
        editing = ((TextImageComplex)image).image;
        leftMouse = false;
        rightMouse= false;
        
        marker = new ColorTuple(Color.BLACK,Color.GREEN,'X');
    }
    
    public boolean act(){
        //Point P = MouseInfo.getPointerInfo().getLocation();
        
        if(leftMouse||rightMouse){
            double xSize = (JP.getWidth()/ (double)SCREEN_SIZE_X);
            double ySize = (JP.getHeight()/(double)SCREEN_SIZE_Y);
            
            int COL = (int)( last.getPoint().x / xSize + image.xOrigin - this.x);
            int ROW = (int)( last.getPoint().y / ySize + image.yOrigin - this.y);
            if(leftMouse){
                try{
                    ColorTuple set = colorKey.get(colorPos%colorKey.size()).clone();
                    set.icon = letterSet.charAt(letterPos%letterSet.length());
                    editing.get(ROW).set(COL,set);
                } catch (Exception e){}
            }
            if(rightMouse){
                try{
                    editing.get(ROW).set(COL,new ColorTuple(Color.BLACK,Color.RED,'X'));
                } catch (Exception e){}
            }
        }
        
        //The texteditor object is kind of like the player; it can move around.
        
        if(text.Frame.TextListener.isHeld(KeyEvent.VK_F)){
            x+=1;
            image.flip(false);
        }
        if(text.Frame.TextListener.isHeld(KeyEvent.VK_S)){
            x-=1;
            image.flip(true);
        }
        if(text.Frame.TextListener.isHeld(KeyEvent.VK_D))y+=1;
        if(text.Frame.TextListener.isHeld(KeyEvent.VK_E))y-=1;
        
        //COPY THE TEXT FROM THE CLIPBOARD!
        if(text.Frame.TextListener.isHeld(KeyEvent.VK_C)){
            try{
                this.letterSet = (String) Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (Exception e){};
        } 
        
        //COPY THE TEXT FROM THE CLIPBOARD!
        if(text.Frame.TextListener.isHeld(KeyEvent.VK_W)){
            write();
        }
        
        //A = scroll down.
        if(text.Frame.TextListener.isHeld(KeyEvent.VK_A)){
            try{
                this.letterPos--;
            } catch (Exception e){};
        }
        
        //Q = scroll up.
        if(text.Frame.TextListener.isHeld(KeyEvent.VK_Q)){
            try{
                this.letterPos++;
            } catch (Exception e){};
        }
        
        return true;
    }
    
    //What does this actor do when its outside of the room?
    public void outSideRoom(){}
    
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1)
            leftMouse = false;
        
        if(e.getButton() == MouseEvent.BUTTON3)
            rightMouse= false;
    }
    
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1)
            leftMouse = true;
        
        if(e.getButton() == MouseEvent.BUTTON2)
            this.colorPos++;
        
        if(e.getButton() == MouseEvent.BUTTON3)
            rightMouse= true;
        
        last = e;
    }
    
    //Creates the starting sprite for this.
    public static ArrayList<ArrayList<ColorTuple>> generateSprite(){
        ArrayList<ArrayList<ColorTuple>> ret = new ArrayList<ArrayList<ColorTuple>>();
        
        Random oRan = new Random();
        
        for(int i = 0; i<SIZE_X; ++i){
            ret.add(new ArrayList<ColorTuple>());
            for(int j = 0; j<SIZE_Y; ++j){
                ret.get(i).add( new ColorTuple(Color.BLACK,Color.red,(char)((int)'X'))); 
            }
        }
        
        return ret;
    }
    
    public void write(){
        try{
            PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
            writer.print(SIZE_Y + " " + SIZE_X +" "+ X_ORIGIN +" "+ Y_ORIGIN);
            for(int i = 0; i< SIZE_X; ++i){
                writer.println();
                for(int j = 0; j<SIZE_Y; ++j){
                    writer.print(editing.get(i).get(j).icon);
                }
            }
            
            ColorTuple trans = new ColorTuple(Color.BLACK,Color.RED,'X');
            for(int i = 0; i< SIZE_X; ++i){
                writer.println();
                for(int j = 0; j<SIZE_Y; ++j){
                    ColorTuple CT = editing.get(i).get(j).clone();
                    
                    if(CT.equals(trans)){
                        writer.print("-");
                    }else{
                        
                        int loc = getNum(CT);
                        writer.print(""+loc);
                    }
                            
                }
            }
            writer.close();
        } catch (Exception E){}
    }
    
    private int getNum(ColorTuple CT){
        for(int i = 0; i < colorKey.size(); ++i){
            ColorTuple temp = colorKey.get(i);
            if(temp.primary.equals(CT.primary)&&temp.secondary.equals(CT.secondary))return i;
        }
        
        return 0;
    }
}


// \/-           

// *    
//  #&^  /-& # |<>

// ll,_-(0)-    

//-*|"   

// / x = 