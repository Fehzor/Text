/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Game.GameAI;

import text.Game.GameBoard;
import text.Game.GameIntellect;
import text.Game.GameMove;
import text.Game.GamePiece;
import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class CycleAI extends GameIntellect{
    int num = 0;
    
    public GameMove chooseMove(GamePiece GP, GameBoard board){
        return GP.moves.get(num++ % GP.moves.size());
    }
    
    public String toString(){
        return "Cycle AI";
    }
}
