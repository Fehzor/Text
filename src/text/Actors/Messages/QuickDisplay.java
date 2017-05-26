/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.Messages;

import text.WorldFrame.Room;
import java.awt.event.KeyEvent;
import text.Actors.Actor;
import text.Actors.Drawable;
import text.Actors.Player;
import text.Utility.*;
import text.Images.*;
import text.Frame.*;
/**
 *
 * @author FF6EB4
 */
public class QuickDisplay extends Actor{
    Room W;
    boolean done;

    public QuickDisplay(Room current, Drawable D){
        super(D);
        blockable = false;
        W = current;
        W.addActor(this);
        TextDisplay.drawables.add(this);
        depth = Integer.MAX_VALUE - 1;
        Player.The.paused = true;
        this.done = false;
    }
    
    public boolean act(){
        if(TextListener.isHeld(KeyEvent.VK_ESCAPE)){
            this.dead = true;
            this.done = true;
            Player.The.paused = false;
        }
        if(done){
            return true;
        }
        
        if(TextListener.firstPress(KeyEvent.VK_ENTER) || 
                TextListener.firstPress(KeyEvent.VK_A)){
            done = true;
            redraw();
        }
        
        return true;
    }
    
    public void redraw(){
        if(done){
            this.image = null;
            this.dead = true;
            W.dropActor(this);
            Player.The.paused = false;
            return;
        }
    }
}
