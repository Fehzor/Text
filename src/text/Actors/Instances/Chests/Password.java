/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.Instances.Chests;

import text.Actors.Interactable;
import text.Frame.TextDisplay;
import text.Images.TextImage;
import text.Utility.Diction.LiteralDictionary;
import text.Utility.ImageLoader;

/**
 *
 * @author FF6EB4
 */
public class Password extends Interactable{
    public Password(){
        super();
        this.image = getImage();
        this.name = "Password: \""+LiteralDictionary.getRandomWord()+"\"";
        this.dead = false;
        this.x = TextDisplay.SCREEN_SIZE_X / 2;
        this.y = TextDisplay.SCREEN_SIZE_Y / 2 + 10;
    }
    
    public TextImage getImage(){
        TextImage ret;
        
        ret = ImageLoader.loadAnimated("floppy.txt","floppy.txt");
        
        return ret;
    }
    
    public String toString(){
        return this.name;
    }
}
