/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Frame;

import text.Actors.Drawable;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import text.Utility.ColorTuple;
import text.Actors.*;
import text.Images.*;
import java.io.*;

/**
 *
 * @author FF6EB4
 */
public class TextDisplay extends JFrame implements Serializable{
    public static TextDisplay Display = new TextDisplay();
    public static JPanel JP;
    
    public static BackGround BG;
    /*//
    public static final int SCREEN_SIZE_X = 160;
    public static final int SCREEN_SIZE_Y = 60;
    /*/
    public static final int SCREEN_SIZE_X = 160;
    public static final int SCREEN_SIZE_Y = 60;
    //*//
    public static ArrayList<Actor> drawables;
    
    public static Comparator depthComparator;
    
    public static int darken = -1;
    
    private TextDisplay(){
        super( "TextDisplay" );// invoke the JFrame constructor
        
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        JP = new JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                TextDisplay.jpPaint(g);
            }
        };
        
        try{
            File font_file = new File("font.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
            Font biggerFont = font.deriveFont((float)19);
            Font bolderFont = biggerFont.deriveFont(Font.BOLD);
            
            JP.setFont(bolderFont);
        }catch (Exception E){
            System.out.println("Failed to load font");
        }
        add(JP);
        pack();
        setVisible(true);
        
        depthComparator = new Comparator<Drawable>(){
            public int compare(Drawable d1, Drawable d2){
                return d1.depth - d2.depth;
            }
            
            public boolean equal(Drawable d1, Drawable d2){
                return compare(d1,d2) == 0;
            }
        };
        
        TextDisplay.drawables = new ArrayList<>();
        
        TextListener TL = new TextListener();
        
        addKeyListener(TL);
        
        setSize( 1000, 1000 );
    }
    
    public static void swapDrawables(ArrayList<Actor> aList){
        drawables = aList;
    }
    
    public static void add(Actor A){
        drawables.add((Actor)A);
    }
    
    /*
    * This is called from within the JPanel, every time it draws.
    *
    */
    public static void jpPaint(Graphics g){
        double xSize = (double)JP.getWidth()/(double)SCREEN_SIZE_X;
        double ySize = (double)JP.getHeight()/(double)SCREEN_SIZE_Y;
        
        if(BG!=null){
            BG.draw(g, xSize, ySize);
        }
        
        try{
            Collections.sort(drawables,depthComparator);
        } catch (Exception E){} //If this fails IDC
        
        /*
        HashSet<Actor> HS = new HashSet<>(drawables);
        
        for(Actor A : HS){
            if(!A.drawBack(g,xSize,ySize))
                try{
                    A.drawFront(g, xSize, ySize);
                } catch (IndexOutOfBoundsException E){
                    System.out.println("DAG NABBIT");
                }
        }
        */
        //*
        
        boolean dark = darken > 0;
        for(int i = 0; i<drawables.size(); ++i){
            
            if(!drawables.get(i).drawBack(g, xSize, ySize))
                try{
                    drawables.get(i).drawFront(g, xSize, ySize);
                } catch (IndexOutOfBoundsException E){
                    System.out.println("TextDisplay, IOOBE");
                }
            
            try{
                if(dark){
                    if(drawables.get(i).depth < darken &&
                            darken < drawables.get(i+1).depth){
                        Color Black = new Color(0,0,0,(float).5);
                        
                        ColorTuple black = new ColorTuple(Black,Black,' ');
                        black.drawBack(g, 0, 0, TextDisplay.JP.getWidth(), TextDisplay.JP.getHeight());
                    }
                    //dark = false;
                }
            }catch (Exception E){
                //System.out.println("FAILURE TO DARKEN!");
            }
        }
        
        
        //*/
    }
}

