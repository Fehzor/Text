/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Hero;
import java.awt.Color;
import java.util.HashMap;
import text.Actors.*;
import text.Images.TextImageBasic;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import text.Utility.ImageLoader;
import text.Frame.TextListener;
/**
 *
 * @author FF6EB4
 */
public class HeroLetter extends Actor{
    private static HashMap<Character,HeroLetter> heromap;
    
    public static ColorTuple hit = new ColorTuple(Color.BLACK,Color.GRAY,' ');
    public static ColorTuple lit = new ColorTuple(Color.WHITE,Color.WHITE,' ');
    
    public double speed;
    int steps = 0;
    
    char c;
    
    int hitAfter = 48;
    boolean litUp = false;
            
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
    public static HeroLetter addHero(int x, double speed, char c, ColorTuple CT){
        if(heromap == null){
            loadletters();
        }
        HeroLetter HL = heromap.get(c).clone();
        HL.x = x;
        HL.speed = speed;
        
        ImageBuilder.colorMergeImage((TextImageBasic)HL.image, CT);
        
        Player.The.current.addActor(HL);
        
        HL.depth = 1000;
        
        return HL;
    }
    
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
     * 
     * @param CT
     * The color it is
     * 
     * @param after
     * When you can hit it by
     */
    public static HeroLetter addHero(int x, double speed, char c, ColorTuple CT, int after){
        if(heromap == null){
            loadletters();
        }
        HeroLetter HL = heromap.get(c).clone();
        HL.x = x;
        HL.speed = speed;
        HL.hitAfter = after;
        
        ImageBuilder.colorMergeImage((TextImageBasic)HL.image, CT);
        
        Player.The.current.addActor(HL);
        
        HL.depth = 1000;
        
        return HL;
    }
    
    public void checkPos(){
        //System.out.println(this.y);
        if(this.y > hitAfter){
            if(!litUp){
                //System.out.println("READY");
                ImageBuilder.colorMergeImage((TextImageBasic)(this.image),lit);
                litUp = true;
            }
            if(TextListener.checkChar(c)){
                ImageBuilder.colorMergeImage((TextImageBasic)(this.image),hit);
                this.pressed = true;
            }
        } else {
            if(TextListener.checkChar(c)){
                this.speed+=.1;
            }
        }
    }
    
    
    public HeroLetter clone(){
        return new HeroLetter(this.c,(TextImageBasic)this.image.clone());
    }
    
    public HeroLetter(char c,TextImageBasic TIB){
        super(0,0,TIB);
        this.blockable = false;
        this.c = c;
    }
    
    public boolean act(){
        checkPos();
        steps +=1;
        y = (int) ((speed * ((double)steps)));
        checkPos();
        return false;
    }
    
    public void outsideRoom(){
        System.out.println("OUTSIDE ROOM LETTER");
        this.dead = true;
    }
    
    private static void loadletters(){
        heromap = new HashMap<>();
        
        ImageLoader.switchMap("LIGHTBRIGHT");
        
        //
        //THE LETTERS
        //
        
        for(int i = (int)'a'; i <= (int)'z'; ++i){
            //System.out.println((char)i);
            TextImageBasic temp = ImageLoader.loadImage("letter_low_"+((char)i)+".txt");
            HeroLetter HL = new HeroLetter((char)i,temp);
            heromap.put((char)i,HL);
        }
        
        for(int i = (int)'A'; i <= (int)'Z'; ++i){
            //System.out.println((char)i);
            TextImageBasic temp = ImageLoader.loadImage("letter_cap_"+((char)i)+".txt");
            HeroLetter HL = new HeroLetter((char)i,temp);
            heromap.put((char)i,HL);
        }
        
        //
        //THE NUMBERS
        //
        
        for(int i = (int)'0'; i <= (int)'9'; ++i){
            //System.out.println((char)i);
            TextImageBasic temp = ImageLoader.loadImage("number_"+((char)i)+".txt");
            HeroLetter HL = new HeroLetter((char)i,temp);
            heromap.put((char)i,HL);
        }
        
        //
        //OTHERS
        //
        
        //SEMICOLON
        TextImageBasic temp = ImageLoader.loadImage("letter_semicolon.txt");
        HeroLetter HL = new HeroLetter(';',temp);
        heromap.put(';',HL);
    }
}
