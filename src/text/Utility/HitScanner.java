/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Utility;

import text.Actors.Actor;
import java.util.*;
import text.Frame.TextDisplay;
import java.awt.*;
import java.io.Serializable;

/**
 *
 * @author FF6EB4
 */
public class HitScanner implements Serializable{
    
    public static int ROWS = TextDisplay.SCREEN_SIZE_X;
    public static int COLS = TextDisplay.SCREEN_SIZE_Y;
    public static int DEFAULT_WIDTH = 10; //Distance away from an object that hits, E/W
    public static int DEFAULT_HEIGHT = 6; //Distance away from an object htat hits, N/S
    public static int HEIGHT = 5;
    
    
    
    //This is a weird way to represent this, but one that I believe works well.
    //Regardless, the string is private, so no one needs to think too hard about it...
    private String[][][] area;
    
    private HashMap<Integer,Actor> regList;
    private HashMap<Actor,Integer> idList;
    private int regNumber = 1;
    
    public HitScanner(){
        area = new String[ROWS][COLS][HEIGHT];
        regList = new HashMap<>();
        idList = new HashMap<>();
    }
    
    //Sign up to get hit!
    public void registerActor(Actor A){
        regList.put(regNumber,A);
        idList.put(A, regNumber);
        ++regNumber;
    }
    
    public void unregisterActor(Actor A){
        try{
            int id = idList.get(A);
            regList.remove(id);
            idList.remove(A);
        } catch (NullPointerException NPE){
            System.out.println("Null Pointer- Unregister Actor");
        }
    }
    
    /**
     * Adds an actor by location. Returns if the location was out of bounds.
     * 
     * @param A
     * @param x
     * @param y
     * @param height
     * @return True if the object is in the room. False otherwise.
     */
    public boolean registerLocation(Actor A, int x, int y, int width, int height, int z){
        int id = idList.get(A);
        
        try{
            area[x][y][z] += ""+(char)id;
        } catch (Exception e){
            return false;
        }
        
        int xStart = x - width;
        int xEnd = x + width;
        int yStart = y - height;
        int yEnd = y + height;
        
        for(int i = xStart; i< xEnd; ++i){
            for(int j = yStart; j<yEnd; ++j){
                try{
                    area[i][j][z] += ""+((char)id);
                } catch (Exception e){
                }
            }
        }
        
        return true;
    }
    
    public boolean registerLocation(Actor A, int x, int y, int z){
        int id = idList.get(A);
        
        int width = DEFAULT_WIDTH;
        int height = DEFAULT_HEIGHT;
        
        return registerLocation(A,x,y,width,height,z);
    }
    

    /**
     * Returns a list of all Actors that get hit at this location.
     * 
     * @param row
     * @param col
     * @param height
     * 
     * Where it is
     * 
     * @return a list of all Actors that get hit at the location
     */
    public ArrayList<Actor> checkHit(int x, int y, int z){
        ArrayList<Actor> aList = new ArrayList<>();
        
        try{
            String key = area[x][y][z];
            
            for(int i = 0; i<key.length(); ++i){
                if(!aList.contains(regList.get((int)key.charAt(i)))){
                    aList.add(regList.get((int)key.charAt(i)));
                }
            }
        } catch (Exception e){
            System.out.println("sup");
            return null;
        }
        return aList;
    }
    
    /**
     * Checks all actors in a line. Used for bullets. Hitscan.
     * 
     * @param col
     * @param height
     * 
     * Where it is
     * 
     * @return a list of all Actors that get hit at the location
     */
    public ArrayList<Actor> checkrow(int y, int z){
        ArrayList<Actor> aList = new ArrayList<>();
        
        for(int i = 0; i< ROWS; ++i){
            ArrayList<Actor> add = checkHit(i,y,z);
            for(Actor A : add){
                if(!aList.contains(A)){
                    aList.add(A);
                }
            }
        }
        
        return aList;
    }
    
    public ArrayList<Actor> checkrow(boolean right,int x,int y, int z){
        ArrayList<Actor> aList = new ArrayList<>();
        
        if(right){
            for(int i = x; i< ROWS; ++i){
                ArrayList<Actor> add = checkHit(i,y,z);
                for(Actor A : add){
                    if(!aList.contains(A)){
                        aList.add(A);
                    }
                }
            }
        } else {
            for(int i = 0; i< x; ++i){
                ArrayList<Actor> add = checkHit(i,y,z);
                for(Actor A : add){
                    if(!aList.contains(A)){
                        aList.add(A);
                    }
                }
            }
        }
        return aList;
    }
    
    //Clears the list of who is where.
    public void clear(){
        for(int a = 0; a<ROWS; ++a){
            for(int b = 0; b<COLS; ++b){
                for(int c = 0; c<HEIGHT; ++c){
                    area[a][b][c] = "";
                }
            }
        }
    }
}
