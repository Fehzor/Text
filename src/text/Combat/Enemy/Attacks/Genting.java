/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Enemy.Attacks;

import text.Actors.Actor;
import text.Combat.CombatSystem;

/**
 *
 * @author FF6EB4
 */
public class Genting extends CombatSystem{
    int pass = 100;
    int passNum = 0;
    public boolean done = false;
    
    public boolean act(){
        if(passNum == pass){
            done = true;
            passNum = 0;
        } else {
            done = false;
            passNum++;
        }
        return true;
    }
    
    public boolean done(){
        return done;
    }
    
    public String[] display(){
        String[] ret = new String[7];
        ret[0] = "";
        ret[1] = "";
        ret[2] = "";
        ret[3] = "...He's genting! He's letting you win! But why????";
        ret[4] = "";
        ret[5] = "";
        ret[6] = "";
        return ret;
    }
    
}
