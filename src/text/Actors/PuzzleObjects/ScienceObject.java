/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.PuzzleObjects;

import java.awt.Color;
import text.Actors.Interactable;
import text.Frame.TextDisplay;
import text.Images.TextImageBasic;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import text.Utility.ImageLoader;
import static text.Utility.SuperRandom.oRan;

/**
 *
 * @author FF6EB4
 */
public class ScienceObject extends Interactable{
    public ScienceObject(){
        super("Science Object");
        this.dead = false;
        //System.out.println("FOOD");
        this.name = "Science Object";
        ImageLoader.switchMap("CITYSCALE");
        this.image = ImageLoader.loadImage("science_object.txt");
        
        this.depth = 0;
        
        this.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        this.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
    }
    
    public ScienceObject clone(){
        return new ScienceObject();
    }
    
    
}
