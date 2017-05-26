/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Utility;
import text.Inventory.*;
import text.Images.*;
import java.util.*;
import java.awt.*;

/**
 *
 * @author FF6EB4
 */
public class LootGenerator {
    public static Random oRan = new Random();
    
    public static ArrayList<Resource> getLootClassic(String name, TextImage image){
        if(image.type == 1){
            return getLootClassic(name,(TextImageBasic)image);
        }
        if(image.type == 2){
            return getLootClassic(name,(TextImageComplex)image);
        }
        if(image.type == 3){
            return getLootClassic(name,(TextImageAnimated)image);
        }

        return null;
    }
    
    public static ArrayList<Resource> getLootClassic(String name,TextImageAnimated image){
        if(oRan.nextInt(100) > 50){
            return getLootClassic(name,image.one);
        } else {
            return getLootClassic(name,image.two);
        }
    }
    
    //Works on complicated sprites
    public static ArrayList<Resource> getLootClassic(String name,TextImageComplex image){
        ArrayList<Resource> ret = new ArrayList<>();
        
        int amount = 1 + oRan.nextInt(oRan.nextInt(10+oRan.nextInt(10))+5);
        
        while(amount > 0){
            Color A = getRandomColor(image,23);
            Color B = getRandomColor(image,77);
            
            while(B.equals(A)){
                A = getRandomColor(image,23);
                B = getRandomColor(image,77);
            }
            
            char icon = getRandomLetter(name);
            int rare = oRan.nextInt(100);
            if(rare == 23 || rare == 37 || rare == 19){
                icon = getRandomLetter(image);
            }
            
            if(icon == ' '){
                B = A; //Spaces just have the same.
            }
            
            ColorTuple CT = new ColorTuple(A,B,icon);
            
            int num = 1 + oRan.nextInt(oRan.nextInt(3)+1);
            Resource R = new Resource(CT,num);
            
            amount -= num;
            
            ret.add(R);
        }
        
        return ret;
    }
    
    //Works on basic sprites
    public static ArrayList<Resource> getLootClassic(String name,TextImageBasic image){
        ArrayList<Resource> ret = new ArrayList<>();
        
        int amount = 1 + oRan.nextInt(oRan.nextInt(10+oRan.nextInt(10))+5);
        
        while(amount > 0){
            Color A = getRandomColor(image,23);
            Color B = getRandomColor(image,77);
            
            while(B.equals(A)){
                A = getRandomColor(image,23);
                B = getRandomColor(image,77);
            }
            
            char icon = getRandomLetter(name);
            int rare = oRan.nextInt(100);
            if(rare == 23 || rare == 37 || rare == 19){
                icon = getRandomLetter(image);
            }
            
            if(icon == ' '){
                B = A; //Spaces just have the same.
            }
            
            ColorTuple CT = new ColorTuple(A,B,icon);
            
            int num = 1 + oRan.nextInt(oRan.nextInt(3)+1);
            Resource R = new Resource(CT,num);
            
            amount -= num;
            
            ret.add(R);
        }
        
        return ret;
    }
    
    //P = % chance that it'll be primary vs secondary
    public static Color getRandomColor(TextImageBasic image, int P){
        Color ret;
        int rows = image.image.size();
        int cols = image.image.get(0).size();
        
        int col = oRan.nextInt(cols);
        int row = oRan.nextInt(rows);
        
        while(image.image.get(row).get(col).primary == ColorTuple.TRANSPARENT){
            col = oRan.nextInt(cols);
            row = oRan.nextInt(rows);
        }
        
        if(oRan.nextInt(100) > P){
            ret = image.image.get(row).get(col).primary;
        } else {
            ret = image.image.get(row).get(col).secondary;
        }
        
        return ret;
    }
    
    public static char getRandomLetter(TextImageBasic image){
        char ret;
        int rows = image.image.size();
        int cols = image.image.get(0).size();
        
        int col = oRan.nextInt(cols);
        int row = oRan.nextInt(rows);
        
        while(image.image.get(row).get(col).primary == ColorTuple.TRANSPARENT){
            col = oRan.nextInt(cols);
            row = oRan.nextInt(rows);
        }
        
        ret = image.image.get(row).get(col).icon;
        
        
        return ret;
    }
    
    public static char getRandomLetter(String name){
        char ret;
        
        ret = name.charAt(oRan.nextInt(name.length()));
        
        return ret;
    }
    
    // Returns letters based on a word only.
    public static ArrayList<Resource> getLootValue(String s, int score){
        ArrayList<Resource> ret = new ArrayList<>();
        
        int amount = score*10;
        
        while(amount > 0){
            Color A = Color.BLACK;
            Color B = Color.WHITE;
            
            char icon = getRandomLetter(s);
            
            int rare = oRan.nextInt(100);
            if(rare == 23 || rare == 37 || rare == 19){
                A = Color.WHITE;
                B = Color.BLACK;
                icon = ((""+icon).toLowerCase()).charAt(0);
            }
            
            if(icon == ' '){
                B = A; //Spaces just have the same.
            }
            
            ColorTuple CT = new ColorTuple(A,B,icon);
            
            int num = 1 + oRan.nextInt(oRan.nextInt(3)+1);
            Resource R = new Resource(CT,num);
            
            amount -= num;
            
            ret.add(R);
        }
        
        return ret;
    }
}
