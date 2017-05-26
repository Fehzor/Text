/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Combat.RPS;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Equipable;
import text.Actors.Player;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import text.Tools.Tool;
import text.Tools.ToolEffect;
import text.Tools.ToolPart;
import text.Utility.ImageLoader;
import static text.Utility.LootGenerator.oRan;

/**
 *
 * @author FF6EB4
 */
public class RPSPart extends ToolPart{
    
    private static TextImageBasic myImage = loadImage();
    
    public RPSPart(){
        super(myImage);
        this.name = "Spider Mind";
        this.type = TYPE_HANDLE;
        this.info = "Rock Paper Scissors- R/P/S";
    }
    
    public Tool compile(Tool T){
        if(T != null){
            return T;
        }
        
        
        ImageLoader.switchMap("HUMAN");
        TextImageComplex animate = new TextImageComplex(ImageLoader.loadImage("player_gunmode.txt"));
        ImageLoader.switchMap("CITYSCALE");
        TextImageComplex gunmodel = new TextImageComplex(ImageLoader.loadImage("spider_stand.txt"));
        animate.addKnob(8, -16, gunmodel);
        
        ToolEffect myArea = new ToolEffect(){
            public ArrayList<Actor> getArea(){
                ArrayList<Actor> hits = Player.The.current.getActors();
                
                return hits;
            }
        };
        
        
        RPSObject ret = new RPSObject(gunmodel,"Spider?"){
            int a = oRan.nextInt(66);
            int b = a+oRan.nextInt(101 - a);
            public int getRPS(){
                int which = oRan.nextInt(100);
                
                if(which < a) return 0;
                if(which < b) return 1;
                return 2;
            }
        };
        
        ret.value = 23;
        
        ret.animate = animate;
        ret.slot = Equipable.TOOL;
        ret.held = true;
        ret.power = ret.power * .15;
        ret.effects.add(myArea);
        
        super.compile(ret);
        
        return ret;
    }
    
    private static TextImageBasic loadImage(){
        ImageLoader.switchMap("GREYSCALE");
        return ImageLoader.loadImage("spider_stand.txt");
    }
}
