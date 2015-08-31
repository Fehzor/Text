/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.properties;

import display.textPanel;
import java.awt.Graphics;
import java.awt.Color;
import utility.letterTuple;
import utility.property;
import java.util.Random;
/**
 *
 * @author Awesomesauce
 */
public class property_Sparkle extends property {
    
    //This property adds some blue to the background
    
    int chance; // Chance = out of 100
    
    Random r = new Random();
    
    public property_Sparkle(int chance){
        super(true);
        
        this.chance = chance;
    }
    
    public void apply(letterTuple LT, int col, int row, textPanel tp, Graphics g){  
        if(r.nextInt(100) < chance){
           LT.setForeground(Color.white);
           LT.setLetter('*');
           LT.setBackground(LT.background().brighter());  
        }
    }
}
