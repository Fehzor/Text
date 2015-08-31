package mechanics;

import mechanics.actors.player;
import mechanics.world;
import display.worldPanel;
import java.util.*;
import java.awt.event.*;

public class input implements KeyListener{
	private String message = "";	
	public worldPanel panel;
	public world currentWorld;
	private boolean worldActive=false; //Does this object have a usable world object?

	private static final int COMMAND_MODE = 0;//Enter a command!
	private static final int MOVEMENT_MODE = 1;//Control the world!
	private static final int TEXTSCROLL_MODE = 2;//Press enter to continue!	

	private static final int NO_MODE = -1;

	private int mode = COMMAND_MODE;

	public void setWorld(world set){
		this.currentWorld = set;
	}

	public void keyPressed(KeyEvent e) {
		//System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
		
		if(this.mode == COMMAND_MODE){
			commandModeInput(e);
		} else if(this.mode == MOVEMENT_MODE){
			//TODO- Count this as movement.
			
			if(e.getKeyCode() == KeyEvent.VK_SEMICOLON){
				this.mode = COMMAND_MODE;
				commandModeInput(e);
				return;
			}
			
			player.keyPress(e);
		}
	}
	
	private void commandModeInput(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(!worldActive){
				mode = MOVEMENT_MODE;
				//TODO- SEND THE INPUT TO THE PLAYER.
			}
			
			message="";		
		} else if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
			if(message.length()==0){
				return;//Trying to delete when there are no letters.
				//BUT THERES NO WORDS THERE.
			}
			message = message.substring(0,message.length()-1);
		} else { //Otherwise, we add to the message here.
			
			message+=KeyEvent.getKeyText(e.getKeyCode());	
		
		}
		panel.displayCommand(message);
	}
	
	public void keyTyped(KeyEvent e){	
	}

	public void keyReleased(KeyEvent e) {
		if(this.mode == MOVEMENT_MODE){
			player.keyRelease(e);
		}
	}
}
