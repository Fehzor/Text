/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Inventory;

import java.awt.Color;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Utility.ColorTuple;

/**
 *
 * @author FF6EB4
 */
public class Core extends Actor{
    Inventory rList = new Inventory(0);
    int size = 1000; //1000 Is the default size of a core
    
    /**
     * Creates an empty core of size size
     * @param size 
     */
    public Core(int size){
        this.size = size;
    }
    
    public Core(ArrayList<Resource> rList){
        this.size = 0;
        for(Resource r : rList){
            this.rList.put(r);
            size += r.amount;
        }
    }
    
    public Core(int size, ArrayList<Resource> rList){
        this(size);
        for(Resource r : rList){
            this.rList.put(r);
        }
    }
    
    /**
     * Is this core full or not?
     * @return 
     * If full true
     */
    public boolean full(){
        return rList.resources.size() >= size;
    }
    
    public ColorTuple getIcon(){
        int r,g,b,r0,g0,b0,icon,tot;
        r=0;
        g=0;
        b=0;
        r0=0;
        g0=0;
        b0=0;
        icon=0;
        tot=1;
        
        for(Resource R : rList.resources){
            for(int i = 0; i<R.amount; ++i){
                r += R.icon.primary.getRed();
                g += R.icon.primary.getBlue();
                b += R.icon.primary.getGreen();
                r0 += R.icon.secondary.getRed();
                g0 += R.icon.secondary.getBlue();
                b0 += R.icon.secondary.getGreen();
                icon += R.icon.icon;
                tot+=1;
            }
        }
        
        r = r / tot;
        g = g / tot;
        b = b / tot;
        r0 = r0 / tot;
        g0 = g0 / tot;
        b0 = b0 / tot;
        icon = icon / tot;
        
        ColorTuple ret = new ColorTuple(new Color(r,g,b),new Color(r0,g0,b0),(char)icon);
        return ret;
    }
}
