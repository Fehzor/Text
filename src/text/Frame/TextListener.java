/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Frame;

import java.awt.event.*;
import text.Actors.Player;
import java.util.*;

/**
 *
 * @author FF6EB4
 */
public class TextListener implements KeyListener{
    
    private static HashMap<Integer,Boolean> keyMap;
    private static HashMap<Integer,Boolean> dupeMap;
    private static HashMap<Character,Boolean> charMap;
    
    public TextListener(){
        keyMap = new HashMap<>();
        dupeMap = new HashMap<>();
        charMap = new HashMap<>();
    }
    
    public void keyTyped(KeyEvent e){}
    
    public void keyPressed(KeyEvent e){
        keyMap.put(e.getKeyCode(),true);
        dupeMap.put(e.getKeyCode(),true);
        charMap.put(e.getKeyChar(),true);
    }
            
    public void keyReleased(KeyEvent e){
        keyMap.put(e.getKeyCode(),false);
        dupeMap.put(e.getKeyCode(),false);
        charMap.put(e.getKeyChar(),false);
    }
    
    public static boolean checkChar(char c){
        //System.out.println(c);
        try{
            return charMap.get(c);
        } catch (NullPointerException e){
            charMap.put(c,false);
        }
        return false;
    }
    
    public static boolean isHeld(int keyCode){
        try{
            dupeMap.put(keyCode,false);
            return keyMap.get(keyCode);
        } catch (NullPointerException e){
            dupeMap.put(keyCode,false);
            keyMap.put(keyCode,false);
        }
        return false;
    }
    
    //Less glitchy for typing!
    public static boolean firstPress(int keyCode){
        try{
            boolean b = dupeMap.get(keyCode);
            dupeMap.put(keyCode,false);
            return b;
        } catch (NullPointerException e){
            dupeMap.put(keyCode,false);
            keyMap.put(keyCode,false);
        }
        return false;
    }
}
