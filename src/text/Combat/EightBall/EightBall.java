/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.EightBall;

import text.Images.TextImage;
import text.Tools.Tool;
import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class EightBall extends Tool{
    public int players = 1+oRan.nextInt(4);
    
    public EightBallSystem EBS;
    
    public EightBall(TextImage TIB, String name){
        super(TIB, name);
        this.name = name;
        this.slot = TOOL;
        
        EBS = new EightBallSystem(this);
    }
    
    public String toString(){
        return this.name;
    }
}
