/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Tools.ToolParts;

import java.util.ArrayList;
import text.Actors.Actor;
import text.Actors.Convertable;
import text.Actors.Pickup;
import text.Actors.Player;
import static text.Actors.Player.current;
import text.Images.TextImageBasic;
import text.Images.TextImageComplex;
import static text.Inventory.Inventory.oRan;
import text.Inventory.Resource;
import text.Tools.Tool;
import text.Tools.ToolEffect;
import text.Tools.ToolPart;
import text.Utility.ImageLoader;

/**
 *
 * @author FF6EB4
 */
public class Bumper extends ToolPart{
    
    private static TextImageBasic myImage = loadImage();
    
    public Bumper(){
        super(myImage);
        this.name = "Bumper";
        this.type = TYPE_END;
        this.info = "Tool end piece. Bumps objects around.";
    }
    
    public Tool compile(Tool T){
        if(T == null){
            return T;
        }
        ToolEffect myEffect = new ToolEffect(){
            public void applyEffect(ArrayList<Actor> list, double power){
                for(Actor A : list){
                try{
                    if(A != Player.The){
                        int noise =(int) power * (oRan.nextInt(5) - oRan.nextInt(5));
                        A.x += noise;
                    }
                } catch(Exception E){}
            }
            }
        };
        T.name = "Bumper " + T.name;
        
        T.effects.add(myEffect);
        
        T.animationTime = T.animationTime / 5;
        
        return T;
    }
    
    private static TextImageBasic loadImage(){
        ImageLoader.switchMap("CITYSCALE");
        return ImageLoader.loadImage("teleporter_held.txt");
    }
}
