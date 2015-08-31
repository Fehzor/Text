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
public class property_AddBlueRandom extends property {
    
    //This property adds some blue to the background
    
    int lower,upper;
    Random r = new Random();
    
    public property_AddBlueRandom(int lower, int upper){
        super(true);
        
        this.lower = lower;
        this.upper = upper;
    }
    
    public void apply(letterTuple LT, int col, int row, textPanel tp, Graphics g){
        Color back = LT.background();
        
        int newBlue = back.getBlue()+r.nextInt(this.upper)+lower;
        if(newBlue > 255) newBlue = 255;
        
        Color newBack = new Color((int)(back.getRed()/1.2),(int)(back.getGreen()/1.2),newBlue);
        
        LT.setBackground(newBack);
    }
}
