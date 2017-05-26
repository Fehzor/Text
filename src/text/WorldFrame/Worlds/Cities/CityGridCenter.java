/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Worlds.Cities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import text.Actors.Player;
import text.Environment.Environment;
import text.Frame.TextDisplay;
import text.WorldFrame.Room;
import text.WorldFrame.World;


/**
 *
 * @author FF6EB4
 */
public class CityGridCenter extends World{
    public int[][] grid;
    
    private int size, len;

    private Point location = new Point(0,0);
    
    private boolean flipped = false;
    private int hallway = -1;
    private boolean hori = true;
    
    private int[][][][] edgeGraphVert,edgeGraphHori;
    
    private int totalRooms;
    private int gridRooms;
    private int roomCount = 0;
    
    private HashMap<Integer,Point> roomToPoints;
    
    //Did the player just.... leave a center? Go up to a center? Go down to a center?
    private boolean leftCenter = false;
    private boolean upToCenter = false;
    private boolean downToCenter = false;
    
    public CityGridCenter(int size, int len){
        this.grid = new int[size][size];
        
        roomToPoints = new HashMap<>();
        
        gridRooms = size*size;
        this.size = size;
        this.len = len;
        
        this.E = new Environment(Environment.TYPE_NONE);
        
        edgeGraphVert = new int[size][size][len][2];
        edgeGraphHori = new int[size][size][len][2];
        //Node over
        //Node down
        //Where
        //Top/bottom
        
        for(int i = 0; i<grid.length; ++i){
            for(int j = 0; j<grid.length; ++j){

                grid[i][j] = totalRooms;
                roomToPoints.put(totalRooms,new Point(i,j));
                totalRooms++;

            }
        }
        
        for(int i = 0; i<edgeGraphVert.length; ++i){
            for(int j = 0; j<edgeGraphVert[0].length; ++j){
                for(int k = 0; k < len; ++k){
                    for(int l = 0; l < 2; ++l){
                        edgeGraphVert[i][j][k][l] = totalRooms;
                        roomToPoints.put(totalRooms,new Point(i,j));
                        totalRooms++;
                    }
                }
            }
        }
        
        for(int i = 0; i<edgeGraphHori.length; ++i){
            for(int j = 0; j<edgeGraphHori[0].length; ++j){
                for(int k = 0; k < len; ++k){
                    for(int l = 0; l < 2; ++l){
                        edgeGraphHori[i][j][k][l] = totalRooms;
                        roomToPoints.put(totalRooms,new Point(i,j));
                        totalRooms++;
                    }
                }
            }
        }
    }
    
    public void playerOutOfBounds(int dir){
        //Oh boy..
        //If in a hallway and going south..
        
        //DId it just leave the middle area???
        if(currentRoomNum < grid.length * grid.length){
            leftCenter = true;
        } else {
             leftCenter = false;
        }
        
        downToCenter = false;
        upToCenter = false;
        
        if(flipped){
            dir = flip(dir);
        }
        
        if((dir == Player.SOUTH || dir == Player.NORTH) && hallway > -1){
            flipped = !flipped;
        }
        
        setRoom(dir);
        
        if(location.x < 0){
            location.x = size-1;
        }
        if(location.y < 0){
            location.y = size-1;
        }
        if(location.x >= size){
            location.x = 0;
        }
        if(location.y >= size){
            location.y = 0;
        }
        
        try{
            switchRoom(getRoomNum());
        } catch (Exception E){}
    }
    
    public void positionPlayer(int dir){
        super.positionPlayer(dir);
        if((dir == Player.SOUTH || dir == Player.NORTH)){
            if(leftCenter == false){
                Player.The.y = TextDisplay.SCREEN_SIZE_Y - Player.The.y;
                Player.The.x = TextDisplay.SCREEN_SIZE_X - Player.The.x;
            } else {
                double percent = Player.The.x / TextDisplay.SCREEN_SIZE_X;
                Player.The.y = (int) (TextDisplay.SCREEN_SIZE_Y * percent);
                
                if((dir == Player.SOUTH && flipped)|| (dir==Player.NORTH && !flipped)){
                    Player.The.x = 5;
                } else {
                    Player.The.x = TextDisplay.SCREEN_SIZE_X - 5;
                }
                //Player.The.x = 10;
            }
        }
    }
    
    public void setRoom(int dir){
       if(hallway > -1){
           if(dir == Player.EAST){
               hallway++;
               if(hallway==len){
                   hallway = -1;
                   
                   if(hori){
                       location.x+=1;
                   } else {
                       location.y+=1;
                   }
               }
               return;
           }
           if(dir == Player.WEST){
               hallway--;
               return;
           }
       }
       
       if(hallway == -1){
           if(dir == Player.NORTH){
               location.y--;
               hallway = len-1;
               hori = false;
               
           }
           if(dir == Player.SOUTH){
               //location.y++;
               hallway = 0;
               hori = false;
           }
           if(dir == Player.EAST){
               //location.x++;
               hallway = 0;
               hori = true;
               
           }
           if(dir == Player.WEST){
               location.x--;
               hallway = len-1;
               hori = true;
           }
       }
    }
    
    private int flip(int direction){
        if(direction == Player.WEST){
            return Player.EAST;
        }
        if(direction == Player.EAST){
            return Player.WEST;
        }
        if(direction == Player.SOUTH){
            return Player.NORTH;
        }
        if(direction == Player.NORTH){
            return Player.SOUTH;
        }
        return -1;
    }
    
    public int getRoomNum(){
        if(hallway == -1){
            return getGridLocation();
        } else {
            if(hori){
                return getHallwayLocationHori();
            } else {
                return getHallwayLocationVert();
            }
        }
    }
    
    private int getGridLocation(){
        int ret = 0;
        
        ret = grid[location.x][location.y];
        
        return ret;
    }
    
    private int getHallwayLocationVert(){
        int ret = 0;
        if(flipped) ret = 1;
        
        ret = edgeGraphVert[location.x][location.y][hallway][ret];
        
        return ret;
    }
    
    private int getHallwayLocationHori(){
        int ret = 0;
        if(flipped) ret = 1;
        
        ret = edgeGraphHori[location.x][location.y][hallway][ret];
        
        return ret;
    }
    
    public boolean needsRooms(){
        return roomCount < totalRooms;
    }
    
    public int addRoom(Room R){
        int ret = super.addRoom(R);
        
        roomCount++;
        
        return ret;
    }
    
    public void displayMap(){
        String[] ret = new String[this.size+this.size];
        ret[0] = "" + name;
        
        for(int i = 0; i < this.size+this.size-1; ++i){
            ret[i+1] = "";
            
            for(int j = 0; j<this.size+this.size-1; ++j){
                if(i%2==0){
                    if(j%2==0){
                        if(location.y == i/2 && location.x == j/2 && hallway == -1){
                            ret[i+1] += "P";
                        } else if(checkDoor(grid[j/2][i/2])){
                            ret[i+1] += "W";
                        }  else {
                            ret[i+1]+="#";
                        }
                    } else {
                        if(hori && location.y == i/2 && location.x == j/2 && hallway != -1){
                            ret[i+1] += "P";
                        } else {
                            ret[i+1]+="=";
                        }
                    }
                } else {
                    if(!hori && location.y == i/2 && location.x == j/2 && hallway != -1){
                            if(j%2==0){
                                ret[i+1]+="P";
                            } else {
                                ret[i+1]+=" ";
                            }
                        } else {
                            if(j%2==0){
                                ret[i+1]+="|";
                            } else {
                                ret[i+1]+=" ";
                            }
                        }
                }
            }
        }
        
        this.display(ret);
    }

    
    //Is A next to B?
    public boolean adjacent(int a, int b){
        Point A = roomToPoints.get(a);
        Point B = roomToPoints.get(b);
        
        if(A.x + 1 < B.x)return false;
        if(A.x - 1 > B.x)return false;
        if(A.y + 1 < B.y)return false;
        if(A.y - 1 > B.y)return false;
        
        return true;
    }
    
    public ArrayList<Integer>adjacentList(int square){
        ArrayList<Integer> ret = new ArrayList<>();
        
        Point P = roomToPoints.get(square);
        
        ret.addAll(roomsAtPoint(P));
        ret.addAll(roomsAtPoint(new Point (P.x,P.y+1)));
        ret.addAll(roomsAtPoint(new Point (P.x,P.y-1)));
        ret.addAll(roomsAtPoint(new Point (P.x+1,P.y)));
        ret.addAll(roomsAtPoint(new Point (P.x-1,P.y)));
        
        return ret;
    }
    
    private ArrayList<Integer>roomsAtPoint(Point P){
        ArrayList<Integer> ret = new ArrayList<>();
        
        if(P.x < 0)P.x = size-1;
        if(P.x > size-1) P.x = 0;
        if(P.y < 0)P.y = size-1;
        if(P.y > size-1) P.y = 0;
        
        ret.add(grid[P.x][P.y]);
        for(int i =0; i<len; ++i){
            ret.add(edgeGraphHori[P.x][P.y][i][0]);
            ret.add(edgeGraphHori[P.x][P.y][i][1]);
            ret.add(edgeGraphVert[P.x][P.y][i][0]);
            ret.add(edgeGraphVert[P.x][P.y][i][1]);
        }
        
        
        return ret;
    }
    
    public int getLinkRoom(){
        int getX = oRan.nextInt(grid.length);
        int getY = oRan.nextInt(grid.length);
        
        int roomNum = grid[getX][getY];
        
        return roomNum;
    }
}
