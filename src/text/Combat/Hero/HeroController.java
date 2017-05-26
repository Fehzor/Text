/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Hero;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import text.Actors.Actor;
import text.Actors.Player;
import text.Combat.CombatSystem;
import text.Frame.TextDisplay;
import text.Images.TextImageAnimated;
import text.Images.TextImageBasic;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import text.Utility.ImageLoader;

/**
 * Controls the guitarheroesque thingses.
 * @author FF6EB4
 */
public class HeroController extends CombatSystem{
    
    public static Random oRan = new Random();
    
    public static final ColorTuple EZ = new ColorTuple(Color.GREEN,Color.YELLOW,' ');
    public static final ColorTuple MED = new ColorTuple(Color.YELLOW,Color.RED,' ');
    public static final ColorTuple HARD = new ColorTuple(Color.RED,Color.GRAY,' ');
    public static final ColorTuple WORD = new ColorTuple(Color.BLUE,Color.GREEN,' ');
        
    public String word = "";
    ArrayList<Integer> posList = new ArrayList<>();
    ArrayList<Double> speedList = new ArrayList<>();
    ArrayList<ColorTuple> colorList = new ArrayList<>();
    public int height = 42;
    
    public int letter = 0; //This is like the index. Which letter is it on?
    
    public int step = 0;//An index for the rate; how many acts to go till spawn?
    public int delay = 3; // Lower delay = faster, in game steps.
    
    boolean repeat = false; // After it ends does it start over until..
    
    ArrayList<HeroLetter> letterList;
    
    public HeroController(){}
    
    /**
     * Spawns a basic HeroController with basic settings- just difficulty and a word
     * 
     * @param s
     * The word that this creates
     * @param difficulty 
     * The difficulty; 0 = easiest, 1 = hardest.
     */
    public HeroController(String s, float difficulty){
        this.word = s;
        
        this.delay = (int)((double)100 - (95*difficulty));

        int lanes = 1+(int)((((double)difficulty) * ((double)10.0)));
        for(int i = 0; i<word.length(); ++i){
            
            int lane = ((oRan.nextInt(lanes)) * 13);
            
            //System.out.println(lane);
            
            
            this.posList.add(lane);
        }
        
        double standardSpeed = .3+((.5+(double)oRan.nextDouble()) * (double)(difficulty));
        double slowSpeed = standardSpeed*(.3+(.5*(double)oRan.nextDouble()));
        double highSpeed = standardSpeed*(((double)oRan.nextDouble() * (double)(difficulty))+1.0);
        
        for(int i = 0; i<word.length(); ++i){
            double hard = oRan.nextDouble() * (double)(difficulty);
            if(hard < .4){
                //System.out.println("EZ");
                colorList.add(EZ);
                speedList.add(standardSpeed);
            } else if(hard < .55){
                //System.out.println("MED");
                colorList.add(MED);
                speedList.add(slowSpeed);
            } else {
                //System.out.println("HARD");
                colorList.add(HARD);
                speedList.add(highSpeed);
            }
        }
        
        this.y = height;
        this.x = 0;
        ImageLoader.switchMap("LIGHTBRIGHT");
        this.image = ImageLoader.loadAnimated("bar.txt","bartwo.txt");
                //.loadImageAnimated("bar.txt","bar.txt");
        this.depth = 10000;
        if(difficulty < .4){
            ImageBuilder.colorMergeImage(((TextImageAnimated)this.image).one,EZ);
        }
        else if(difficulty < .6){
            ImageBuilder.colorMergeImage(((TextImageAnimated)this.image).one,MED);
        }
        else if(difficulty > .6){
            ImageBuilder.colorMergeImage(((TextImageAnimated)this.image).one,HARD);
        }
        
        ImageBuilder.colorMergeImage(((TextImageAnimated)this.image).two, WORD);
        
        this.letterList = new ArrayList<>();
    }
    
    public boolean act(){
        if(this.held != true){
            return go();
        } else {
            return false;
        }
    }
    
    
    
    public boolean go(){
        Player.The.paused = true;
        
        if(this.image != null){
            ((TextImageAnimated)this.image).resetFrame();
        }
        
        if(this.image != null){
            for(HeroLetter HL : letterList){
                if(this.y > HL.y - 5 && this.y < HL.y + 1){
                    //System.out.println("TWO");
                    ((TextImageAnimated)this.image).setSecond();
                    break;
                }
            }
        }

        
        //Is it done??
        //System.out.println("LEN:"+word.length()+" LET:"+letter+" SIZ:"+this.letterList.size());
        
        if(word.length() == letter && this.letterList.size() == 0){
            this.dead = true;
            Player.The.paused = false;
            this.setDone();
            letter = 0;
            step = 0;
            return true;
        }
        
        //Is it time to act?
        if(step < delay){
            step+=1;
            return true;
        } else {
            step = 0;
        }
        

        
        
        if(word.length() > letter){
            //Get the data..
            char c = word.charAt(letter);
            int spawn = posList.get(letter);
            double speed = speedList.get(letter);
            ColorTuple CT = colorList.get(letter);

            //Spawn the letter. Done.
            this.letterList.add(HeroLetter.addHero(spawn, speed, c, CT,height));
            
            letter += 1;
        }
        
        
        for(int i = letterList.size() - 1; i >= 0; --i){
            if(letterList.get(i).pressed){
                letterList.remove(i);
                this.hits += 1;
                //System.out.println("ADDING ONE");
                break;
            }
            if( letterList.get(i).y > TextDisplay.SCREEN_SIZE_Y){
                letterList.remove(i);
                //System.out.println("SUBTRACTING ONE");
                this.misses += 1;
            }
        }
        
        return true;
    }
    
    public static String generatePracticeSet(boolean caps,String letters,int quantity){
        String ret = "";
        for(int i = 0; i<quantity; ++i){
            int r = oRan.nextInt(letters.length());
            char c = letters.charAt(r);
            String s = ""+c;
            if(caps){
                if(oRan.nextInt(100) > 50){
                    s = s.toUpperCase();
                }
            }
            ret+=s;
        }
        
        return ret;
    }
    
    public String[] display(){
        String[] ret = new String[7];
        ret[0] = "";
        ret[1] = "";
        ret[2] = "";
        ret[3] = "...You play a dandy tune! A formidable assault!";
        ret[4] = "";
        ret[5] = "";
        ret[6] = "";
        return ret;
    }
}
