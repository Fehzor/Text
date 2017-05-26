/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Utility;

import text.Actors.Messages.Menu;
import java.awt.Color;
import text.Images.*;
import java.util.*;
import java.io.*;
import text.Inventory.*;
import text.Actors.*;
import text.Frame.*;
import text.Utility.ColorTuple;
/**
 *
 * @author FF6EB4
 */
public class MenuBuilder {
    
    private MenuBuilder(){
    }
    
    
    public static Drawable buildLineInput(String s){
        ArrayList<ArrayList<ColorTuple>> ret = new ArrayList<>();
        
        ret.add(new ArrayList<>());
        
        ColorTuple black = new ColorTuple(Color.black,Color.green,' ');
        ColorTuple pointer = black.clone();
        pointer.icon = '>';
        
        ret.get(0).add(pointer.clone());
        
        for(int i = 0; i< s.length(); ++i){
            pointer.icon = s.charAt(i);
            ret.get(0).add(pointer.clone());
        }
        
        while(ret.get(0).size() < TextDisplay.SCREEN_SIZE_X){
            ret.get(0).add(black.clone());
        }
        
        TextImageBasic tibRet = new TextImageBasic(ret,0,0);
        Drawable dRet = new Drawable(tibRet);
        dRet.depth = Integer.MAX_VALUE-1; //The inventory WILL be on top >:)
        return dRet;
    }
    
    public static Drawable buildDisplay(String[] toDisplay){
        ArrayList<ArrayList<ColorTuple>> ret = new ArrayList<>();
        
        
        
        //
        //GENERATE COLORTUPLES TO ADD;
        //
        ColorTuple black = new ColorTuple(Color.black,Color.green,' ');
        ColorTuple side = black.clone();
        side.icon = '|';
        ColorTuple corner = black.clone();
        corner.icon = '+';
        ColorTuple hori = black.clone();
        hori.icon = '-';
        
        //
        //ADD THE TOP
        //
        
        ret.add(new ArrayList<>());
        ret.get(0).add(corner);
        for(int j = 1;j<Inventory.DISPLAY_WIDTH; ++j){
                    ret.get(0).add(hori);
        }
        ret.get(0).add(corner);
        
        //
        //ADD THE MIDDLE
        //
        for(int i = 0; i<toDisplay.length; ++i){
            ret.add(new ArrayList<>());
            ret.get(i+1).add(side); /// side = |
            int j;
            for(j = 0; j<toDisplay[i].length() ;++j){
                if(j<Inventory.DISPLAY_WIDTH-1){
                    ColorTuple temp = black.clone();
                    temp.icon = toDisplay[i].charAt(j);
                    ret.get(i+1).add(temp);
                }
            }
            
            for(;j<Inventory.DISPLAY_WIDTH-1; ++j){
                    ret.get(i+1).add(black);
                }
            ret.get(i+1).add(side); /// side = |
        }
        
        //
        //ADD THE BOTTOM
        //
        ret.add(new ArrayList<>());
        ret.get(toDisplay.length+1).add(corner);
        for(int j = 1;j<Inventory.DISPLAY_WIDTH; ++j){
                    ret.get(toDisplay.length+1).add(hori);
        }
        
        //
        //CONSTRUCT IT AND RETURN;
        //
        TextImageBasic tibRet = new TextImageBasic(ret,0,0);
        Drawable dRet = new Drawable(tibRet);
        dRet.depth = Integer.MAX_VALUE-1; //The inventory WILL be on top >:)
        return dRet;
    }
    
    public static Drawable buildDisplayFull(String[] toDisplay){
        ArrayList<ArrayList<ColorTuple>> ret = new ArrayList<>();
        
        
        
        //
        //GENERATE COLORTUPLES TO ADD;
        //
        ColorTuple black = new ColorTuple(Color.black,Color.green,' ');
        ColorTuple side = black.clone();
        side.icon = '|';
        ColorTuple corner = black.clone();
        corner.icon = '+';
        ColorTuple hori = black.clone();
        hori.icon = '-';
        
        //
        //ADD THE TOP
        //
        
        ret.add(new ArrayList<>());
        ret.get(0).add(corner);
        for(int j = 1;j<TextDisplay.SCREEN_SIZE_X-1; ++j){
                    ret.get(0).add(hori);
        }
        ret.get(0).add(corner);
        
        //
        //ADD THE MIDDLE
        //
        for(int i = 0; i<toDisplay.length; ++i){
            ret.add(new ArrayList<>());
            ret.get(i+1).add(side); /// side = |
            int j;
            for(j = 0; j<toDisplay[i].length() ;++j){
                if(j<TextDisplay.SCREEN_SIZE_X-2){
                    ColorTuple temp = black.clone();
                    temp.icon = toDisplay[i].charAt(j);
                    ret.get(i+1).add(temp);
                }
            }
            
            for(;j<TextDisplay.SCREEN_SIZE_X-2; ++j){
                    ret.get(i+1).add(black);
                }
            ret.get(i+1).add(side); /// side = |
        }
        
        //
        //ADD THE BOTTOM
        //
        ret.add(new ArrayList<>());
        ret.get(toDisplay.length+1).add(corner);
        for(int j = 1;j<TextDisplay.SCREEN_SIZE_X-1; ++j){
                    ret.get(toDisplay.length+1).add(hori);
        }
        ret.get(toDisplay.length+1).add(corner);
        
        //
        //CONSTRUCT IT AND RETURN;
        //
        TextImageBasic tibRet = new TextImageBasic(ret,0,0);
        Drawable dRet = new Drawable(tibRet);
        dRet.depth = Integer.MAX_VALUE-1; //The inventory WILL be on top >:)
        return dRet;
    }
    
    public static Drawable buildResourceDisplay(ArrayList<Resource> rlist){
        ArrayList<ArrayList<ColorTuple>> ret = new ArrayList<>();

        //
        //GENERATE COLORTUPLES TO ADD;
        //
        ColorTuple black = new ColorTuple(Color.black,Color.green,' ');
        ColorTuple side = black.clone();
        side.icon = '|';
        ColorTuple corner = black.clone();
        corner.icon = '+';
        ColorTuple hori = black.clone();
        hori.icon = '-';
        
        //
        //ADD THE TOP
        //
        
        ret.add(new ArrayList<>());
        ret.get(0).add(corner);
        for(int j = 1;j<Inventory.DISPLAY_WIDTH; ++j){
                    ret.get(0).add(hori);
        }
        ret.get(0).add(corner);
        
        //
        //ADD THE MIDDLE
        //
        int num = 0;
        int row = 1;
        while(num<rlist.size()){
            ret.add(new ArrayList<>());
            ret.get(row).add(side);
            for(int i = 0; i<Inventory.DISPLAY_WIDTH-1; ++i){
                int spaceRemaining = Inventory.DISPLAY_WIDTH-1-i;
                //System.out.println(num);
                //System.out.println(rlist.size());
                if(num < rlist.size()){
                    Resource r = rlist.get(num);
                    
                    //System.out.println(r.amount);
                    //
                    // EACH RESOURCE CAN HAVE AN AMOUNT THAT WE MUST ACCOUNT FOR
                    //
                    if(r.amount==1){
                        ret.get(row).add(r.get());
                        ++num;
                    } else if(r.amount==2 && spaceRemaining>=2){
                        ret.get(row).add(r.get());
                        ret.get(row).add(r.get());
                        num++;
                        i++;
                        
                    } else if(r.amount==3 && spaceRemaining>=3){
                        ret.get(row).add(r.get());
                        ret.get(row).add(r.get());
                        ret.get(row).add(r.get());
                        num++;
                        i+=2;
                        //
                        // IF IT IS TOO BIG TO FIT IT IS REDUCED LIKE SO...
                        //
                    } else if(r.amount>3 && spaceRemaining>=
                            ((Integer)r.amount).toString().length()){
                        ret.get(row).add(r.get());
                        ret.get(row).add(new ColorTuple(Color.black,Color.green,'x'));
                        String s = ((Integer)r.amount).toString();
                        for(int k = 0; k <s.length();++k){
                            ret.get(row).add(new ColorTuple(Color.black,Color.green,s.charAt(k)));
                        }
                        num++;
                        i+=1 + s.length();
                        //System.out.println(2+s.length());
                    } else {
                        ret.get(row).add(black);
                    ++num;
                    }
                } else {
                    ret.get(row).add(black);
                    ++num;
                }
                
            }
            ret.get(row).add(side);
            ++row;
        }
        
        //
        //ADD THE BOTTOM
        //
        ret.add(new ArrayList<>());
        ret.get(row).add(corner);
        for(int j = 1;j<Inventory.DISPLAY_WIDTH; ++j){
                    ret.get(row).add(hori);
        }
        ret.get(row).add(corner);
        
        //
        //CONSTRUCT IT AND RETURN;
        //
        TextImageBasic tibRet = new TextImageBasic(ret,0,0);
        Drawable dRet = new Drawable(tibRet);
        dRet.depth = Integer.MAX_VALUE-1; //The inventory WILL be on top >:)
        return dRet;
    }
    
    //THIS ONE DISPLAYS A MENU.
    public static Drawable buildMenuDisplay(String[] toDisplay,int selection){
        ArrayList<ArrayList<ColorTuple>> ret = new ArrayList<>();
        
        //
        //GENERATE COLORTUPLES TO ADD;
        //
        ColorTuple black = new ColorTuple(Color.black,Color.green,' ');
        ColorTuple high = new ColorTuple(Color.gray,Color.blue,' ');
        ColorTuple side = black.clone();
        side.icon = '|';
        ColorTuple corner = black.clone();
        corner.icon = '+';
        ColorTuple hori = black.clone();
        hori.icon = '-';
        
        //
        //ADD THE TOP
        //
        
        ret.add(new ArrayList<>());
        ret.get(0).add(corner);
        for(int j = 1;j<Menu.DISPLAY_WIDTH; ++j){
                    ret.get(0).add(hori);
        }
        ret.get(0).add(corner);
        
        //
        //ADD THE MIDDLE
        //
        for(int i = 0; i<toDisplay.length; ++i){
            //System.out.println(toDisplay[i]);
            ret.add(new ArrayList<>());
            ret.get(i+1).add(side); /// side = |
            int j;
            for(j = 0; j<toDisplay[i].length() ;++j){
                if(j<Menu.DISPLAY_WIDTH-1){  
                    ColorTuple temp = black.clone();
                    if(i==selection) {
                        temp = high.clone();
                    }
                    temp.icon = toDisplay[i].charAt(j);
                    ret.get(i+1).add(temp);
                }
            }
            
            for(;j<Menu.DISPLAY_WIDTH-1; ++j){
                    if(i == selection){
                        ret.get(i+1).add(high);
                    } else {
                        ret.get(i+1).add(black);
                    }
                }
            ret.get(i+1).add(side); /// side = |
        }
        
        //
        //ADD THE BOTTOM
        //
        ret.add(new ArrayList<>());
        ret.get(toDisplay.length+1).add(corner);
        for(int j = 1;j<Menu.DISPLAY_WIDTH; ++j){
                    ret.get(toDisplay.length+1).add(hori);
        }
        ret.get(toDisplay.length+1).add(corner);
        
        //
        //CONSTRUCT IT AND RETURN;
        //
        TextImageBasic tibRet = new TextImageBasic(ret,0,0);
        Drawable dRet = new Drawable(tibRet);
        dRet.depth = Integer.MAX_VALUE-1; //The inventory WILL be on top >:)
        return dRet;
    }
}
