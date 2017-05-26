/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Templates;

import java.util.ArrayList;
import text.Environment.Environment;
import text.Game.GameAI.RandomAI;
import static text.Game.GameBoard.BOARD_HEIGHT;
import static text.Game.GameBoard.BOARD_WIDTH;
import text.Game.GameIntellect;
import text.Game.GameMoves.CheckAndMove;
import text.Game.GameMoves.MoveAndTake;
import text.Game.GamePiece;
import text.WorldFrame.GameCoordinator;
import text.WorldFrame.Progress;

/**
 *
 * @author FF6EB4
 */
public class GameTemplate extends WorldTemplate{
    
    public static ArrayList<int[][]> boardList = loadBoardList();
    public static ArrayList<GamePiece> allPieces = loadPieces();
    
    public int level = GameCoordinator.The.prog.gameWorldLevel;
    
    public GameTemplate(){
        this.worldKind = GAME;
        this.worldType = GAME_ROOM;
        this.E = new Environment(Environment.TYPE_NONE);
        this.size = 2;
        this.rooms = 3;
        
        selectGameBoard();
        
        selectGamePieces();
        
    }
    
    
    public void selectGameBoard(){
            this.gameboard = boardList.get(level);
    }
    
    public void selectGamePieces(){
        this.pieceList = new ArrayList<>();
        this.pieceList.add(allPieces.get(0));
        this.pieceList.add(allPieces.get(1));
        this.pieceList.add(allPieces.get(2));
    }
    /**
     * 0 = empty
     * 1 = blocked
     * 2 = blocker
     * 
     * @return 
     * A list of boards that tutorialize everything in the minigame.
     */
    private static ArrayList<int[][]> loadBoardList() {
        ArrayList<int[][]> boards = new ArrayList<>();
        
        //
        //Board 1, an empty board.
        //
        int[][] board = new int[BOARD_WIDTH][BOARD_HEIGHT];
        boards.add(board);
        
        //
        //Board 2, a row with a piece missing.
        //
        board = new int[BOARD_WIDTH][BOARD_HEIGHT];
        for(int i = 0; i<BOARD_HEIGHT; ++i){
            if(i != 3){
                board[5][i] = 1;
            }
        }
        boards.add(board);
        
        //
        //Board 3, a single blocker
        //
        board = new int[BOARD_WIDTH][BOARD_HEIGHT];
        board[5][4] = 2;
        boards.add(board);
        
        //
        //Board 4, a full row.
        //
        board = new int[BOARD_WIDTH][BOARD_HEIGHT];
        for(int i = 0; i<BOARD_HEIGHT; ++i){
            
            board[5][i] = 1;
            
        }
        boards.add(board);
        
        //
        //Board 5, multiple blockers
        //
        board = new int[BOARD_WIDTH][BOARD_HEIGHT];
        board[2][4] = 2;
        board[3][3] = 2;
        board[4][5] = 2;
        board[5][4] = 2;
        board[6][3] = 2;
        board[7][5] = 2;
        boards.add(board);
        
        
        
        //
        //Board 6, blockers in rows
        //
        board = new int[BOARD_WIDTH][BOARD_HEIGHT];
        board[2][4] = 2;
        board[2][3] = 2;
        board[2][5] = 2;
        
        board[6][4] = 2;
        board[6][3] = 2;
        board[6][5] = 2;
        
        board[5][4] = 2;
        board[5][3] = 2;
        board[5][5] = 2;
        boards.add(board);
        
        
        //
        //Hard Board, multiple blockers with walls
        //
        /*/
        board = new int[BOARD_WIDTH][BOARD_HEIGHT];
        for(int i = 0; i<BOARD_HEIGHT; ++i){
            if(i != 3){
                board[6][i] = 1;
            }
            if(i != 4){
                board[3][i] = 1;
            }
        }
        board[2][4] = 2;
        board[4][5] = 2;
        board[5][4] = 2;
        board[7][5] = 2;
        boards.add(board);
        //*/
        
        
        return boards;
    }

    private static ArrayList<GamePiece> loadPieces() {
        ArrayList<GamePiece> ret = new ArrayList<>();
        
        //The most simple piece possible- moves across...
        ret.add(new GamePiece('A'));
        ret.get(0).GI = new RandomAI();
        ret.get(0).moves.add( new CheckAndMove(0,1,"→") );
        
        //A checker!
        ret.add(new GamePiece('B'));
        ret.get(1).GI = new RandomAI();
        ret.get(1).moves.add( new MoveAndTake(1,1,"↘X") );
        ret.get(1).moves.add( new MoveAndTake(-1,1,"↗X") );
        
        //Capable of moving forward in a variety of ways.
        ret.add(new GamePiece('C'));
        ret.get(2).GI = new RandomAI();
        ret.get(2).moves.add( new CheckAndMove(0,1,"→") );
        ret.get(2).moves.add( new CheckAndMove(1,1,"↘") );
        ret.get(2).moves.add( new CheckAndMove(-1,1,"↗") );
        
        return ret;
    }
}
