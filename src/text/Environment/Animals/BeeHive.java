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
public class BeeHive extends Nest{
    public BeeHive(TextImageBasic TIB){
        super(TIB);
        for(int i = 0; i < 100; ++i){
            this.add(new Bee());
        }
    }
    
    public String toString(){
        return "Beehive";
    }
}
