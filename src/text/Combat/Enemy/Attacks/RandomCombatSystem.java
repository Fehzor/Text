/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Enemy.Attacks;

import java.util.ArrayList;
import text.Actors.Player;
import text.Combat.CombatSystem;
import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class RandomCombatSystem extends CombatSystem{
    public ArrayList<CombatSystem> systems = new ArrayList<>();
    public ArrayList<Integer> weights = new ArrayList<>();
    int totalWeight = 0;
    
    CombatSystem current;
    
    public boolean first = true;
    
    public boolean go(){
        if(first){
            Player.The.current.addActor(current);
            first = false;
        }
        
        boolean ret = current.go();
        this.done = current.done();
        this.hits = current.hits;
        this.misses = current.misses;
        
        return ret; 
    }
    
    public boolean done(){
        if(done){
            done = false;
            first = true;
            current.hits = 0;
            current.misses = 0;
            current = getSystem();
            return true;
        } else {
            return false;
        }
    }
    
    public CombatSystem getSystem(){
        int get = oRan.nextInt(totalWeight);
        int i = 0;
        while(get > weights.get(i)){
            get = get - weights.get(i);
            ++i;
        }
        return systems.get(i);
    }
    
    public void addCombatSystem(CombatSystem CS, int weight){
        if(systems.size() == 0){
            current = CS;
        }
        systems.add(CS);
        weights.add(weight);
        totalWeight+= weight;
    }
    
    public void addCombatSystem(CombatSystem CS){
        this.addCombatSystem(CS, 1);
    }
}
