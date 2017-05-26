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
public class RandomAI extends GameIntellect{
    public GameMove chooseMove(GamePiece GP, GameBoard board){
        int r = oRan.nextInt(GP.moves.size());
        return GP.moves.get(r);
    }
    
    public String toString(){
        return "Random AI";
    }
}
