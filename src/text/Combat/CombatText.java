/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat;
import text.Combat.Hero.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import text.Actors.*;
import text.Frame.TextDisplay;
import text.Images.TextImageBasic;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import text.Utility.ImageLoader;
import text.Frame.TextListener;
/**
 *
 * @author FF6EB4
 */
public class CombatText extends Actor{
    private static HashMap<Character,CombatText> letterMap;
    
    public double speed;
    int steps = 0;
    
    char c;
    int up = -1;
    boolean down = false;
    

    public boolean pressed = false;
    
    /**
     * The main method of the heroletter class- this creates a letter at x with speed speed.
     * 
     * @param x
     * How far right to put it
     * 
     * @param speed
     * How fast it goes down
     * 
     * @param c 
     * What letter it has
     */
    public static CombatText addLetter(int x, double speed, char c, ColorTuple CT){
        if(letterMap == null){
            loadletters();
        }
        CombatText HL = letterMap.get(c).clone();
        HL.x = x * 16;
        HL.speed = speed;
        HL.y = TextDisplay.SCREEN_SIZE_Y / 2;
        
        ImageBuilder.colorMergeImage((TextImageBasic)HL.image, CT);
        
        Player.The.current.addActor(HL);
        
        HL.depth = 1000;
        
        
        
        return HL;
    }
    
    public static void addPhrase(int start, String phrase){
        
        ColorTuple OB = new ColorTuple(Color.ORANGE,Color.BLUE,' ');
        for(int i = 0; i < phrase.length(); ++i){
            addLetter(i+start,1,phrase.charAt(i),OB);
        }
    }
    
    
    public CombatText clone(){
        return new CombatText(this.c,(TextImageBasic)this.image.clone());
    }
    
    public CombatText(char c,TextImageBasic TIB){
        super(0,0,TIB);
        this.blockable = false;
        this.c = c;
    }
    
    public boolean act(){
        steps +=1;
        y = (int) ((speed * ((double)steps))) - up;
        if(down && up > 0){
            up--;
        } else if(!down){
            if(up > 5) down = true;
            up++;
            up++;
        }
        return false;
    }
    
    public void outsideRoom(){
        System.out.println("OUTSIDE ROOM LETTER");
        this.dead = true;
    }
    
    private static void loadletters(){
        letterMap = new HashMap<>();
        
        ImageLoader.switchMap("LIGHTBRIGHT");
        
        //
        //THE LETTERS
        //
        
        for(int i = (int)'a'; i <= (int)'z'; ++i){
            //System.out.println((char)i);
            TextImageBasic temp = ImageLoader.loadImage("letter_low_"+((char)i)+".txt");
            CombatText HL = new CombatText((char)i,temp);
            letterMap.put((char)i,HL);
        }
        
        for(int i = (int)'A'; i <= (int)'Z'; ++i){
            //System.out.println((char)i);
            TextImageBasic temp = ImageLoader.loadImage("letter_cap_"+((char)i)+".txt");
            CombatText HL = new CombatText((char)i,temp);
            letterMap.put((char)i,HL);
        }
        
        //
        //THE NUMBERS
        //
        
        for(int i = (int)'0'; i <= (int)'9'; ++i){
            //System.out.println((char)i);
            TextImageBasic temp = ImageLoader.loadImage("number_"+((char)i)+".txt");
            CombatText HL = new CombatText((char)i,temp);
            letterMap.put((char)i,HL);
        }
        
        //
        //OTHERS
        //
        
        //SEMICOLON
        TextImageBasic temp = ImageLoader.loadImage("letter_semicolon.txt");
        CombatText HL = new CombatText(';',temp);
        letterMap.put(';',HL);
    }
}
