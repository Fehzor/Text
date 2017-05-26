/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Templates;

import text.WorldFrame.Templates.CityTemplate;
import java.awt.Point;
import java.util.Random;
import text.Actors.Instances.CityPart;
import text.Actors.Instances.Door;
import text.Environment.Environment;
import text.Frame.TextDisplay;
import text.Utility.ImageLoader;
import text.Images.TextImageBasic;
import text.Utility.ImageBuilder;
import text.Utility.SuperRandom;
import text.Utility.Tiles.TileBuilder;
import text.WorldFrame.Room;
import static text.WorldFrame.Templates.WorldTemplate.CITY_HALLWAY;
import static text.WorldFrame.Templates.WorldTemplate.GRID_CITY;
import static text.WorldFrame.Templates.WorldTemplate.GRID_WORLD;
import static text.WorldFrame.Templates.WorldTemplate.INDOOR_ROOM;
import static text.WorldFrame.Templates.WorldTemplate.LOST_WORLD;
import static text.WorldFrame.Templates.WorldTemplate.ROUND_WORLD;
import text.WorldFrame.World;
import text.WorldFrame.Worlds.CircleWorld;
import text.WorldFrame.Worlds.Cities.CityGridCenter;
import text.WorldFrame.Worlds.Cities.CityHallway;
import text.WorldFrame.Worlds.GridWorld;
import text.WorldFrame.Worlds.LostWorld;
import text.WorldFrame.Worlds.RoomWorld;

/**
 *
 * @author FF6EB4
 */
public class CityBuilder {
    public static Random oRan = new Random();
    
    private static TextImageBasic corner = ImageLoader.loadImage("corner.txt");
    
    public static World generate(WorldTemplate CT){
        World ret = createWorld(CT);
        
        ret.name = CT.adjective+"City";
        
        if(CT.worldType == GRID_CITY){
            return ret;
        }
        
        addAreas(CT,ret);
            
        return ret;
    }
    
    public static World createWorld(WorldTemplate CT){
        if(CT.worldType == GRID_CITY){
            return generateGridCity(CT);
        }
        
        if(CT.worldType == CITY_HALLWAY){
            CityHallway ret = new CityHallway(CT.size+3);
            return ret;
        }
        
        if(CT.worldType == LOST_WORLD){
            LostWorld ret = new LostWorld(CT.size*2, new Environment());
            return ret;
        }
        
        if(CT.worldType == GRID_WORLD){
            GridWorld ret = new GridWorld(CT.size*2, new Environment());
            return ret;
        }
        
        if(CT.worldType == ROUND_WORLD){
            CircleWorld ret = new CircleWorld(1,CT.size*2, new Environment());
            return ret;
        }
        
        if(CT.worldType == INDOOR_ROOM){
            RoomWorld ret = new RoomWorld();
            return ret;
        }
        
        
        return null;
    }

    public static World generateGridCity(WorldTemplate CT){
        CityGridCenter ret = new CityGridCenter(CT.size,CT.size/2);
        System.out.println(CT.size);
        
        int i = 0;
        for(i = 0; i<CT.size*CT.size; ++i){
            Room R = new Room(i,ret);
            //addCorners(CT, R);
            addCityPartsC(CT,R);
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
            TextImageBasic sidewalk = TileBuilder.buildSidewalk();
            
            ImageBuilder.colorMergeImage(wall, CT.background);
            ImageBuilder.colorMergeImage(sidewalk, CT.background);
            
            R.BG.addTextImageBasic(new Point(0,0), wall);
            R.BG.addTextImageBasic(new Point(0,wall.image.size()), sidewalk);
            
            R.block(Room.NORTH,24);
            if(CT.cityPartsA != null && CT.cityPartsA.size() > 0){
                addCityPartsA(CT,R);
            }
            if(CT.cityPartsB != null && CT.cityPartsB.size() > 0){
                addCityPartsB(CT,R);
            }
            
            ret.addRoom(R);
            if(oRan.nextInt(100) > 20 && CT.subWorlds != null){
                addDoor(CT,R);
            }
        }
    }
    
    /*
    public static void addCorners(WorldTemplate CT, Room R){
        TextImageBasic temp = corner.clone();
        ImageBuilder.colorMergeImage(temp,CT.background);
        
        R.BG.addTextImageBasic(new Point(0,0), temp);
        
        temp.flipImage();
        R.BG.addTextImageBasic(new Point(TextDisplay.SCREEN_SIZE_X-20,0), temp);
    }
    */
    public static void addDoor(WorldTemplate CT,Room R){
        WorldTemplate choose = CT.subWorlds.get(oRan.nextInt(CT.subWorlds.size()));
        
        /*
        World temp;
        temp = WorldBuilder.generate(choose);
        
        Door D = new Door(temp,"Door");
        */
        
        Door D = new Door(choose,"Door");
        choose.toSet = D;
        D.current = R;
        
        D.y = 0;
        D.x = TextDisplay.SCREEN_SIZE_X / 2;
        D.depth = -1;
        
        R.addActor(D);
        
    }
    
    //CITY PARTS
    public static void addCityPartsA(WorldTemplate CT, Room R){
        if(CT.cityPartsA == null || CT.cityPartsA.size() == 0){
            return;
        }
        CityPart CP = CT.cityPartsA.get(oRan.nextInt(CT.cityPartsA.size())).clone();
        
        CP.y = 30;
        CP.depth = 30;
        CP.x = SuperRandom.nextIntReverseGaussian(TextDisplay.SCREEN_SIZE_X);
        
        R.addActor(CP);
    }
    
    //VEHICLES
    public static void addCityPartsB(WorldTemplate CT, Room R){
        if(CT.cityPartsB == null || CT.cityPartsB.size() == 0){
            return;
        }
        CityPart CP = CT.cityPartsB.get(oRan.nextInt(CT.cityPartsB.size())).clone();
        
        CP.y = 50;
        CP.depth = 50;
        CP.x = SuperRandom.nextIntReverseGaussian(TextDisplay.SCREEN_SIZE_X);
        
        R.addActor(CP);
    }
    
    //STATUES AND FOUNTAINS
    public static void addCityPartsC(WorldTemplate CT, Room R){
        if(CT.cityPartsC == null || CT.cityPartsC.size() == 0){
            return;
        }
        CityPart CP = CT.cityPartsC.get(oRan.nextInt(CT.cityPartsC.size())).clone();
        
        CP.y = TextDisplay.SCREEN_SIZE_Y / 2;
        CP.depth = TextDisplay.SCREEN_SIZE_Y / 2;
        CP.x = TextDisplay.SCREEN_SIZE_X / 2;
        
        R.addActor(CP);
    }
}
