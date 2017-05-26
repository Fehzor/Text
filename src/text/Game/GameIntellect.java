/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Game;

import java.util.ArrayList;
import text.Actors.Interactable;
import text.Game.GameAI.*;
import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class GameIntellect extends Interactable{
    public static ArrayList<GameIntellect> GIList = loadGIList();
    
    public GameMove chooseMove(GamePiece GP, GameBoard board){
        System.out.println("NOPE");
        return null;
    }
    
    public String toString(){
        return "Intellect";
    }
    
    public static GameIntellect getRandomGI(){
        return GIList.get(oRan.nextInt(GIList.size()));
    }
    
    public static ArrayList<GameIntellect> loadGIList(){
        ArrayList<GameIntellect> ret = new ArrayList<>();
        
        ret.add(new RandomAI());
        ret.add(new CycleAI());
        ret.add(new RepeaterAI());
        
        return ret;
    }
}
