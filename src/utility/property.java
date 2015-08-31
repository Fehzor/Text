/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import utility.letterTuple;
import display.textPanel;
import java.awt.Graphics;

/**
 *
 * @author Awesomesauce
 */
public abstract class property {
    
    private boolean before;
    
    public property(boolean before){
        this.before = before;
    }
    
    /*
    Applies an effect to a letter tuple while drawing. 
    For instance, a shine..
    */
    public void apply(letterTuple LT, int col, int row, textPanel tp, Graphics g){
        return;
    }
    
    /*
    Is the property applied before or after?
    */
    public boolean before(){
        return this.before;
    }
}
