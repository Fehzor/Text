/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanics;

import java.util.*;
import utility.*;
/**
 *
 * @author Awesomesauce
 */
public class worldGenerator_GridTower extends worldGenerator{
    
    int height, width;
    ArrayList<ArrayList<letterTuple>> background;
            
    public worldGenerator_GridTower(int height, int width, ArrayList<ArrayList<letterTuple>> background){
        this.height = height;
        this.width = width;
        
        this.background = background;
    }
    
    public void generateWorld(worldHub WH){
        
        
    }
}
