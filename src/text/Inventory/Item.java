/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Inventory;

import java.awt.Color;
import java.io.Serializable;
import text.Utility.*;
import java.util.*;
/**
 *
 * @author FF6EB4
 */
public class Item implements Serializable{
    public ArrayList<String> tags;
    public ColorTuple icon;
    
    public ColorTuple get(){
        return icon;
    }
    
    public Item(){
        this.tags = new ArrayList<>();
        this.icon = new ColorTuple(Color.BLACK,Color.RED,'?');
    }
    
    public Item(ColorTuple icon){
        this.tags = new ArrayList<>();
        this.icon = icon;
    }
    
    public Item(ArrayList<String> tags,ColorTuple icon){
        this.tags = tags;
        this.icon = icon;
    }
}
