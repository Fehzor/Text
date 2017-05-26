/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Game.GamePieces;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Game.GameBoard;
import text.Game.GameIntellect;
import text.Game.GameMove;
import text.Game.GamePiece;
import text.Utility.ImageLoader;

/**
 *
 * @author FF6EB4
 */
public class BlockPiece extends GamePiece{
    
    public BlockPiece(){
        super('X');
        this.dead = false;
        this.image = ImageLoader.loadImage("game_box.txt").clone();
        this.colorImageRandom();
    }
    
    public int[][] makeMove(GameBoard board){
        return board.board;
    }
    
    public BlockPiece clone(){
        BlockPiece ret = new BlockPiece();
        
        ret.image = this.image.clone();
        
        return ret;
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = new ArrayList<>();
        ret.add(Option.cancel(this));
        
        ret.add(Option.display(this, "Info", "Blocks pieces"));
        
        return ret;
    }
    
    public String toString(){
        return "Block";
    }
}
