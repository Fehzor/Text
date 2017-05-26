/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Regions;

import java.util.ArrayList;
import static text.Utility.SuperRandom.oRan;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class Region {
    public World head;
    public ArrayList<World> tail;
    
    public Region(World head, ArrayList<World> tail){
        this.head = head;
        this.tail = tail;
    }
    
    //Connects to the region by head... like the human centipede.
    public void connect(Region R){
        World A = randomWorld();
        
        World B = R.head;
        
        A.connect(B);
    }
    
    //Connects to the region multiple times.
    public void intertwine(Region R){
        for(int i = 0 ; i < 3; ++i){
            World A = randomWorld();
            World B = R.randomWorld();
            
            A.connect(B);
        }
    }
    
    public World randomWorld(){
        int r = oRan.nextInt(tail.size());
        
        return tail.get(r);
    }
}
