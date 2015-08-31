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
public class collider_circle extends collider {
    
    int x,y,radius;
    
    public collider_circle(int x, int y, int radius, actor owner){
        super(owner);
        
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    
    public boolean checkCollision(int x, int y){
        int distx = Math.abs(this.x-x);
        int disty = Math.abs(this.y-y);
        
        return Math.sqrt(distx*distx + disty*disty) < this.radius;
    }
}
