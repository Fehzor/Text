/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Templates;

import java.awt.Color;
import java.util.ArrayList;
import text.Actors.Instances.CityPart;
import text.Images.TextImageBasic;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import text.WorldFrame.World;
import static text.WorldFrame.World.oRan;

/**
 *
 * @author FF6EB4
 */
public class IndoorTemplate extends WorldTemplate{
    public IndoorTemplate(){
        this.worldKind = INDOOR;
        this.worldType = INDOOR_ROOM;
        
        this.size = 2;
        
        int r = oRan.nextInt(255);
        int g = oRan.nextInt(255);
        int b = oRan.nextInt(255);
        
        size = 2+oRan.nextInt(3);
        
        background = new ColorTuple(new Color(r,g,b),Color.WHITE,' ');
        foreground = new ColorTuple(new Color(255-r,255-g,255-b),Color.WHITE,' ');
        
        loadRoomObjects();
    }
    
    public void loadRoomObjects(){
        cityPartsA = new ArrayList<>();
        cityPartsB = new ArrayList<>();
        cityPartsC = new ArrayList<>();
        
        for(int i = 0; i < 1+oRan.nextInt(3); ++i){
            CityPart CPA = CityPart.indoorParts.get(oRan.nextInt(CityPart.indoorParts.size())).clone();
            ImageBuilder.colorMergeImage((TextImageBasic)CPA.image, foreground);
            cityPartsA.add(CPA);
        }
        
        for(int i = 0; i < 1+oRan.nextInt(2); ++i){
            CityPart CPB = CityPart.tableParts.get(oRan.nextInt(CityPart.tableParts.size())).clone();
            ImageBuilder.colorMergeImage((TextImageBasic)CPB.image, foreground);
            cityPartsB.add(CPB);
        }
        
        for(int i = 0; i < 1+oRan.nextInt(2); ++i){
            CityPart CPC = CityPart.tableTopParts.get(oRan.nextInt(CityPart.tableTopParts.size())).clone();
            ImageBuilder.colorMergeImage((TextImageBasic)CPC.image, foreground);
            cityPartsC.add(CPC);
        }
    }
    
}
