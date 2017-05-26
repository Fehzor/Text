/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package text.Utility;

import text.Utility.ImageBuilder;
import java.awt.Color;
import text.Images.*;
import java.util.*;
import java.io.*;
import text.Inventory.*;
import text.Actors.*;
import text.Images.TextImageAnimated;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import text.Utility.ColorTuple;
import text.Utility.ColorTuple;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
/**
 *
 * @author FF6EB4
 */
public class ImageLoader {
    private static HashMap<Character,ColorTuple> map;
    private static HashMap<String,HashMap<Character,ColorTuple>> colorKeys;
    private static HashMap<String,TextImageBasic> loadedImages;
    private static ImageLoader me = new ImageLoader();//Force the constructor to do stuff.
    
    
    
    private ImageLoader(){
        loadedImages = new HashMap<>();
        colorKeys = new HashMap<>();
        loadKeys();
        //maptest();
    }
    
    public static HashMap<String,HashMap<Character,ColorTuple>> getColorKeys(){
        return colorKeys;
    }
    
    public static TextImageAnimated loadAnimated(String frameA, String frameB){
        TextImageBasic still = ImageLoader.loadImage(frameA);
        TextImageBasic move = ImageLoader.loadImage(frameB);
        
        TextImageComplex stillC = new TextImageComplex(still);
        TextImageComplex walkC = new TextImageComplex(move);
        
        TextImageAnimated ret = new TextImageAnimated(stillC,walkC);
        
        return ret;
    }
    
    public static TextImageBasic loadImage(String location){
        if(loadedImages.containsKey(location)){
            return loadedImages.get(location).clone();
        }
        
        ArrayList<ArrayList<ColorTuple>> ret = new ArrayList<>();
        
        try{
            Scanner oScan = new Scanner(new File(location));
            int cols = oScan.nextInt();
            int rows = oScan.nextInt();
            int xO = oScan.nextInt();
            int yO = oScan.nextInt();
            
            oScan.nextLine();
            
            for(int i = 0; i<rows; ++i){
                String row = oScan.nextLine();
                //System.out.println(row+" : "+i);
                ret.add(new ArrayList<ColorTuple>());
                for(int j = 0; j<cols; ++j){
                    char c = row.charAt(j);
                    
                    ColorTuple ct = new ColorTuple(Color.BLACK,Color.BLACK,c);
                    
                    ret.get(i).add(ct);
                    //System.out.print(c);
                }
            }
            
            for(int i = 0; i<rows; ++i){
                String row = oScan.nextLine();
                //System.out.println(row+" : "+i);
                for(int j = 0; j<cols; ++j){
                    //System.out.println(row.charAt(j));
                    ColorTuple ct = map.get(row.charAt(j));
                    //System.out.print(""+ct.icon);
                    ret.get(i).get(j).primary = ct.primary;
                    ret.get(i).get(j).secondary = ct.secondary;
                }
            }
            
            //System.out.println("RETURNING IMAGE");
            TextImageBasic tibret = new TextImageBasic(ret,xO,yO);
            loadedImages.put(location, tibret);
            return tibret;
            
        } catch(FileNotFoundException e){
            System.out.println("FILE NOT FOUND");
            return null;
        } catch(Exception e){
            System.out.println("OTHER ERROR");
            System.err.println(e);
            //map = new HashMap<>();
            return null;
        } 
    }
    
    public static void addMap(String name, HashMap<Character,ColorTuple> add){
        map = add;
        
        colorKeys.put(name, add);
    }
    
    public static ArrayList<ColorTuple> loadMap(String location){
        map = new HashMap<>();
        ArrayList<ColorTuple> ret = new ArrayList<>();
        
        try{
            Scanner oScan = new Scanner(new File(location));
            String key = oScan.next();
            //System.out.println(key);
            int load = oScan.nextInt();
            //System.out.println(load);
            
            for(int i = 0; i<load; ++i){
                ///oScan.nextLine();
                String next = oScan.next();
                //System.out.print(next);
                Color a = new Color(oScan.nextInt(),oScan.nextInt(),oScan.nextInt());
                //System.out.println("Color 1 done");
                Color b = new Color(oScan.nextInt(),oScan.nextInt(),oScan.nextInt());
                //System.out.println("Color 2 done");
                ColorTuple CTAdd = new ColorTuple(a,b,' ');
                map.put(next.charAt(0), CTAdd);
                ret.add(CTAdd);
            }
            
            //ALL maps have - as clear.
            ColorTuple clear = new ColorTuple(ColorTuple.TRANSPARENT,ColorTuple.TRANSPARENT,' ');
            map.put('-',clear);
            
            //System.out.println("Placed -");
            colorKeys.put(key,map);
            
            //System.out.println("MAP LOADED");
            return ret;
        } catch(FileNotFoundException e){
            System.out.println("MAP FILE NOT FOUND");
            return ret;
        } catch(Exception e){
            System.out.println("MAP ERR");
            map = new HashMap<>();
            return ret;
        }
        
        //return ret;
    }
    
    public static void switchMap(String name){
        map = colorKeys.get(name);
    }
    
    public void loadKeys(){
        loadMap("HUMAN");
        loadMap("ROCKY");
        loadMap("GREYSCALE");
        loadMap("GREYSCALE_LIGHT");
        loadMap("LIGHTBRIGHT");
        loadMap("ANIMAL_DEMO");
        loadMap("GREENSCALE");
        loadMap("AQUA");
        loadMap("CITYSCALE");
        loadMap("TREESCALE");
        loadMap("ANIMAL_SCHEME_A");
        loadMap("ANIMAL_SCHEME_B");
        loadMap("ANIMAL_SCHEME_C");
        loadMap("HUMAN_TWO");
        
        //Add additional keys as well. i.e. "GREENSCALE_LIGHT" = greyscale light + green
        ColorTuple Green = new ColorTuple(Color.GREEN,Color.GREEN,' ');
        HashMap<Character,ColorTuple> GreenScale = ImageBuilder.averageColorMap("GREYSCALE_LIGHT", Green);
        ImageLoader.addMap("GREENSCALE_LIGHT", GreenScale);
    }
}



//    @&^^^^^^