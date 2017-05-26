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
public class ControlledQuickDisplay extends Actor{
    Room W;
    boolean done;

    public ControlledQuickDisplay(int x, int y, Room current, TextImageBasic TIB){
        super(x,y);
        this.image = TIB;
        blockable = false;
        W = current;
        W.addActor(this);
        TextDisplay.drawables.add(this);
        depth = Integer.MAX_VALUE - 1;
        this.done = false;
    }
    
    public boolean act(){return true;}
    

}
