/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Environment.Animals;

import java.awt.Color;
import java.util.ArrayList;
import text.Behaviour.BehaviourBuzz;
import text.Behaviour.BehaviourFeral;
import text.Images.TextImageAnimated;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import text.Utility.ColorTuple;
import text.WorldFrame.Room;

/**
 *
 * @author FF6EB4
 */
public class Bee extends Animal{
    
    public static ArrayList<String> sayings = loadSayings();
    public static ColorTuple ct = new ColorTuple(Color.BLACK,Color.YELLOW,'B');
    
    public Bee(){
        behav = new BehaviourBuzz();
        
        this.name = "Bee";
        super.name = "Bee";
        this.adjective = "";
        super.adjective = "";

        x = 50;
        y = 50;
        
        TextImageBasic A = new TextImageBasic(ct,true);
        TextImageComplex B =new TextImageComplex(A);
        B.yOrigin = 15;
        
        this.image = new TextImageAnimated(B,B);
    }
    
    public Bee(Room start){
        behav = new BehaviourBuzz();
        
        super.current = start;
        
        this.name = "Bee";
        super.name = "Bee";
        super.adjective = "";

        x = 50;
        y = 50;
        
        this.image = new TextImageBasic(ct,true);
    }
    
    public static String getDisplay(){
        return sayings.get(oRan.nextInt(sayings.size()));
    }
    
    public static ArrayList<String> loadSayings(){
        ArrayList<String> ret = new ArrayList<>();
        
        ret.add("I never saw things that way!");
        ret.add("Ya like jazzzzzz?");
        ret.add("Your advice is excellent!");
        ret.add("You've really challenged all my views and awaken me to new perceptions.");
        ret.add("Intriguing. No, fascinating! No, STUPENDOUS!! I mean, yes....sounds like a good idea. You've,  um, convinced me.");
        ret.add("I may be a bee but when it comes to your ideas I....hmmm.....um. Damn this inadequate bee brain.");
        ret.add(" I always thought the queen was the one true God but then again I'm just a bee.");
        ret.add("Wait, what's the new religion's view on thievery? You know what? Forget I mentioned that.");
        ret.add("The queen's ass is my only diety. But there's room for a pantheon.");
        ret.add("Well the queen said sure so what am I gonna do, hurr durr start a rebellion?");
        
        return ret;
    }
    
    public String toString(){
        return "Bee";
    }
}
