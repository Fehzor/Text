/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Game;

import java.util.ArrayList;
import text.Actors.Interactable;
import text.Game.GameMoves.CheckAndMove;
import text.Game.GameMoves.MoveAndTake;
//import text.Game.GameMoves.*;
import text.Utility.ImageLoader;
import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class GameMove extends Interactable{
    public static ArrayList<GameMove> moveList = loadMoveList();
    
    public GameMove(){
        //TODO MAKE AN IMAGE FOR THIS THING
        this.image = ImageLoader.loadImage("game_piece.txt");
    }
    
    public int[][] makeMove(GamePiece actUpon, GameBoard board){
        return board.board;
    }
    
    public boolean act(){
        return false;
    }
    
    public String toString(){
        return "Move";
    }
    
    public static GameMove getRandomMove(){
        return moveList.get(oRan.nextInt(moveList.size()));
    }
    
    public static ArrayList<GameMove> loadMoveList(){
        ArrayList<GameMove> ret = new ArrayList<>();
        
        CheckAndMove R = new CheckAndMove(0,1,"→");
        CheckAndMove D = new CheckAndMove(1,0,"↓");
        CheckAndMove U = new CheckAndMove(-1,0,"↑");
        CheckAndMove L = new CheckAndMove(0,-1,"←");
        
        CheckAndMove DR = new CheckAndMove(1,1,"↘");
        CheckAndMove UR = new CheckAndMove(-1,1,"↗");
        
        CheckAndMove DL = new CheckAndMove(1,-1,"↙");
        CheckAndMove UL = new CheckAndMove(-1,-1,"↖");
        
        CheckAndMove RR = new CheckAndMove(0,2,"→→");
        CheckAndMove RRU = new CheckAndMove(1,2,"→↘");
        CheckAndMove RRD = new CheckAndMove(-1,2,"→↗");
        
        MoveAndTake DRT = new MoveAndTake(1,1,"↘X");
        MoveAndTake URT = new MoveAndTake(-1,1,"↗X");
        MoveAndTake DLT = new MoveAndTake(1,-1,"↙X");
        MoveAndTake ULT = new MoveAndTake(-1,-1,"↖X");
        
        MoveAndTake RT = new MoveAndTake(0,1,"→X");
        MoveAndTake DT = new MoveAndTake(1,0,"↓X");
        MoveAndTake UT = new MoveAndTake(-1,0,"↑X");
        MoveAndTake LT = new MoveAndTake(0,-1,"←X");
        
        ret.add(R);
        ret.add(D);
        ret.add(U);
        ret.add(L);
        
        ret.add(DR);
        ret.add(UR);
        ret.add(DL);
        ret.add(UL);
        
        ret.add(RR);
        ret.add(RRU);
        ret.add(RRD);
        
        ret.add(DRT);
        ret.add(URT);
        ret.add(DLT);
        ret.add(ULT);
        
        ret.add(RT);
        ret.add(DT);
        ret.add(UT);
        ret.add(LT);
        
        return ret;
    }
}
