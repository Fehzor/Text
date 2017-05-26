/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.PlayerCombatSystem;

import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class Body {
    public boolean head = true;
    public boolean torso = true;
    public boolean lArm = true;
    public boolean rArm = true;
    public boolean lLeg = true;
    public boolean rLeg = true;
    
    
    public String[]Display(){
        String[] ret = new String[7];
        ret[0] = "Your health  |";
        ret[1] = "Head:      "+ (head ? "✓" : "X")+" |";
        ret[2] = "Torso:     "+ (torso? "✓" : "X")+" |";
        ret[3] = "Left Arm:  "+ (lArm ? "✓" : "X")+" |";
        ret[4] = "Right Arm: "+ (rArm ? "✓" : "X")+" |";
        ret[5] = "Left Leg:  "+ (lLeg ? "✓" : "X")+" |";
        ret[6] = "Right Leg: "+ (rLeg ? "✓" : "X")+" |";
        
        return ret;
    }
    
    /**
     * Damages a random body part.
     * 
     * NOTE- this can and does repeat body parts. Basically, the first time you get
     * hit, you lose 1 life. The second time, you lose 1 5/6 of the time, etc.
     * 
     * @int i
     * Number of times to deal damage
     * 
     * 
    */
    public void damage(int i){
        for(int j = 0; j < i; ++j){
            int randomNum = oRan.nextInt(6);
            
            switch(randomNum){
                case 0:
                    head = false;
                    break;
                case 1:
                    torso = false;
                    break;
                case 2:
                    lArm = false;
                    break;
                case 3:
                    rArm = false;
                    break;
                case 4:
                    lLeg = false;
                    break;
                case 5:
                    rLeg = false;
                    break;
                          
            }
        }
    }
    
    //Is it dead yet?
    public boolean dead(){
        return !(head||torso||lArm||rArm||lLeg||rLeg);
    }
    
}
