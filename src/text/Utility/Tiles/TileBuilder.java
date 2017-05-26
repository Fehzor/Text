/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Utility.Tiles;

import text.Utility.ImageLoader;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import text.Environment.Environment;
import text.Frame.TextDisplay;
import text.Images.*;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class TileBuilder {
    private static TileBuilder init = new TileBuilder();
    private TileBuilder(){
        loadTiles();
    }
    
    public static Random oRan = new Random();
    
    private static HashMap<Integer,Tile> tiles;
    
    private static ArrayList<Integer> bricks; 
    private static ArrayList<Integer> windows;
    private static ArrayList<Integer> water;
    private static int game;
    private static int sidewalk;
    
    public static TextImageBasic buildSolid(){
        ArrayList<ArrayList<ColorTuple>> ctList = new ArrayList<>();
        for(int i = 0; i<21;++i){
            ctList.add(new ArrayList<>());
            for(int j = 0; j < TextDisplay.SCREEN_SIZE_X; ++j ){
                ctList.get(i).add(new ColorTuple(Color.LIGHT_GRAY,Color.CYAN,'#'));
            }
        }
        return new TextImageBasic(ctList);
    }
    
    public static TextImageBasic checkerFloorBackGround(ColorTuple A, ColorTuple B, int h, int w){
        TextImageBasic TIB = new TextImageBasic();
        ArrayList<ArrayList<ColorTuple>> img = new ArrayList<>();
        
        
        boolean red = true;
        
        for(int i = 0; i < TextDisplay.SCREEN_SIZE_Y; ++i){
            img.add(new ArrayList<>());
            if(i%h == 0){
                red = !red;
            }
            for(int j = 0; j < TextDisplay.SCREEN_SIZE_X; ++j){
                
                if(red){
                    img.get(i).add(A.clone());
                } else {
                    img.get(i).add(B.clone());
                }
                if(j%w == 0){
                    red = !red;   
                }
            }
        }
        
        TIB.image = img;
        
        return TIB;
    }
    
    public static TextImageBasic buildSidewalk(){
        Tile A = tiles.get(sidewalk);
        
        A.TILE_HEIGHT = 10;
        
        TextImageBasic ret = new TextImageBasic();
        
            for(int j = 0; j<8; ++j){
                Tile t = A;
                t.addTo(0, ret);
            }
        return ret;
    }
    
    public static TextImageBasic addBoarderSquare(TextImageBasic TIB, ColorTuple CT){
        ArrayList<ArrayList<ColorTuple>> img = TIB.image;
        
        img.add(0,new ArrayList<>());
        img.add(new ArrayList<>());
        
        for(int i = 1; i < img.size()-1; ++i){
            img.get(i).add(0,CT.clone());
            img.get(i).add(CT.clone());
        }
        
        for(int i = 0; i < img.get(1).size(); ++i){
            img.get(0).add(CT.clone());
            img.get(img.size()-1).add(CT.clone());
        }
        
        return TIB;
    }
    
    public static TextImageBasic buildGameBoard(int width, int height){
        Tile A = tiles.get(game);
        Tile B = A.clone();
        Tile C = A.clone();
        Tile D = A.clone();
        A = A.clone();
        
        ColorTuple background = new ColorTuple(Color.RED,Color.BLACK,' ');
        ColorTuple foreground = new ColorTuple(Color.BLACK,Color.RED,' ');
        ImageBuilder.colorMergeImage(A.image, background);
        ImageBuilder.colorMergeImage(B.image, foreground);
        ImageBuilder.colorMergeImage(A.image, background);
        ImageBuilder.colorMergeImage(B.image, foreground);
        ImageBuilder.colorMergeImage(A.image, background);
        ImageBuilder.colorMergeImage(B.image, foreground);
        
        A.TILE_HEIGHT = 4;
        B.TILE_HEIGHT = 4;
        
        ColorTuple white = new ColorTuple(Color.BLUE, Color.BLUE,' ');
        ColorTuple black = new ColorTuple(Color.BLUE, Color.BLUE,' ');
        ImageBuilder.colorMergeImage(C.image, white);
        ImageBuilder.colorMergeImage(D.image, black);
        ImageBuilder.letterCover(C.image,'S');
        ImageBuilder.letterCover(D.image,'F');
        
        C.TILE_HEIGHT = 4;
        D.TILE_HEIGHT = 4;
        
        TextImageBasic ret = new TextImageBasic();
        boolean red = false;
        
        for(int i = 0; i<height; ++i){
            if(width % 2 == 0){
                red = !red;
            }
            for(int j = 0; j<width; ++j){
                red = !red;
                if(j==0){
                    Tile t = C;
                    t.addTo(i, ret);
                } else if(j==width-1){
                    Tile t = D;
                    t.addTo(i, ret);
                } else if(red){
                    Tile t = A;
                    t.addTo(i, ret);
                } else {
                    Tile t = B;
                    t.addTo(i, ret);
                }
                
            }
        }
        
        addBoarderSquare(ret,new ColorTuple(Color.BLACK, Color.WHITE,'#'));
        
        return ret;
    }
    
    /**
     * Creates an image of water.
     * @param horizontal is the water horizontal or vertical?
     * @param depth how deep, from 0 to 255, is the water?
     * @return 
     */
    public static TextImageBasic buildWater(boolean horizontal, int depth){
        int[][] tileSet = new int[1][1];
        
        if(horizontal){
            tileSet = new int[4][14];
            
        } else { //If not horizontal, it covers the whole screen.
            tileSet = new int[8][14];
        }
        
        int id = water.get(0);
        
        for(int i = 0; i < tileSet.length; ++i){
            for(int j = 0; j < tileSet[0].length;++j){
                tileSet[i][j] = id;
            }
        }
        
        TextImageBasic ret = build(tileSet);
        
        ImageBuilder.makeTransparent(ret, depth);
        
        return ret;
    }
    
    public static TextImageBasic buildBricked(){
        int[][] tileSet = new int[3][14];
        
        changeBricks(tileSet);
        
        addWindows(tileSet);
        
        TextImageBasic ret = build(tileSet);
        
        
        return ret;
    }
    
    public static int[][] changeBricks(int[][] tileSet){
        int tile = oRan.nextInt(bricks.size());
        tile = bricks.get(tile);
        
        for(int i = 0; i < tileSet.length; ++i){
            for(int j = 0; j < tileSet[0].length;++j){
                tileSet[i][j] = tile;
            }
        }
        
        return tileSet;
    }
    
    public static TextImageBasic build(int[][] tileset){
        TextImageBasic ret = new TextImageBasic();
        
        for(int i = 0; i<tileset.length; ++i){
            for(int j = 0; j<tileset[0].length; ++j){
                int tilenum = tileset[i][j];
                Tile t = tiles.get(tilenum);
                
                t.addTo(i, ret);
            }
        }
        
        return ret;
    }
    
    public static void addWindows(int[][] tileset){
        int id = windows.get(oRan.nextInt(windows.size()));
        Tile window = tiles.get(id);
        
        int len = tileset[0].length;
        
        for(int i = 0; i<tileset[0].length; ++i){
            if(oRan.nextInt(100) > 50){
                tileset[1][i] = id;
            }
        }
    }
    
    private static void loadTiles(){
        tiles = new HashMap<>();
        
        bricks = new ArrayList<>();
        windows = new ArrayList<>();
        water = new ArrayList<>();
        
        try{
            Scanner oScan = new Scanner(new File("tile_list"));
            
            int id = 0;
            while(oScan.hasNextLine()){
                //
                // FORMAT IS AS SUCH
                //
                // [filename] [base pallet] [collection]
                //
                String name = oScan.next();
                String pallet = oScan.next();
                String type = oScan.next();
                
                ImageLoader.switchMap(pallet);
                TextImageBasic tib = ImageLoader.loadImage(name);
                
                Tile add = new Tile(tib);
                
                tiles.put(id,add);
                
                switch (type){
                    case "brick":
                        bricks.add(id);
                        break;
                    case "window":
                        windows.add(id);
                        break;
                    case "water":
                        water.add(id);
                        //System.out.println("WATER");
                        break;
                    case "game":
                        game = id;
                        break;
                    case "sidewalk":
                        sidewalk = id;
                        break;
                }
                
                id++;
            }
        } catch(FileNotFoundException E){
            System.err.println("Something went wrong loading tile_list!");
        }
    }
}


