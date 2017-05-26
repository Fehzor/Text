/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Environment;

import text.Utility.ImageBuilder;
import java.util.*;
import java.awt.*;
import java.io.Serializable;
import text.Utility.*;
import text.Images.BackGround;
import static text.WorldFrame.World.oRan;
/**
 * @author FF6EB4
 */
public class Soil implements Serializable{
    public static ArrayList<ColorTuple> baseSet;
    public static HashMap<ColorTuple,Integer> baseRarity;
    private boolean init = false;
    
    public int water = 0;
    public int rocks = 5;
    
    ArrayList<ColorTuple> composition;
    //TODO- Add small rocks to the soil :D
    
    public Soil(){
        if(!init){
            generateBaseSet();
        }
        composition = new ArrayList<>();
        composition.add(new ColorTuple(Color.BLACK,Color.BLACK,' '));
    }
    
    public Soil(int minTuples){
        if(!init){
            generateBaseSet();
        }
        composition = generateSoilSet(minTuples);
    }
    
    public Soil(Soil s){
        this.composition = new ArrayList<>();
        this.composition.addAll(s.composition);
    }
    
    public void addTint(ColorTuple CT){
        this.composition.add(CT);
    }
    
    public ArrayList<ColorTuple> generateSoilSet(int minTuples){
        ArrayList<ColorTuple> ret = new ArrayList<>();
        Random oRan = new Random();
        
        //Duplicates are welcome. Strangely.
        while(ret.size() < minTuples){
            for(ColorTuple CT : baseSet){
                int chance = baseRarity.get(CT);
                if(oRan.nextInt(100) < chance){
                    ColorTuple add = CT.clone();
                    ret.add(add);
                }
            }
        }
        
        return ret;
    }
    
    public BackGround makeBack(){
        BackGround ret;
        
        ColorTuple CT = new ColorTuple(Color.GRAY,Color.GRAY,' ');
        for(ColorTuple C : composition){
            CT.secondary = ImageBuilder.mergeColors(C.secondary,CT.secondary);
            CT.primary = ImageBuilder.mergeColors(C.primary, CT.primary);
        }
        
        ret = new BackGround(CT,".:,-+*><",80);
        
        if(this.water > 0){
            ret.addWater(water);
        }
        
        return ret;
    }
    
    public ColorTuple averageColor(){
        ColorTuple CT = new ColorTuple(Color.GRAY,Color.WHITE,' ');
        for(ColorTuple C : composition){
            CT.secondary = ImageBuilder.mergeColors(C.secondary,CT.secondary);
            CT.primary = ImageBuilder.mergeColors(C.primary, CT.primary);
        }
        
        return CT;
    }
    
    public ColorTuple randomColor(){
        Random oRan = new Random();
        ColorTuple CT = new ColorTuple(Color.WHITE,Color.BLACK,' ');
        for(ColorTuple C : composition){
            if(oRan.nextInt(100) > 50){
                CT.secondary = ImageBuilder.mergeColors(C.secondary,CT.secondary);
                CT.primary = ImageBuilder.mergeColors(C.primary, CT.primary);
            }
        }
        
        return CT;
    }
    
    public static void generateBaseSet(){
        baseSet = new ArrayList<>();
        baseRarity = new HashMap<>();
        
        Color Brn = new Color(153, 102, 51);
        Color burntOrange = ImageBuilder.mergeColors(Color.ORANGE, Brn);
        Color ltBrn = ImageBuilder.mergeColors(Brn,Color.WHITE);
        Color dkBrn = ImageBuilder.mergeColors(Brn, Color.BLACK);
        Color dkBlu = ImageBuilder.mergeColors(Color.BLUE, Color.BLACK);
        Color ltBlu = ImageBuilder.mergeColors(Color.BLUE, Color.WHITE);
        Color sndy = new Color(255,252,179);
        Color dkrd = new Color(79,16,16);
        
        ColorTuple Brown = new ColorTuple(Brn,Brn,' ');
        ColorTuple DarkBrown = new ColorTuple(dkBrn,Brn,' ');
        ColorTuple LightBrown = new ColorTuple(ltBrn,dkBrn,' ');
        ColorTuple Green = new ColorTuple(Color.GREEN,Color.WHITE,' ');
        ColorTuple Orange = new ColorTuple(burntOrange,Color.WHITE,' ');
        ColorTuple Blue = new ColorTuple(Color.BLUE,ltBlu,' ');
        ColorTuple LightBlue = new ColorTuple(ltBlu,Color.BLUE,' ');
        ColorTuple DarkBlue = new ColorTuple(dkBlu,ltBlu,' ');
        ColorTuple Sandy = new ColorTuple(sndy,Color.WHITE,' ');
        ColorTuple DarkRed = new ColorTuple(dkrd,sndy,' ');
        ColorTuple WhiteBlack = new ColorTuple(Color.WHITE,Color.BLACK,' ');
        
        //baseRarity = % (as an int) of this one being in something.
        //IE 80 is common; 80% of soils will have Brown.
        baseSet.add(Brown);
        baseRarity.put(Brown,70);
        
        baseSet.add(LightBrown);
        baseRarity.put(LightBrown,70);
        
        baseSet.add(DarkBrown);
        baseRarity.put(DarkBrown,70);
        
        baseSet.add(DarkRed);
        baseRarity.put(DarkRed,45);
        
        baseSet.add(Sandy);
        baseRarity.put(Sandy,45);
        
        baseSet.add(Orange);
        baseRarity.put(Orange,15);
        
        baseSet.add(Green);
        baseRarity.put(Green,4);
        
        baseSet.add(Blue);
        baseRarity.put(Blue,2);
        
        baseSet.add(LightBlue);
        baseRarity.put(LightBlue,2);
        
        baseSet.add(DarkBlue);
        baseRarity.put(DarkBlue,2);
        
        baseSet.add(WhiteBlack);
        baseRarity.put(WhiteBlack, 2);

    }
}
