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
import text.Images.TextImageAnimated;
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
public class Handle extends ToolPart{
    
    private static TextImageBasic myImage = loadImage();
    
    public Handle(){
        super(myImage);
        this.name = "Hammer Handle";
        this.type = TYPE_HANDLE;
        this.info = "Tool handle. Hits with great force in front.";
    }
    
    public Tool compile(Tool T){
        if(T != null){
            return T;
        }
        
        ImageLoader.switchMap("HUMAN");
        TextImageComplex animateA = new TextImageComplex(ImageLoader.loadImage("player_strikemode.txt"));
        TextImageComplex animateB = new TextImageComplex(ImageLoader.loadImage("player_stand.txt"));
        ImageLoader.switchMap("CITYSCALE");
        TextImageComplex toolmodel = new TextImageComplex(ImageLoader.loadImage("tool_hammer.txt"));
        animateB.addKnob(8, -16, toolmodel);
        
        TextImageAnimated animate = new TextImageAnimated(animateA,animateB);
        animate.setFrameSkip(3);
        animate.go();
        
        ToolEffect myArea = new ToolEffect(){
            public ArrayList<Actor> getArea(){
                
                boolean right = !Player.The.image.flipped();
                ArrayList<Actor> hits = null;
                if(right){
                    hits = Player.The.current.HitScan.checkHit(Player.The.x + 10,Player.The.y,0);
                } else {
                    hits = Player.The.current.HitScan.checkHit(Player.The.x - 10,Player.The.y,0);
                }

                return hits;
            }
        };
        
        Tool ret = new Tool((TextImage)toolmodel,(TextImage)animate,"Hammer");
        ret.effects.add(myArea);
        ret.held = true;
        ret.slot = Equipable.TOOL;
        ret.power = ret.power * 1.5;
        //ret.force = ret.force * 10;
        
        super.compile(ret);
        
        return ret;
    }
    
    private static TextImageBasic loadImage(){
        ImageLoader.switchMap("CITYSCALE");
        return ImageLoader.loadImage("handle.txt");
    }
}
