/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Templates;

import text.WorldFrame.Templates.WorldTemplate;
import java.awt.Color;
import java.util.ArrayList;
import text.Actors.Instances.CityPart;
import text.Environment.Environment;
import text.Images.TextImageBasic;
import text.Utility.ColorTuple;
import text.Utility.Diction.LiteralDictionary;
import text.Utility.ImageBuilder;
import static text.WorldFrame.World.oRan;

/**
 * @author FF6EB4
 */
public class CityTemplate extends WorldTemplate{
    public CityTemplate(){
        this.worldKind = CITY;
        
        //For now, just randomize all cities.
        int rand = oRan.nextInt(100);
        
        if(rand < 40){
            this.worldType = CITY_HALLWAY;
        } else if(rand < 55){
            this.worldType = ROUND_WORLD;
        } else {
            this.worldType = GRID_CITY;
        }
        //this.worldType = ROUND_WORLD;
        
        int r = oRan.nextInt(255);
        int g = oRan.nextInt(255);
        int b = oRan.nextInt(255);
        
        size = 2+oRan.nextInt(3);
        
        background = new ColorTuple(new Color(r,g,b),Color.WHITE,' ');
        foreground = new ColorTuple(new Color(255-r,255-g,255-b),Color.WHITE,' ');
        
        for(int i = 0; i < 2+oRan.nextInt(4); ++i){
            CityPart CP = CityPart.genericParts.get(oRan.nextInt(CityPart.genericParts.size())).clone();
            ImageBuilder.colorMergeImage((TextImageBasic)CP.image, foreground);
            cityPartsA.add(CP);
            
            CityPart CP2 = CityPart.vehicleParts.get(oRan.nextInt(CityPart.vehicleParts.size())).clone();
            ImageBuilder.colorMergeImage((TextImageBasic)CP2.image, foreground);
            cityPartsB.add(CP2);
            
            CityPart CP3 = CityPart.statueParts.get(oRan.nextInt(CityPart.statueParts.size())).clone();
            ImageBuilder.colorMergeImage((TextImageBasic)CP3.image, foreground);
            cityPartsC.add(CP3);
        }
        
        this.subWorlds = new ArrayList<>();
        IndoorTemplate temp = new IndoorTemplate();
        this.subWorlds.add(temp);
        
        this.E = new Environment(Environment.TYPE_NONE);
        
        this.adjective = LiteralDictionary.getAdjective();
        this.adjective = adjective.charAt(0)+adjective.substring(1).toLowerCase();
    }
    
    public CityTemplate(int type){
        this();
        this.worldType = type;
    }
    
    public static void morph (WorldTemplate WT){
        if(oRan.nextInt(100) < 45){
            CityPart CP = CityPart.genericParts.get(oRan.nextInt(CityPart.genericParts.size())).clone();
            ImageBuilder.colorMergeImage((TextImageBasic)CP.image, WT.foreground);
            WT.cityPartsA.set(0,CP);
        }
        
        if(oRan.nextInt(100) < 45){
            CityPart CP2 = CityPart.vehicleParts.get(oRan.nextInt(CityPart.vehicleParts.size())).clone();
            ImageBuilder.colorMergeImage((TextImageBasic)CP2.image, WT.foreground);
            WT.cityPartsB.set(0,CP2);
        }
        
        if(oRan.nextInt(100) < 45){
            CityPart CP3 = CityPart.statueParts.get(oRan.nextInt(CityPart.statueParts.size())).clone();
            ImageBuilder.colorMergeImage((TextImageBasic)CP3.image, WT.foreground);
            WT.cityPartsC.set(0,CP3);
        }
        
        if(oRan.nextInt(100) < 50){
            IndoorTemplate temp = new IndoorTemplate();
            WT.subWorlds.set(0,temp);
        }
        
        int rand = oRan.nextInt(100);
        
        if(rand < 40){
            WT.worldType = CITY_HALLWAY;
        } else if(rand < 55){
            WT.worldType = ROUND_WORLD;
        } else {
            WT.worldType = GRID_CITY;
        }
    }
}
