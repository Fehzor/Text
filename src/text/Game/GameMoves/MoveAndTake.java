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
public class MoveAndTake extends GameMove{
    int row = 0;
    int col = 0;
    
    public MoveAndTake(int row,int col,String name){
        super();
        this.name = name;
        this.row = row;
        this.col = col;
    }
    
    public int[][] makeMove(GamePiece actUpon, GameBoard board){
        
        if(checkBounds(actUpon,board.board)){
            if(!checkPosition(actUpon,board.board)){
                int piece = board.board[col + actUpon.posX][row + actUpon.posY];
                GamePiece replace = board.pieceMap.get(piece);
                replace.dead = true;
            }
            System.out.println("Bounds succeeded!");
            return move(actUpon,board.board);
        }
        System.out.println("Bounds failed!");
        return board.board;
    }
    
    public int[][] move(GamePiece actUpon, int[][] board){
        board[actUpon.posX][actUpon.posY] = 0;
        board[col + actUpon.posX][row + actUpon.posY] = actUpon.ID;
        actUpon.posY = row + actUpon.posY;
        actUpon.posX = col + actUpon.posX;
        
        return board;
    }
    
    public boolean checkPosition(GamePiece actUpon, int[][] board){
        
        if(board[col + actUpon.posX][row + actUpon.posY] > 0)return false;
        
        return true;
    }
    
    public boolean checkBounds(GamePiece actUpon, int[][] board){
        if(row + actUpon.posY < 0)return false;
        if(row + actUpon.posY > GameBoard.BOARD_HEIGHT - 1)return false;
        if(col + actUpon.posX < 0)return false;
        if(col + actUpon.posX > GameBoard.BOARD_WIDTH - 1)return false;
        return true;
    }
    
    public String toString(){
        return this.name;
    }
}
