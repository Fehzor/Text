package utility;

import display.textPanel;
import java.awt.*;
import java.util.*;

public class letterTuple{
	//A black zero on a black background is to be the transparent letterTuple.
	public static final letterTuple TRANSPARENT = new letterTuple(' ',Color.black,Color.black);
	
	private Color background = Color.black;//These are set to the default- a white '-' on a black background.
	private Color foreground = Color.white;
	private char letter = '#';
        
        /**
         * Set/get the various properties
         * @param c 
         */
        public void setBackground(Color c){
            this.background = c;
        }
        
        public void setForeground(Color c){
            this.foreground = c;
        }
        
        public void setLetter(char c){
            this.letter = c;
        }
        
        public Color background(){
            return this.background;
        }
        
        public Color foreground(){
            return this.foreground;
        }
        
        public char letter(){
            return this.letter;
        }
        
        public void drawAt(int col, int row, textPanel tp, Graphics g){
		Graphics2D g2d = (Graphics2D) g;	
	
		float startX = ((float)col * (float)tp.xInterval);		
		float startY = ((float)row * (float)tp.yInterval);	

		g2d.setColor(background());	
		
		//System.out.println(startX+" "+startY+" "+(int)xInterval+" "+(int)yInterval);
		g2d.fillRect((int)startX,(int)startY, (int)Math.ceil(tp.xInterval), (int)Math.ceil(tp.yInterval));
		
		g2d.setColor(foreground());
		g2d.drawString(letter()+"",startX+tp.xInterval*1/10,startY+tp.yInterval*4/5);
	} 
	
	public letterTuple(){
	}

	public letterTuple(char letter, Color background, Color foreground){
		this.letter = letter;
		this.background = background;
		this.foreground = foreground;
	}
	
	public letterTuple(letterTuple LT){
		this.letter = LT.letter();
		this.foreground = new Color(LT.foreground().getRGB());
		this.background = new Color(LT.background().getRGB());
	}
	
	//Set this letterTuple to LT.
	public void set(letterTuple LT){
		this.letter = LT.letter();
		this.foreground = new Color(LT.foreground().getRGB());
		this.background = new Color(LT.background().getRGB());
	}

	public boolean equals(Object o){
		letterTuple other = (letterTuple)o;
		if(
			other.background().equals(this.background) && 
			other.foreground().equals(this.foreground) &&
			other.letter() == this.letter
		){
			return true;
		}
		
		return false;
	}

}
