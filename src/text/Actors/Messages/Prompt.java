/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Actors.Messages;

import text.WorldFrame.World;
import text.WorldFrame.MainWorld;
import text.Actors.Messages.InspectMenu;
import text.Utility.MenuBuilder;
import text.WorldFrame.Room;
import java.awt.event.KeyEvent;
import java.util.*;
import java.io.*;
import text.Actors.Actor;
import text.Actors.Drawable;
import text.Actors.Player;
import static text.Actors.Player.inv;
import static text.Actors.Player.tools;
import text.Utility.*;
import text.Images.*;
import text.Frame.*;
import text.Inventory.*;
import text.WorldFrame.*;
import text.WorldFrame.Templates.WorldBuilder;
/**
 *
 * @author FF6EB4
 */
public class Prompt extends Actor implements Serializable{
    Room W;
    boolean done;
    
    String text = "";
    
    public Prompt(Room current){
        super(new Drawable());
        blockable = false;
        W = current;
        
        W.addActor(this);
        TextDisplay.drawables.add(this);
        
        depth = Integer.MAX_VALUE - 1;
        Player.The.paused = true;
        this.done = false;
    }
    
    
    
    public boolean act(){
        if(TextListener.firstPress(KeyEvent.VK_ESCAPE)){
            this.dead = true;
            this.done = true;
            Player.The.paused = false;
        }
        
        if(done){
            return true;
        }
        
        //Check all allowed keys.
        checkKey(KeyEvent.VK_A, 'a');
        checkKey(KeyEvent.VK_B, 'b');
        checkKey(KeyEvent.VK_C, 'c');
        checkKey(KeyEvent.VK_D, 'd');
        checkKey(KeyEvent.VK_E, 'e');
        checkKey(KeyEvent.VK_F, 'f');
        checkKey(KeyEvent.VK_G, 'g');
        checkKey(KeyEvent.VK_H, 'h');
        checkKey(KeyEvent.VK_I, 'i');
        checkKey(KeyEvent.VK_J, 'j');
        checkKey(KeyEvent.VK_K, 'k');
        checkKey(KeyEvent.VK_L, 'l');
        checkKey(KeyEvent.VK_M, 'm');
        checkKey(KeyEvent.VK_N, 'n');
        checkKey(KeyEvent.VK_O, 'o');
        checkKey(KeyEvent.VK_P, 'p');
        checkKey(KeyEvent.VK_Q, 'q');
        checkKey(KeyEvent.VK_R, 'r');
        checkKey(KeyEvent.VK_S, 's');
        checkKey(KeyEvent.VK_T, 't');
        checkKey(KeyEvent.VK_U, 'u');
        checkKey(KeyEvent.VK_V, 'v');
        checkKey(KeyEvent.VK_W, 'w');
        checkKey(KeyEvent.VK_X, 'x');
        checkKey(KeyEvent.VK_Y, 'y');
        checkKey(KeyEvent.VK_Z, 'z');
        
        checkKey(KeyEvent.VK_0, '0');
        checkKey(KeyEvent.VK_1, '1');
        checkKey(KeyEvent.VK_2, '2');
        checkKey(KeyEvent.VK_3, '3');
        checkKey(KeyEvent.VK_4, '4');
        checkKey(KeyEvent.VK_5, '5');
        checkKey(KeyEvent.VK_6, '6');
        checkKey(KeyEvent.VK_7, '7');
        checkKey(KeyEvent.VK_8, '8');
        checkKey(KeyEvent.VK_9, '9');
        
        checkKey(KeyEvent.VK_SPACE, ' ');
        checkKey(KeyEvent.VK_UNDERSCORE, '_');
        checkKey(KeyEvent.VK_MINUS, '-');
        checkKey(KeyEvent.VK_PERIOD, '.');
        checkKey(KeyEvent.VK_QUOTE, '"');
        
        checkDel();
        
        enter();
        
        redraw();
        
        return true;
    }
    
    public void checkKey(int key, char c){
        if(TextListener.firstPress(key)){
            this.text += c;
        }
    }
    
    public void checkDel(){
        if(TextListener.firstPress(KeyEvent.VK_BACK_SPACE)){
            this.text = "";
        }
    }
    
    public void enter(){
        if(TextListener.firstPress(KeyEvent.VK_ENTER)){
            done = true;
        }
    }
    
    public void parse(){
        Scanner oScan = new Scanner(this.text);
        if(!oScan.hasNext()){
            return; //If nothing was input no problem just leave.
        }

        String s = oScan.next();
        
        if(s.equals("return")){
            returnToSpawn();
        }
        
        //Display the map
        if(s.equals("map") || s.equals("m") || s.equals("ma") || s.contains("map")){
            Player.The.current.owner.displayMap();
        }

        //Display held resources
        if(s.equals("inv") || s.equals("i") || s.equals("in") || s.contains("inv")){
            if(oScan.hasNext()){
                String parse = oScan.next();
                Player.The.inv.displayResource(parse);
            } else {
                Player.The.inv.displayResource("all");
            }
        }
        
        //Display held objects
        if(s.equals("obj") || s.equals("o") || s.equals("ob") || s.contains("obj")){
            ArrayList<Actor> get = inv.inspectStuff();
                get.add(0,new Option(this){
                    public String toString(){
                        return "Cancel";
                    }
                });

                if( get.size() > 0 ){
                    Player.The.myMenu = new InspectMenu(get,Player.The.current);

                }
        }
        
        //Display held objects
        if(s.equals("to") || s.equals("t") || s.equals("too") || s.contains("tool")){
            ArrayList<Actor> get = tools.inspectStuff();
                get.add(0,new Option(this){
                    public String toString(){
                        return "Cancel";
                    }
                });

                if( get.size() > 0 ){
                    Player.The.myMenu = new InspectMenu(get,Player.The.current);

                }
        }
    }
    
    public void redraw(){
        if(done){
            this.image = null;
            this.dead = true;
            W.dropActor(this);
            Player.The.paused = false;
            
            this.parse();
            
            return;
        } else {
            Drawable d = MenuBuilder.buildLineInput(this.text);
            this.image = d.image;
        }
    }
    
    public static void returnToSpawn(){
        Player.The.current.pause();;
        ((MainWorld)WorldBuilder.generateMain()).playFirst();
    }
}
