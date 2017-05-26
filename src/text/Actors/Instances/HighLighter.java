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
import text.Images.TextImageBasic;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import text.Utility.ImageLoader;
import text.Inventory.Physical;
import text.Utility.Diction.LiteralDictionary;
import text.Utility.Diction.Word;
import text.WorldFrame.Templates.WorldTemplate;
import static text.WorldFrame.Templates.WorldTemplate.WORDSEARCH;
import text.WorldFrame.Worlds.WordSearchWorld;

/**
 *
 * @author FF6EB4
 */
public class HighLighter extends Interactable {
    
    WordSearchWorld owner;
    
    int uses = 5;
    
    public HighLighter(WordSearchWorld owner){
        super("Highlighter");
        this.dead = false;
        this.owner = owner;
        //System.out.println("FOOD");
        this.name = "Highlighter";
        ImageLoader.switchMap("CITYSCALE");
        this.image = ImageLoader.loadImage("highlighter.txt");
        this.depth = y;
    }
    
    public HighLighter clone(){
        return new HighLighter(this.owner);
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = super.pollOptions();
        
        ret.add(new Option(this){
            public boolean act(){
                try{
                    ((HighLighter)this.owner).owner = (WordSearchWorld)Player.The.current.owner;
                } catch (Exception E){System.out.println("SET FAILED");};
                
                ((HighLighter)owner).owner.toggleHighlight(true);
                
                return true;
            }
            
            public String toString(){
                return "Highlight";
            }
        });
        
        Option A = new Option(this){
            public boolean act(){
                 ((HighLighter)owner).owner.toggleHighlight(false);
                 String word = ((HighLighter)owner).owner.word;
                 ((HighLighter)owner).owner.word = "";
                 
                 if(LiteralDictionary.isWord(word)){
                     if(((HighLighter)owner).owner.taken.contains(word)){
                         return true;
                     }
                     ((HighLighter)owner).owner.taken.add(word);
                     Player.inv.addStuff(new Physical<Word>(LiteralDictionary.getWord(word)));
                 }
                 uses = uses - 1;
                 if(uses == 0){
                     Player.inv.removeStuff(owner);
                 }
                 
                return true;
            }
            
            public String toString(){
                return "Rip current letters";
            }
        };
        
        ret.add(A);
        
        return ret;
    }
    
    public String toString(){
        return "Highlighter- uses:"+uses;
    }
}
