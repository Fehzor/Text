/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textEditors;

import display.displayFrame;
import display.worldPanel;
import java.util.ArrayList;
import java.util.Random;
import mechanics.actors.player;
import mechanics.input;
import mechanics.world;
import utility.letterTuple;
import models.textModel;
import mechanics.*;


/**
 *
 * @author Awesomesauce
 */
public class editorWorld {
        public static editorInput in;
	public static worldPanel WP;
	public static displayFrame DF;
	public static world W;
        
        public static textModel testing;
        
        public editorWorld(){
            generate();
        }
        
	private static void generate(){
		ArrayList<ArrayList<letterTuple>> background = new ArrayList<ArrayList<letterTuple>>();
		
		in = new editorInput();
                
                editorWorld.W = new world();
                W.removeBackground();
                
		editorWorld.WP = new worldPanel( W, new input() );
		WP.addMouseListener(in);
                
                editorWorld.DF = new displayFrame(WP, "Text Editor!!!!!!!!!!!");
                
                player P = player.thePlayer;
	}  
}
