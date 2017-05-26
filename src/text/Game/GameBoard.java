/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Actors.Player;
import text.Game.GamePieces.BlockPiece;
import text.Game.GamePieces.BlockerPiece;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import text.Utility.ImageLoader;
import text.Utility.Tiles.TileBuilder;
import text.WorldFrame.Room;
import text.WorldFrame.Worlds.GameWorld;

/**
 *
 * @author FF6EB4
 */
public class GameBoard extends Actor{
    
    public int[][] board;
    public HashMap <Integer, GamePiece> pieceMap;
    int piece = 1;
    
    public static final int TILE_WIDTH = 9;
    public static final int TILE_HEIGHT = 4;
    
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 8;
    
    public static final int SKIP = 15;
    public int skip = 0;
    
    
    
    public GameBoard(Room R){
        super();
        
        this.image = TileBuilder.buildGameBoard(BOARD_WIDTH,BOARD_HEIGHT);
        R.BG.addTextImageBasicBack(new Point(36,19),(TextImageBasic)this.image);
        
        ImageLoader.switchMap("CITYSCALE");
        TextImageComplex term = new TextImageComplex((TextImageBasic)ImageLoader.loadImage("game_terminal.txt"));
        this.image = term;
        
        //((TextImageComplex)this.image).addKnob(0,0,term);
        
        board = new int [BOARD_WIDTH][BOARD_HEIGHT];
        pieceMap = new HashMap<>();
        this.depth = -1000;
        
        for(int i = 0; i < BOARD_WIDTH; ++i){
            for(int j = 0; j< BOARD_HEIGHT; ++j){
                board[i][j] = 0;
            }
        }
        this.current = R;
    }
    
    public GameBoard(int[][] board, Room R){
        this(R);
        createBoard(board);
    }
    
    public void createBoard(int[][] board){
        BlockPiece block = new BlockPiece();
        BlockerPiece blocker = new BlockerPiece();
        
        for(int i = 0; i<BOARD_WIDTH; ++i){
            for(int j = 0; j < BOARD_HEIGHT; ++j){
                if(board[i][j] ==  1){
                    BlockPiece add = block.clone();
                    this.addGamePiece(add,j,i);
                }
                
                if(board[i][j] ==  2){
                    //System.out.println("adding blocker");
                    BlockerPiece add = blocker.clone();
                    this.addGamePiece(add,j,i);
                }
            }
        }
    }
            
    //
    public boolean act(){
        if(skip < SKIP){
            skip++;
            return true;
        } else {
            skip = 0;
        }
        
        this.updateBoard();
        this.setBoard();
        boolean solved = this.checkSolution();
        
        if(solved){
            //System.out.println("SOLVED");
            if(Player.The.current.owner instanceof GameWorld){
                ((GameWorld)Player.The.current.owner).openTreasure();
            }
        }
        
        return true;
    }
    
    private void updateBoard(){
        for(int i = piece-1; i >= 1; --i){
            GamePiece GP = pieceMap.get(i);
            if(GP.dead == true || GP.held == true){
                if(board[GP.posX][GP.posY] == GP.ID){
                    board[GP.posX][GP.posY] = 0;
                }
            } else {
                this.board = GP.makeMove(this);
            }
        }
    }
    
    //Moves the pieces to position.
    private void setBoard(){
        //System.out.println("SETBOARD");
        
        for(int i = 1; i < piece; ++i){
            GamePiece GP = pieceMap.get(i);
            int xpos = this.x + GP.posX * TILE_WIDTH + TILE_WIDTH/2;
            int ypos = this.y + GP.posY * TILE_HEIGHT + TILE_HEIGHT - 1;
            GP.x = xpos;
            GP.y = ypos;
        }
        
        /*
        for(int i = 0; i < BOARD_WIDTH; ++i){
            System.out.println();
            for(int j = 0; j< BOARD_HEIGHT; ++j){
                System.out.print(board[i][j]);
            }
        }
        
        System.out.print("\n\n#$$$#\n");
        */
    }
    
    private boolean checkSolution(){
        for(int i = 0; i < BOARD_HEIGHT; ++i){
            if(board[BOARD_WIDTH - 1][i] > 0){
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> options = new ArrayList<>();
        options.add(Option.cancel(this));
        
        for(int i = 0; i< BOARD_HEIGHT; ++i){
            options.add(Option.attachGamePiece(this, i));
        }
        
        return options;
    }
    
    public boolean addGamePiece(GamePiece toAdd, int row, int col){
        if(board[col][row] != 0){
            return false;
        }
        
        pieceMap.put(piece,toAdd);
        this.board[col][row] = piece;
        toAdd.ID = piece;
        toAdd.posX = col;
        toAdd.posY = row;
        piece++;
        
        this.current.addActor(toAdd);
        toAdd.held = false;
        toAdd.dead = false;
        setBoard();
        
        return true;
    }
    
    public String toString(){
        return "Game Board";
    }
}
