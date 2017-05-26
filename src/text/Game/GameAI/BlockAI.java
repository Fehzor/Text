/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Game.GameAI;

import text.Game.GameBoard;
import static text.Game.GameBoard.BOARD_HEIGHT;
import static text.Game.GameBoard.BOARD_WIDTH;
import text.Game.GameIntellect;
import text.Game.GameMove;
import text.Game.GamePiece;
import text.Game.GamePieces.BlockPiece;
import text.Game.GamePieces.BlockerPiece;
import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class BlockAI extends GameIntellect{
    
    int block = -1;
    
    public GameMove chooseMove(GamePiece GP, GameBoard board){
        if(oRan.nextInt(100) < 23){
            block = -1;
        }
        
        //If it's lacking a piece to block...
        if(block == -1){
            for(int[] a : board.board){
                for(int b : a){
                    if( b != 0 && GP.ID != b){
                        if(!((board.pieceMap.get(b) instanceof BlockPiece) ||
                                (board.pieceMap.get(b) instanceof BlockerPiece))){
                            block = b;
                            break;
                        }
                    }
                }
            }
        } else { //Otherwise, check to make sure
            boolean found = false;
            for(int[] a : board.board){
                for(int b : a){
                    if( b == block ) found = true;
                }
            }
            if(found == false){
                block = -1;
                return null;
            }
        }
        
        boolean up = true;
        for(int i = 0; i < BOARD_HEIGHT; ++i){
                for(int j = 0; j < BOARD_WIDTH; ++j){
                    int b = board.board[j][i];
                    if(b == GP.ID){
                        up = false;
                    }
                    if( b == block){
                        if(up){
                            return GP.moves.get(1);
                        } else {
                            return GP.moves.get(0);
                        }
                    }
                }
            }
        
        return null;
    }
    
    public String toString(){
        return "Block AI";
    }
}
