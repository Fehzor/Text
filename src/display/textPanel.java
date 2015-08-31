package display;

/**
*This is a class that represents a box of letters in a grid.
*/

import utility.letterTuple;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class textPanel extends JPanel{
	public final JPanel component = new JPanel();	
	private ArrayList<ArrayList<letterTuple>> letters = new ArrayList<ArrayList<letterTuple>>();
	
        public int truewidth, trueheight;
	public int width,height;
	public int letterHeight, letterWidth;
	public float xInterval, yInterval;
	
	public textPanel(int height_in_boxes, int width_in_boxes){		
		this.letterHeight = height_in_boxes;
		this.letterWidth = width_in_boxes;	
		resize();
		
		//Populate the list of letters to udpate
		for(int col = 0; col<letterWidth; ++col){
			letters.add(new ArrayList<letterTuple>());
			for(int row = 0; row<letterHeight; ++row){
				letters.get(col).add(new letterTuple());
			}
		}

		this.setFont(new Font("Helvetica", Font.BOLD, 12));
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawLetters(g);
	}

	public void resize(){
		Dimension size = getSize();
		Insets insets = getInsets();
                this.truewidth = size.width;
                this.trueheight = size.width;
		this.width = size.width - insets.left - insets.right;
		this.height = size.height - insets.top - insets.bottom;	
		
		this.xInterval = ((float)width / (float)this.letterWidth);
		this.yInterval = ((float)height / (float)this.letterHeight);
	}	

	/**
	*Takes a letterTuple and puts it at col, row.
	*/
	public void updateAt(letterTuple toSet, int col, int row){
		letters.get(col).set(row,toSet);
	}
	
	private void drawLetters(Graphics g){
		this.resize(); //Maybe this won't always be needed?
		
		for(int col = 0; col<letterWidth; ++col){
			for(int row = 0; row<letterHeight; ++row){
                                letterTuple toDraw = letters.get(col).get(row);
                                
				toDraw.drawAt(col,row, this, g);
			}
		}
	}
		
	

	public void placeString(String place, int colStart, int rowStart){
		for(int i=0;i<place.length();++i){
			letterTuple next = new letterTuple(place.charAt(i),Color.black,Color.white);
			updateAt(next,colStart+i,rowStart);
		}
	}	
}
