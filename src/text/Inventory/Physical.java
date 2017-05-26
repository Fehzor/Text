/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Inventory;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import text.Utility.ColorTuple;

/**
 * Has a name and something it is holding onto.
 * 
 * @author FF6EB4
 */
public class Physical<E> extends Item implements Serializable{
    String name; //What is its name?
    private E data;
    
    public Physical(E data){
        super();
        this.data = data;
    }
    
    public Physical(ColorTuple icon,E data){
        super(icon);
        this.data = data;
    }
    
    public Physical(ArrayList<String> tags,ColorTuple icon, E data){
        super(tags, icon);
        this.data = data;
    }
    
    public E getData(){
        return data;
    }
    
    public String toString(){
        return name;
    }
}
