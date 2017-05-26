/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.Hero;

import text.Tools.ToolParts.*;
import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Equipable;
import text.Actors.Player;
import static text.Actors.Player.current;
import text.Combat.Hero.Instrument;
import text.Images.TextImage;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import text.Tools.Tool;
import text.Tools.ToolEffect;
import text.Tools.ToolPart;
import text.Utility.ImageLoader;

/**
 *
 * @author FF6EB4
 */
public class WhistleHead extends ToolPart{
    
    private static TextImageBasic myImage = loadImage();
    
    public WhistleHead(){
        super(myImage);
        this.name = "Whistle head";
        this.type = TYPE_HANDLE;
        this.info = "Instrument base- df/jk | Hits all";
    }
    
    public Tool compile(Tool T){
        if(T != null){
            return T;
        }
        
        
        ImageLoader.switchMap("HUMAN");
        TextImageComplex animate = new TextImageComplex(ImageLoader.loadImage("player_gunmode.txt"));
        ImageLoader.switchMap("CITYSCALE");
        TextImageComplex gunmodel = new TextImageComplex(ImageLoader.loadImage("whistle.txt"));
        animate.addKnob(8, -16, gunmodel);
        
        ToolEffect myArea = new ToolEffect(){
            public ArrayList<Actor> getArea(){
                ArrayList<Actor> hits = Player.The.current.getActors();
                
                return hits;
            }
        };
        
        
        Instrument ret = new Instrument();
        ret.animate = animate;
        ret.slot = Equipable.INSTRUMENT;
        ret.held = true;
        ret.difficulty = ret.difficulty * .5;
        ret.power = ret.power * .15;
        ret.effects.add(myArea);
        
        super.compile(ret);
        
        return ret;
    }
    
    private static TextImageBasic loadImage(){
        ImageLoader.switchMap("CITYSCALE");
        return ImageLoader.loadImage("whistle.txt");
    }
}
