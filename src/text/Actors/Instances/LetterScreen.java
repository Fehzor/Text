/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.Instances;

import java.awt.Color;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Interactable;
import text.Actors.Messages.Option;
import text.Actors.Player;
import text.Frame.TextDisplay;
import text.Images.TextImageBasic;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import text.Utility.ImageLoader;
import text.Images.TextImageComplex;
import text.Utility.Diction.LiteralDictionary;
import text.Utility.LootGenerator;
import text.WorldFrame.Worlds.WordSearchWorld;

/**
 *
 * @author FF6EB4
 */
public class LetterScreen extends Interactable {
    public char c;
    
    public LetterScreen(char c){
        super("Projector");
        this.name = "Projector";
        this.c = c;
        this.dead = false;
        //System.out.println("FOOD");
        this.name = "";
        ImageLoader.switchMap("CITYSCALE");
        //NEEDS A SCREEN IMAGE!!!!!!!!!!!!!!!
        TextImageBasic TIB = ImageLoader.loadImage("hologram.txt");
        this.image = new TextImageComplex(TIB);
        TextImageBasic let = ImageLoader.loadImage("letter_cap_"+c+".txt");
        
        ((TextImageComplex)this.image).addKnob(-6, -18, let);
        
        Color cyan  = new Color(51, 204, 255);
        
        ImageBuilder.colorMergeImage(let, new ColorTuple(cyan,Color.WHITE,' '));
        
        this.x = TextDisplay.SCREEN_SIZE_X /2;
        this.y = TextDisplay.SCREEN_SIZE_Y /2;
        
        this.depth = y;
    }
    
    public LetterScreen clone(){
        return new LetterScreen(this.c);
    }
    
    public ArrayList<Actor> pollOptions(){
        Option A = Option.cancel(this);

        ArrayList<Actor> aList = new ArrayList<Actor>();
        aList.add(A);
        
        Option changeLetter = new Option(this){
        
            LetterScreen screen = (LetterScreen)owner;
        
            public boolean act(){
                owner.paused = false;
              
                if(Player.The.inv.checkResources(screen.c+"",1)){
                    Player.The.inv.drainResources(""+screen.c, 1);
                    char c = LiteralDictionary.getRandomLetter();

                    ImageLoader.switchMap("CITYSCALE");
                    TextImageBasic TIB = ImageLoader.loadImage("hologram.txt");
                    screen.image = new TextImageComplex(TIB);
                    TextImageBasic let = ImageLoader.loadImage("letter_cap_"+c+".txt");
                    ((TextImageComplex)screen.image).addKnob(-6, -18, let);
        
                    Color cyan  = new Color(51, 204, 255);
        
                    ImageBuilder.colorMergeImage(let, new ColorTuple(cyan,Color.WHITE,' '));

                    screen.c = c;
                } else {
                    String[] disp = new String[1];
                    disp[0] = "You don't have any '"+c+"'.";
                    Player.The.current.owner.display(disp);
                }
                  return true;}
              public String toString(){return "Change Letter (1x'"+screen.c+"')";}
        };
        
        aList.add(changeLetter);

        return aList;
    }
    
    public String toString(){
        return "Projector: "+this.c;
    }
}
