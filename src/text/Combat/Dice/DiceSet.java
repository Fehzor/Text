/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Dice;

import static java.awt.event.KeyEvent.VK_G;
import static java.awt.event.KeyEvent.VK_H;
import java.util.ArrayList;
import text.Frame.TextListener;
import text.Tools.Tool;
import text.Utility.ImageLoader;

/**
 *
 * @author FF6EB4
 */
public class DiceSet extends Tool{
    
    public ArrayList<Die> dice = new ArrayList<>();
    public int missDivisor;
    public int startHits;
    
    public DiceRunner myRunner;
    
    public DiceSet(){
        super(ImageLoader.loadImage("dice.txt"),"Dice Set"); 
        this.slot = INSTRUMENT;
        
        this.startHits = 5;
        this.missDivisor = 500;
        myRunner = new DiceRunner(this);
    }
    
    public boolean act(){
        if(!held)return true;
        if(TextListener.isHeld(VK_G)){
            this.activate();
        }
        if(TextListener.isHeld(VK_H)){
            this.activate();
        }
        return false;
    }
    
    public String toString(){
        return this.name;
    }
}
