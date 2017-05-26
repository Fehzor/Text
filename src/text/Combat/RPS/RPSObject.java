/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.RPS;

import text.Combat.EightBall.*;
import text.Images.TextImage;
import text.Tools.Tool;
import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class RPSObject extends Tool{
    public int value = 10;
    
    public int getRPS(){
        return 1;
    }
    
    public RPSSystem RPS;
    
    public RPSObject(TextImage TIB, String name){
        super(TIB, name);
        this.name = name;
        this.slot = TOOL;
        
        RPS = new RPSSystem(this);
    }
    
    public String toString(){
        return this.name;
    }
}
