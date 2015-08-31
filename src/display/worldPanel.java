package display;

import mechanics.input;
import models.drawable;
import models.textModel;
import display.textPanel;
import display.displayFrame;
import utility.letterTuple;
import mechanics.world;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import mechanics.actors.*;

public class worldPanel extends textPanel{
        world current;

	public static final int DEFAULT_INVENTORY_HEIGHT = 7;	
	
	private String[] message = new String[DEFAULT_INVENTORY_HEIGHT-2];
	private int maxMessageLength = displayFrame.DEFAULT_MAIN_WIDTH-2;	

	public worldPanel(world starter, input in){
		super(displayFrame.DEFAULT_MAIN_HEIGHT+DEFAULT_INVENTORY_HEIGHT, displayFrame.DEFAULT_MAIN_WIDTH);
		
                
		loadWorld(starter);
                
		paintBackground(0,0,displayFrame.DEFAULT_MAIN_WIDTH, displayFrame.DEFAULT_MAIN_HEIGHT);
		
		//Draw a frame for the inventory.
		drawFrame(0,displayFrame.DEFAULT_MAIN_HEIGHT,displayFrame.DEFAULT_MAIN_WIDTH-1,displayFrame.DEFAULT_MAIN_HEIGHT+DEFAULT_INVENTORY_HEIGHT-1);
		
		for(int i=0;i<message.length;++i){
			message[i] = "";
		}
		
		
                //Set up the input
		//setFocusTraversalKeysEnabled(false);
		setFocusable(true);
                 
		addKeyListener(in);
		in.panel = this;
	}
	
	//Where to start and how much to paint
	//Updates to the background image.
	public void paintBackground(int colStart, int rowStart, int cols, int rows){
		for(int col=colStart;col<colStart+cols;++col){
			for(int row=rowStart;row<rowStart+rows;++row){
				//If the area to be painted is actually on the screen-
				if(!(col>displayFrame.DEFAULT_MAIN_WIDTH-1||row>displayFrame.DEFAULT_MAIN_HEIGHT-1||col<0||row<0)){
					updateAt(current.background.get(col).get(row),col,row);
				}
			}
		}
	}

	public void update(drawable D){
		//if(!D.check())return;//If it doesn't need updating don't update it.	
		
		//Remove the old image
		textModel TM = D.getModel();
		
                //Apparently, the drawable D was less drawable tahn I thought..
                if(TM == null)return;
                
                int cols = TM.width();
		int rows = TM.height();
		
		//TODO- avoid painting the ENTIRE background by updating to background only if actually empty?
		paintBackground(D.getLastX(), D.getLastY(), cols, rows);
		
		//Add the new one.
		for(int col=D.getX();col<D.getX()+cols;++col){
			for(int row=D.getY();row<D.getY()+rows;++row){
				if(!(col>displayFrame.DEFAULT_MAIN_WIDTH-1||row>displayFrame.DEFAULT_MAIN_HEIGHT-1||col<0||row<0)){
					//If it is on the screen, attempt to update.					
					letterTuple toAdd = TM.displayAt(col-D.getX(),row-D.getY());
					if(toAdd==letterTuple.TRANSPARENT){
						updateAt(current.background.get(col).get(row),col,row);
					} else {
						updateAt(toAdd,col,row);
					}
				}
			}
		}

		D.update();
	}

	private void drawFrame(int colFrom, int rowFrom, int colTo, int rowTo){
                letterTuple corner = new letterTuple();
                corner.setLetter('#');
                this.updateAt(corner,colFrom,rowFrom);
                this.updateAt(corner,colTo,rowTo);
                this.updateAt(corner,colTo,rowFrom);
                this.updateAt(corner,colFrom,rowTo);

                letterTuple verticalEdge = new letterTuple();
                verticalEdge.setLetter('|');
                for( int row=rowFrom+1; row<rowTo; ++row ){
                        this.updateAt(verticalEdge,colFrom,row);
                        this.updateAt(verticalEdge,colTo,row);
                }

                letterTuple horizontalEdge = new letterTuple();
                horizontalEdge.setLetter('=');
                for( int col=colFrom+1; col<colTo; ++col ){
                        this.updateAt(horizontalEdge,col,rowFrom);
                        this.updateAt(horizontalEdge,col,rowTo);
                };

                letterTuple middle = new letterTuple();
                middle.setLetter(' ');
                for( int col=colFrom+1; col<colTo; ++col ){
                        for( int row=rowFrom+1; row<rowTo; ++row ){
                                this.updateAt(middle,col,row);
                        }
                }
        }
	
	private void displayMessage(){
		for(int i=0;i<this.message.length;++i){
			if( this.message.equals("")!=true ){
				placeString(message[i],1,displayFrame.DEFAULT_MAIN_HEIGHT+1+i);
				
				if( this.message[i].length()<maxMessageLength ){
					int n = maxMessageLength-message[i].length();
					String spaces = new String(new char[n]).replace('\0',' ');
					placeString(spaces,1+message[i].length(),displayFrame.DEFAULT_MAIN_HEIGHT+1+i);
				}
			}
		}
	}
	
	//Display the method on the area for it to do so.
	public void displayStrings(String s){
		String lines[] = s.split("[\\r\\n]+");
		
		//DISPLAY THE MESSAGE AND TALK TO THE INPUT CLASS
	}
	
	public void displayCommand(String s){
		message[message.length-1]=s;
		
		displayMessage();
	}


        
        public void loadWorld(world toLoad){
            if(current != null) {current.stop();}
            current = toLoad;
            current.start(this);
            
            player.thePlayer.currentWorld = current;
            
            this.paintBackground(0,0,displayFrame.DEFAULT_MAIN_WIDTH, displayFrame.DEFAULT_MAIN_HEIGHT);
        }
}
