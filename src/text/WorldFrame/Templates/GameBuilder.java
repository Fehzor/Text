/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Templates;

import java.awt.Color;
import java.awt.Point;
import text.Environment.*;
import text.Frame.TextDisplay;
import text.Game.GameBoard;
import text.Game.GamePiece;
import text.Game.GamePieces.BlockPiece;
import text.Images.TextImageBasic;
import text.Utility.ColorTuple;
import text.Utility.SuperRandom;
import text.Utility.Tiles.TileBuilder;
import text.WorldFrame.Room;
import static text.WorldFrame.Room.*;
import text.WorldFrame.World;
import text.WorldFrame.Worlds.*;
import text.WorldFrame.Worlds.Cities.*;



/**
 *
 * @author FF6EB4
 */
public class GameBuilder extends WorldBuilder{
        public static World generate(WorldTemplate WT){
        Environment E = WT.E;
        World ret = new GridWorld((int) Math.sqrt((double)WT.rooms),E);
        
        if(WT.worldType == WildTemplate.LOST_WORLD){
            ret = new LostWorld(WT.rooms,E);
        }
        
        if(WT.worldType == WildTemplate.GRID_CITY){
           ret = new CityGridCenter(WT.size,(int)Math.ceil(((double)WT.size)/3.0));
        }
        
        if(WT.worldType == WildTemplate.ROUND_WORLD){
            ret = new CircleWorld((int) Math.sqrt((double)WT.rooms),(int) Math.sqrt((double)WT.rooms),E);
        }
        
        if(WT.worldType == WorldTemplate.CITY_HALLWAY){
            ret = new CityHallway((int) Math.sqrt((double)WT.rooms) + 3);
        }
        
        if(WT.worldType == WorldTemplate.GAME_ROOM){
            ret = new GameWorld();
        }
        
        ret.E = WT.E;
        ret.source = WT;
        
        //TextImageBasic TIB = TileBuilder.buildGameBoard(7,5);
        
        
        
        if(WT.worldType != WorldTemplate.GAME_ROOM){
            buildNonGameWorld(ret,WT);
        } else {
            Room bored = new Room(0, ret);
            addBackGround(bored);
            addGameBoard(bored, WT);
            
            Room  pool = new Room(1, ret);
            addBackGround(pool);
            for(int i = 0; i < oRan.nextInt(5) + 3; ++i){
                    GamePiece GP = WT.pieceList.get(oRan.nextInt(WT.pieceList.size()));
                    addGamePiece(pool,GP);
            }
            
            Room prize = new Room(2, ret);
            
            bored.block(Room.SOUTH,4);
            bored.block(Room.NORTH,4);
            bored.block(Room.WEST,4);
            
            pool.block(Room.SOUTH,4);
            pool.block(Room.NORTH,4);
            pool.block(Room.EAST,4);
            
            prize.block(Room.SOUTH,4);
            prize.block(Room.WEST,4);
            prize.block(Room.EAST,4);
            
            ret.addRoom(bored);
            ret.addRoom(pool);
            ret.addRoom(prize);
        }
        
        
        
        return ret;
    }
        
   public static void buildNonGameWorld(World ret, WorldTemplate WT){
       while(ret.needsRooms()){
            Room R = new Room(ret.roomCount(),ret);
            addBackGround(R);
            
            
            if(oRan.nextInt(100) < 20){
                addGameBoard(R,WT);
            } else {
                for(int i = 0; i < oRan.nextInt(5) + 3; ++i){
                    GamePiece GP = WT.pieceList.get(oRan.nextInt(WT.pieceList.size()));
                    addGamePiece(R,GP);
                }
            }
            ret.addRoom(R);
        }
   }
    
    public static void addGamePiece(Room R, GamePiece GP){
        GP = GP.clone();
        GP.x = SuperRandom.nextIntGaussian(TextDisplay.SCREEN_SIZE_X);
        GP.y = SuperRandom.nextIntGaussian(TextDisplay.SCREEN_SIZE_Y);
        GP.depth = GP.y;
        R.addActor(GP);
    }
    
    public static void addGameBoard(Room R, WorldTemplate WT){
            GameBoard GB;
            if(WT.gameboard != null){
                GB = new GameBoard(WT.gameboard,R);
            } else {
                GB = new GameBoard(R);
            }
            GB.x = 37;
            GB.y = 20;
            GB.depth = 20;
            R.addActor(GB);
    }
    
    public static void addBackGround(Room R){
            Color beige = new Color(255, 204, 153);
            ColorTuple A = new ColorTuple(beige,Color.WHITE,'|');
            ColorTuple B = new ColorTuple(Color.WHITE,beige,'_');
            TextImageBasic checker = TileBuilder.checkerFloorBackGround(A, B, 4, 9);
            R.BG.addTextImageBasicBack(new Point(0,0), checker);
    }
}
