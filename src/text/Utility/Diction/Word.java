/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Utility.Diction;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Inventory.Resource;
import text.Utility.LootGenerator;

/**
 *
 * @author FF6EB4
 */
public class Word extends Actor{
    public String s;
    public int number;
    public char type;
    public int freq;
    public double disp;
    
    public int value = 0;
    
    public Word(int number, String s, char type, int freq, double disp){
        this.number = number;
        this.s = s;
        this.type = type;
        this.freq = freq;
        this.disp = disp;
        
        this.held = true;
        this.paused = true;
        value = LiteralDictionary.getValue(s);
    }
    
    public Word(String s){
        this.number = -1;
        this.s = s;
        this.type = ' ';
        this.freq = 0;
        this.disp = 0.0;
        
        this.held = true;
        this.paused = true;
        
        value = LiteralDictionary.getValue(s);
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = new ArrayList<>();
        
        ArrayList<Resource> give = LootGenerator.getLootValue(this.s, this.value);
        
        Option A = Option.convert(this, give);
        
        ret.add(Option.cancel(this));
        ret.add(A);
        
        return ret;
    }
    
    public String toString(){
        return "\""+s+"\" ("+value+"¢)";
    }
}
