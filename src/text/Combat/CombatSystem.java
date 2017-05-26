/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat;

import java.util.PriorityQueue;
import text.Actors.Actor;
import text.Utility.ColorTuple;

/**
 *
 * @author FF6EB4
 */
public class CombatSystem extends Actor{
    
    public boolean done = false;
    
    public int hits = 0;
    public int misses = 0;
    
    int pass = 100;
    int passNum = 0;
    
    public String[] display(){
        String[] ret = new String[7];
        ret[0] = "";
        ret[1] = "";
        ret[2] = "";
        ret[3] = "...Ya got nothin'. Better just wait...";
        ret[4] = "";
        ret[5] = "";
        ret[6] = "";
        return ret;
    }
    
    public void setDone(){
        this.done = true;
    }
    
    public boolean done(){
        if(done){
            done = false;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean go(){
        if(passNum == pass){
            done = true;
            passNum = 0;
        } else {
            done = false;
            passNum++;
        }
        return true;
    }
    
    public void reset(){}
}
