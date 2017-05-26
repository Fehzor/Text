package text.WorldFrame.Templates;


import java.awt.Point;
import text.Actors.Instances.CityPart;
import text.Actors.Instances.Door;
import text.Frame.TextDisplay;
import text.Images.TextImageBasic;
import text.Utility.ImageBuilder;
import text.Utility.Tiles.TileBuilder;
import text.WorldFrame.Room;
import static text.WorldFrame.Templates.CityBuilder.addAreas;
import static text.WorldFrame.Templates.CityBuilder.addCityPartsA;
//import static text.WorldFrame.Templates.CityBuilder.addCorners;
import static text.WorldFrame.Templates.CityBuilder.createWorld;
import static text.WorldFrame.Templates.CityBuilder.oRan;
import text.WorldFrame.Templates.WorldBuilder;
import static text.WorldFrame.Templates.WorldTemplate.GRID_CITY;
import text.WorldFrame.World;
import text.WorldFrame.Worlds.Cities.CityGridCenter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FF6EB4
 */
public class IndoorBuilder extends CityBuilder{
    private static boolean first = true;
    
    public static World generate(WorldTemplate CT){
        if(CT.worldType !=GRID_CITY){
            World ret = createWorld(CT);
            addAreas(CT,ret);
            return ret;
        } else {
            World ret = generateGridCity(CT);
            return ret;
        }
    }
    
    public static World generateGridCity(WorldTemplate CT){
        CityGridCenter ret = new CityGridCenter(CT.size,CT.size/2);
        //System.out.println(CT.size);
        
        int i = 0;
        for(i = 0; i<CT.size*CT.size; ++i){
            Room R = new Room(i,ret);
            ret.addRoom(R);
        }
        
        //All rooms are hallway like in cities.
        addAreas(CT,ret);
        
        return ret;
    }
    
    public static void addAreas(WorldTemplate CT, World ret){
        int i = 0;
        while(ret.needsRooms()){
            Room R = new Room(i++,ret);
            TextImageBasic wall = TileBuilder.buildBricked();
            
            ImageBuilder.colorMergeImage(wall, CT.background);
            
            R.BG.addTextImageBasic(new Point(0,0), wall);
            
            R.block(Room.NORTH,24);
            
            addCityPartsA(CT,R);
            
            addTables(CT,R);
            if(first && CT.toSet != null){
                first = false;
                addDoor(CT,R);
            }
            
            addTables(CT,R);
            
            ret.addRoom(R);
            
        }
        
        first = true;
    }
    
    public static void addTables(WorldTemplate CT, Room R){
        CityPart CP = CT.cityPartsB.get(oRan.nextInt(CT.cityPartsB.size())).clone();
        
        CP.y = 55;
        CP.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        CP.depth = CP.y;
        
        CityPart CP2 = CT.cityPartsC.get(oRan.nextInt(CT.cityPartsC.size())).clone();
        
        CP2.y = 46;
        CP2.x = CP.x;
        CP2.depth = CP.y+5;
        
        R.addActor(CP);
        R.addActor(CP2);
    }
    
    public static void addDoor(WorldTemplate CT,Room R){
        Door D = new Door(CT.toSet,"Door");
        D.current = R;
        
        D.y = 0;
        D.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        D.depth = -1;
        
        R.addActor(D);
        
    }
}
