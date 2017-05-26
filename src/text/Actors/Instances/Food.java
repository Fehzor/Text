/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.Instances;

import java.awt.Color;
import text.Actors.Interactable;
import text.Images.TextImageBasic;
import text.Utility.ColorTuple;
import text.Utility.ImageBuilder;
import text.Utility.ImageLoader;

/**
 *
 * @author FF6EB4
 */
public class Food extends Interactable {
    public Food(){
        super("Cake");
        this.dead = false;
        //System.out.println("FOOD");
        this.name = "Cake";
        ImageLoader.switchMap("GREYSCALE");
        this.image = ImageLoader.loadImage("fruit_acorn.txt").clone();
        ImageBuilder.colorMergeImage((TextImageBasic)this.image, new ColorTuple(Color.BLUE,Color.ORANGE,' '));
    }
    
    public Food clone(){
        return new Food();
    }
    
    public boolean equals(Object o){
        return o instanceof Food;
    }
}
