/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Worlds;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import text.Actors.Player;
import text.Environment.Environment;
import text.Frame.TextDisplay;
import text.WorldFrame.Room;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class CircleWorld extends World implements Serializable {
    int[][] grid;
    Point pos;
    int r, circ;

    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    
    public CircleWorld(int r, int circ, Environment E){
        super();
        
        grid = new int[r][circ];
        pos = new Point(0,0);
        
        //Radius and circumfrence
        this.r = r;
        this.circ = circ;
        
        int a = 0;
        for(int i = 0; i<r; ++i){
            for(int j = 0; j<circ; ++j){
                grid[i][j] = a;
                a+=1;
            }
        }
        
        this.E = E;
    }
    
    public int addRoom(Room R){
        return super.addRoom(R);
    }
    
    public boolean needsRooms(){
        return roomCount() < r*circ;
    }


    public void playerOutOfBounds(int dir){
        int room = this.currentRoomNum;
        int row = room / circ;
        int col = room % circ;
        
        this.pos = new Point(col,row);
        
        if(dir == NORTH){
            if(pos.y == 0){
                pos.y = pos.y;
            } else {
                pos.y -=1;
            }
        }

        if(dir == SOUTH){
            if(pos.y == r-1){
                pos.x = (pos.x + (circ/2))%circ;
            } else {
                pos.y +=1;
            }
        }

        if(dir == EAST){
            
            if(pos.x == circ-1){
                pos.x = 0;
            } else {
                pos.x +=1;
            }
        }

        if(dir == WEST){
            if(pos.x == 0){
                pos.x = circ-1;
            } else {
                pos.x -=1;
            }
        }
        
        int roomNum = grid[pos.y][pos.x];

        this.switchRoom(roomNum);
        //this.startCurrentRoom();
        
        if(dir == SOUTH){
            if(pos.y == r-1){
               Player.The.y = -10;
            }
        }
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
        Point up = new Point(pos.x,pos.y-1);
        Point down = new Point(pos.x,pos.y+1);
        
        if(pos.y-1 < 0){
            up = new Point(pos.x,pos.y);
        }
        
        if(pos.y+1 > r){
            down = new Point(pos.x,pos.y);
        }

        ret.add(grid[left.x][left.y]);
        ret.add(grid[right.x][right.y]);
        ret.add(grid[up.x][up.y]);
        ret.add(grid[down.x][down.y]);

        return ret;
    }

    private int modSize(int i){
        if(i < 0) return circ - 1;

        return i % circ;
    }
    
    public void displayMap(){
        String[] ret = new String[this.r+2];
        
        int pRoomNum= Player.The.current.id;
        
        ret[0] = "" + name;
        
        for(int i = 0; i < r; ++i){
            for(int j = 0; j < circ; ++j){
                if(j == 0){
                    ret[i+1] = "";
                }
                
                int nextRoom = i*r+ j;
                
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
        
        ret[r+1] = "This place feels round...";
        
        this.display(ret);
    }
}