/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Robits;

import text.Environment.Animals.*;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import static text.Environment.Animals.Animal.oRan;
import text.Environment.Environment;
import text.Images.TextImage;
import text.Images.TextImageAnimated;
import text.Images.TextImageBasic;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import text.Utility.ImageLoader;
import java.lang.*;
import text.Images.TextImageComplex;

/**
 *
 * @author FF6EB4
 */
public class RobitTemplate implements Serializable{
    public static ArrayList<TextImageAnimated> robitSprites = loadSprites();

    public static ArrayList<TextImageAnimated> loadSprites(){
        ArrayList<TextImageAnimated> ret = new ArrayList<>();
        
        try{
            ImageLoader.switchMap("CITYSCALE");
            Scanner oScan = new Scanner(new File("robot_list.txt"));
            while(oScan.hasNextLine()){
                String name = oScan.next();
                TextImageAnimated add = ImageLoader.loadAnimated(name+"_stand.txt", name+"_walk.txt");
                ret.add(add);
            }
        } catch(FileNotFoundException E){
            System.err.println("Something went wrong loading robits!!");
        }
        
        return ret;
    }
}
