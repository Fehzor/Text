/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Environment.Animals;

import java.util.ArrayList;
import text.Actors.Interactable;
import text.Actors.Player;
import text.Behaviour.BehaviourFeral;
import text.Images.TextImageBasic;
import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class Nest extends Interactable{
    ArrayList<Animal> AList = new ArrayList<>();
    
    public Nest( TextImageBasic image ){
        this.image = image;
    }
    
    public boolean act(){
        if(oRan.nextInt(100) == 23 && AList.size()>0){
            Animal A = AList.get(0);
            if(A == null){
                return true;
            }
            A.dead = false;
            A.x = this.x;
            A.y = this.y;
            A.home = this;
            //A.behav = new BehaviourFeral();
            Player.The.current.addActor(A);
            AList.remove(0);
        }
        
        return true;
    }
    
    public void add(Animal A){
        this.AList.add(A);
    }
    
    public String toString(){
        return this.name;
    }
}
