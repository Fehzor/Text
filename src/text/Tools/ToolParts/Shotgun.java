/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Tools.ToolParts;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Equipable;
import text.Actors.Player;
import static text.Actors.Player.current;
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
public class Shotgun extends ToolPart{
    
    private static TextImageBasic myImage = loadImage();
    
    public Shotgun(){
        super(myImage);
        this.name = "Empty Blunderbus";
        this.type = TYPE_HANDLE;
        this.info = "Tool handle. Shoots everything.";
    }
    
    public Tool compile(Tool T){
        if(T != null){
            return T;
        }
        
        ImageLoader.switchMap("HUMAN");
        TextImageComplex animate = new TextImageComplex(ImageLoader.loadImage("player_gunmode.txt"));
        ImageLoader.switchMap("CITYSCALE");
        TextImageComplex gunmodel = new TextImageComplex(ImageLoader.loadImage("tool_gun.txt"));
        animate.addKnob(8, -16, gunmodel);
        
        ToolEffect myArea = new ToolEffect(){
            public ArrayList<Actor> getArea(){
                ArrayList<Actor> hits = Player.The.current.HitScan.checkrow(!Player.The.image.flipped(),Player.The.x,Player.The.y,0);

                return hits;
            }
        };
        
        Tool ret = new Tool((TextImage)gunmodel,(TextImage)animate,"Blunderbus");
        ret.slot = Equipable.TOOL;
        ret.effects.add(myArea);
        ret.held = true;
        ret.power = ret.power * .8;
        
        super.compile(ret);
        
        return ret;
    }
    
    private static TextImageBasic loadImage(){
        ImageLoader.switchMap("CITYSCALE");
        return ImageLoader.loadImage("tool_gun.txt");
    }
}
