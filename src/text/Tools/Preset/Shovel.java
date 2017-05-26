/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Tools.Preset;

import java.awt.event.KeyEvent;
import text.Actors.Instances.Door;
import text.Actors.Instances.Hole;
import text.Actors.Player;
import text.Frame.TextDisplay;
import text.Frame.TextListener;
import text.Images.TextImage;
import text.Images.TextImageComplex;
import text.Utility.ImageLoader;
import text.Tools.Tool;
import text.WorldFrame.Worlds.CaveWorld;

/**
 *
 * @author FF6EB4
 */
public class Shovel extends Tool{
        public int COOLDOWN = 60;
        public int cool = 0;
    
        public Shovel(){
            super((TextImage)(ImageLoader.loadImage("tool_shovel.txt")),"Tool");
            this.name = "The Shovel";
            this.slot = MAP;
            this.animationTime = 60;
            
            
            ImageLoader.switchMap("HUMAN");
            this.animate = new TextImageComplex(ImageLoader.loadImage("player_gunmode.txt"));
            ImageLoader.switchMap("CITYSCALE");
            TextImageComplex gunmodel = new TextImageComplex(ImageLoader.loadImage("tool_shovel.txt"));
            ((TextImageComplex)animate).addKnob(8, -16, gunmodel);
            
            
        }
        
        public boolean act(){
        if(!held)return true;
        
        
            
        if(TextListener.isHeld(KeyEvent.VK_M)){
                this.animate.flip(Player.The.image.flipped());
                Player.The.image = this.animate;
                Player.The.paused = true;
                Player.The.animationTime = this.animationTime;
                
            
            if(Player.The.current.owner instanceof CaveWorld){
                CaveWorld CW = (CaveWorld)Player.The.current.owner;
                
                if(CW.depth == 666){
                    String[] disp = new String[]
                    {"Your shovel holds no power over Science. Type return to leave."};
                    Player.The.current.owner.display(disp);
                    return true;
                }
                if(CW.depth > 666){
                    String[] disp = new String[]
                    {"...............................I AM ERROR."};
                    Player.The.current.owner.display(disp);
                    return true;
                }
                
                //Player.The.current.addActor(new Hole());
                CW.swapRooms();
                
            } else {
                Door D = new Door(new CaveWorld(),"Trapdoor");
                D.x = Player.The.x;
                D.y = Player.The.y; 
                Player.The.current.addActor(D);
            }
        }
        
        cool = 0;
        
        return false;
    }

    public Shovel clone(){
        Shovel ret =  new Shovel();
        ret.image = this.image.clone();
        return ret;
    }
    
    public String toString(){
        return name;
    }
}
