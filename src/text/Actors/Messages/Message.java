/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.Messages;

import java.awt.event.KeyEvent;
import java.util.concurrent.locks.ReentrantLock;
import text.Actors.Actor;
import text.Actors.Drawable;
import text.Actors.Player;
import text.Frame.TextDisplay;
import text.Frame.TextListener;
import text.Utility.MenuBuilder;
import text.WorldFrame.Room;
import text.WorldFrame.World;

/**
 *
 * @author FF6EB4
 */
public class Message extends Actor{
    public static final int DISPLAY_WIDTH = 35;
    World W;
    
    public Message(World W,Drawable D){
        super(D);
        blockable = false;
        Player.The.paused = true;
        this.depth = 1000;
        this.W = W;
    }
    
    public boolean act(){
        if(TextListener.isHeld(KeyEvent.VK_ESCAPE)){
            this.dead = true;
            Player.The.paused = false;
        }
                
        if(TextListener.firstPress(KeyEvent.VK_A) || 
                TextListener.firstPress(KeyEvent.VK_ENTER) || 
                TextListener.firstPress(KeyEvent.VK_M)){
            dead = true;
            this.image = null;
            Player.The.paused = false;
        }
        
        return true;
    }
    


}
