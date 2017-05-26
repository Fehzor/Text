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
public class Violin extends ToolPart{
    
    private static TextImageBasic myImage = loadImage();
    
    public Violin(){
        super(myImage);
        this.name = "Violin Base";
        this.type = TYPE_HANDLE;
        this.info = "Instrument base- qwertyt/tyuiop | Hits all";
    }
    
    public Tool compile(Tool T){
        if(T != null){
            return T;
        }
        
        
        ImageLoader.switchMap("HUMAN");
        TextImageComplex animate = new TextImageComplex(ImageLoader.loadImage("player_gunmode.txt"));
        ImageLoader.switchMap("TREESCALE");
        TextImageComplex gunmodel = new TextImageComplex(ImageLoader.loadImage("instrument_violin.txt"));
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
        ret.difficulty = .8;
        ret.name = "Violin";
        ret.myCombatSystem = new HeroController("qwerty",(float)0.5);
        
        ret.left = "qwerty";
        ret.right = "tyuiop";
        
        ret.power = ret.power * .5;
        ret.effects.add(myArea);
        
        ret.image = this.image;
        
        super.compile(ret);
        
        return ret;
    }
    
    private static TextImageBasic loadImage(){
        ImageLoader.switchMap("TREESCALE");
        return ImageLoader.loadImage("instrument_violin.txt");
    }
}
