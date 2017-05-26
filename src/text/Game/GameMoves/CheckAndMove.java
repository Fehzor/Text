/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Game.GameMoves;

import text.Game.GameBoard;
import text.Game.GameMove;
import text.Game.GamePiece;

/**
 *
 * @author FF6EB4
 */
public class CheckAndMove extends GameMove{
    int row = 0;
    int col = 0;
    
    public CheckAndMove(int row,int col,String name){
        super();
        this.name = name;
        this.row = row;
        this.col = col;
    }
    
    public int[][] makeMove(GamePiece actUpon, GameBoard board){
        if(check(actUpon,board.board)){
            return move(actUpon,board.board);
        }
        return board.board;
    }
    
    public int[][] move(GamePiece actUpon, int[][] board){
        board[actUpon.posX][actUpon.posY] = 0;
        board[col + actUpon.posX][row + actUpon.posY] = actUpon.ID;
        actUpon.posY = row + actUpon.posY;
        actUpon.posX = col + actUpon.posX;
        
        return board;
    }
    
    public boolean check(GamePiece actUpon, int[][] board){
        if(row + actUpon.posY < 0)return false;
        if(row + actUpon.posY > GameBoard.BOARD_HEIGHT - 1)return false;
        if(col + actUpon.posX < 0)return false;
        if(col + actUpon.posX > GameBoard.BOARD_WIDTH - 1)return false;
        
        if(board[col + actUpon.posX][row + actUpon.posY] > 0)return false;
        
        return true;
    }
    
    public String toString(){
        return this.name;
    }
}
