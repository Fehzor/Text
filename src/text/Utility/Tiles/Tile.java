/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Utility.Tiles;

import text.Images.*;

import java.util.*;
import text.Utility.ColorTuple;

/**
 * @author FF6EB4
 */
public class Tile {
    
    public int TILE_HEIGHT = 8;
    
    public TextImageBasic image;
    
    public Tile(TextImageBasic image){      
        
        this.image = image;
    }
    
    //adds this Tile's image to a TIB
    //Start is the tile down to add to. 
    //So if you put 1 it starts at 1*tilehight = 7 right now
    //Ergo it is one tile down from the first row.
    public void addTo(int start,TextImageBasic addTo){
        TextImageBasic myImage = image.clone();
        
        int row = start * TILE_HEIGHT;
        for(ArrayList<ColorTuple> list : myImage.image){
            while(addTo.image.size() <= row){
                addTo.image.add(new ArrayList<>());
            }
            
            for(ColorTuple T : list){
                addTo.image.get(row).add(T);
            }
            row++;
        }   
    }
    
    public Tile clone(){
        return new Tile(this.image.clone());
    }
    
}
