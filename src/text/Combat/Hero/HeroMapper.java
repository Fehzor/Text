/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Hero;

import java.util.ArrayList;
import java.util.HashMap;
import text.Actors.Player;
import text.Combat.CombatSystem;
import text.Frame.TextDisplay;
import text.Utility.ColorTuple;

/**
 *
 * @author FF6EB4
 */
public class HeroMapper extends CombatSystem{
    public ArrayList<Integer> intList = new ArrayList<>();
    public HashMap<Integer,Character> charMap = new HashMap<>();
    public HashMap<Integer,Integer> stepMap = new HashMap<>();
    public HashMap<Integer,Integer> posMap = new HashMap<>();
    public HashMap<Integer,Double> speedMap = new HashMap<>();
    
    public ArrayList<HeroLetter> letterList = new ArrayList<>();
    
    public int lastLetter = 0;
    public int letter = 0;
    
    public int step = 0;
    public int delay = 3;
    
    public int height = 0;
    
    public boolean act(){
        if(this.held != true){
            return go();
        } else {
            return false;
        }
    }
    
    public boolean go(){
        Player.The.paused = true;
        
        if(letter == lastLetter){
            this.dead = true;
            Player.The.paused = false;
            this.setDone();
            letter = 0;
            step = 0;
        }
        
        //Is it time to act?
        if(step < delay){
            step+=1;
            return true;
        } else {
            step = 0;
        }
        
        if(letter < lastLetter){
            for(int i : intList){
                if(letter == stepMap.get(i)){
                    int spawn = posMap.get(i);
                    double speed = speedMap.get(i);
                    char c = charMap.get(i);
                    ColorTuple CT = HeroController.EZ;
                    
                    this.letterList.add(HeroLetter.addHero(spawn,speed,c,CT,height));
                }
            }
            
            letter++;
        }
        
        for(int i = letterList.size() - 1; i >= 0; --i){
            if(letterList.get(i).pressed){
                letterList.remove(i);
                this.hits += 1;
                //System.out.println("ADDING ONE");
                break;
            }
            if( letterList.get(i).y > TextDisplay.SCREEN_SIZE_Y){
                letterList.remove(i);
                //System.out.println("SUBTRACTING ONE");
                this.misses += 1;
            }
        }
        
        return true;
    }
    
    public void addHero(int step, int row, double speed, char let){
        int i = intList.size();
        this.intList.add(i);
        posMap.put(i,row * 13);
        stepMap.put(i, step);
        speedMap.put(i, speed);
        charMap.put(i, let);
        if(step >= lastLetter){
            lastLetter = step + 1;
        }
    }
}
