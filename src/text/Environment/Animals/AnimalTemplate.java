/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Environment.Animals;

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

/**
 *
 * @author FF6EB4
 */
public class AnimalTemplate implements Serializable{
    
    public static ArrayList<String> animalNames;
    public static HashMap<String,TextImageAnimated> animalImages;
    public static HashMap<String,Environment> animalEnvironments;
    public static HashMap<String,Integer> animalSizes;
    public static HashMap<String,TextImageBasic> animalNests;
    public static HashMap<String,ArrayList<String>> animalDialogue;
    
    public static ArrayList<ColorTuple> furSheens; //COLOR OF FUR
    public static ArrayList<ColorTuple> coatSheens; //COLOR OF FEATHERS, ETC.
    public static ArrayList<ColorTuple> boneSheens; //COLOR OF BIRD FEET, BEAKS, ETC>
    public static ArrayList<ColorTuple> radSheens; //NEON COLORS FOR RADICAL ANIMALS!
    
    Environment preffered;
    int size;
    
    //Creates a randomized color scheme and sprite for this animal.
    public static TextImageAnimated generateSprite(){
        
        ColorTuple C = furSheens.get(oRan.nextInt(furSheens.size()));
        ColorTuple A = coatSheens.get(oRan.nextInt(coatSheens.size()));
        ColorTuple B = boneSheens.get(oRan.nextInt(boneSheens.size()));
        ColorTuple D = radSheens.get(oRan.nextInt(radSheens.size()));
        
        
        //0,1,2,3
        //0 = fur
        //1 = coat
        //2 = bone
        HashMap<Character,ColorTuple> map = ImageBuilder.buildColorMap(C, A, B, D);
        
        //for(int i = 0; i< 100; ++i)System.out.println(oRan.nextInt(animalNames.size()));
        
        String animal = animalNames.get(oRan.nextInt(animalNames.size()));
        TextImageAnimated image = (animalImages.get(animal)).clone();
        
        
        ImageBuilder.swapColorScheme((TextImageAnimated)image, map);
        
        ImageBuilder.addNoise((TextImageAnimated)image, 15);
        
        return image;
    }
    
    //Creates a randomized color scheme for the animal... based on a type
    public static TextImageAnimated generateSprite(String name){
        
        ColorTuple C = furSheens.get(oRan.nextInt(furSheens.size()));
        ColorTuple A = coatSheens.get(oRan.nextInt(coatSheens.size()));
        ColorTuple B = boneSheens.get(oRan.nextInt(boneSheens.size()));
        ColorTuple D = radSheens.get(oRan.nextInt(radSheens.size()));
        
        
        //0,1,2,3
        //0 = fur
        //1 = coat
        //2 = bone
        HashMap<Character,ColorTuple> map = ImageBuilder.buildColorMap(C, A, B, D);
        
        //for(int i = 0; i< 100; ++i)System.out.println(oRan.nextInt(animalNames.size()));
        
        TextImageAnimated image = (animalImages.get(name)).clone();
        
        ImageBuilder.swapColorScheme((TextImageAnimated)image, map);
        
        ImageBuilder.addNoise((TextImageAnimated)image, 15);
        
        return image;
    }
            /**
    Loads animals into lists.
    */
    static void loadAnimals(){
        animalImages = new HashMap<>();
        animalEnvironments = new HashMap<>();
        animalSizes = new HashMap<>();
        animalNests = new HashMap<>();
        animalDialogue = new HashMap<>();
        
        animalNames = new ArrayList<>();
        furSheens = new ArrayList<>();
        coatSheens = new ArrayList<>();
        boneSheens = new ArrayList<>();
        radSheens = new ArrayList<>();
        
        
        ImageLoader.switchMap("GREYSCALE");
        
        
        //Animals are stored like so-
        //name [4 environment vars] size nest?
        
        try{
            Scanner oScan = new Scanner(new File("animal_info.txt"));
            while(oScan.hasNextLine()){
                String name = oScan.next();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                //a-d = environment
                double a = oScan.nextDouble(); 
                double b = oScan.nextDouble();
                double c = oScan.nextDouble();
                double d = oScan.nextDouble();
                int e = oScan.nextInt(); //Size
                int f = oScan.nextInt(); //0 = no nest, 1 = nest
                
                animalNames.add(name);
                animalEnvironments.put(name, new Environment(a,b,c,d));
                TextImageAnimated temp = ImageLoader.loadAnimated(name+"_stand.txt",name+"_walk.txt");
                animalImages.put(name,temp);
                animalSizes.put(name,e);
                
                if(f != 0){
                    String s = oScan.next();
                    ImageLoader.switchMap("TREESCALE");
                    TextImageBasic TEMP = ImageLoader.loadImage(s);
                    animalNests.put(name, TEMP);
                }
                try{
                    Scanner newScan = new Scanner(new File(name+"_dialogue.txt"));
                    animalDialogue.put(name,new ArrayList<>());
                    while(newScan.hasNextLine()){
                        animalDialogue.get(name).add(newScan.nextLine());
                    }
                } catch (Exception E){System.out.println(name+" has no dialogue!");};
            }
        } catch(FileNotFoundException E){
            System.err.println("Something went wrong loading animal_species!");
        }
    
        //*/
        
        ColorTuple red = new ColorTuple(Color.RED,Color.BLACK,' ');
        ColorTuple blu = new ColorTuple(Color.BLUE,Color.WHITE,' ');
        ColorTuple grey= new ColorTuple(Color.GRAY,Color.GREEN,' ');
        ColorTuple yelo= new ColorTuple(Color.YELLOW,Color.BLACK,' ');
        ColorTuple blak= new ColorTuple(Color.BLACK,Color.GRAY,' ');
        ColorTuple orange = new ColorTuple(Color.ORANGE,Color.BLACK,' ');
        ColorTuple white = new ColorTuple(Color.WHITE,Color.BLACK,' ');
        ColorTuple tux = new ColorTuple(Color.BLACK,Color.WHITE,' ');
        ColorTuple pank = new ColorTuple(new Color(255,110,180),Color.BLACK,' ');
        ColorTuple brwn = new ColorTuple(new Color(128, 96, 0),Color.WHITE,' ');
        ColorTuple ltbrwn = new ColorTuple(new Color(204, 153, 0),Color.BLACK,' ');
        ColorTuple autumn = new ColorTuple(new Color(204, 102, 0),Color.WHITE,' ');
        ColorTuple turtyllo = new ColorTuple(new Color(102, 102, 0),Color.YELLOW,' ');
        ColorTuple dkblu = new ColorTuple(new Color(0, 64, 128),Color.GREEN,' ');
        ColorTuple dprd = new ColorTuple(new Color(128, 0, 0),new Color(102, 102, 0),' ');
        ColorTuple dkviolt = new ColorTuple(new Color(115, 38, 77),new Color(102, 102, 0),' ');
        ColorTuple gold = new ColorTuple(new Color(255, 209, 26),Color.YELLOW,' ');
        ColorTuple electric = new ColorTuple(new Color(102, 255, 255),Color.YELLOW,' ');
        ColorTuple neongreen = new ColorTuple(new Color(83, 255, 26),Color.BLUE,' ');
        ColorTuple purple = new ColorTuple(new Color(255, 102, 255),Color.YELLOW,' ');
        
        coatSheens.add(red);
        coatSheens.add(blu);
        coatSheens.add(grey);
        coatSheens.add(yelo);
        coatSheens.add(turtyllo);
        coatSheens.add(dkblu);
        coatSheens.add(dprd);
        coatSheens.add(dkviolt);
        coatSheens.add(gold);
        coatSheens.add(electric);
        
        boneSheens.add(grey);
        boneSheens.add(yelo);
        boneSheens.add(blak);
        boneSheens.add(dkblu);
        boneSheens.add(turtyllo);
        boneSheens.add(dprd);
        boneSheens.add(gold);
        boneSheens.add(neongreen);
        boneSheens.add(purple);
        
        furSheens.add(blak);
        furSheens.add(orange);
        furSheens.add(white);
        furSheens.add(tux);
        furSheens.add(pank);
        furSheens.add(brwn);
        furSheens.add(ltbrwn);
        furSheens.add(autumn);
        furSheens.add(turtyllo);
        furSheens.add(dkviolt);
        furSheens.add(gold);
        
        radSheens.add(gold);
        radSheens.add(orange);
        radSheens.add(yelo);
        radSheens.add(tux);
        radSheens.add(pank);
        radSheens.add(electric);
        radSheens.add(neongreen);
        radSheens.add(purple);
    }
}
