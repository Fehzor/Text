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
public class SciencePowder extends Interactable{
    public SciencePowder(){
        super("Bone");
        this.dead = false;
        //System.out.println("FOOD");
        this.name = "Science Powder";
        ImageLoader.switchMap("HUMAN");
        this.image = ImageLoader.loadImage("science.txt");
        if(oRan.nextInt(100) > 50){
            this.image.flip();
        }
        this.depth = 0;
        
        this.x = oRan.nextInt(TextDisplay.SCREEN_SIZE_X);
        this.y = oRan.nextInt(TextDisplay.SCREEN_SIZE_Y);
    }
    
    public SciencePowder clone(){
        return new SciencePowder();
    }
    
    public boolean equals(Object o){
        try{
            SciencePowder test = (SciencePowder)o;
            
            SciencePowder second = test.clone();
            
            return true;
        } catch (Exception E){}
        return false;
    }
}
