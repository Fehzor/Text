/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Inventory;

import text.Utility.*;
import java.io.Serializable;
/**
 *
 * @author FF6EB4
 */
public class Resource extends Item implements Serializable {
    public int amount;
    
    public Resource(ColorTuple icon){
        super(icon);
        tag();
        amount = 1; //Presumably there are one of them if unspecified...
    }
    
    public Resource(ColorTuple icon, int amount){
        super(icon);
        tag();
        this.amount = amount;
    }
    
    public Resource clone(){
        return new Resource(this.icon);
    }
    
    private void tag(){
        this.tags.add(icon.icon+"");
        
        //TODO Make an awesome tagging system to sort for colors!
    }
    
    public boolean equals(Object o){
        Resource other = (Resource)o;
        
        return other.icon.equals(this.icon);
    }
    
    public boolean isType(String s){
        if(s.equals("all")){
            return true;
        }
        
        if(s.length() == 1){
            return this.icon.icon == s.charAt(0);
        }
        
        int red,grn,blu;
        red = icon.primary.getRed();
        grn = icon.primary.getGreen();
        blu = icon.primary.getBlue();
        boolean ret = false;
        
        if(s.equals("red")){
            ret = red > grn+50 && red > blu+50 && red > 100;
            return ret;
        }
        
        if(s.equals("blue") || s.equals("blu")){
            ret = blu > grn+50 && red < blu+50 && blu > 100;
            return ret;
        }
        
        if(s.equals("green") || s.equals("grn")){
            ret = red < grn+50 && grn > blu+50 && grn > 100;
            return ret;
        }
        
        if(s.equals("white") || s.equals("wht")){
            ret = red > 230 && blu > 230 && grn > 230;
            return ret;
        }
        
        if(s.equals("black") || s.equals("blk")){
            ret = red < 20 && blu < 20 && grn < 20;
            return ret;
        }
        
        if(s.equals("grey") || s.equals("gray")){
            int difR = red - blu;
            int difB = blu - grn;
            int difG = grn - red;
            int sum = Math.abs(difR) + Math.abs(difB) + Math.abs(difG);
            ret = sum < 30;
            return ret;
        }
        
        if(s.equals("bright")){
            ret = red > 150 || blu > 150 || grn > 150;
            return ret;
        }
        
        if(s.equals("dim")){
            ret = red<80 || blu<80 || grn<80;
            ret = red<150 && blu<150 && grn<150;;
            return ret;
        }
        
        if(s.equals("uppercase")){
            if(icon.icon >= 'A' && icon.icon <= 'Z'){
                return true;
            }
        }
        
        if(s.equals("lowercase")){
            if(icon.icon >= 'a' && icon.icon <= 'z'){
                return true;
            }
        }
        
        if(s.equals("alphabetical")){
            return isType("lowercase") || isType("uppercase");
        }
        
        if(s.equals("vowel")){
            if(icon.icon == 'a' || icon.icon == 'e' ||icon.icon == 'i' ||
                    icon.icon == 'o' || icon.icon == 'u' ||icon.icon == 'y' ||
                    icon.icon == 'A' || icon.icon == 'E' ||icon.icon == 'I' ||
                    icon.icon == 'O' || icon.icon == 'U' ||icon.icon == 'Y' ||
                    icon.icon == 'w' ||icon.icon == 'W' ){
                return true && isType("alphabetical");
            }
        }
        
        if(s.equals("consonant")){
            if(icon.icon == 'a' || icon.icon == 'e' ||icon.icon == 'i' ||
                    icon.icon == 'o' || icon.icon == 'u' ||icon.icon == 'I' ||
                    icon.icon == 'A' || icon.icon == 'E' ||
                    icon.icon == 'O' || icon.icon == 'U'){
                return false;
            } else {
                return true && isType("alphabetical");
            }
        }
        
        return false;
    }
}
