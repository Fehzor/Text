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
public class Converter extends ToolPart{
    
    private static TextImageBasic myImage = loadImage();
    
    public Converter(){
        super(myImage);
        this.name = "Converter";
        this.type = TYPE_END;
        this.info = "Tool end piece. Converts applicable objects.";
    }
    
    public Tool compile(Tool T){
        if(T == null){
            return T;
        }
        ToolEffect myEffect = new ToolEffect(){
            public void applyEffect(ArrayList<Actor> list,double power){
                ArrayList<Pickup> pList = new ArrayList<>();
                for(Actor A : list){
                try{
                    ArrayList<Resource> give = ((Convertable)A).convert();
                    A.dead = true;
                    
                    int amt =(int)( power * give.size() );
                    System.out.println(amt);
                    
                    for(int i = 0; i < amt; ++i){
                        int j = i % give.size();
                        Resource R = give.get(j);
                        
                        int xadd = oRan.nextInt(20) - 10;
                        int yadd = oRan.nextInt(10) - 5;
                        Pickup P = new Pickup(A.x + xadd,A.y + yadd,R.icon);
                        pList.add(P);
                    }
                } catch(Exception E){}
                
                for(Pickup P : pList){
                    Player.The.current.addActor(P);
                }
            }
            }
        };
        T.name = "Conversion " + T.name;
        
        T.effects.add(myEffect);
        
        return T;
    }
    
    private static TextImageBasic loadImage(){
        ImageLoader.switchMap("CITYSCALE");
        return ImageLoader.loadImage("teleporter_held.txt");
    }
}
