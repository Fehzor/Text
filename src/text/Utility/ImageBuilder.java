/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Utility;

import java.util.*;
import java.awt.*;
import text.Actors.*;
import text.Images.*;
import text.Images.TextImage;
import text.Images.TextImage;
import text.Images.TextImageAnimated;
import text.Images.TextImageAnimated;
import text.Images.TextImageBasic;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import text.Images.TextImageComplex;
import text.Utility.ColorTuple;
import text.Utility.ColorTuple;

/**
 *
 * @author FF6EB4
 */
public class ImageBuilder {
    private static ImageBuilder me = new ImageBuilder();
    
    private static HashMap<Character,ColorTuple> map;
    private static HashMap<String,HashMap<Character,ColorTuple>> colorKeys;
    
    private static Random oRan = new Random();
    
    //USED TO RECOLOR GREY THINGS
    private static ColorTuple clear;
    private static ColorTuple gA;
    private static ColorTuple gB;
    private static ColorTuple gC;
    private static ColorTuple gD;
    
    private ImageBuilder(){
        colorKeys = ImageLoader.getColorKeys();
        
        HashMap<Character,ColorTuple> greyscale = colorKeys.get("GREYSCALE");
        gA = greyscale.get('0');
        gB = greyscale.get('1');
        gC = greyscale.get('2');
        gD = greyscale.get('3');
    }
    
    public static void swapColorScheme(TextImageAnimated TIA,HashMap<Character,ColorTuple> newScheme){
        swapColorScheme((TextImageComplex)TIA.one,newScheme);
        swapColorScheme((TextImageComplex)TIA.two,newScheme);
    }
    
    //THE IMAGE MUST USE GREYSCALE. ALL BASIC ANIMAL IMAGES USE THIS.
    public static void swapColorScheme(TextImageComplex TIC, HashMap<Character,ColorTuple> newScheme){
        ArrayList<ArrayList<ColorTuple>> img = TIC.image;
        
        for(ArrayList<ColorTuple> ACT : img){
            for(ColorTuple CT : ACT){
                ColorTuple temp = CT.clone();
                //System.out.println(CT.equals(temp));
                
                if(CT.equals(gA)){
                    temp = newScheme.get('0');
                }
                if(CT.equals(gB)){
                    temp = newScheme.get('1');
                }
                if(CT.equals(gC)){
                    temp = newScheme.get('2');
                }
                if(CT.equals(gD)){
                    temp = newScheme.get('3');
                }
                
                CT.primary = temp.primary;
                CT.secondary = temp.secondary;
            }
        }
    }
    
    public static void swapColorScheme(TextImageBasic TIB, HashMap<Character,ColorTuple> newScheme){
        ArrayList<ArrayList<ColorTuple>> img = TIB.image;
        
        for(ArrayList<ColorTuple> ACT : img){
            for(ColorTuple CT : ACT){
                ColorTuple temp = CT.clone();
                //System.out.println(CT.equals(temp));
                
                if(CT.equals(gA)){
                    temp = newScheme.get('0');
                }
                if(CT.equals(gB)){
                    temp = newScheme.get('1');
                }
                if(CT.equals(gC)){
                    temp = newScheme.get('2');
                }
                if(CT.equals(gD)){
                    temp = newScheme.get('3');
                }
                
                CT.primary = temp.primary;
                CT.secondary = temp.secondary;
            }
        }
    }
    
    //Builds a color map out of 4 colortuples, and puts it into the ImageLoader.
    public static HashMap<Character,ColorTuple> buildColorMap( ColorTuple A, ColorTuple B, ColorTuple C, ColorTuple D){
        HashMap<Character,ColorTuple> newmap = new HashMap<>();
        newmap.put('0',A);
        newmap.put('1',B);
        newmap.put('2',C);
        newmap.put('3',D);
        
        ColorTuple clear = new ColorTuple(ColorTuple.TRANSPARENT,ColorTuple.TRANSPARENT,' ');
        newmap.put('-',clear);
        
        return newmap;
    }
    
    public static HashMap<Character,ColorTuple> averageColorMap(String map_name,ColorTuple skew){
        colorKeys = ImageLoader.getColorKeys();
        map = colorKeys.get(map_name);
        
        
        HashMap<Character,ColorTuple> ret = new HashMap<>();
        
        Iterator it = map.entrySet().iterator();
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            ColorTuple toSkew = (ColorTuple)pair.getValue();
            char c = (char)pair.getKey();
            //System.out.println(c);
            
            if(c != '-'){
                Color sec = mergeColors(toSkew.secondary,skew.secondary);
                Color pri = mergeColors(toSkew.primary,skew.primary);
            
                ColorTuple add = new ColorTuple(pri, sec,'F');
            
                ret.put(c, add);
            
                //it.remove(); // avoids a ConcurrentModificationException
            }
        }
        ColorTuple clear = new ColorTuple(ColorTuple.TRANSPARENT,ColorTuple.TRANSPARENT,' ');
        ret.put('-',clear);
        return ret;
    }
    
    public static Color mergeColors(Color a, Color b){
        int aRed = a.getRed();
        int aBlu = a.getBlue();
        int aGrn = a.getGreen();
        
        int bRed = b.getRed();
        int bBlu = b.getBlue();
        int bGrn = b.getGreen();
        
        int red = (int)(((float)(aRed + bRed))/2.0);
        int blu = (int)(((float)(aBlu + bBlu))/2.0);
        int grn = (int)(((float)(aGrn + bGrn))/2.0);
        
        return new Color(red,grn,blu);
    }
    
    //
    // COLOR IMAGES!
    //
    
    public static void alterImage(TextImageComplex TIB, int amt){
        ArrayList<ArrayList<ColorTuple>> TIBList = TIB.image;
        
        for(int i = 0; i<TIBList.size(); ++i){
            for( ColorTuple CT : TIBList.get(i)){
                if(CT.primary != ColorTuple.TRANSPARENT){
                    CT.primary = alterColor(CT.primary,amt);
                    CT.secondary = alterColor(CT.secondary,amt);
                }
            }
        }
    }
    
    public static void alterImage(TextImageBasic TIB, int amt){
        ArrayList<ArrayList<ColorTuple>> TIBList = TIB.image;
        
        for(int i = 0; i<TIBList.size(); ++i){
            for( ColorTuple CT : TIBList.get(i)){
                if(CT.primary != ColorTuple.TRANSPARENT){
                    CT.primary = alterColor(CT.primary,amt);
                    CT.secondary = alterColor(CT.secondary,amt);
                }
            }
        }
    }
    
    public static void colorMergeImage(TextImage TI, ColorTuple merge){
        if(TI.type == 1){
            colorMergeImage((TextImageBasic)TI,merge);
        }
        
        if(TI.type == 2){
            colorMergeImage((TextImageComplex)TI,merge);
        }
        
        if(TI.type == 3){
            colorMergeImage((TextImageAnimated)TI,merge);
        }
    }
    
    public static void colorMergeImage(TextImageAnimated TIA, ColorTuple merge){
        colorMergeImage((TextImageComplex)TIA.one,merge);
        colorMergeImage((TextImageComplex)TIA.two,merge);
    }
    
    public static void colorMergeImage(TextImageBasic TIB, ColorTuple merge){
        ArrayList<ArrayList<ColorTuple>> TIBList = TIB.image;
        
        for(int i = 0; i<TIBList.size(); ++i){
            for( ColorTuple CT : TIBList.get(i)){
                if(CT.primary != ColorTuple.TRANSPARENT){
                    CT.primary = mergeColors(CT.primary,merge.primary);
                    CT.secondary = mergeColors(CT.secondary,merge.secondary);
                }
            }
        }
    }
    
    public static void colorMergeImage(TextImageComplex TIB, ColorTuple merge){
        ArrayList<ArrayList<ColorTuple>> TIBList = TIB.image;
        
        for(int i = 0; i<TIBList.size(); ++i){
            for( ColorTuple CT : TIBList.get(i)){
                if(CT.primary != ColorTuple.TRANSPARENT){
                    CT.primary = mergeColors(CT.primary,merge.primary);
                    CT.secondary = mergeColors(CT.secondary,merge.secondary);
                }
            }
        }
    }
    
    public static void darkenImage(TextImageBasic TIB, int amt){
        ArrayList<ArrayList<ColorTuple>> TIBList = TIB.image;
        
        for(int i = 0; i<TIBList.size(); ++i){
            for( ColorTuple CT : TIBList.get(i)){
                if(CT.primary != ColorTuple.TRANSPARENT){
                    CT.primary = darkenColor(CT.primary,amt);
                    CT.secondary = darkenColor(CT.secondary,amt);
                }
            }
        }
    }
    
    public static void darkenImage(TextImageComplex TIC, int amt){
        ArrayList<ArrayList<ColorTuple>> TICList = TIC.image;
        
        for(int i = 0; i<TICList.size(); ++i){
            for( ColorTuple CT : TICList.get(i)){
                if(CT.primary != ColorTuple.TRANSPARENT){
                    CT.primary = darkenColor(CT.primary,amt);
                    CT.secondary = darkenColor(CT.secondary,amt);
                }
            }
        }
    }
    
    public static void addNoise(TextImage TI, int amt){
        if(TI.type == 1){
            addNoise((TextImageBasic)TI,amt);
        }
        
        if(TI.type == 2){
            addNoise((TextImageComplex)TI,amt);
        }
        
        if(TI.type == 3){
            addNoise((TextImageAnimated)TI,amt);
        }
    }
    
    public static void addNoise(TextImageAnimated TIA, int amt){
        addNoise(TIA.one,amt);
        addNoise(TIA.two,amt);
    }
    
    public static void addNoise(TextImageComplex TIC, int amt){
        ArrayList<ArrayList<ColorTuple>> TICList = TIC.image;
        
        for(int i = 0; i<TICList.size(); ++i){
            for( ColorTuple CT : TICList.get(i)){
                if(CT.primary != ColorTuple.TRANSPARENT){
                    int noise = oRan.nextInt(oRan.nextInt(amt)+1)-oRan.nextInt(oRan.nextInt(amt)+1);
                    CT.primary = darkenColor(CT.primary,noise);
                    noise = oRan.nextInt(oRan.nextInt(amt)+1)-oRan.nextInt(oRan.nextInt(amt)+1);
                    CT.secondary = darkenColor(CT.secondary,noise);
                }
            }
        }
    }
    
    public static void addNoise(TextImageBasic TIB, int amt){
        ArrayList<ArrayList<ColorTuple>> TICList = TIB.image;
        
        for(int i = 0; i<TICList.size(); ++i){
            for( ColorTuple CT : TICList.get(i)){
                if(CT.primary != ColorTuple.TRANSPARENT){
                    int noise = oRan.nextInt(oRan.nextInt(amt)+1)-oRan.nextInt(oRan.nextInt(amt)+1);
                    CT.primary = darkenColor(CT.primary,noise);
                    noise = oRan.nextInt(oRan.nextInt(amt)+1)-oRan.nextInt(oRan.nextInt(amt)+1);
                    CT.secondary = darkenColor(CT.secondary,noise);
                }
            }
        }
    }
    
    //Darkens a color by amt
    public static Color darkenColor(Color c, int amt){
        int r = c.getRed() - amt;
        int g = c.getGreen() - amt;
        int b = c.getBlue() - amt;
        
        if(r<0) r = 0;
        if(g<0) g = 0;
        if(b <0) b = 0;
        
        if(r>255) r = 255;
        if(g>255) g = 255;
        if(b>255) b = 255;
        
        return new Color(r,g,b);
    }
    
    public static ColorTuple alterColorTuple(ColorTuple CT, int amt){
        ColorTuple ret = CT.clone();
        
        ret.primary = alterColor(ret.primary,amt);
        ret.secondary = alterColor(ret.secondary,amt);
        
        return ret;
    }
    
    public static Color alterColor(Color c, int amt){
        int r = c.getRed() - oRan.nextInt(amt)+oRan.nextInt(amt);
        int g = c.getGreen() - oRan.nextInt(amt)+oRan.nextInt(amt);
        int b = c.getBlue() - oRan.nextInt(amt)+oRan.nextInt(amt);
        
        if(r<0) r = 0;
        if(g<0) g = 0;
        if(b <0) b = 0;
        
        if(r>255) r = 255;
        if(g>255) g = 255;
        if(b>255) b = 255;
        
        return new Color(r,g,b);
    }
    
    /**
     * Sets the alpha value of TIB to alpha
    */
    public static void makeTransparent(TextImageBasic TIB, int alpha){
        ArrayList<ArrayList<ColorTuple>> TICList = TIB.image;
        
        for(int i = 0; i<TICList.size(); ++i){
            for( ColorTuple CT : TICList.get(i)){
                if(CT.primary != ColorTuple.TRANSPARENT){
                    int red = CT.primary.getRed();
                    int grn = CT.primary.getGreen();
                    int blu = CT.primary.getBlue();
                    CT.primary = new Color(red,grn,blu,alpha);

                }
            }
        }
    }
    
    public static void makeTransparent(ColorTuple CT, int alpha){
        int red = CT.primary.getRed();
        int grn = CT.primary.getGreen();
        int blu = CT.primary.getBlue();
        CT.primary = new Color(red,grn,blu,alpha);
    }
    
    
    /**
     * Covers the image in char c
     * @param TIB textImageBasic to cover
     * @param c  char to cover it in
     */
    public static void letterCover(TextImageBasic TIB, char c){
        ArrayList<ArrayList<ColorTuple>> TICList = TIB.image;
        
        for(int i = 0; i<TICList.size(); ++i){
            for( ColorTuple CT : TICList.get(i)){
                if(CT.primary != ColorTuple.TRANSPARENT){
                    CT.icon = c;
                }
            }
        }
    }
    
    //Shrinks an image to widthxheight
    public static TextImageBasic resizeImage(TextImageBasic TIB, int width, int height){
        
        
        
        ArrayList<ArrayList<ColorTuple>> retList = new ArrayList<>();
        
        ArrayList<ArrayList<ColorTuple>> base = TIB.image;
        
        double iScale = base.get(0).size();
        double jScale = base.size();
        //I got them backwards... my bad
        iScale = (double)iScale / (double)width;
        jScale = (double)jScale / (double)height;
        
        System.out.println(iScale +" "+ jScale);
        
        for(int i = 0; i < height; ++i){
            retList.add(new ArrayList<>());
            for(int j = 0; j < width; ++j){
                ColorTuple CT;
                
               
                int x = (int)Math.floor(i*jScale);
                int y = (int)Math.floor(j*iScale);
                if(x > base.size() - 1){
                    x = base.size() - 1;
                }
                if(y > base.get(0).size() - 1){
                    y = base.get(0).size() - 1;
                }
                
                //if(x < 1) x = 1;
                //if(y < 1) y = 1;
                
                CT = base.get(x).get(y);
                
                retList.get(i).add(CT);
            }
        }
        
        return new TextImageBasic(retList);
    }
}
