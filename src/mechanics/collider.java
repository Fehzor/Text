/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanics;

/**
 *
 * @author Awesomesauce
 */
public abstract class collider {
    public actor owner;
    
    public collider(actor a){
        owner = a;
    }
    
    public abstract boolean checkCollision(int x, int y);
}
