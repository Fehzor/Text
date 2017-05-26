/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.Instances;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import text.Actors.Interactable;
import text.Actors.NewsPaper;
import text.Actors.Searchable;
import text.Utility.ImageLoader;
import text.Images.TextImageBasic;
import text.Tools.ToolParts.SleepyPills;

/**
 *
 * @author FF6EB4
 */
public class CityPart extends Searchable{
    public static ArrayList<CityPart> genericParts = loadCityParts();
    public static ArrayList<CityPart> vehicleParts = loadVehicleParts();
    public static ArrayList<CityPart> statueParts = loadStatueParts();
    
    public static ArrayList<CityPart> tableParts = loadTableParts();
    public static ArrayList<CityPart> tableTopParts = loadTableTopParts();
    public static ArrayList<CityPart> indoorParts = loadIndoorParts();
    
    public int listNumber = 0;
    
    
    public CityPart(){
        super();
    }
    
    public CityPart(TextImageBasic img, String name){
        super(img, name);
        this.name = (name.charAt(0)+"").toUpperCase()+name.substring(1);
    }
    
    public CityPart clone(){
        CityPart ret = new CityPart((TextImageBasic)this.image.clone(),this.name);;
        
        return ret;
    }
    
    private static ArrayList<CityPart> loadCityParts(){
        ArrayList<CityPart> ret = new ArrayList<>();
        
        ImageLoader.switchMap("CITYSCALE");
        //*/
        try{
            Scanner oScan = new Scanner(new File("city_parts.txt"));
            while(oScan.hasNextLine()){
                String name = oScan.next();
                TextImageBasic img = ImageLoader.loadImage("city_"+name+".txt");
                CityPart CP = new CityPart(img, name);
                CP.listNumber = 1;
                ret.add(CP);
            }
        } catch(FileNotFoundException E){
            System.err.println("Something went wrong loading city_parts!");
        }
        //*/
        VendingMachine news = new VendingMachine(VendingMachine.NEWS);
        news.stock(new NewsPaper(), "grey", 5);
        news.stock(new NewsPaper(), "grey", 5);
        news.stock(new NewsPaper(), "grey", 5);
        
        news.listNumber = 1;
        
        
        ret.add(news);
        ret.add(news);
        ret.add(news);
        
        return ret;
    }
    
    private static ArrayList<CityPart> loadIndoorParts(){
        ArrayList<CityPart> ret = new ArrayList<>();
        
        ImageLoader.switchMap("CITYSCALE");
        
        try{
            Scanner oScan = new Scanner(new File("indoor_list.txt"));
            while(oScan.hasNextLine()){
                String name = oScan.next();
                TextImageBasic img = ImageLoader.loadImage("indoor_"+name+".txt");
                CityPart CP = new CityPart(img, name);
                CP.listNumber = 1;
                ret.add(CP);
            }
        } catch(FileNotFoundException E){
            System.err.println("Something went wrong loading city_parts!");
        }
        
        VendingMachine pillsA = new VendingMachine(VendingMachine.PILLS);
        pillsA.stock(new SleepyPills(), "white", 50);
        pillsA.listNumber = 1;
        
        VendingMachine pillsB = new VendingMachine(VendingMachine.PILLS);
        pillsB.stock(new SleepyPills(), "blue", 50);
        pillsB.listNumber = 1;
        
        VendingMachine pillsC = new VendingMachine(VendingMachine.PILLS);
        pillsC.stock(new SleepyPills(), "red", 50);
        pillsC.listNumber = 1;
        
        ret.add(pillsA);
        ret.add(pillsB);
        ret.add(pillsC);
        
        return ret;
    }
        
    private static ArrayList<CityPart> loadTableParts(){
        ArrayList<CityPart> ret = new ArrayList<>();
        
        ImageLoader.switchMap("CITYSCALE");
        
        try{
            Scanner oScan = new Scanner(new File("table_list.txt"));
            while(oScan.hasNextLine()){
                String name = oScan.next();
                TextImageBasic img = ImageLoader.loadImage("indoor_"+name+".txt");
                CityPart CP = new CityPart(img, name);
                CP.listNumber = 2;
                ret.add(CP);
            }
        } catch(FileNotFoundException E){
            System.err.println("Something went wrong loading city_parts!");
        }
        
        return ret;
    }
        
    private static ArrayList<CityPart> loadTableTopParts(){
        ArrayList<CityPart> ret = new ArrayList<>();
        
        ImageLoader.switchMap("CITYSCALE");
        
        try{
            Scanner oScan = new Scanner(new File("tabletop_list.txt"));
            while(oScan.hasNextLine()){
                String name = oScan.next();
                TextImageBasic img = ImageLoader.loadImage("indoor_"+name+".txt");
                CityPart CP = new CityPart(img, name);
                CP.listNumber = 3;
                ret.add(CP);
            }
        } catch(FileNotFoundException E){
            System.err.println("Something went wrong loading city_parts!");
        }
        
        return ret;
    }

    private static ArrayList<CityPart> loadVehicleParts() {
        ArrayList<CityPart> ret = new ArrayList<>();
        
        ImageLoader.switchMap("CITYSCALE");
        
        try{
            Scanner oScan = new Scanner(new File("vehicle_parts.txt"));
            while(oScan.hasNextLine()){
                String name = oScan.next();
                TextImageBasic img = ImageLoader.loadImage("city_"+name+".txt");
                CityPart CP = new CityPart(img, name);
                CP.listNumber = 2;        
                ret.add(CP);
            }
        } catch(FileNotFoundException E){
            System.err.println("Something went wrong loading vehicle_parts!");
        }
        
        
        return ret;
    }
    
    private static ArrayList<CityPart> loadStatueParts() {
        ArrayList<CityPart> ret = new ArrayList<>();
        
        ImageLoader.switchMap("CITYSCALE");
        
        try{
            Scanner oScan = new Scanner(new File("statue_parts.txt"));
            while(oScan.hasNextLine()){
                String name = oScan.next();
                TextImageBasic img = ImageLoader.loadImage("city_"+name+".txt");
                CityPart CP = new CityPart(img, name);
                CP.listNumber = 3;
                ret.add(CP);
            }
        } catch(FileNotFoundException E){
            System.err.println("Something went wrong loading statue_parts!");
        }
        
        return ret;
    }
}
