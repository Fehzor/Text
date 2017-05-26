/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Worlds;

import text.WorldFrame.World;
import text.Environment.*;
import java.awt.Point;
import java.io.Serializable;
import text.Actors.*;
import text.Frame.*;
import java.util.*;
import text.WorldFrame.Room;
/**
 *
 * @author FF6EB4
 */
public class GridWorld extends World implements Serializable {
    int[][] grid;
    Point pos;
    int size;
    
    public static Random oRan = new Random();
        
    public GridWorld(int size, Environment E){
        super();
        
        this.size = size;
        grid = new int[size][size];
        pos = new Point(0,0);
        
        for(int i = 0; i<size*size; ++i){
            grid[i%size][i/size] = i;
        }
        
        this.E = E;
    }
    
    public GridWorld(int size){
        super();
        
        this.size = size;
        grid = new int[size][size];
        pos = new Point(0,0);
        
        E = new Environment(Environment.TYPE_NORMAL);
        
        /*
        for(int i = 0; i<size*size; ++i){
            Room temp = new Room(i,this);
            decorateRoom(temp);
            addRoom(temp);
            
            grid[i%size][i/size] = i;
        }
        */
        //this.addPuzzle(new WumpusPuzzle(this.size*this.size));
    }
    
    public int addRoom(Room R){
        return super.addRoom(R);
    }
    
    public boolean needsRooms(){
        return roomCount() < size*size;
    }
    

    public void playerOutOfBounds(int dir){
        int room = this.currentRoomNum;
        int row = room / size;
        int col = room % size;
        this.pos = new Point(col,row);
        
        if(dir == Player.NORTH){
            if(pos.y == 0){
                pos.y = size-1;
            } else {
                pos.y -=1;
            }
        }

        if(dir == Player.SOUTH){
            if(pos.y == size-1){
                pos.y = 0;
            } else {
                pos.y +=1;
            }
        }

        if(dir == Player.EAST){
            if(pos.x == size-1){
                pos.x = 0;
            } else {
                pos.x +=1;
            }
        }

        if(dir == Player.WEST){
            if(pos.x == 0){
                pos.x = size-1;
            } else {
                pos.x -=1;
            }
        }

        int roomNum = grid[pos.x][pos.y];

        this.switchRoom(roomNum);
        //this.startCurrentRoom();
    }

    public boolean adjacent(int a, int b){
        ArrayList<Integer> adjac = adjacentList(a);
        //System.out.println(adjac);
        return adjac.contains(b);
    }

    public ArrayList<Integer> adjacentList(int a){
        ArrayList<Integer> ret = new ArrayList<>();

        Point left = new Point(modSize(pos.x-1),pos.y);
        Point right = new Point(modSize(pos.x+1),pos.y);
        Point up = new Point(pos.x,modSize(pos.y-1));
        Point down = new Point(pos.x,modSize(pos.y+1));

        ret.add(grid[left.x][left.y]);
        ret.add(grid[right.x][right.y]);
        ret.add(grid[up.x][up.y]);
        ret.add(grid[down.x][down.y]);

        return ret;
    }

    private int modSize(int i){
        if(i < 0) return size - 1;

        return i % size;
    }
    
    public void displayMap(){
        String[] ret = new String[this.size+1];
        
        ret[0] = ""+name;
        
        int pRoomNum= Player.The.current.id;
        
        for(int i = 0; i < ret.length-1; ++i){
            for(int j = 0; j < ret.length-1; ++j){
                if(j == 0){
                    ret[i+1] = "";
                }
                
                int nextRoom = i*size + j;
                
                if(nextRoom == pRoomNum){
                  ret[i+1]+= "[P]";
                } else if(checkStory(nextRoom)){
                    ret[i+1] += "[S]";
                } else if(checkDoor(nextRoom)){
                    ret[i+1] += "[W]";
                } else {
                    ret[i+1] += "[-]";
                }
            }
        }
        
        this.display(ret);
    }
}
