/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.WorldFrame.Regions;

import java.util.ArrayList;
import static text.Utility.SuperRandom.oRan;
import text.WorldFrame.Templates.WorldBuilder;
import text.WorldFrame.Templates.WorldTemplate;
import text.WorldFrame.World;
import static text.WorldFrame.Templates.WorldTemplate.CITY_HALLWAY;
import static text.WorldFrame.Templates.WorldTemplate.GRID_CITY;

/**
 *
 * @author FF6EB4
 */
public class RegionFactory {

    public static int KIND_CLUMP = 0;
    public static int KIND_CIRCLE = 1;
    public static int KIND_CITY = 2;
    
    
    public static Region spawn(WorldTemplate WT, int kind, int num){
        World W = WorldBuilder.generate(WT);
        
        ArrayList<World> worldList = new ArrayList<>();
        for(int i = 0; i < num; ++i){
            WorldTemplate alter = WT.clone();
            WorldTemplate.morph(alter);
            World temp = WorldBuilder.generate(alter);
            worldList.add(temp);
            temp.name += " " + (i+1);
        }
        
        //Connect them to the middle and then randomly.
        if(kind == KIND_CLUMP){
            for(int i = 0; i<num*2; ++i){
                if(i < num){
                    World A = worldList.get(i);
                    A.connect(W);
                } else {
                    World A = worldList.get(oRan.nextInt(num));
                    World B = worldList.get(oRan.nextInt(num));
                    A.connect(B);
                }
            }
        }
        
        //Connect them all in a circle.
        if(kind == KIND_CIRCLE){
            for(int i = 0; i<num+1; ++i){
                if(i == 0){
                    World A = worldList.get(i);
                    A.connect(W);
                } else if(i == num) {
                    World A = worldList.get(i-1);
                    W.connect(A);
                } else {
                    World A = worldList.get(i);
                    World B = worldList.get(i-1);
                    B.connect(A);
                }
            }
        }
        
        //A hallway leads to a city center, with things jutting out.
        if(kind == KIND_CITY){
            WT.worldType = CITY_HALLWAY;
            W = WorldBuilder.generate(WT);
            
            WT.worldType = GRID_CITY;
            World beta = WorldBuilder.generate(WT);
            System.out.println(W + " " + beta);
            W.connect(beta);
            
            for(int i = 1; i<num; ++i){
                World A = worldList.get(i);
                beta.connect(A);
            }
            
        }
        
        return new Region(W,worldList);
    }
}
