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

/**
 *
 * @author Awesomesauce
 */
public class property_AddBlue extends property {
    
    //This property adds some blue to the background
    
    int amount;
    
    public property_AddBlue(int amount){
        super(true);
        
        this.amount= amount;
    }
    
    public void apply(letterTuple LT, int col, int row, textPanel tp, Graphics g){
        Color back = LT.background();
        
        int newBlue = back.getBlue()+this.amount;
        if(newBlue > 255) newBlue = 255;
        
        Color newBack = new Color((int)(back.getRed()/1.2),(int)(back.getGreen()/1.2),newBlue);
        
        LT.setBackground(newBack);
    }
}
