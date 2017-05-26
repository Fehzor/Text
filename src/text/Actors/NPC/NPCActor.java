/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.NPC;

import java.util.ArrayList;
import java.util.HashMap;
import text.Actors.Actor;
import text.Actors.Messages.Option;
import text.Behaviour.Behaviour;
import static text.Environment.Animals.AnimalTemplate.animalImages;
import text.Frame.TextDisplay;
import text.Images.TextImageAnimated;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import text.Utility.ImageLoader;
import text.Utility.SuperRandom;

/**
 *
 * @author FF6EB4
 */
public class NPCActor extends Actor{
    public Behaviour behav;
    
    public String[] text = {"I'm a default actor!"};
    
    private boolean moving = false;
    
    public NPCActor(String modelName, String map){
        this.image = loadImage(map,modelName);
        
        this.behav = new NPCBehaviourWalk();
        
        this.x = SuperRandom.nextIntGaussian(TextDisplay.SCREEN_SIZE_X);
        this.y = SuperRandom.nextIntGaussian(TextDisplay.SCREEN_SIZE_Y);
    }
    
    public boolean act(){
        if(this.paused)return true;
        behav.act(this);
        this.depth = y;
        
        if(moving){
            ((TextImageAnimated)image).go();
        } else {
            ((TextImageAnimated)image).stop();
            ((TextImageAnimated)image).resetFrame();
        }
        
        return true;
    }
    
    public ArrayList<Actor> pollOptions(){
        ArrayList<Actor> ret = new ArrayList<>();
        
        ret.add(Option.display(this, "Talk", text));
        ret.add(Option.cancel(this));
        
        return ret;
    }
    
    public static TextImageAnimated loadImage(String map, String model){
        ImageLoader.switchMap(map);
        
        TextImageBasic TIBA;
        TIBA = ImageLoader.loadImage(model+"_stand.txt");
        
        TextImageBasic TIBB;
        TIBB = ImageLoader.loadImage(model+"_walk.txt");
        
        TextImageComplex one = new TextImageComplex(TIBA);
        TextImageComplex two = new TextImageComplex(TIBB);
        
        TextImageAnimated ret = new TextImageAnimated(one,two);
        
        return ret;
    }
    
    public void recolor(ColorTuple A, ColorTuple B, ColorTuple C, ColorTuple D){
        
        this.image = image.clone();
        
        //0,1,2,3
        //0 = fur
        //1 = coat
        //2 = bone
        HashMap<Character,ColorTuple> map = ImageBuilder.buildColorMap(C, A, B, D);
        
        //for(int i = 0; i< 100; ++i)System.out.println(oRan.nextInt(animalNames.size()));
        
        ImageBuilder.swapColorScheme((TextImageAnimated)image, map);
    }
    
    public void move(){
        moving = true;
    }
    
    public void stop(){
        moving = false;
    }
}
