/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.Messages;

import text.Utility.MenuBuilder;
import text.WorldFrame.Room;
import java.awt.event.KeyEvent;
import text.Actors.Actor;
import text.Actors.Actor;
import text.Actors.Drawable;
import text.Actors.Drawable;
import text.Actors.Player;
import text.Actors.Player;
import text.Utility.*;
import text.Images.*;
import text.Frame.*;
/**
 *
 * @author FF6EB4
 */
public class Menu extends Actor{
    public static final int DISPLAY_WIDTH = 35;
    Room W;
    public int selection;
    String[] options;
    public boolean done;
    public Actor owner;
    
    public Menu(String[]options, Room current, Actor owner){
        this(options,current);
        this.owner = owner;
    }
    
    public Menu(String[]options, Room current){
        super(MenuBuilder.buildMenuDisplay(options,0));
        blockable=false;
        //System.out.println("MENU");
        this.options = options;
        W = current;
        
        W.addActor(this);
        TextDisplay.drawables.add(this);
        
        depth = Integer.MAX_VALUE - 1;
        Player.The.paused = true;
        selection = 0;
        this.done = false;
    }
    
    public Menu(Room current){
        super(new Drawable());
        this.options = options;
        W = current;
        
        W.addActor(this);
        TextDisplay.drawables.add(this);
        
        depth = Integer.MAX_VALUE - 1;
        Player.The.paused = true;
        selection = 0;
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
        
        if(TextListener.firstPress(KeyEvent.VK_D)){
            if(selection < options.length-1){
                selection++;
                redraw();
            }
        }
        
        if(TextListener.firstPress(KeyEvent.VK_E)){
            if(selection > 0){
                selection--;
                redraw();
            }
        }
        
        if(TextListener.firstPress(KeyEvent.VK_A) || 
                TextListener.firstPress(KeyEvent.VK_ENTER)){
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
            this.menuEnd();
            return;
        }
        Drawable d = MenuBuilder.buildMenuDisplay(options, selection);
        this.image = d.image;
    }
    
    public void menuEnd(){
        //IF YOU WANT IT TO DO SOMETHING AT END FILL THIS IN.
    }
    
}
