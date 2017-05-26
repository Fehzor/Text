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
public class RepeaterAI extends GameIntellect{
    int move = -1;
    public GameMove chooseMove(GamePiece GP, GameBoard board){
        if(move == -1 || oRan.nextInt(100) < 30){
            move = oRan.nextInt(GP.moves.size());
        }
        return GP.moves.get(move);
    }
    
    public String toString(){
        return "Random Repeater AI";
    }
}
