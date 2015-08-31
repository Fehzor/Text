/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanics.colliders;

import mechanics.actor;
import mechanics.collider;

/**
 *
 * @author Awesomesauce
 */
public class collider_rectangle extends collider {
    
    int xStart,yStart,xEnd,yEnd;
    
    public collider_rectangle(int xStart, int xEnd, int yStart, int yEnd, actor owner){
        super(owner);
        
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }
    
    /**
    Checks if its in the box.
    */
    public boolean checkCollision(int x, int y){
        return x<=xEnd&&x>=xStart&&y<=yEnd&&y>=yStart;
    }
}
